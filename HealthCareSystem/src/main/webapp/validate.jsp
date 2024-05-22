<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%-- Directive taglib is used to import the external additional tag libraries(JSTL core library)--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Validation</title>
</head>
<%-- data transfer from req param -- JB --%>
<jsp:setProperty property="*" name="patient_bean" />
<body>
	<%--invoke B.L method of a Java bean --%>
	<%-- <h5> Login status - ${sessionScope.user.validateUser()}</h5> --%>
	<c:redirect url="${sessionScope.patient_bean.validatePatient()}.jsp" />
</body>
</html>