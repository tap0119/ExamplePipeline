package junit;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import models.Reimbursment;
import services.ReimbService;
import services.UserService;
import util.ConnectionUtil;

public class junitTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		Connection conn = ConnectionUtil.getConnection();
	}
	
	@AfterClass 
	public static void tearDownAfterClass() {
	}
	
	
	//-------------TEST CASES-------------------  
	@Test
	public void isManangerTrue() {
		UserService US = new UserService();
		assertTrue(US.userIsMananger("Man"));
	}
	
	@Test
	public void isManangerFalse() {
		UserService US = new UserService();
		assertFalse(US.userIsMananger("Not a real user"));
	}
	
	@Test
	public void selectAllReimb() {
		
		ReimbService RS = new ReimbService();
		
		//get a returned list of all reimb
		List<Reimbursment> RSall = RS.viewSelect(0,0,0);
		
		//get the first object in the returned list
		Reimbursment RSfirst = RSall.get(0);
		
		//get first id of the returned list
		int id = RSfirst.getReimd_id();
		
		//first id of the returned list should be 1
		assertEquals(id, 1);
	}
	
	@Test
	public void selectPendingReimb() {
		
		ReimbService RS = new ReimbService();
		
		//get a returned list of all pending reimb
		List<Reimbursment> RSall = RS.viewSelect(0, 1, 0);
		
		//get the first object in the returned list
		Reimbursment RSfirst = RSall.get(0);
		
		//get the status id of the first object
		int id = RSfirst.getReimb_status_id();
		
		//the status id should be 1 (pending)
		assertEquals(id, 1);
	}



}
