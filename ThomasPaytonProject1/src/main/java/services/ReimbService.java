package services;

import java.util.List;

import dao.ReimbDao;
import dao.ReimbDao2;
import models.Reimbursment;

public class ReimbService {

private ReimbDao reimb = null;

//submit a new reimb
	public boolean newReimb(Reimbursment reimbObj) {
		reimb = new ReimbDao2();
		return reimb.newReimb(reimbObj);
	}

//view select reimb, by type, status or username
	public List<Reimbursment>  viewSelect(int type, int status, int uid) {
		reimb = new ReimbDao2();
		return reimb.viewSelect(type, status, uid);
	}
	
//Mananger approve deny
	public boolean approveDeny(String rid, int uid, String time, String status) {
		reimb = new ReimbDao2();
		return reimb.approveDeny(rid, uid, time, status);
	}

}
