<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>

<% com.pexperiment.model.Player player = (com.pexperiment.model.Player) request.getAttribute("player"); %>
Player name: <%= player.getPlayerName() %>
<br></br>
Total hands: <%= player.getTotalHands() %>
<br></br>
Vpip hands: <%= player.getVpipHands() %>
<br></br>
<br></br>
<form method="post" action="uploadHistory.jsp">
<input type="submit" value="back"/>
</form>
</body>
</html>