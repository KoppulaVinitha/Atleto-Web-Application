<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Gym Registration</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle1.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
    
</head>
<body>
<h2>Register a New Gym</h2>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/admin.htm" >Home Page</a>

	<form:form action="${contextPath}/gym/register.htm" commandName="gym"
		method="post">

		<table>
			<tr>
				<td>Gym Name:</td>
				<td><form:input path="gymName" size="30" required="required" />
					<font color="red"><form:errors path="gymName" /></font></td>
			</tr>

			<tr>
				<td>Gym Address:</td>
				<td><form:input path="gymAddress" size="30" required="required" />
					<font color="red"><form:errors path="gymAddress" /></font></td>
			</tr>


			<tr>
				<td>Gym Description:</td>
				<td><form:input path="gymDescription" size="30" required="required" />
					<font color="red"><form:errors path="gymDescription" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input class = "btn btn-success" type="submit" value="Register Gym" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>