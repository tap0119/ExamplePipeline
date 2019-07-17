package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dao.UserDao;
import dao.UserDao2;
import models.Userdata;
import services.UserService;
import util.HtmlTemplate;

import services.UserService;

/**
 * Servlet implementation class WebLogin
 */
public class WebLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(WebLogin.class);
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		//hash the password
		Integer hash = 17;
		hash = (hash * 31 + password.hashCode());
		hash = (hash * 31 + username.hashCode());
		
		
		String hashString = hash.toString();
		
		if(new UserService().confirmLogin(username,hashString)) {//validate they are a valid user
			
			//select the userdata from the current user, returns an object
			UserDao ud = new UserDao2();
			Userdata user = null;
			user=ud.selectUser(username);
			
			
			HttpSession session = request.getSession(); //gets the session of user 
			session.setAttribute("username", username); //set session attribute to session
			session.setAttribute("uid", user.getEsr_user_id());
			session.setAttribute("role", user.getUser_role_id());
			
			if(user.getUser_role_id() == 1) {
				logger.info(username + " :Logged in successfully");
				RequestDispatcher rq = request.getRequestDispatcher("user/home.html"); //go to user/home.html if match
				rq.forward(request, response);
			}else {
				logger.info("Mananger" + username + " :Logged in successfully");
				RequestDispatcher rq = request.getRequestDispatcher("mananger/manHome.html"); //go to user/home.html if match
				rq.forward(request, response);
				
			}
		
			
			
		}else {//if username and password don't match, send alert and redirect in html
			RequestDispatcher rq = request.getRequestDispatcher("incorrectLogin.html"); 
			rq.forward(request, response);
		    logger.warn(username + " : suffered a failed login attempt");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
