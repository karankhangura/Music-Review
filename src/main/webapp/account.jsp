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

    <title>MY ACCOUNT | ListenUp</title>

    <style>
        .container {
            width: 90%;
            max-width: 1400px;
            margin: auto;
            font-size: 24px;
        }
        #header {
            display: flex;
            padding-bottom: 30px;
            border-bottom: 2px solid #C4C4C4;
            margin-bottom: 30px;
        }
        #username {
            font-size: 50px;
            margin-top: 40px;
        }
        #title {
            font-size: 32px;
        }
        #reviews {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }
        .review-box {
            background-color: #404D66;
            padding: 20px;
            margin-bottom: 20px;
            width: 49%;
            display: flex;
        }
        .review-box a {
            color: #C4C4C4;
        }
        .review-box a:hover {
            color: #909090;
        }
        .album-img {
        	wisth: 150px;
        	height: 150px;
        }
        .album-img img {
        	width: 150px;
        	height: auto;
        	margin-right: 20px;
        }
        .filled {
        	color: #2D7D19;
        	font-size: 20px;
        }
        .unfilled {
        	color: #C4C4C4;
        	font-size: 20px;
        }
        .review {
        	line-height: 24px;
        }
    </style>
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
			
			String username = request.getParameter("username");
	%>
    <div id="navbar">
        <div id="nav-left">
            <a id="listenup-name" href="search.jsp">ListenUp</a>
        </div>

        <div id="nav-right"> 
            <a class="nav-link" href=<%=disp2%>><%=em%></a>
            <a class="nav-link" href=<%=disp%>><%=red%></a>
            <a class="nav-link" href="search.jsp">Albums</a>
        </div>
    </div>

    <div class="container">
        <div id="header">
            <p id="username">
            	<% 
            		if (username.equals(em)) { 
            			out.println("Hello, " + em + "!");
            		}
            		else {
            			out.println(username);
            		}
            	%>
            		
            </p>
        </div>

		<p id="title"><% if (username.equals(em)) out.println("My "); %>Reviews</p>
		
        <div id="reviews">
            <%
            	ArrayList<ArrayList<String>> allReviews = Helper.getRevsUsername(username);
            	for (ArrayList<String> review : allReviews) {
            		String albumName = Helper.getName(review.get(1));
            		int rating = Integer.parseInt(review.get(2));
            %>
            	<div class="review-box">
            		<div class="album-img">
            			<img src=<%=Helper.getCover(review.get(1)) %> alt=<%=albumName %>>
            		</div>
            		
            		<div class="review-info">
	            		<p>for <a href="details.jsp?albumid=<%=review.get(1) %>"><%=albumName %></a>:</p>
	            		
	            		<% for (int i=0; i < rating; ++i) { %>
	            			<i class="fa-solid fa-star filled"></i>
	            		<% } %>
	            		
	            		<% for (int i=rating; i < 5; ++i) { %>
	            			<i class="fa-solid fa-star unfilled" style="font-weight: 100;"></i>
	            		<% } %>
	            		
	            		<p class="review"><%=review.get(0) %></p>
            		</div>
            	</div>
			<% } %>
        </div>
    </div>
    
    
    <script src="https://kit.fontawesome.com/9b2ed648bc.js" crossorigin="anonymous"></script>
</body>
</html>