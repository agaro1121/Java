<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK href="resources/CSS/${sessionScope.user.theme}"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Shares Owned</title>
</head>
<body>
	<%@ include file="/resources/includes/Header.html"%>
	<%@ include file="/resources/includes/SEMenu.html"%>
	<h1>All Shares Owned</h1>

	<table border="1">
		<tr>
			<th>ShareHolder_ID</th>
			<th>Name</th>
			<th>Stock Id</th>
			<th>Stock Symbol</th>
			<th>Company</th>
			<th>Stock Exchange</th>
			<th>Amount of Shares</th>
		</tr>
		<c:forEach var="userShares" items="${ownedShares}">
			<c:forEach var="userShare" items="${userShares}">
				<tr>
					<c:forEach var="shareDetail" items="${userShare}">
						<td>${shareDetail}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>


</body>
</html>