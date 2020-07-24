<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Class Finder</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle1.css"/>">

<link rel="stylesheet" href="<c:url value="https://www.w3schools.com/w3css/4/w3.css"/> ">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/> ">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>    
    
<h1><i><font color='white'>ATLETO</font></i></h1>
    
<br>
<br>
<a class = "btn btn-warning" href="user/login.htm">User Login</a>
<a class = "btn btn-danger" href="classes/viewallclasses.htm">All Classes Posted on the site</a>
<br/>
<br/>
    
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
<div class="w3-content w3-display-container">
 
    
    
    
   
    
<!--    <img class="mySlides" src="CSS/Image/slideimg1.jpeg">-->

<!--
  <button class="w3-button w3-black w3-display-left" onclick="plusDivs(-1)">&#10094;</button>
  <button class="w3-button w3-black w3-display-right" onclick="plusDivs(1)">&#10095;</button>
-->
</div>

    <script>
var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}    
    x[myIndex-1].style.display = "block";  
    setTimeout(carousel, 7000); // Change image every 2 seconds
}
</script>


</body>
</html>