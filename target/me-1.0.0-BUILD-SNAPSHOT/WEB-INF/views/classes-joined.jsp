<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Home</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/AdminStyle1.css"/>">
<link rel="stylesheet"
	href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="claslist" value="${user.claslists}" />

	<a class = "btn btn-primary" href="${contextPath}/user/customer.htm" >Home Page</a>
	<a class="btn btn-primary" href="${contextPath}/classes/search.htm">Search
		Class</a>
		

	<h1>Joined Classes for ${user.firstName}</h1>

	<c:choose>

		<c:when test="${empty claslist }">
			<h2>Please join the classes for viewing this section</h2>
		</c:when>

		<c:otherwise>
			<table border="1">

				<tr>
					<td id="td1">Class ID</td>
					<td id="td1">Gym</td>
					<td id="td1">Class Name</td>
					<td id="td1">Class Description</td>
					<td id="td1">Class Posted on</td>

				</tr>
				<c:forEach items="${claslist}" var="clas">

					<form:form action="${contextPath}/classes/add.htm"
						commandName="claslist" method="post">
						<tr>
							<td><input type="text" name="clasID" value="${clas.clasID}"
								readonly></td>
							<td style="color:blanchedalmond;">${clas.gym.gymName}</td>
							<td style="color:blanchedalmond;">${clas.clasName}</td>
							<td style="color:blanchedalmond;">${clas.clasDesc}</td>
							<td style="color:blanchedalmond;">${clas.postedDate}</td>
						</tr>
					</form:form>


				</c:forEach>
			</table>

		</c:otherwise>
	</c:choose>
</body>

</html>