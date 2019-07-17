package web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.UserDao;
import dao.UserDao2;
import models.Reimbursment;
import models.Userdata;
import services.ReimbService;

@MultipartConfig
public class WebNewReimb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session.equals(null)) {					//return to start if user is not logged in
			response.sendRedirect("http://localhost:8080/Project1/");
		}
		
		response.setContentType("text.html");
		
		InputStream fileContent = null;
		try {
			if(request.getPart("file") != null) {
				//create the blob object
				
				  // String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
				
			    Part filePart = request.getPart("file"); // get the <input type="file" name="file">
			    fileContent = filePart.getInputStream();
			}
		}catch (javax.servlet.ServletException e){
			
		}
		
		//get the username of the current session
		String username = (String)session.getAttribute("username");

		//select the userdata from the current user, and get the user_id
		UserDao ud = new UserDao2();
		Userdata user = null;
		user=ud.selectUser(username);
		Integer uid = user.getEsr_user_id();
	    
	    
	    //get current date and time
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY hh:mm a");
		Date date = new Date();
		String time = dateFormat.format(date);
		
		//parse as doubles and ints from the parameters inputted
		Integer i = null;
		String s = null;
		Integer status = 1;
		Double amount = Double.parseDouble(request.getParameter("amount"));
		Integer type = Integer.parseInt(request.getParameter("type"));
	    
		//create the reimb object with data created above and passed in
		Reimbursment reimb = new Reimbursment(
			i, 								//id 	(populated when added to database)
			amount, 						//amount
			time,	 						//submitted time
			s,	 							//resolved time 	(null)
			request.getParameter("description"), //description
			fileContent, 					//blob picture
			uid,						 	//author id (from user model)
			i,	 							//resolver id		(null)
			status, 						//status, 1=pending
			type						  	//type   
		);

	   
		//go to reimb service with the newReimb method
	   if(new ReimbService().newReimb(reimb)) {
			   response.sendRedirect("http://localhost:8080/Project1/user/viewOwn.html");
	  }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
