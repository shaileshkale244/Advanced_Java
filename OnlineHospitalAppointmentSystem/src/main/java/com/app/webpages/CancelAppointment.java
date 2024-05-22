package com.app.webpages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.AppointmentDaoImpl;

@WebServlet("/cancel")
public class CancelAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			HttpSession session = request.getSession();
			AppointmentDaoImpl aptDao = (AppointmentDaoImpl) session.getAttribute("appointment_dao");
			PrintWriter pw = response.getWriter();
			String result = aptDao.cancelAppointment(Integer.parseInt(request.getParameter("appointmentId")));
			if (result != null) {
				RequestDispatcher rd = request.getRequestDispatcher("appointments_list");
				pw.print("<h4>" + result + "</h4>");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
