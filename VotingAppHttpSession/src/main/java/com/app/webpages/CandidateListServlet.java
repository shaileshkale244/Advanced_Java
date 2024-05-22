package com.app.webpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDaoImpl;
import com.app.entities.User;

@WebServlet("/candidate_list")
public class CandidateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			HttpSession session = request.getSession();
			System.out.println(session);
			CandidateDaoImpl cdao = (CandidateDaoImpl) session.getAttribute("candidate_dao");
			User user = (User) session.getAttribute("user_info");

			if (user != null) {
				pw.print("<h3> Welcome for voting: " + user.getFirstName() + " " + user.getLastName() + "</h3><br>");

				pw.print("<form action=\"logout\" method=\"post\">\r\n" + "      <table border=\"\">\r\n"
						+ "        <tr style=\"padding: 10px;\">\r\n" + "          <th>Candidate Name</th>\r\n"
						+ "          <th>Party</th>\r\n" + "          <th>Choose Candidate</th>\r\n" + "        </tr>");
				cdao.getAllCandidate().stream()
						.forEach(c -> pw.print("<tr style=\"height: 40px;\">\r\n" + "          <td>" + c.getName()
								+ "</td>\r\n" + "          <td>" + c.getParty() + "</td>\r\n" + "          <td>\r\n"
								+ "            <input type=\"radio\" id=" + c.getId() + " name=\"candidate\" value="
								+ c.getId() + " />\r\n" + "          </td>\r\n" + "        </tr>"));
				pw.print("<tr >\r\n"
						+ "       <td colspan=\"3\"><input type=\"submit\" value=\"Vote\"style=\"width: 200px; height: 20px; text-align: center;\" /></td>\r\n"
						+ "        </tr>\r\n" + "      </table></form>");
			} else
				pw.print("<h4> No Cookies , Session Tracking Failed !!!!</h4>");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
