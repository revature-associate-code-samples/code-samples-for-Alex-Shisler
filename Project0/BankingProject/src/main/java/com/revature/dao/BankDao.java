package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.project0.BankUser;


public interface BankDao {
	public boolean insertBankCust(BankUser banker);
	public boolean insertBankCustomerProcedure(BankUser banker);
	public BankUser Login(String user, String password);
	public BankUser getBankUser();
	public ArrayList<BankUser> getAllBankCusts();
	public BankUser searchBankUser(String user);

}
