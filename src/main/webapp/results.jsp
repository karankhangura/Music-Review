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

    <title>RESULTS | ListenUp</title>

    <style>
        .container {
            width: 80%;
            margin: 50px auto;
            font-size: 24px;
        }
        #search {
            text-align: center;
            width: 100%;
            margin: auto;
            margin-bottom: 30px;
        }
        #search label {
            padding-right: 10px;
        }
        #album-search {
            font-size: 24px;
            font-weight: 300;
            background-color: #404D66;
            border: 1px solid #1C2330;
            width: 90%;
            margin: auto;
            padding-left: 10px;
        }
        #title {
            font-size: 48px;
            border-bottom: 1px solid #1ED760;
            margin-top: 50px;
        }
        #total-results {
            margin-top: 15px;
            margin-bottom: 30px;
        }
        .album-result {
            background-color: #404D66;
            display: flex;
            padding: 20px;
            border-radius: 50px;
            margin-bottom: 15px;
        }
        .album-cover {
        	width: 200px;
        	height: 200px;
            margin-right: 25px;
            margin-left: 10px;
        }
        .album-cover img {
            width: 200px;
            height: auto;
            margin: 0;
            border-radius: 20px;
        }
        .album-details a {
            color:#C4C4C4;
            font-size: 42px;
        }
        .album-details a:hover {
            color:#2D7D19;
        }
        .rating {
        	display: flex;
            margin-top: 7%;
        }
       .rating i {
            font-size: 24px;
            margin-right: 2px;
        }
        .rating-p {
        	font-weight: 300;
        	margin-left: 5px;
        }
        .filled {
            color:#2D7D19;
        }
        .unfilled {
            color: #C4C4C4;
        }
    </style>
    
    <%@ page import = "api.SearchAlbum"  %>
    <%@ page import = "se.michaelthelin.spotify.model_objects.specification.AlbumSimplified" %>
    <%@ page import = "se.michaelthelin.spotify.model_objects.specification.ArtistSimplified" %>
    <%@ page import = "se.michaelthelin.spotify.model_objects.specification.Image" %>
    <%@ page import = "se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest" %>
    <%
    	String query = (String) request.getParameter("album-search");
    	System.out.println(query);
    	AlbumSimplified[] albums = SearchAlbum.searchAlbums_Async(query);
    	for (AlbumSimplified a : albums) {
    		System.out.println(a.getName());
    	}
    
    %>
    
</head>
<body>
	<%@page import="java.util.*"
	    import ="Util.Helper"
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
							red = "Logout";
							disp = "LogoutDispatcher";
							disp2 = "account.jsp?username=" + em; //should be changed to profile page for user
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
        <form action="" method="GET" id="search">
            <label for="album-search">Album name:</label>
            <input type="text" name="album-search" id="album-search">
        </form>
        
        <div id="results">
        	<% out.println("<p id=\"title\"> Results for " + query + "</p>");
        		out.println("<p id=\"total-results\">Showing " + albums.length + " result(s)</p>");
        	%>

            
		<% for(AlbumSimplified a : albums) { %>
            <div class="album-result">
                <div class="album-cover">
                <% 
                	Image[] covers = a.getImages();
                	
                %>
                	<img src = <%= covers[0].getUrl() %>>
                </div>

                <div class="album-details">
                    <a href="details.jsp?albumid=<%= a.getId() %>" class="album-title"><%= a.getName() %></a>
                    <% ArtistSimplified[] artists = a.getArtists();
                    	for (ArtistSimplified art : artists) {
                    		out.print("<p>" + art.getName() + "</p>" );
                    	}
                    %>
                    <%-- <p><%= a.getArtists().clone(). %></p> --%>
                    
                    <div class="rating">
                    	<% 
                    		double rating = Helper.getRating(a.getId());
                    		int temp = (int)rating;
                    		
                    		// Output the number of stars according to the average rating
                    		for (int i=0; i < temp; ++i) {
                    			out.println("<i class=\"fa-solid fa-star filled\"></i>");
                    		}
                    		
                    		// Half-star if applicable
                    		if (rating-temp >= 0.3) {
                    			out.println("<i class=\"fa-solid fa-star-half-stroke filled\"></i>");
                    		}
                    		
                    		if (rating == 0) {
                    			out.println("<p>No reviews yet</p>");
                    		}
                    		else {
                    			for (double i=Math.ceil(rating); i < 5; ++i) {
                        			out.println("<i class=\"fa-solid fa-star unfilled\" style=\"font-weight: 100;\"></i>");
                        		}
                    		}
                    		
                    		out.println("<p class=\"rating-p\">(" + rating + ")</p>");
                    	%>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>

    <script src="https://kit.fontawesome.com/9b2ed648bc.js" crossorigin="anonymous"></script>
</body>
</html>
