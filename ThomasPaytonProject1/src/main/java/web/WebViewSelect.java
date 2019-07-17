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

import services.ReimbService;
import services.UserService;

public class WebViewSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ObjectMapper om = new ObjectMapper();
	private static Logger logger = Logger.getLogger(WebViewOwn.class);
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {		//return to start if user is not logged
				
				Integer type = Integer.parseInt((String)request.getParameter("type"));
				Integer status = Integer.parseInt((String)request.getParameter("status"));
				Integer uid = Integer.parseInt((String)request.getParameter("uid"));

				response.setContentType("application/json");//specify send a json back
				PrintWriter out = response.getWriter();//printwriter object
				
				out.println(om.writeValueAsString(new ReimbService().viewSelect(type, status, uid)));
				//send the json back
				
				logger.info( session.getAttribute("username") +" viewed selected reimb");
				return;
		}//session is null
		
		session.invalidate(); //delete the current session
		response.sendRedirect("http://localhost:8080/Project1/"); 
		//return to home if invalid user tries to view
	}

}
