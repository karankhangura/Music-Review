<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dongle:wght@300;400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="style.css">

    <title>DETAILS | ListenUp</title>


    <style>
        .container {
            width: 95%;
            max-width: 1500px;
            margin: 15px auto;
            font-size: 24px;
        }
        #title {
            font-size: 48px;
            height: 55px;
        }
        #artist {
            font-size: 32px;
        }
        #album {
            display: flex;
            justify-content: space-between;
        }
        #left {
        	max-width: 23%;
        }
        #album-cover img {
            width: 250px;
            height: 250px;
        }
        .section-header {
            font-size: 32px;
        }
        .play-btn {
        	font-size: 15px;
        	padding-left: 15px;
        	visibility: hidden;
        }
        .play-btn:hover {
        	color: #2D7D19;
        	transition: 0.3s;
        }
        #middle {
            width: 55%;
        }
        #stars {
            display: flex;
            margin-bottom: 40px;
        }
        #stars i {
           	color: #C4C4C4;
            font-size: 60px;
            padding-right: 10px;
        }
        #stars i:hover, #stars i:not(:hover) {
            transition: 0.3s;
        }
        #review {
            color: white;
            background-color: #404D66;
            font-size: 24px;
            border: none;
            width: 100%;
            height: 200px;
            padding: 10px;
        }
        #error-msg {
            margin-bottom: 10px;
        }
        #error-msg p {
            font-size: 20px;
            line-height: 20px;
            color: red;
        }
        #review-btns {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        #review-btns button {
            color: white;
            font-size: 24px;
            background-color: #1C2330;
            border: none;
            border-radius: 10px;
            padding: 0 25px;
        }
        #review-btns button:hover {
            color: #1C2330;
            background-color: white;
        }
        #review-btns a:hover, #write-review-link a:hover, #back-link:hover {
            color: #2D7D19;
        }
        #all-reviews {
            display: none;
        }
        #overall-stars {
            display: flex;
            align-items: center;
        }
        #overall-stars i {
        	font-size: 60px;
            padding-right: 10px;
        }
        #overall-stars p {
        	color: #C4C4C4;
        	font-weight: 300;
        }
        #write-review-link {
            display: flex;
            justify-content: flex-end;
        }
        .review-box {
            background-color: #404D66;
            padding: 20px;
            margin-bottom: 15px;
        }
        .filled {
            color: #2D7D19;
            font-size: 15px;
        }
        .unfilled {
        	color: #C4C4C4;
            font-size: 15px;
        }
        .username {
            color: #C4C4C4;
        }
        #chat {
        	display: none;
        }
        #chat-box {
        	background-color: #404D66;
			width: 100%;
        	height: 600px;
        	display: flex;
        	flex-direction: column;
        	justify-content: space-between;
        }
        #messages {
        	overflow-y: auto;
        	max-height: 600px;
        }
        .user-msg, .recipient-msg {
        	color: #2A3343;
        	background-color: #C4C4C4;
        	margin-top: 10px;
        	margin-bottom: 10px;
        	padding: 10px 15px;
        	line-height: 24px;
        	border-radius: 20px;
        }
        .user-msg {
        	margin-left: 50%;
        	margin-right: 10px;
        	text-align: right;
        }
        .recipient-msg {
        	margin-right: 50%;
        	margin-left: 10px;
        }
        #msg-form {
        	display: flex;
        	justify-content: space-between;
        }
        #msg-input {
        	font-size: 24px;
            background-color: #2A3343;
            width: 100%;
            margin: 10px 0 10px 10px;
            padding: 5px 10px;
            border: none;
        }
        #send-btn {
        	color: #2A3343;
        	background-color: white;
        	font-size: 24px;
        	border: none;
        	width: 100px;
        	margin: 10px;
        }
        #send-btn:hover {
        	color: white;
        	background-color: #2A3343;
        }
        #right {
            text-align: center;
            margin-top: 15%;
        }
        #right button {
            font-size: 32px;
            color: white;
            background-color: #404D66;
            border: none;
            width: 100%;
            margin-top: 3%;
            padding: 5px;
        }
        #right button:hover {
            color: #404D66;
            background-color: #C4C4C4;
        }
        #album-table {
            text-align: left;
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 10%;
        }
        #album-table th {
            font-size: 28px;
            border-bottom: 2px solid white;
            padding-bottom: 5px;
            padding-left: 10px;
        }
        #album-table td {
            padding-top: 5px;
            padding-left: 10px;
        }


    </style>
    <%@ page import = "se.michaelthelin.spotify.model_objects.specification.ArtistSimplified" %>
    <%@ page import = "se.michaelthelin.spotify.model_objects.specification.TrackSimplified" %>
    <%@ page import = "se.michaelthelin.spotify.model_objects.specification.Album" %>
    <%@ page import = "se.michaelthelin.spotify.model_objects.specification.Image" %>
    <%@ page import = "api.AlbumDetails"  %>
</head>
<body>
		<%@page import="java.util.*"
		import="java.lang.Math.*"
	    import ="Util.Helper"
	    %>

		<%
			session.setMaxInactiveInterval(2);
		%>

		<%

			Cookie[] cookies  = request.getCookies();
			String em = "Sign Up";
			String red = "Login";
			String disp = "login.jsp";
			String disp2 = "signup.jsp";
			if(cookies!=null){
				String temp ="";
				try{
					for(Cookie c : cookies){
						if(c.getName().equals("em")){
							em=c.getValue();
							temp = em.replaceAll("=", " ");
							em="" + temp +"";
							session.setAttribute("username", em);
							request.setAttribute("username", em);
							red = "Logout";
							disp = "LogoutDispatcher";
							disp2 = "account.jsp?username=" + em;
							break;
						}
					}
				}
				catch (Exception E){
				}
			}
	%>

    <div id="navbar">
        <div id="nav-left">
            <a id="listenup-name" href="home.jsp">ListenUp</a>
        </div>

        <div id="nav-right">
        	<% if (em.equals("guest")) { %>
        		<p class="disabled">Guest</p>
        	<% } else { %>
            	<a class="nav-link" href="<%=disp2%>"><%=em%></a>
            <% } %>

            <a class="nav-link" href="<%=disp%>"><%=red%></a>
            <a class="nav-link" href="search.jsp">Albums</a>
        </div>
    </div>

    <div class="container">
        <div id="album-name">
        	<%String ID = (String)request.getParameter("albumid");
        		Album album = AlbumDetails.getAlbum(ID);
       			ArtistSimplified[] artists = album.getArtists();

       			// getting cover url
       			Image[] covers = album.getImages();
       			String coverUrl = covers[0].getUrl();

       			// getting tracks and total runtime
       			TrackSimplified[] tracks = album.getTracks().getItems();
       			Integer runtime = 0;
       			for (int i = 0; i < tracks.length; i++) {
       				System.out.println("Duration of this song: " + tracks[i].getDurationMs()/1000);
       				runtime += (tracks[i].getDurationMs()/1000);
       			}
       			System.out.println("Total runtime: " + runtime);

       			// adding artists to database
       			for (ArtistSimplified artist : artists) {
       				Helper.addArtist(artist.getId(), artist.getName());
       				// adding album to database
       				Helper.addAlbum(ID, album.getName(), coverUrl, artist.getId(), runtime, album.getReleaseDate());
       			}

       			for (int i = 0; i < tracks.length; i++) {
       				Helper.addSong(tracks[i].getId(), tracks[i].getName(), tracks[i].getDurationMs()*1000, i+1, artists[0].getId(), ID);
       			}

        	%>
        	<%String name = Helper.getName(ID); %>
       		<%String artist = Helper.getArtist(ID); %>
            <p id="title"> <%=name%> </p> <!-- sql -->
            <p id="artist"><%=artist%></p>
        </div>

        <div id="album">
            <div id="left">
                <div id="album-cover">
               		 <!-- more generic -->
               		 <% String cover = Helper.getCover(ID); %>
               		 <% ArrayList<ArrayList<String>> rev = Helper.getRevs(ID); %>
                	<img src="<%=cover%>" alt="album cover">
                </div>

                <div id="album-tracklist">
                	<!-- sql -->
                    <p class="section-header">Tracklist</p>
					<%
						ArrayList<String> songs = Helper.getSongs(ID);
						for (int i =0; i<songs.size(); i++) {
							out.println("<p class=\"track\">"+(i+1)+". "+songs.get(i) + "<a class=\"play-btn-a\"><i class=\"fa-solid fa-circle-play play-btn\"></i></a></p>");
						}
					%>
                </div>
            </div>

            <div id="middle">

            	<table id="album-table">
                    <tr>
                        <th>Album Details</th>
                    </tr>
                    <tr>
                    	<% String date = Helper.getReleaseDate(ID); %>
                        <td>Release Date: <%=date %></td>
                    </tr>
                    <tr>
                    	<% Double rate = Helper.getRating(ID);
                    		if (rate == 0.0) {
                    			out.println("<td>No reviews yet (0.0)</td>");
                    		}
                    		else {
                    	%>
                        <td>Rating: <%=rate %></td>
                        <% } %>
                    </tr>
                    <tr>
                    	<% String duration = Helper.getAlbumDuration(ID); %>
                        <td>Runtime: <%=duration %></td>
                    </tr>
                </table>



                <div id="rate-and-review">
                    <p class="section-header">Rate and Review</p>

                    <div id="stars">
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                    </div>

                    <form action="ReviewDispatcher" method="GET" id="review-form">
                        <textarea id="review" name="review"></textarea>

						<input type="hidden" name="username" id="username" value=<%=em%>>
                        <input type="hidden" name="rating" id="rating">
						<input type="hidden" name="id" id="albumid" value=<%=Helper.getID(name)%>>
                        <div id="error-msg"></div>

                        <div id="review-btns">
                            <button type="submit">Submit</button>
                            <a>View all reviews</a>
                        </div>
                    </form>
                </div>

                <div id="all-reviews">
                    <p class="section-header">All Reviews</p>

                    <div id="overall-stars">
                    	<%
                    		double rating = Helper.getRating(ID);
                    		int temp = (int)rating;

                    		// Output the number of stars according to the average rating
                    		for (int i=0; i < temp; ++i) {
                    			out.println("<i class=\"fa-solid fa-star filled\"></i>");
                    		}

                    		// Half-star if applicable
                    		if (rating-temp >= 0.3) {
                    			out.println("<i class=\"fa-solid fa-star-half-stroke filled\"></i>");
                    		}

                    		// Rest of the stars
                    		for (double i=Math.ceil(rating); i < 5; ++i) {
                    			out.println("<i class=\"fa-solid fa-star unfilled\" style=\"font-weight: 100;\"></i>");
                    		}

                    		out.println("<p>(" + rating + ")</p>");
                    	%>
                    </div>

                    <div id="write-review-link">
                        <a>Write a review</a>
                    </div>

                    <%
                    	// Iterate through list of all reviews for this album
                    	ArrayList<ArrayList<String>> allReviews = Helper.getRevs(ID);
                    	for (ArrayList<String> review : allReviews) {
                    %>
                    		<div class="review-box">
                    			<div class="user-stars">
                    			 	<%
                    			 		// Rating
                    			 		int revRating = Integer.valueOf(review.get(2));
                    			 		for (int i=0; i < revRating; ++i) {
                    			 			out.println("<i class=\"fa-solid fa-star filled\"></i>");
                    			 		}
                    			 		for (int i=revRating; i < 5; ++i) {
                    			 			out.println("<i class=\"fa-solid fa-star unfilled\" style=\"font-weight: 100;\"></i>");
                    			 		}
                    			 	%>
                    			</div>

                    			<%
                    				// Username & review
	                    			out.println("<a href=\"account.jsp?username=" + review.get(1) + "\"><p class=\"username\">" + review.get(1) + "</p></a>");
	                    			out.println("<p class=\"user-review\">" + review.get(0) + "</p>");
                    			%>
                    		</div>
                    <% } %>
                </div>
                <script>
					var websocket = new WebSocket("ws://localhost:8080/ListenUp/ChatServerEndpoint");
					websocket.onmessage = function processMessage(message){
						var jsonData = JSON.parse(message.data);
		 				if (jsonData.message != null){
		 					var str = jsonData.message;
		 					const mes = str.split(":");

		 					var messToAdd;
		 					var containerToAppendTo = document.getElementById('messages');

		 					if(mes[0] == "<%=em%>"){
		 							messToAdd = document.createElement('div');
		 							messToAdd.className = 'user-msg';
		 					}
		 					else{
		 						messToAdd = document.createElement('div');
		 						messToAdd.className = 'recipient-msg';
		 					}
		 					messToAdd.innerHTML = mes[1];
		 					containerToAppendTo.appendChild(messToAdd);
						}
					}
					function sendMessage() {
						var msg = document.getElementById('msg-input').value;
						if (msg != "") {
							websocket.send(document.getElementById('msg-input').value);
							document.getElementById('msg-input').value = "";
						}
					}
				</script>
                	<div id="chat">
                		<p class="section-header">Chat about <%=name %></p>

                		<div id="chat-box">
                			<div id="messages"></div>

							<div id="msg-form">
								<input type="text" id="msg-input">
                				<button type="button" id="send-btn" onclick="sendMessage();">Send</button>
							</div>
                		</div>

                		<a id="back-link">Back to Reviews</a>
                	</div>
            </div>

            <div id="right">
                <p>Chat with someone about this album:</p>

                <button type="button" id="chat-btn">Chat</button>

            </div>
        </div>
    </div>
    
    <script>
		// Sends chat message
	    var input = document.getElementById("msg-input");
	    input.addEventListener("keypress", function(event) {
	    	if (event.key === "Enter") {
	    		document.getElementById("send-btn").click();
	    	}
	    });
    </script>
    
    <script>
	    // Retrieves access token for Spotify API 
	   let httpRequest = new XMLHttpRequest(); 
	   let authEndpoint = 'https://accounts.spotify.com/api/token';
	   httpRequest.open('POST', authEndpoint);
	
	   httpRequest.setRequestHeader('Authorization', 'Basic ' + btoa('bc70b7456e4046c99f46c807d5969037:4d4e2da2e3b5483192e6d10dc78fa6da'));
	   httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	   httpRequest.send('grant_type=client_credentials');
	
	   httpRequest.onreadystatechange = function() {
	       if(httpRequest.readyState == 4) {
	           if(httpRequest.status == 200) {
	               accessAPI(httpRequest.responseText);
	           }
	           else {
	               console.log(httpRequest.status);
	           }
	       }
	   }
	
	   // Calls API to get all album tracks
	   function accessAPI(results) {
	       let resultsJSON = JSON.parse(results);
	
	       let endpoint = "https://api.spotify.com/v1/albums/<%=ID%>/tracks";
	       httpRequest.open("GET", endpoint);
	       httpRequest.setRequestHeader('Authorization', 'Bearer ' + resultsJSON.access_token);
	       httpRequest.send();
	
	       httpRequest.onreadystatechange = function() {
	           if(httpRequest.readyState == 4) {
	               if(httpRequest.status == 200) {
	                      displayNewReleases(httpRequest.responseText);
	               }
	               else {
	                   console.log(httpRequest.status);
	               }
	           }
	       }
	   }
	
	   // Adds Spotify link to each track 
	   function displayNewReleases(results) {
	       let resultsJSON = JSON.parse(results);
	       
	       let tracklistBtns = document.querySelectorAll(".play-btn-a");
	       for (let i=0; i < tracklistBtns.length; ++i) {
	           tracklistBtns[i].setAttribute("href", resultsJSON.items[i].external_urls.spotify);
	           tracklistBtns[i].setAttribute("target", "_blank");
	       }
	   }
	</script>

    <script src="https://kit.fontawesome.com/9b2ed648bc.js" crossorigin="anonymous"></script>
    <script src="javascript/details.js"></script>
</body>
</html>
