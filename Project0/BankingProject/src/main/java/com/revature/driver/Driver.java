package com.revature.driver;

import com.revature.services.BankServices;
import com.revature.view.BankViewer;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(BankServices.getBankService().listAllBankCustomers());
		BankViewer niceView = new BankViewer();
		niceView.accountOptions();
	}

}
