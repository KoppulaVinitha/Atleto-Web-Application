<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Classes</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle1.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
    </head>
    <body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<a class = "btn btn-primary" href="${contextPath}/user/customer.htm" >Home Page</a>
	<h1>Please Search your favorite class</h1>
    
        <form action="${contextPath}/classes/search.htm" method="post">
            
            Enter Name <input type="text" name="inputtext" required /> <br><br>
            
            <label>Search Type:</label>
            <input type="radio" name="searchkey" value="gym" checked="checked"> Gym
            <input type="radio" name="searchkey" value="clasname"> Class Name <br/>
            
            <input type="hidden" name="action" value="searchuser"/>
            <input class = "btn btn-success" type="submit" name="search" value="Search Class"/>
        </form>
    </body>
</html>