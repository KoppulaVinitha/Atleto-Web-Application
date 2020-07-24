<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Classes Add</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle1.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">

</head>
<body>
<c:set var="gymname" value="${gymName}" />
<c:set var="clas" value="${clasList}" />

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/host.htm" >Home Page</a>
<h2>Update the Class for ${gymname}</h2>

	<form:form action="${contextPath}/classes/update.htm"
		method="post">

		<table>
			<tr>
				<td>Class ID:</td>
				<td><input type ="text" name="clasId" value="${clas.clasID}" readonly></td>
			</tr>
			
			<tr>
				<td>Class Name:</td>
				<td><input type ="text" name = "clasName" value="${clas.clasName}"></td>
			</tr>
			
			<tr>
				<td>Class Description:</td>
				<td><input type ="text" name = "clasDesc" value="${clas.clasDesc}"></td>
			</tr>

			

			<tr>
				<td colspan="2"><input class = "btn btn-success" type="submit" value="Update Class" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>