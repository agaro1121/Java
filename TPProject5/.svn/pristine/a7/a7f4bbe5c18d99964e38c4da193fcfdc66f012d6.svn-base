<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK href="resources/CSS/${sessionScope.user.theme}" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock Exchange Manager</title>
</head>
<body>
	<%@ include file="/resources/includes/Header.html"%>
	<%@ include file="/resources/includes/SEMenu.html"%>

	<h1>Stock Exchange Manager Page</h1>
	<h2>Welcome ${sessionScope.user.firstname}
		${sessionScope.user.lastname}</h2>
	<h3>Stock Exchange ID: ${stockExID}</h3>
	
	
	<br>
	<br>
	
	
	<h4><p>Reset Password</p></h4>
	<form action="/TPProject5/changePassword" method="post">
		New Password: <input type="password" name="newPassword"> <input
			type="submit" value="Submit">
	</form>

	<form action="/TPProject5/createRequest" method="post">
		<h4><p>Send a requests to an admin</p></h4>
		Comments:
		<textarea name="description" rows="3" cols="40"></textarea>
		<select name="admin">
			<option value="0">Optional Administrator...</option>
			<c:forEach var="entry" items="${admins}">
				<option value="${entry[0]}">${entry[1]}</option>
			</c:forEach>
		</select> <br /> <input type="submit" name="submitbutton" />
	</form>
	<br>
	<h4><p>Choose Theme</p></h4>
	<form action="/TPProject5/chooseTheme" method="post">
		<select name="theme">
			<option value="DarkTheme.css">Dark</option>
			<option value="LightTheme.css">Light</option>
		</select> <input type="submit" name="submitbutton" value="Submit">
	</form>

</body>
</html>