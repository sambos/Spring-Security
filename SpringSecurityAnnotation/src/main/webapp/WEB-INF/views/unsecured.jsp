<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World</title>
</head>
<body>
 <h1>Title : ${title}</h1>
 <h1>Message : ${message}</h1>
 <ul>
 <li><a href="<c:url value="/protected" />" > protected</a></li>
  <li><a href="<c:url value="/confidential"/>" > confidential</a></li>
   <li><a href="<c:url value="/session"/>" > session</a></li>
 </ul>
</body>
</html>