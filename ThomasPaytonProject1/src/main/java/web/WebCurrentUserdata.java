package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDao;
import dao.UserDao2;
import models.Userdata;

public class WebCurrentUserdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ObjectMapper om = new ObjectMapper();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); // get the current session
		
		if (session.equals(null)) {					//return to start if user is not logged in
			response.sendRedirect("http://localhost:8080/Project1/");
		}
		
		//get the uid and username attribute from the session
		String username = (String) session.getAttribute("username");
		
		//get the current userdata
		UserDao ud = new UserDao2();
		Userdata user = null;
		user=ud.selectUser(username);
		
		//don't return the password (hashed) or email
		user.setEsr_password(null);
		user.setUser_email(null);
		
		response.setContentType("application/json");//specify send a json back
		PrintWriter out = response.getWriter();//printwriter object
		
		System.out.println(user);
		
		out.println(om.writeValueAsString(user));
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
