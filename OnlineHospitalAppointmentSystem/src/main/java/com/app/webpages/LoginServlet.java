package com.app.webpages;

import static com.app.utils.DBConnection.closeConnection;
import static com.app.utils.DBConnection.openConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.core.Patient;
import com.app.dao.AppointmentDaoImpl;
import com.app.dao.DoctorDaoImpl;
import com.app.dao.PatientDaoImpl;

//web.xml file through db connection
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AppointmentDaoImpl aptDao;
	private PatientDaoImpl patientDao;
	private DoctorDaoImpl docDao;

	@Override
	public void init() throws ServletException {
		ServletConfig config = getServletConfig();
		try {
			openConnection(config.getInitParameter("db_url"), config.getInitParameter("user_name"),
					config.getInitParameter("password"));
			aptDao = new AppointmentDaoImpl();
			patientDao = new PatientDaoImpl();
			docDao = new DoctorDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Error in " + getClass(), e);
		}
	}

	public void destroy() {
		try {
			aptDao.cleanup();
			patientDao.cleanUp();
			docDao.cleanup();
			closeConnection();
		} catch (SQLException e) {
			System.out.println("Error in destroy!!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		session.setAttribute("patient_dao", patientDao);
		session.setAttribute("appointment_dao", aptDao);
		session.setAttribute("doctor_dao", docDao);

		try {
			PrintWriter pw = response.getWriter();
			String emailId = request.getParameter("emailId");
			String password = request.getParameter("password");
			Patient patient = patientDao.signIn(emailId, password);
			if (patient == null) {
				pw.print("<h4>Sorry UserName or Password Error!</h4>");
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.include(request, response);
			} else {
				session.setAttribute("patient_info", patient);
				RequestDispatcher rd = request.getRequestDispatcher("appointments_list");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			throw new ServletException("Error in " + getClass(), e);
		}
	}

}
