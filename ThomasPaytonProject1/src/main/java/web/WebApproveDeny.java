package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dao.ReimbDao2;
import dao.UserDao;
import dao.UserDao2;
import models.Userdata;
import services.ReimbService;
import services.UserService;

public class WebApproveDeny extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(WebApproveDeny.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session != null) {		//return to start if user is not logged
			String username = (String)session.getAttribute("username");
			if(new UserService().userIsMananger(username)){ //check that user is mananger
				
				
				//get parameters passed in
				String status = request.getParameter("ad");     //if mananager approved or denied
				String rid = request.getParameter("reimbList"); //the id of reimb selected
				
			    //get current date and time
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY hh:mm a");
				Date date = new Date();
				String time = dateFormat.format(date);
				
				//select the userdata from the current user, and get the user_id
				UserDao ud = new UserDao2();
				Userdata user = null;
				user=ud.selectUser(username);
				Integer uid = user.getEsr_user_id();
				
				if(new ReimbService().approveDeny(rid, uid, time, status)) {
					response.setContentType("text.html");
					response.sendRedirect("http://localhost:8080/Project1/mananger/manViewAll.html");
					  }		
				return;
			}//user is not mananger
		}//session is null
		
		session.invalidate(); //delete the current session
		response.sendRedirect("http://localhost:8080/Project1/"); 
		//return to home if invalid user tries to view
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
