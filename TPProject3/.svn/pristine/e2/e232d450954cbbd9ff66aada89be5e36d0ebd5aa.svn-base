<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<LINK href="CSS/${theme}" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Brokers</title>
</head>
<body>
	<%@ include file="../includes/Header.html"%>
	<%@ include file="../includes/ShareTicker.html"%>
	<%@ include file="../includes/SHMenu.html"%>

	<h1>Brokers</h1>
	<table border="1">
	<tr>
		<th>Broker ID</th>
		<th>Name</th>
		<th>Place of Work</th>
	</tr>
		<c:forEach var="listElement" items="${brokers}">
			<tr>
				<c:forEach var="broker" items="${listElement}">
					<td>${broker}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

</body>
</html>