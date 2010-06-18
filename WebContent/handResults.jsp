<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/pexpStyle.css"/>
<title>Insert title here</title>
</head>
<body>
<div class="contentContainer">
<% com.pexperiment.model.Player player = (com.pexperiment.model.Player) request.getAttribute("player"); %>
Player name: <%= player.getPlayerName() %>
<br/>
Total hands: <%= player.getTotalHands() %>
<br/>
Vpip hands: <%= player.getVpipHands() %>
<br/>
Pfr hands:  <%= player.getPfrHands() %>
<br/>



<form method="post" action="uploadHistory.jsp">
<input type="submit" value="back"/>
</form>
</div>
</body>
</html>