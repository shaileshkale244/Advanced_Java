<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>include directive</title>
</head>
<%! String msg1="message1"; %>
<body>
<% 
String msg2="message2";
pageContext.setAttribute("num1", 1024);
%>
<%@ include file="test3.jsp" %>


</body>
</html>