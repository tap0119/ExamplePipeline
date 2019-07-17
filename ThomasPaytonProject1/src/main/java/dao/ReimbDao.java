package dao;

import java.util.List;

import models.Reimbursment;

public interface ReimbDao {
	
	//sumbit new reimb
	public boolean newReimb(Reimbursment Reimb);
	
	//view reimb by user, type or status
	public List<Reimbursment> viewSelect(int type, int status, int uid);
	
	//mananger approve/deny reimb
	public boolean approveDeny(String rid, int uid, String time, String status);
}
