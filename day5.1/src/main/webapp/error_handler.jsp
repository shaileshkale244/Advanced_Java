<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Handling Page</title>
</head>
<body>
<h5>Error:<%=exception%></h5>
<h3>Error Message:  ${pageContext.exception.message}</h3>
<h5>Error code: ${pageContext.errorData.statusCode}</h5>
<h5>Error Page: ${pageContext.errorData.requestURI}</h5>
</body>
</html>