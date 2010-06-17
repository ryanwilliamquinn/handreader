<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/pexpStyle.css"/>
<script type="text/javascript" src="js/pexpScript.js"></script>
<title>Insert title here</title>

</head>
<body>
<h1>Hello</h1>
<br/>



<form method="post" action="LoginServlet">
Login: <input type="text" id="playerName" name="playerName" />
<br></br>
Password: <input type="text" id="password" name="password" />
<br></br>
<input type="submit" value="submit"/>
</form>

<br></br><br></br>
<a href="#" id="regLink" onClick="showReg()">Click here to create a new account</a>
<form method="post" action="NewAccountServlet" id="regForm" class="registration">
New Account
<br></br>
Login: <input type="text" id="newPlayerName" name="newPlayerName" />
<br></br>
Password: <input type="text" id="newPassword" name="newPassword" />
<br></br>
<input type="submit" value="submit"/>
</form>



</body>
</html>