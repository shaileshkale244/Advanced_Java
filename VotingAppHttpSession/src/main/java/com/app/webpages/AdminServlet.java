package com.app.webpages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDaoImpl;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidateDaoImpl cdao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			HttpSession session = request.getSession();
			cdao = (CandidateDaoImpl) session.getAttribute("candidate_dao");
			pw.print("<h5>The top Two candiates are:</h5>");
			pw.print("<table>");
			cdao.getTopTwoCandidates().stream().forEach(k -> pw.print("<tr><td>" + k.getName() + "</td><td>" + k.getParty()+ "</td><td>" + k.getVotes()+ "</td>"));
			pw.print("</table><br>");
			pw.print("<h5>Party wise votes</h5>");
			pw.print("<table>");
			cdao.getPartyWiseVotes().forEach((k, v) -> pw.print("<tr><td>" + k + "</td><td>" + v + "</td>"));
			pw.print("</table>");
			pw.print("<h5><a href='logout'>Logout</a></h5>");
		} catch (Exception e) {
			throw new ServletException("Error in " + getClass(), e);
		}

	}

}
