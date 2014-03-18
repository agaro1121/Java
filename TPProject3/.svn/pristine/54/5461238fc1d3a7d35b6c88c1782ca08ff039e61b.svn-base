<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<LINK href="CSS/${theme}" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Controls</title>
</head>
<body>
<%@ include file="../WEB-INF/includes/Header.html"%>
<c:if test="${sessionScope != null}">
<h1>Admin Controls</h1>

<a href="Saluton?sender=logout">(Logout)</a>

  <form action="Saluton?sender=changePasswordButton" method="post" >
New Password:     <input  type="password" name="newPassword">
<input type="submit" value="Submit">
</form>


</c:if>
</body>
</html>