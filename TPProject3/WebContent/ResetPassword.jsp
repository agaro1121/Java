<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<LINK href="CSS/DarkTheme.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
</head>
<body>
<%@ include file="../WEB-INF/includes/Header.html"%>
	
<p>Enter your username to have your password reset</p>
	<form action="Saluton?sender=resetPasswordButton" method="post">
	Username:
	<input id="text" type="text" name="Username">
	<input type="submit" value="Submit">
	</form>

</body>
</html>