package com.revature.services;

import java.util.List;

import com.revature.dao.BankImpl;
import com.revature.project0.BankUser;


/*
 * Controller 
 */
public class BankServices {


	private static BankServices bankService;
	
	private BankServices() {
		super();
	}
	
	public static BankServices getBankService() {
		if(bankService == null) {
			bankService = new BankServices();
		}
		return bankService;
	}
	
	public BankUser getBankerDetails() {
		return BankImpl.getBankDao().getBankUser();
	}
	
	public List<BankUser> listAllBankCustomers(){
		return BankImpl.getBankDao().getAllBankCusts();
	}
	
}
