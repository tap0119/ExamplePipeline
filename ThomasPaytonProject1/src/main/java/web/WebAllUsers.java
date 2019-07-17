package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import services.ReimbService;
import services.UserService;


public class WebAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ObjectMapper om = new ObjectMapper();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); // get the current session
		if (session.equals(null)) {					//return to start if user is not logged in
			response.sendRedirect("http://localhost:8080/Project1/");
		}
		
		response.setContentType("application/json");//specify send a json back
		PrintWriter out = response.getWriter();
		
		out.println(om.writeValueAsString(new UserService().selectAllUser()));//return all users
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
