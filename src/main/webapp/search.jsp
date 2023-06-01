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

    <title>SEARCH | ListenUp</title>

    <style>
        .container {
            width: 80%;
            text-align: center;
            margin: 50px auto;
        }
        .container p {
            font-size: 36px;
        }
        #search {
            margin-bottom: 100px;
        }
        #album-search {
            font-size: 24px;
            font-weight: 300;
            background-color: #404D66;
            border: 1px solid #1C2330;
            width: 75%;
            padding: 0 10px;
        }
        #album-imgs-div {
            display: flex;
            justify-content: center;
            margin-top: 50px;
        }
        .album-img img {
            width: 23%;
            height: auto;
            margin: 0 1%;
        }
    </style>
</head>
<body>
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
        <div id="search">
            <p>Find an album:</p>
            
            <form action="results.jsp" method="GET">
                <input type="text" name="album-search" id="album-search">
            </form>
        </div>

        <div id="popular-albums">
            <p>Popular Albums This Week:</p>

            <div id="album-imgs-div">
                <div class="album-img"></div>
                <div class="album-img"></div>
                <div class="album-img"></div>
                <div class="album-img"></div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="javascript/home.js"></script>
</body>
</html>
