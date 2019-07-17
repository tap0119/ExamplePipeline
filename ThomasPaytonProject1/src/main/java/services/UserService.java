package services;

import java.util.List;

import dao.ReimbDao2;
import dao.UserDao;
import dao.UserDao2;
import models.Reimbursment;
import models.Userdata;

public class UserService {
	
//VERIFY USERNAME AND PASSWORD MATCH
	public boolean confirmLogin(String username, String hash) {
		UserDao ud = new UserDao2();
		Userdata user = null;
		
		//get a user userdata object and continue if username is valid
		if((user=ud.selectUser(username))!=null){
			
			//if password passed in matches password on database
			if(user.getEsr_password().equals(hash)) {
				return true;
			}
		}
		return false;
	}
	
//VERIFY USERNAME EXISTS
	public boolean usernameExists(String username) {
		//if user userdata object returns something return true
		if(new UserDao2().selectUser(username)!=null) {
			return true;
		}
		return false;
	}
	
//verify if user is a mananger (type = 2)
	public boolean userIsMananger(String username) {

		//get the username of the current session, and the user object
		UserDao ud = new UserDao2();
		Userdata user = null;
		user=ud.selectUser(username);
		
		//return to start if user is not a mananger
		try {
			if(user.getUser_role_id() !=2) {
				return false;
			}else {
				return true;
			}
		}catch (NullPointerException e) {//if no user is logged in
			return false;
		}
	}
	
//Return all users
	public List<Userdata> selectAllUser() {
		UserDao user = new UserDao2();
		return user.selectAllUser();
	}

}
