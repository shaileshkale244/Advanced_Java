package com.app.webpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.core.Appointment;
import com.app.core.AppointmentStatus;
import com.app.dao.AppointmentDaoImpl;
import com.app.dao.DoctorDaoImpl;

@WebServlet("/schedule_appointment")
public class SchedulApoointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// doget for form generation
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();

		int patientId = Integer.parseInt(request.getParameter("patientId"));
		DoctorDaoImpl docDao = (DoctorDaoImpl) session.getAttribute("doctor_dao");

		try {
			pw.print("  <caption> Schedule New Appointment</caption>"
					+ "<form method='POST' action='schedule_appointment'>\r\n"
					+ "<table style=\"background-color: lightgrey; margin: auto\">\r\n"
					+ "			<tr>"
					
					+ "       <td> <label for='patientId'>Patient ID:</label></td>\r\n"
					+ "       <td> <input type='number' id='patientId' name='patientId' value=" + patientId
					+ " required hidden ></td></tr> \r\n"

					+ "   <tr>  <td>   <label for='doctorId'>Doctor:</label></td>\r\n"
					+ "      <td>  <select  id='doctorId' name='doctorId' required>\r\n");

			docDao.getAllDoctorDetails().stream()
					.forEach(doc -> pw.print("<option value=" + doc.getId() + ">" + doc.getName() + "</option>"));

			pw.print("</select></td></tr> <tr> "
					+ "<td><label for='appointmentDate'>Appointment Date:</label></td>\r\n"
					+ "      <td>  <input type='date' id='appointmentDate' name='appointmentDate'  min = " + LocalDate.now()
					+ " required></td></tr>\r\n"

					+ "     <tr> <td>  <label for='appointmentTime'>Appointment Time:</label></td>\r\n"
					+ "    <td>    <input type='time' id='appointmentTime' name='appointmentTime'  min = " + LocalTime.now()
					+ " required></td></tr>\r\n"

					+ "   <tr>  <td>   <label for='durationMinutes'>Duration (minutes):</label></td>\r\n"
					+ "     <td>   <select  id='durationMinutes' name='durationMinutes' required>\r\n"
					+ "            <option value=30>30</option>\r\n" + "            <option value=45>45</option>\r\n"
					+ "            <option value=60>60</option>\r\n" + "            </select>    </td> </tr>   \r\n"
					+ "        \r\n"

					+ "   <tr>   <td>  <label for='status'>Status:</label></td>\r\n"
					+ "      <td>  <input type='text' id='status' name='status' value='SCHEDULED' required hidden></td>\r\n"
					+ "            \r\n" + "       </tr>\r\n"

					+ "    <tr> <td>   <input type='submit' value='Schedule Appointment'></td>\r\n" + " </tr>  </table> </form>");
		} catch (SQLException e) {

			throw new ServletException("Error in " + getClass(), e);
		}
	}

	// dopost for form submission
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try {
			PrintWriter pw = response.getWriter();
			HttpSession session = request.getSession();
			// Patient patient = (Patient) request.getAttribute("patient_info");
			AppointmentDaoImpl aptDao = (AppointmentDaoImpl) session.getAttribute("appointment_dao");

			int patientId = Integer.parseInt(request.getParameter("patientId"));
			int doctorId = Integer.parseInt(request.getParameter("doctorId"));
			LocalDate appointmentDate = LocalDate.parse(request.getParameter("appointmentDate"));
			LocalTime appointmentTime = LocalTime.parse(request.getParameter("appointmentTime"));
			int durationMinutes = Integer.parseInt(request.getParameter("durationMinutes"));
			String status = request.getParameter("status");
			Appointment apt = null;

			// valid entry checking for date and time
			if (appointmentDate.isAfter(LocalDate.now())
					|| (appointmentDate.isEqual(LocalDate.now()) && appointmentTime.isAfter(LocalTime.now()))) {

				apt = new Appointment(patientId, doctorId, Date.valueOf(appointmentDate), Time.valueOf(appointmentTime),
						durationMinutes, AppointmentStatus.valueOf(status));
			}

			String result = aptDao.scheduleAppointment(apt);
			RequestDispatcher rd = request.getRequestDispatcher("appointments_list");
			pw.print("<h4>" + result + "</h4>");
			rd.include(request, response);

		} catch (Exception e) {
			throw new ServletException("Error in " + getClass(), e);
		}
	}
}
