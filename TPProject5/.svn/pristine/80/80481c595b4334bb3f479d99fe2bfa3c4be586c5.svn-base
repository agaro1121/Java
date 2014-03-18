<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK href="resources/CSS/DarkTheme.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%@ include file="/resources/includes/Header.html" %>

	<div class="LoginForm">
		<sf:form method="post" action="login.do" modelAttribute="user">
			<table>
				<tr>
					<td><br> <br> <br></td>
				</tr>
				<tr>
					<td><label for="username">Username:</label> <sf:input
							path="username" size="20" id="username" /><br> <br>
					<label for="password">Password:</label> <sf:password
							path="password" showPassword="false" size="20" id="password" /><br>
						<br></td>
				</tr>
				<tr id="buttons">
					<td><a href="ResetPassword.jsp">Forgot Password</a> <input
						type="button" value="Register"
						onclick="window.location.href='Success.jsp'" /> <input
						type="submit" value="Login"></td>

				</tr>
			</table>
		</sf:form>
	</div>


</body>
</html>