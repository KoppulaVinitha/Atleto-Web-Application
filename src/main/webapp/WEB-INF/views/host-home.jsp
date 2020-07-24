<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Host Home Page</title>

<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle1.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">

</head>

<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="claslist" value="${classes}" />
<a class = "btn btn-primary" href="${contextPath}/classes/register.htm" >Add a new Class for ${user.gym.gymName}</a>
<%-- <a href="${contextPath}/classes/delete.htm" >Delete an existing Clas for ${user.company.companyName}</a> <br /> --%>
<a id ="a1" class = "btn btn-danger" href="${contextPath}" > Logout</a>
 <h1>The Below Classes are already present for ${user.gym.gymName}</h1>


<table class="table-condensed" border="1">

<tr>
<td id="td1"> Class ID </td>
<td id="td1"> Class Name </td>
<td id="td1"> Class Description </td>
<td id="td1"> Posted By Name </td>
<td id="td1"> Posted By UserName </td>
<td id="td1"> Posted Date </td>
<td id="td1"> Update Class </td>
<!-- <td id="td1"> Delete Clas</td> -->

</tr>
<c:forEach items="${claslist}" var="clas">

<form:form action="${contextPath}/classes/update.htm" commandName="claslist"
		method="get">
<tr>
<td><input type="text" name="clasId" value="${clas.clasID}" readonly></td>
<td> ${clas.clasName}</td>
<td> ${clas.clasDesc}</td>
<td> ${clas.postedName}</td>
<td> ${clas.postedID}</td>
<td> ${clas.postedDate}</td>
<td><input class = "btn btn-success" type="submit" name="action" value="Update"></td>
<!--<td><a class = "btn btn-primary" href="${contextPath}/classes/modify.htm" >Delete</a></td>-->
</tr>
</form:form>


</c:forEach>
</table> 

</body>
</html>