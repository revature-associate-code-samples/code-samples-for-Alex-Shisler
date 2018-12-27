package com.revature.dao;


import java.util.ArrayList;

import com.revature.user.Reimbursement;

public interface ReimbursementDAO {
	public boolean insertReimbursements(Reimbursement reimburse);

	public Reimbursement deleteReimbursement(int id);

	public Reimbursement searchReimbursements(int id);
	
	public ArrayList<Reimbursement> getReimbursList(int E_ID);//get a specific user's reimbursements
	
	public boolean updateReimbursement(int id, String status);//usable only by manager
	
	
}
