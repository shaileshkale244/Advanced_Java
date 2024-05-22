<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
</head>

<jsp:useBean id="patient_bean" class="com.app.beans.PatientBean" scope="session" />

<jsp:useBean id="doctor_bean" class="com.app.beans.DoctorBean"
	scope="session" />
	<jsp:useBean id="apt_bean" class="com.app.beans.AppointmentBean"
	scope="session" />
<body>
	
	<h5 style="color: red;">${sessionScope.user.message}</h5>
	<form action="validate.jsp" method="post">
		<table style="background-color: lightgrey; margin: auto">
			<tr>
				<td>Enter User Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><input type="password" name="password" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Login" /></td>
				<td><a href="patient_register.jsp">Sign up</a></td>
			</tr>
		</table>
	</form>

</body>
</html>