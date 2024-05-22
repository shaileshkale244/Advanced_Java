package com.app.webpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;

@WebServlet(value = "/login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl udao;

	@Override
	public void init() throws ServletException {
		try {
			udao = new UserDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Error in " + getClass(), e);
		}

	}

	public void destroy() {
		try {
			udao.cleanUp();
		} catch (SQLException e) {
			System.out.println("Error in destroy!!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try (PrintWriter pw = response.getWriter()) {
			String emailId = request.getParameter("emailId");
			String password = request.getParameter("password");
			User user = udao.signIn(emailId, password);
			if (user == null) {
				pw.write("<h3>Wrong Email or Password!!   <a href='login.html'> Please Click to Retry</a> </h3>");

			} else {
				//cookie generation
				Cookie c1 =new Cookie("user_details",user.toString());
				response.addCookie(c1);
				
				//after login events
				if (user.getRole().equals("voter")) {
					if (user.isStatus())
						response.sendRedirect("logout");
					else
						response.sendRedirect("candidate_list");
				}
				if (user.getRole().equals("admin"))
					response.sendRedirect("admin");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
