package api;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class SpotifyAuthorization {
	private static final String clientID = "02fea13161264ed9b84552610e4875d2";
	private static final String clientSecret = "b0432a0363864a3780cd40d84fecc2c4";
	
	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
			.setClientId(clientID)
			.setClientSecret(clientSecret)
			.build();
	
	private static final ClientCredentialsRequest ccr = spotifyApi.clientCredentials().build();
			
	public static void clientCredentials_Sync() {
		try {
			final ClientCredentials clientCredentials = ccr.execute();
			spotifyApi.setAccessToken(clientCredentials.getAccessToken());
			System.out.println("Sync token: " + clientCredentials.getAccessToken());
		}
		catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
		      System.out.println("Error: " + e.getMessage());
		} 
	}
	
	public static String clientCredentials_Async() throws InterruptedException, ExecutionException {
		String token = "";
		try {
			final CompletableFuture<ClientCredentials> clientCredentialsFuture = ccr.executeAsync();
			final ClientCredentials clientCredentials = clientCredentialsFuture.get();
			spotifyApi.setAccessToken(clientCredentials.getAccessToken());
//			System.out.println("Async token: " + clientCredentials.getAccessToken());
			token = clientCredentials.getAccessToken();
		}
		catch (CompletionException e) {
			System.out.println("Error: " + e.getCause().getMessage());
		}
		catch (CancellationException e) {
			System.out.println("Async operation cancelled.");
		}
		return token;
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	    clientCredentials_Sync();
	    clientCredentials_Async();
	  }
}
