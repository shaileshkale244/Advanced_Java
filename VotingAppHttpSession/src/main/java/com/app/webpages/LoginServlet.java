package com.app.webpages;

import static com.app.utils.DBConnection.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDaoImpl;
import com.app.dao.UserDaoImpl;
import com.app.entities.User;

//@WebServlet(value = "/login", loadOnStartup = 1)
//not required as there is web.xml file is holding the entry info 
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl udao;
	private CandidateDaoImpl cdao;

	@Override
	public void init() throws ServletException {
		ServletConfig config = getServletConfig();
		try {
			// Database connection
			openConnection(config.getInitParameter("db_url"), config.getInitParameter("user_name"),
					config.getInitParameter("password"));

			udao = new UserDaoImpl();
			cdao = new CandidateDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Error in " + getClass(), e);
		}

	}

	public void destroy() {
		try {
			udao.cleanUp();
			cdao.cleanup();
			closeConnection();
		} catch (SQLException e) {
			System.out.println("Error in destroy!!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try (PrintWriter pw = response.getWriter()) {
			// getting the session
			HttpSession session = request.getSession();

			String emailId = request.getParameter("emailId");
			String password = request.getParameter("password");
			User user = udao.signIn(emailId, password);
			if (user == null) {
				pw.write("<h3>Wrong Email or Password!!   <a href='login.html'> Please Click to Retry</a> </h3>");
			} else {
				session.setAttribute("user_info", user);
				session.setAttribute("user_dao", udao);
				session.setAttribute("candidate_dao", cdao);

				// after login events
				if (user.getRole().equals("voter")) {
					if (user.isStatus())
						response.sendRedirect("logout");
					else
						response.sendRedirect("candidate_list");
				} else if (user.getRole().equals("admin"))
					response.sendRedirect("admin");
			}

		} catch (Exception e) {
			throw new ServletException("Error in " + getClass(), e);
		}
	}

}
