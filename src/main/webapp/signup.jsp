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

    <title>SIGN UP | ListenUp</title>

    <style>
        .container {
            width: 40%;
            margin: 50px auto;
        }
        #signup-box {
            border: 2px solid white;
            padding: 5% 15%;
        }
        #signup-box h1 {
            font-size: 48px;
            text-align: center;
            font-weight: 700;
        }
        #signup-box label {
            font-size: 28px;
            line-height: 10px;
        }
        #signup-box input {
            font-size: 24px;
            font-weight: 300;
            background-color: #404D66;
            width: 100%;
            margin-bottom: 10%;
        }
        #submit-btn {
            color: #2A3343;
            font-size: 28px;
            background-color: white;
            border: none;
            border-radius: 10px;
            width: 100%;
            margin-bottom: 10px;
        }
        #submit-btn:hover {
            color: white;
            background-color: #2D7D19;
        }
        #guest-btn {
        	color: white;
            font-size: 28px;
            background-color: #1C2330;
            border: none;
            border-radius: 10px;
            width: 100%;
        }
        #guest-btn:hover {
            background-color: #2D7D19;
        }
    </style>
</head>
<body>
    <div id="navbar">
        <div id="nav-left">
            <a id="listenup-name" href="home.jsp">ListenUp</a>
        </div>

        <div id="nav-right"> 
            <a class="nav-link" href="signup.jsp">Sign Up</a>
            <a class="nav-link" href="login.jsp">Login</a>
            <a class="nav-link" href="search.jsp">Albums</a>
        </div>
    </div>
    	

    <div class="container">
    		<% String er = (String) request.getAttribute("error");
			if (er != null) out.println(er);
			%>
        <div id="signup-box">
            <h1>Sign Up</h1>

            <form action="RegisterDispatcher" method=GET>
                <label for="email-signup">Email</label><br>
                <input type="text" name="email-signup" id="email-signup">

                <br>

                <label for="username-signup">Username</label><br>
                <input type="text" name="username-signup" id="username-signup">

                <br>

                <label for="password-signup">Password</label><br>
                <input type="password" name="password-signup" id="password-signup">

                <br>

                <label for="confirm-password-signup">Confirm Password</label><br>
                <input type="password" name="confirm-password-signup" id="confirm-password-signup">

                <button type="submit" id="submit-btn">Create Account</button>
                <button type="button" id="guest-btn" onClick="location.href='LoginDispatcher?email-login=guest@mail.com&password-login=guest';">Sign-In as Guest</button>
            </form>
        </div>
    </div>
</body>
</html>
