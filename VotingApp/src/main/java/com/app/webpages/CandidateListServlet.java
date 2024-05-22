package com.app.webpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.CandidateDao;
import com.app.dao.CandidateDaoImpl;

@WebServlet("/candidate_list")
public class CandidateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CandidateDao cdao;

	public void init(ServletConfig config) throws ServletException {
		try {
			cdao = new CandidateDaoImpl();
		} catch (SQLException e) {
			throw new ServletException("Error in " + getClass(), e);
		}
	}

	public void destroy() {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h1>Succesful</h1>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			Cookie[] cookies = request.getCookies();
			Optional<Cookie> user = Arrays.stream(cookies).filter(c -> c.getName().equals("user_details")).findFirst();
			pw.print("<h3> Welcome for voting: " + user.get().getValue() + "</h3><br>");
			pw.print("<form action=\"candidate_list\" method=\"post\">\r\n" + "      <table border=\"\">\r\n"
					+ "        <tr style=\"padding: 10px;\">\r\n" + "          <th>Candidate Name</th>\r\n"
					+ "          <th>Party</th>\r\n" + "          <th>Vote</th>\r\n" + "        </tr>");
			cdao.getAllCandidate().stream()
					.forEach(c -> pw.print("<tr style=\"height: 40px;\">\r\n" + "          <td>" + c.getName()
							+ "</td>\r\n" + "          <td>" + c.getParty() + "</td>\r\n" + "          <td>\r\n"
							+ "            <input type=\"radio\" id=" + c.getId() + " name=\"vote\" value=" + c.getId()
							+ " />\r\n" + "          </td>\r\n" + "        </tr>"));
			pw.print("<tr>\r\n" + "          <td><input type=\"submit\" value=\"Vote\" /></td>\r\n"
					+ "        </tr>\r\n" + "      </table>");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
