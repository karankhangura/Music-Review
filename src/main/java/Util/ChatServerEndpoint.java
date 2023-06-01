package Util;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/ChatServerEndpoint", configurator=ChatServerConfigurator.class)
public class ChatServerEndpoint{
	static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void handleOpen(EndpointConfig endpointConfig, Session userSession) {
		userSession.getUserProperties().put("username", endpointConfig.getUserProperties().get("username"));
		users.add(userSession);
	}

	@OnClose
	public void handleClose(Session userSession) {
		users.remove(userSession);
	}

	@OnError
	public void handleError(Throwable t) {

	}

	@OnMessage
	public void handleMessage(String message, Session userSession) {
		String username = (String) userSession.getUserProperties().get("username");

		if(username != null) {
			users.stream().forEach(x -> {
				try {
					x.getBasicRemote().sendText(buildJsonData(username, message));
				}
				catch(Exception e){
					e.printStackTrace();
				}
			});
		}
	}

	private String buildJsonData(String username, String message) {
		JsonObject jsonObject = Json.createObjectBuilder().add("message", username+": "+message).build();
		StringWriter stringWriter = new StringWriter();
		try(JsonWriter jsonWriter = Json.createWriter(stringWriter)){
			jsonWriter.write(jsonObject);
		}
		return stringWriter.toString();
	}
}
