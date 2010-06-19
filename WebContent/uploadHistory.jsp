<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link rel="stylesheet" type="text/css" href="css/pexpStyle.css"/>
	<script type="text/javascript" src="js/pexpScript.js"></script>
<title>Hand History Reader</title>
</head>
<body>
<div class="contentContainer">
<% com.pexperiment.model.Login login = (com.pexperiment.model.Login) session.getAttribute("login"); %>
<h1>Hello <%= login.getPlayerName() %> </h1>
<br/>
<form enctype="multipart/form-data" method="post" action="uploadServlet" name="uploadForm" id="uploadForm" >
<hr/>
<input type="file" name="fileUp" id="fileUp" value=""/>
<span class="error" id="uploadError" name="uploadError">You must select a file before attempting to upload</span>
<hr/>
<input type="button" onclick="verifyUpload();" value="upload"/>
</form>

<br></br>
<form method="post" action="DeletePlayerServlet">
<input type="submit" value="delete current player"/>
</form>

<br></br>
<form method="post" action="DeleteAllPlayersServlet">
<input type="submit" value="delete all players"/>
</form>

<br></br>
<form method="post" action="LogoutServlet">
<input type="submit" value="logout"/>
</form>

</div>
</body>
</html>