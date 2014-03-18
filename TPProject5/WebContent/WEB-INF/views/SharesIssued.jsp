<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK href="resources/CSS/${sessionScope.user.theme}" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shares Issued</title>
</head>
<body>
	<%@ include file="/resources/includes/Header.html"%>
	<%@ include file="/resources/includes/SEMenu.html"%>
</body>
<h1>Shares Issued</h1>

<table border="1">
	<tr>
		<th>Stock ID</th>
		<th>SYMBOL</th>
		<th>COMPANY</th>
		<th>TIME START</th>
		<th>TIME END</th>
		<th>AMOUNT</th>
		<th>STARTING PRICE</th>
	</tr>
	<c:forEach var="shares" items="${sharesIssued}">
		<tr>
			<c:forEach var="share" items="${shares}">
				<td>${share}</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

<h4>Update a Share's Amount</h4>
<form action="/TPProject5//updateShareAmount.do" method="post">
	Stock ID: <select name="stockID">
		<c:forEach var="stock" items="${sharesIssued}">
			<option>${stock[0]}</option>
		</c:forEach>
	</select> <input type="text" name="amount"> <input type="submit"
		value="Submit">
</form>

<h4>Update a Share's Price</h4>
<form action="/TPProject5//updateSharePrice.do" method="post">
	Stock ID: <select name="stockID">
		<c:forEach var="stock" items="${sharesIssued}">
			<option>${stock[0]}</option>
		</c:forEach>
	</select> <input type="text" name="amount"> <input type="submit"
		value="Submit">
</form>

</html>