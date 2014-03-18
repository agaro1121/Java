<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<LINK href="CSS/${theme}" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>
	<%@ include file="../includes/Header.html"%>
	<%@ include file="../includes/ShareTicker.html"%>
	<%@ include file="../includes/SHMenu.html"%>

	<h1>Search</h1>

	<form action="Saluton?sender=searchBrokers" method="post">
		Search for a Broker:<br> <select name="searchby">
			<option>Search by...</option>
			<option value="Name">Name</option>
			<option value="StockEx">Stock Exchange</option>
		</select> <input id="text" type="text" name="userInput"> <input
			type="submit" value="Search">
	</form>

	<form action="Saluton?sender=searchCompany" method=post>
		Search for a Company:<br> <select name="searchby">
			<option>Search by...</option>
			<option value="Name">Name</option>
			<option value="City">City</option>
			<option value="Country">Country</option>
			<option value="StockSymbol">Stock Symbol</option>
			<option value="StockEx">Stock Exchange</option>
		</select> <input id="text" type="text" name="userInput"> <input
			type="submit" value="Search">

	</form>
	<br>
	<br>
	<form action="Saluton?sender=searchAvailableStock&searchby=Name"
		method="post">
		Search for Available Shares By Company Name:<br> <input id="text"
			type="text" name="userInput"> <input type="submit"
			value="Search">
	</form>
	<br>
	<br>
	<form action="Saluton?sender=searchAvailableStock&searchby=Price"
		method="post">
		Search for Available Shares By Price Range:<br> Between $<input
			id="text" type="text" name="userInput"> AND $<input id="text"
			type="text" name="userInput2"> <input type="submit"
			value="Search">
	</form>

</body>
</html>