<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
   <html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="preconnect" href="https://fonts.googleapis.com"> 
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> 
    <link href="https://fonts.googleapis.com/css2?family=Dongle:wght@300;400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="style.css">

    <title>LOGIN | ListenUp</title>

    <style>
        .container {
            width: 35%;
            margin: 50px auto;
        }
        #login-box {
            border: 2px solid white;
            padding: 5% 15%;
        }
        #login-box h1 {
            font-size: 48px;
            text-align: center;
            font-weight: 700;
        }
        #login-box label {
            font-size: 30px;
            line-height: 10px;
        }
        #login-box input {
            font-size: 24px;
            font-weight: 300;
            background-color: #404D66;
            width: 100%;
            margin-bottom: 10%;
        }
        #login-btn {
            color: #2A3343;
            font-size: 28px;
            background-color: white;
            border: none;
            border-radius: 10px;
            width: 100%;
            margin-bottom: 10px;
        }
        #login-btn:hover, #guest-login:hover {
            color: white;
            background-color: #2D7D19;
        }
        #login-box a {
            font-size: 24px;
        }
        #forgot-password-link:hover {
            color: #2D7D19;
        }
        #guest-login {
            background-color: #1C2330;
            font-size: 28px;
            border: none;
            border-radius: 10px;
            width: 100%;
        }
        #register-redirect {
            text-align: center;
            margin-top: 10%;
            width: 100%;
        }
        #register-redirect p {
            font-size: 32px;
            border-bottom: 1px solid white;
        }
        #redirect-btn {
            font-size: 32px;
            color: #2A3343;
            background-color: white;
            border: none;
            width: 100%;
            margin-top: 3%;
        }
        #redirect-btn:hover {
            color: white;
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
        <div id="login-box">
            <h1>Login</h1>

            <form action="LoginDispatcher" method="GET">
                <label for="email-login">Email</label><br>
                <input type="text" name="email-login" id="email-login">

                <br>

                <label for="password-login">Password</label><br>
                <input type="password" name="password-login" id="password-login">
                
                <button type="submit" id="login-btn">Log In</button>
                <button type="button" onClick="location.href='LoginDispatcher?email-login=guest@mail.com&password-login=guest';" id="guest-login">Log In as Guest</button>
            </form>
            
           <!--  <a href="" id="forgot-password-link">Forgot your password?</a><br> -->
        </div>

        <div id="register-redirect">
            <p>New to ListenUp?</p>
            <button type="button" id="redirect-btn" onClick="location.href='signup.jsp';">Create your ListenUp account now!</button>
        </div>
    </div>
</body>
</html>
