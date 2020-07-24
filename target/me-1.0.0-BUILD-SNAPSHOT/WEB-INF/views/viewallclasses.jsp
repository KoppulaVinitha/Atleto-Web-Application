<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View All Classes</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="claslist" value="${classes}" />

<a class = "btn btn-success" href="${contextPath}/user/login.htm" >Login And Start Joining Classes</a>



<table class="table-condensed" border="1">

<tr>
<!--<td id="td1"> Class ID </td>-->
<td id="td1"> Gym </td>
<td id="td1"> Class Name </td>
<td id="td1"> Class Description </td>
<td id="td1"> Posted by </td>
<td id="td1"> Posted Date </td>


</tr>
<c:forEach items="${claslist}" var="clas">

<form:form action="${contextPath}/classes/add.htm" commandName="claslist"
		method="post">
<tr>
<!--<td>${clas.clasID}</td>-->
<td>${clas.gym.gymName}</td>
<td> ${clas.clasName}</td>
<td> ${clas.clasDesc}</td>
<td> ${clas.postedName}</td>
<td> ${clas.postedDate}</td>
</tr>
</form:form>
</c:forEach>
</table> 
</body>
</html>