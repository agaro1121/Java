<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<LINK href="CSS/${theme}" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request a Trade</title>
</head>
<body>
	<%@ include file="../includes/Header.html"%>
	<%@ include file="../includes/ShareTicker.html"%>
	<%@ include file="../includes/SHMenu.html"%>

	<h1>Request a Trade</h1>

	<h3>Sell Shares</h3>
	<table>
		<c:forEach var="column" items="${columns}">
			<th>${column}</th>
		</c:forEach>

		<c:forEach var="listElement" items="${shares}">
			<tr>
				<c:forEach var="share" items="${listElement}" begin="0" end="4">
					<td>${share}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

	<form action="Saluton?sender=makeRequest&transactionType=SELL" method="post">

		<select name="stockID" id="stockID"
			onchange="populateBrokers(this.value)">
			<option disabled="disabled" selected="selected">Select a
				Stock ID...</option>
				
			<c:forEach var="stockID" items="${shares}">
				<option value="${stockID[0]}">${stockID[0]}</option>
			</c:forEach>
			
		</select> <select name="broker" id="brokers">
			<option disabled="disabled" selected="selected">Select a
				Broker</option>
		</select> Amount: <input id="text" type="text" name="amount"> <input
			type="submit" value="Submit Request"><br> <br>
	</form>
	
	
<!-- ****************************** BUY  ******************************-->
	<h3>Buy Shares</h3>
	<table>
		<c:forEach var="column" items="${aColumns}">
			<th>${column}</th>
		</c:forEach>

		<c:forEach var="listElement" items="${aShares}">
			<tr>
				<c:forEach var="share" items="${listElement}" begin="0" end="5">
					<td>${share}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

	<form action="Saluton?sender=makeRequest&transactionType=BUY" method="post">
		
		<select name="stockID" id="stockID"
			onchange="populateBuyBrokers(this.value)">
			<option disabled="disabled" selected="selected">Select a
				Stock ID...</option>
			<c:forEach var="stockID" items="${aShares}">
				<option value="${stockID[0]}">${stockID[0]}</option>
			</c:forEach>
		</select> <select name="broker" id="aBrokers">
			<option disabled="disabled" selected="selected">Select a 
				Broker</option>
		</select> 
		Amount: <input id="text" type="text" name="amount"> <input
			type="submit" value="Submit Request"><br> <br>
	</form>


	<script type="text/javascript">
		function populateBrokers(selectValue) {
			var select = document.getElementById('brokers');
			select.options.length = 1;
			<c:forEach var="listElement" items="${shares}">
			if (selectValue == "${listElement[0]}") {
				<c:forEach var="broker" items="${listElement[5]}">
				select.options.add(new Option("${broker[1]}", "${broker[0]}"));
				</c:forEach>
			}
			</c:forEach>
		}
	</script>
	
		<script type="text/javascript">
		function populateBuyBrokers(selectValue) {
			var select = document.getElementById('aBrokers');
			select.options.length = 1;
			<c:forEach var="listElement" items="${aShares}">
			if (selectValue == "${listElement[0]}") {
				<c:forEach var="broker" items="${listElement[6]}">
				select.options.add(new Option("${broker[1]}", "${broker[0]}"));
				</c:forEach>
			}
			</c:forEach>
		}
	</script>
</body>
</html>