package com.app.webpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;

@WebServlet("/voter_register")
public class RegisterVoterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDaoImpl userdao;

	public void init(ServletConfig config) throws ServletException {
		try {
			userdao = new UserDaoImpl();
		} catch (SQLException e) {
			throw new ServletException("Error in init" + getClass(), e);
		}

	}

	public void destroy() {
		try {
			userdao.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			String voterRegistration;
			LocalDate dob = LocalDate.parse(request.getParameter("dob"));
			User user = new User(request.getParameter("fname"), request.getParameter("lname"),
					request.getParameter("email"), request.getParameter("password"), Date.valueOf(dob));

			if (Period.between(dob, LocalDate.now()).getYears() > 21) {
				voterRegistration = userdao.voterRegistration(user);
				pw.print("<h3>" + voterRegistration + "</h3>");
				pw.print("<h5> Please <a href='login.html'>Login</a><h5>");
			} else
				pw.print("<h5 style='color:red;'>Age less than 21 Years <a href='login.html'>Home</a><h5>");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
