<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<LINK href="CSS/${theme}" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Preferences</title>
</head>
<body>
<%@ include file="../WEB-INF/includes/Header.html"%>
<%@ include file="../WEB-INF/includes/ShareTicker.html"%>

	<a href="Saluton?sender=logout">(Logout)</a>

	<form action="Saluton?sender=changePasswordButton" method="post">
		New Password: <input type="password" name="newPassword"> <input
			type="submit" value="Submit">
	</form>

	<form action="Saluton?sender=createRequest" method="post">
		<p>Send a requests to an admin</p>
		Comments:
		<textarea name="description" rows="3" cols="40"></textarea>
		<select name="admin">
			<option value="0">Administrator...</option>
			<c:forEach var="entry" items="${admins}">
				<option value="${entry.key}">${entry.value}</option>
			</c:forEach>
		</select> <br /> <input type="submit" name="submitbutton" />
	</form>

	<br>Choose a theme:
	<form action="Saluton?sender=chooseTheme" method="post">
		<select name="theme">
			<option value="DarkTheme.css">Dark</option>
			<option value="LightTheme.css">Light</option>
		</select> <input type="submit" name="submitbutton" />
	</form>
	<br>
	<br>

	<c:if test="${isShare_Holder == true}">
		<table>
			<th>Share Ticker</th>
			<tr><td>
			<form action="Saluton?sender=setShareTicker" method="post">
				
					<c:forEach var="stock" items="${checkBoxStocks}" varStatus="index">
						<c:set var="counter" value="${index.count}"></c:set>
						<c:choose>
							<c:when test="${fn:contains(ticker, counter)}">
								<input name="userInput" type="checkbox" value="${stock[0]}"
									checked="checked">${stock[1]}<br>
							</c:when>
							<c:otherwise>
								<input name="userInput" type="checkbox" value="${stock[0]}">${stock[1]}<br>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</td></tr>
			<tr><td></td><td><input type="submit" name="submitbutton" />
			</form>
			</td></tr>
		</table>

	</c:if>

</body>
</html>