package com.app.webpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.core.Patient;
import com.app.dao.AppointmentDaoImpl;
import com.app.dao.DoctorDaoImpl;

 
@WebServlet("/appointments_list")
public class AppointmentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			HttpSession session = request.getSession();
			AppointmentDaoImpl aptDao = (AppointmentDaoImpl) session.getAttribute("appointment_dao");
			Patient patient =(Patient)session.getAttribute("patient_info");
			DoctorDaoImpl docDao = (DoctorDaoImpl)session.getAttribute("doctor_dao");
			PrintWriter pw = response.getWriter();
			LocalDate today = LocalDate.now();
			LocalTime now = LocalTime.now();
			
			pw.print("<h4>Welcome "+patient.getName()+"</h4>");			
			pw.print("<a href='schedule_appointment?patientId="+patient.getId()+"'>Schedule New Appointment</a> ");
			
			pw.print("<h5>Appointment List: </h5>  ");			
			pw.print("<table border=\"1\" style=\"background-color: lightgrey; margin: auto\">\r\n"
					+ "<tr>\r\n"
					+ "<th>Name of Doctor</th>\r\n"
					+ "<th>Appointment Date</th>\r\n"
					+ "<th>Appointment Time</th>\r\n"
					+ "<th>Action</th>\r\n"
					+ "</tr>");
			
			aptDao.getAppointments(patient.getId()).stream()
		//filtering all appointments which are upcoming 
			.filter(a -> {
                LocalDate appointmentDate = a.getAppointmentDate().toLocalDate();
                LocalTime appointmentTime = a.getAppointmentTime().toLocalTime();
                return appointmentDate.isAfter(today) ||
                        (appointmentDate.isEqual(today) && appointmentTime.isAfter(now));
            })
			//printing the appointments into table
			.forEach(apt->{
				try {
					pw.print("<tr>\r\n"
							+ "<td>"+docDao.getDoctorName(apt.getDoctorId()) +"</td>\r\n"
							+ "<td>"+apt.getAppointmentDate()+"</td>\r\n"
							+ "<td>"+apt.getAppointmentTime()+"</td>\r\n"
							+ "<td>\r\n"
							+ "<form method=\"POST\" action='cancel'>\r\n"
							+ "<input type=\"hidden\" name=\"appointmentId\" value='"+apt.getId()+"'>\r\n"
							+ "<input type=\"submit\" value=\"Cancel\">\r\n"
							+ "</form>\r\n"
							+ "</td>\r\n"
							+ "</tr>\r\n"
							+ "<tr>"					
							);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			pw.print("</table>");
			pw.print("<br><h4><a href='logout'>Log me Out </a></h4>");
			
		}catch (Exception e) {
			throw new ServletException("Error in " + getClass(), e);
		}
	}

}
