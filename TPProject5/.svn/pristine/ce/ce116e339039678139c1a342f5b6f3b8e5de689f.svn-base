<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK href="resources/CSS/${sessionScope.user.theme}" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Trades</title>
</head>
<body>
<%@ include file="/resources/includes/Header.html" %>
<%@ include file="/resources/includes/SEMenu.html"%>
	<h1>Trades Made By ShareHolders Who Are Brokers</h1>
	<table border=1>
		<tr>
			<th>Time</th>
			<th>Company</th>
			<th>Amount</th>
			<th>Total Price</th>
			<th>Buy Broker</th>
			<th>Sell Broker</th>
		</tr>
		<c:forEach var="trade" items="${trades}">
			<tr>
				<c:forEach var="tradeAttribute" items="${trade}">
					<td>${tradeAttribute}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

</body>
</html>