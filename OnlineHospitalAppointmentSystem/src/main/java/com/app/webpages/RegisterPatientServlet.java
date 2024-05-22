package com.app.webpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.core.Patient;
import com.app.dao.PatientDaoImpl;

@WebServlet("/register_patient")
public class RegisterPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try {

			PrintWriter pw = response.getWriter();
			PatientDaoImpl patientDao = new PatientDaoImpl();

			String name = request.getParameter("fname") + " " + request.getParameter("lname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			LocalDate dob = LocalDate.parse(request.getParameter("dob"));

			Patient patient = new Patient(name, email, password, Date.valueOf(dob));

			String status = patientDao.patientRegistration(patient);
			// redirecting with the message
			pw.print("<h4>" + status + "</h4>");
			RequestDispatcher rd = request.getRequestDispatcher("/login.html");
			rd.include(request, response);
		} catch (Exception e) {
			throw new ServletException("do post", e);
		}
	}
}
