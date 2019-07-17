package dao;

import static util.CloseStreams.close;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import models.Reimbursment;
import models.Userdata;
import util.ConnectionUtil;
import web.WebViewOwn;

public class ReimbDao2 implements ReimbDao{
	private static Logger logger = Logger.getLogger(ReimbDao2.class);

	
	//submit a new reimb
	@Override
	public boolean newReimb(Reimbursment Reimb) {
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
	
		Statement stmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement ps2 = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			//get the user_id for the current username
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MAX(reimb_id) FROM ers_reimbursment");
			rs.next();
			int top = rs.getInt(1) + 1;
			
			//insert into the ers_reimb table the data passed in
			stmt2 = conn.createStatement();
			ps2 = conn.prepareStatement("INSERT INTO ers_reimbursment VALUES (?,?,?,?,?,?,?,?,?,?)");
			ps2.setInt(1, top);
			ps2.setDouble(2, Reimb.getReimb_amount());
			ps2.setString(3, Reimb.getReimb_submitted());
			ps2.setString(4, "N/A");						 //resolved time
			ps2.setString(5, Reimb.getReimb_description());
			ps2.setBinaryStream(6, Reimb.getReimb_receipt());
			ps2.setInt(7, Reimb.getReimb_author());
			ps2.setInt(8, 0); 							//resolver id 
			ps2.setInt(9, Reimb.getReimb_status_id()); //set to pending
			ps2.setInt(10, Reimb.getReimb_type_id());
					
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
	
	//approve/deny a reimb
	@Override
	public boolean approveDeny(String rid, int uid, String time, String status) {
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			stmt = conn.createStatement();
			

			String sql = "UPDATE ers_reimbursment "
			+ "SET reimb_resolved = '" + time //update with params
			+"', reimb_resolver = " + uid
			+", reimb_status_id = " + status
			+ " WHERE reimb_id = " + rid; //select where the reimb id is
			
			logger.trace("Trying to update Reimb DB with: " + sql);

			int row = stmt.executeUpdate(sql); //execute query
			
			logger.trace(row + " row updated of reimb table - Mananger ID: "+ uid + 
					" updated reimb id: " + rid + " with " + time +", " + status);
	
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(ps);
		}

		return false;
	}

	
	//view select reimb, by type, status, or username
	@Override
	public List<Reimbursment> viewSelect(int type, int status, int uid) {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		Statement stmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement ps2 = null;

		List<Reimbursment> reimbSort = new ArrayList();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			//USER select
			//if user string was 0, give back all users 
			//don't add 'AND reimb_author= id' to statement
			String user = "";
			if (!(uid == 0)){
				user = "AND reimb_author = " + uid + " ";
			}
			

			stmt = conn.createStatement();
			
			//TYPE select
			String typ = null;
			if(type == 0) {//if type is 0 return all types
				typ = "1,2,3,4";
			}else {
				typ = Integer.toString(type);
			}
			
			//STATUS select
			String stat = null;
			if(status == 0) {//if status is 0 return all statuses
				stat = "1,2,3";
			}else {
				stat = Integer.toString(status);
			}
			
			String sql = "SELECT * FROM ers_reimbursment WHERE reimb_type_id IN ("
					+ typ + ") AND reimb_status_id IN (" + stat + ")"  + user;
			
			logger.trace("Attempt to select reimbursment: " + sql);
			//execute the query 
			rs = stmt.executeQuery(sql);
			//populate the array of reimb objects
			while(rs.next()) {
				reimbSort.add(new Reimbursment(
					rs.getInt(1), 		//reimb_id
					rs.getDouble(2), 	//reimb_amount
					rs.getString(3),	//reimb_submitted
					rs.getString(4),	//reimb_resolved
					rs.getString(5),	//reimb_description
					rs.getBinaryStream(6),	//reimb_recipt
					rs.getInt(7),		//reimb_author
					rs.getInt(8),       //reimb_resolver
					rs.getInt(9),       //reimb_status_id
					rs.getInt(10)		//reimb_type_id
					));
				//get the blob
				InputStream input = rs.getBinaryStream(6);
				if (input != null){
					
					//create a new file from the blob with reimb_id as its name (unique name)
					File file = new File(
					"C:\\Users\\thoma\\Dropbox\\Revature\\Week 4 - Servlets\\Project1\\img\\" + rs.getInt(1) +".png");
					
					if(file.exists()) {//check if file already exists
						logger.trace("Retrieved existing file: " + file.getAbsolutePath());
					}else {
					//if file does not exist write it using the blob	
						FileOutputStream output = new FileOutputStream(file);
						byte[] buffer = new byte[1024];
						while (input.read(buffer) > 0){
							output.write(buffer);
						}
						logger.trace("New file at: " + file.getAbsolutePath());
					}
					
					
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(rs2);
			close(stmt2);
			close(stmt);
			close(ps2);
		}

		return reimbSort;
	}

}
