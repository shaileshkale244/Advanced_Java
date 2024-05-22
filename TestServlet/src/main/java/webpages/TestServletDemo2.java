package webpages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class TestServletDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  public void init() throws ServletException{
	  System.out.println("in init"+getClass());
  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw =response.getWriter()){
			pw.write("<h2> This is the Second Servlet Page by me</h2>"
					+ LocalDate.now());
		}
		
	}



}
