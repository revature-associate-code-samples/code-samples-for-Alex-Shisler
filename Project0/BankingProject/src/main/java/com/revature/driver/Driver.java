package com.revature.driver;

import com.revature.dao.BankImpl;
import com.revature.services.BankServices;
import com.revature.view.BankViewer;

public class Driver {

	public static void main(String[] args) {
		BankServices bank = BankServices.getBankService();
		bank.start();
	}

}
