package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDao2;
import models.Userdata;


public class WebCreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get all the parameters
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		String firstname = (String)request.getParameter("firstname");
		String lastname = (String)request.getParameter("lastname");
		String email = (String)request.getParameter("email");
		Integer role = Integer.parseInt((String)request.getParameter("role"));
		
		UserDao u = new UserDao2();
		Userdata user = null;
		
		try {//check if user already exists, and if so tell mananger and stop
			if(u.selectUser(username) != null) {
				   response.sendRedirect("http://localhost:8080/Project1/mananger/NewUserExists.html");
			}
		}catch(NullPointerException e) {
			
		}
				
		
		//hash the password
		Integer hash = 17;
		hash = (hash * 31 + password.hashCode());
		hash = (hash * 31 + username.hashCode());
		
		String hashString = hash.toString();
		
		
		Userdata newUser = null;
		newUser = new Userdata(

				0,			 		//ers_user_id, added when added to database
				username,	  		//ers_username
				hashString,			//ers_password
				firstname,			//ers_firstname
				lastname,			//ers_lastname
				email,				//user_email
				role				//user_role_id
				
				);
		
		UserDao ud = new UserDao2();
		
		if(ud.createUser(newUser)) {
			//if new user created successfully tell mananger and return to manHome.html
			   response.sendRedirect("http://localhost:8080/Project1/mananger/NewUserCreated.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
