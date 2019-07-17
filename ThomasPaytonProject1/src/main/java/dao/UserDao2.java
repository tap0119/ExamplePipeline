package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Userdata;
import util.ConnectionUtil;

public class UserDao2 implements UserDao{

//Select a single user
	public Userdata selectUser(String username) {
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Userdata user = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			stmt = conn.createStatement();
			ps = conn.prepareStatement("SELECT * FROM ers_users WHERE ers_username = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new Userdata(
					rs.getInt(1), 		//ers_user_id
					rs.getString(2),  	//ers_username
					rs.getString(3),	//ers_password
					rs.getString(4),	//ers_firstname
					rs.getString(5),	//ers_lastname
					rs.getString(6),	//user_email
					rs.getInt(7)		//user_role_id
					);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}

		return user;
	}

//Return all userdata
	public List<Userdata> selectAllUser() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Userdata> users = new ArrayList();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ers_users");
			
			while(rs.next()) {
				users.add(new Userdata(
					rs.getInt(1), 		//ers_user_id
					rs.getString(2), 	//ers_username
					null,				//ers_password, not returned
					rs.getString(4),	//ers_firstname
					rs.getString(5),	//ers_lastname
					null,				//user_email, not returned
					rs.getInt(7)		//user_role_id
					));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}

		return users;
	}

	@Override
	public boolean createUser(Userdata newUser) {
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
	
		Statement stmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement ps2 = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			//get the user_id for the current username
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MAX(ers_user_id) FROM ers_users");
			rs.next();
			int top = rs.getInt(1) + 1;
			
			//insert into the ers_reimb table the data passed in
			stmt2 = conn.createStatement();
			ps2 = conn.prepareStatement("INSERT INTO ers_users VALUES (?,?,?,?,?,?,?)");
			
			ps2.setInt(1, top);									//ers_user_id
			ps2.setString(2, newUser.getEsr_username());	 	//ers_username
			ps2.setString(3, newUser.getEsr_password());		//ers_password
			ps2.setString(4, newUser.getEsr_firstname());		//ers_firstname
			ps2.setString(5, newUser.getEsr_lastname());		//ers_lastname
			ps2.setString(6, newUser.getUser_email());			//user_email
			ps2.setInt(7, newUser.getUser_role_id());			//user_role_id
			
			rs2 = ps2.executeQuery();
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(ps);
			close(rs2);
			close(stmt2);
			close(ps2);
		}
			
		
		return false;
	}

}
