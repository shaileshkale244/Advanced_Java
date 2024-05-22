<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%-- <h4>product id: ${param.pid}</h4>
<h4>product name: ${param.pname}</h4>
<h4>product price: ${param.price}</h4> --%>

<%--attribute to store product details & forward to test5 to display details  --%>
<%String product = request.getParameter("pid")+request.getParameter("pname")+request.getParameter("price");
request.setAttribute("details", product);
%>

<jsp:include page="test5.jsp"/>

</body>
</html>