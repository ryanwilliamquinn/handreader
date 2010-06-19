<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/pexpStyle.css"/>
	<script type="text/javascript" src="js/pexpScript.js"></script>
	<title>HHR</title>
</head>
<body>
	<div class="contentContainer">
		<h1>The Hand History Reader</h1>
		<div class="forms">
					
			<form method="post" action="LoginServlet" id="loginForm" name="loginForm">
				<h2>Login</h2>
				<% String errorMsg = (String) request.getAttribute("errorMsg"); %>	
				<% if (errorMsg == null) errorMsg = ""; %>					 
				<label class="showerror"> <%= errorMsg.toString() %> </label> 
				<label for="playerName" class="inputLabel">Username:</label> <input class="textInput" type="text" id="playerName" name="playerName" />
				<label for="playerName" class="inputLabel">Password:</label> <input class="textInput" type="text" id="password" name="password" />
				<input type="submit" value="submit"/>
			</form>
			<form method="post" action="NewAccountServlet" id="regForm" class="registration">
				<h2>Create New Account</h2>
				<label for="playerName" class="inputLabel">New Username:</label> <input class="textInput" type="text" id="newPlayerName" name="newPlayerName" />
				<label for="playerName" class="inputLabel">New Password:</label> <input class="textInput" type="text" id="newPassword" name="newPassword" />
				<input type="submit" value="submit"/>
			</form>
		<div class="mtb10">
			<a href="#" id="regLink" onClick="showReg()">Click here to create a new account</a>
			<a href="#" id="loginLink" onClick="showLogin()" class="ghost">Click here to return to login</a>
		</div>
		</div>
	</div>
</body>
</html>