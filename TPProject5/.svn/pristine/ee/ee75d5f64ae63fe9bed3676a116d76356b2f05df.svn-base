<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK href="resources/CSS/${sessionScope.user.theme}" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${buttonValue} ${objectType}</title>
</head>
<body>
	<%@ include file="/resources/includes/Header.html"%>
	<%@ include file="/resources/includes/SEMenu.html"%>

	<h1>${buttonValue} ${objectType}</h1>

	<c:choose>
		<c:when test="${objectType == 'ShareHolders'}">
			<c:if test="${actionType == 1}">
				<form action="/TPProject5/Suspend.do" method="post">
			</c:if>
			<c:if test="${actionType == 2}">
				<form action="/TPProject5/unSuspend.do" method="post">
			</c:if>
			<c:if test="${actionType == 3}">
				<form action="/TPProject5/Approve.do" method="post">
			</c:if>
			<c:if test="${actionType == 4}">
				<form action="/TPProject5/Deny.do" method="post">
			</c:if>
			<c:if test="${actionType == 5}">
				<form action="/TPProject5/ApproveCurrSH.do" method="post">
			</c:if>
			<table border=1>
				<tr>
					<th>ID</th>
					<th>Name</th>
				</tr>
				</c:when>

				<c:when test="${objectType == 'Brokers'}">
					<c:if test="${actionType == 1}">
						<form action="/TPProject5/SuspendB.do" method="post">
					</c:if>
					<c:if test="${actionType == 2}">
						<form action="/TPProject5/unSuspendB.do" method="post">
					</c:if>
					<c:if test="${actionType == 3}">
						<form action="/TPProject5/ApproveB.do" method="post">
					</c:if>
					<c:if test="${actionType == 4}">
						<form action="/TPProject5/DenyB.do" method="post">
					</c:if>
					<c:if test="${actionType == 5}">
						<form action="/TPProject5/ApproveCurrB.do" method="post">
					</c:if>
					<table border=1>
						<tr>
							<th>ID</th>
							<th>Name</th>
						</tr>
				</c:when>

				<c:when test="${objectType == 'Companies'}">
					<c:if test="${actionType == 1}">
						<form action="/TPProject5/List.do" method="post">
					</c:if>
					<c:if test="${actionType == 2}">
						<form action="/TPProject5/DeList.do" method="post">
					</c:if>
					<table border=1>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Symbol</th>
							<th>Starting Price</th>
							<th>Current Price</th>
						</tr>
				</c:when>
				</c:choose>



				<c:forEach var="object" items="${objects}">
					<tr>
						<c:forEach var="objectDetail" items="${object}">
							<td>${objectDetail}</td>
						</c:forEach>
						<td><input name="userIDs" type="checkbox"
							value="${object[0]}"></td>

					</tr>
				</c:forEach>
			</table>
			<input name="action" type="hidden" value="${action}">
			<input name="submit" type="submit" value="${buttonValue}">
			</form>
</body>
</html>