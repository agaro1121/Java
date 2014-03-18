<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<LINK href="CSS/${theme}" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Options</title>
</head>
<body>
<%@ include file="../WEB-INF/includes/Header.html"%>
<%@ include file="../WEB-INF/includes/ShareTicker.html"%>

	<h1>Welcome ${Username}</h1>
	<a href="Saluton?sender=logout">(Logout)</a>

	<c:if test="${sessionScope.Username != null }">
	<a href="Saluton?sender=aUserPref">User Preferences</a>

		<c:if test="${isAdmin == true}">
			<a href="Saluton?sender=adminPage">Administrative Controls</a>
			<br>
			<br>
		</c:if>
		<c:if test="${isBroker == true}">
      user is a broker as well<br>
			<br>
		</c:if>
		<c:if test="${isSE_Manager == true}">
      user is a se manager as well<br>
			<br>
		</c:if>
		<c:if test="${isShare_Holder == true}">
			<a href="Saluton?sender=ShareHolderPage">Share Holder Controls</a>
			<br>
			<br>
		</c:if>

	</c:if>
</body>
</html>