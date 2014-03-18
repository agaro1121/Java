<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<LINK href="CSS/DarkTheme.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
<%@ include file="../WEB-INF/includes/Header.html"%>

	<h1>Registration Page</h1>
	<form action="Saluton?sender=registerButton" method="post">
		<table>
			<tr>
				<td>First Name: <input id="text" type="text" name="firstname"><br>
					<br> Last Name: <input id="text" type="text" name="lastname"><br>
					<br> Password : <input id="text" type="password"
					name="password"><br> <br> <input type="checkbox"
					name="isAdmin" value="isAdmin">Administrator<br> <input
					type="checkbox" name="isBroker" value="isBroker">Broker<br>
					<input type="checkbox" name="isSE_Manager" value="isSE_Manager">Stock
					Exchange Manager<br> <input type="checkbox"
					name="isShare_Holder" value="isShare_Holder">Share Holder
				</td>
			</tr>
			<tr id="buttons">
				<td><input type="submit" value="register"></td>
			</tr>
		</table>
	</form>
</body>
</html>