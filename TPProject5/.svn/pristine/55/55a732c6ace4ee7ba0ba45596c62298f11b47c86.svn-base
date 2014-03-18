<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK href="resources/CSS/${sessionScope.user.theme}" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${objectType} Management</title>
</head>
<body>
	<%@ include file="/resources/includes/Header.html"%>
	<%@ include file="/resources/includes/SEMenu.html"%>
<h1>${objectType} Management</h1>
	
	<c:choose>
	<c:when test="${objectType == 'ShareHolder'}">
		<br><a href="/TPProject5/ApproveCurrentShareHolders">Approve Currently Denied ShareHolders</a>
		<br><a href="/TPProject5/ApproveShareHolders">Approve Pending Shareholder</a><br>
		<br><a href="/TPProject5/DenyShareHolders">Deny Pending Shareholder</a><br>
		<br><a href="/TPProject5/tradesBSH">View Trades Made If Also Broker</a> <br> 
		<br><a href="/TPProject5/allSharesOwned">View All Shares Owned</a><br>
		<br><a href="/TPProject5/ShareHolders">Suspend Shareholder</a><br>
		<br><a href="/TPProject5/unSuspend">UN-Suspend ShareHolders</a><br>  
		<br><a href="/TPProject5/trades">View shareholder activity</a> <br> 
	</c:when>
	<c:when test="${objectType == 'Broker'}">
		<br><a href="/TPProject5/ApproveCurrentBrokers">Approve Currently Denied Brokers</a>
		<br><a href="/TPProject5/ApproveBrokers">Approve Pending Brokers</a><br>
		<br><a href="/TPProject5/DenyBrokers">Deny Pending Brokers</a>
		<br><a href="/TPProject5/Brokers">Suspend Broker</a><br>
		<br><a href="/TPProject5/unSuspendB">UN-Suspend Broker</a>
		<br><a href="/TPProject5/allSharesOwnedSHB">View Shares Owned If Also ShareHolder</a><br>
		<br><a href="/TPProject5/tradesB">View All Trades Made</a> <br>
	</c:when>
	<c:when test="${objectType == 'Company'}">
		<br><a href="/TPProject5/Companies">De-list Company</a><br>
		<br><a href="/TPProject5/listCompany">List Company</a><br>
		<br><a href="/TPProject5/tradesBSH">View company shares traded by owner</a><br> 
		<br><a href="/TPProject5/tradesB">View company shares traded by broker</a> <br>
		<br><a href="/TPProject5/SharesIssued">Set Share Price/Amount</a><br>
	</c:when>
	</c:choose>
	



</body>
</html>