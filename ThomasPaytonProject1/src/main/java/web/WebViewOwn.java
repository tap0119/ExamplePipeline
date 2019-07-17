package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDao;
import dao.UserDao2;
import models.Userdata;
import services.ReimbService;

/**
 * Servlet implementation class WebViewOwn
 */
public class WebViewOwn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ObjectMapper om = new ObjectMapper();
	private static Logger logger = Logger.getLogger(WebViewOwn.class);

//return list of reimbursments for current user
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); // get the current session
		
		if (session.equals(null)) {					//return to start if user is not logged in
			response.sendRedirect("http://localhost:8080/Project1/");
		}
		
		//get the uid and username attribute from the session
		Integer uid = (Integer) session.getAttribute("uid"); 
		String username = (String) session.getAttribute("username");
		
		Integer type = Integer.parseInt((String)request.getParameter("type"));
		Integer status = Integer.parseInt((String)request.getParameter("status"));
		
		response.setContentType("application/json");//specify send a json back
		PrintWriter out = response.getWriter();
		
		out.println(om.writeValueAsString(new ReimbService().viewSelect(type,status,uid)));
		//send the json back, with needed params
		
		logger.info("'" + username + "' : reimb returned, of type " + type + " and status " + status);
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
