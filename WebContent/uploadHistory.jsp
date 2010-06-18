<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link rel="stylesheet" type="text/css" href="css/pexpStyle.css"/>
<title>Insert title here</title>
</head>
<body>
<div class="contentContainer">
<% com.pexperiment.model.Login login = (com.pexperiment.model.Login) session.getAttribute("login"); %>
<h1>Hello <%= login.getPlayerName() %> </h1>
<br/>

<form enctype="multipart/form-data" method="post" action="uploadServlet" >
<hr/>
<input type="file" name="mptest" value=""/>
<hr/>
<input type="submit" value="upload"/>
</form>

<br></br>
<form method="post" action="ClearDatabaseServlet">
<input type="submit" value="clear database"/>
</form>
<br></br>
<br></br>
<form method="post" action="LogoutServlet">
<input type="submit" value="logout"/>
</form>

</div>
</body>
</html>