package com.revature.dao;

import java.util.List;

import com.revature.project0.BankUser;


public interface BankDao {
	public boolean insertBankCust(BankUser banker);
	public boolean insertBankCustomerProcedure(BankUser banker);
	public boolean updateBankCustomer();
	public BankUser getBankUser();
	public List<BankUser> getAllBankCusts();
	
	
}
