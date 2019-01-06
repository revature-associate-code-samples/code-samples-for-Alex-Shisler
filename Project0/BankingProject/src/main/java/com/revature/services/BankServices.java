package com.revature.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.BankImpl;
import com.revature.project0.BankUser;
import com.revature.view.BankViewer;

/*
 * Controller 
 */
public class BankServices {

	private Scanner reader;
	private BankViewer viewer = new BankViewer();
	private BankUser actedUser;
	private int nonUserID = -1;
	private boolean logged = false;
	private boolean exit = false;
	private static BankServices bankService;
	private BankImpl data;
	/*--------------------Variables---------------------*/
	private BankServices() {
		super();
		data = data.getBankDao();
		reader = new Scanner(System.in);
	}

	/*-------------------------------------------------*/
	public static BankServices getBankService() {
		if (bankService == null) {
			bankService = new BankServices();
		}
		return bankService;
	}

	public BankUser getBankerDetails() {
		return data.getBankUser();
	}

	public List<BankUser> listAllBankCustomers() {
		return data.getAllBankCusts();
	}
	/*---------------------Getters---------------------------*/

	public void start() {

		while (!exit) {
			if (!logged) {
				viewer.displayLogo();
				viewer.signInOptions();
				loginMenu();
			} // loginOptions
			else {
				if (data.getBankUser().getRole().equals("admin"))
					adminAcctOptions();
				else
					accountOptions();
			} // accountOptions
		} // loop

	}

	private void loginMenu() {
		int choice = reader.nextInt();
		switch (choice) {
		case 1:
			accountCreation();
			break;

		case 2:
			logIn();
			break;
		case 3:
			exit();
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}

	private void exit() {
		// TODO Auto-generated method stub
		exit = true;
		viewer.exitView();
	}

	private void accountCreation() {
		BankUser newUser = new BankUser();
		String temp;
		viewer.namePrompt();
		try {
			while (!data.isUsernameUnique(temp = reader.next())) {
				System.out.println("Username " + temp + " is already taken, please try a different one.");
			}
			newUser.setAccountName(temp);
			viewer.passwordPrompt();
			newUser.setPassword(reader.next());
			viewer.firstNamePrompt();
			newUser.setFirstName(reader.next());
			viewer.lastNamePrompt();
			newUser.setLastName(reader.next());
		} catch (InputMismatchException e) {
			System.out.println("Invalid input provided.");
		}
		newUser.setRole("user");
		newUser.setIsAccessible("n");
		if (data.insertBankCust(newUser))
			viewer.accountConfirmation();
	}// accountCreation process

	private void logIn() {
		try {
			viewer.namePrompt();
			String user;
			String pass;

			user = reader.next();
			viewer.passwordPrompt();
			pass = reader.next();
			data.Login(user, pass).getCid();

			if (data.getBankUser().getCid() != -1 && data.getBankUser().getIsAccessible().equals("y")) {
				logged = true;
			} else if (data.getBankUser().getCid() == -1)
				System.out.println("Invalid login credentials, please try again");
			else
				System.out.println("Still waiting on admin access, please try again later.");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input type.");
		}
	}// logIn

	private void accountOptions() {

		viewer.accountOptions(data.getBankUser());
		switch (reader.nextInt()) {
		case 1:
			deposit();
			break;
		case 2:
			withdrawal();
			break;
		case 3:
			checkBalance();
			break;
		case 4:
			logOut();
			break;
		default:
			System.out.println("Invalid input");
			break;
		}

	}// accountOptions

	private void adminAcctOptions() {
		viewer.adminAcctOptions(data.getBankUser());
		switch (reader.nextInt()) {
		case 1:
			deposit();
			break;
		case 2:
			withdrawal();
			break;
		case 3:
			checkBalance();
			break;
		case 4:
			grantAccess();
			break;
		case 5:
			logOut();
			break;
		default:
			System.out.println("Invalid input");
			break;
		}

	}

	private void deposit() {
		viewer.depositDisplay();
		double amount;
		try {
			amount = reader.nextDouble();
			data.deposit(amount);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Invalid data type");
			
		}//try catch
	}//deposit

	private void withdrawal() {
		viewer.withdrawalDisplay();
		double amount;
		try {
			amount = reader.nextDouble();
			data.withdrawal(amount);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Invalid data type");
		} // try-catch
	}// withdrawal

	private void checkBalance() {
		data.getAccountInfo();
		viewer.displayBalance(data.getBankUser().getAccountBal());
	}//checkBalance()

	private void logOut() {
		viewer.logOutView();
		logged = false;
	}

	private void grantAccess() {
		ArrayList<BankUser> table = data.getAllBankCusts();
		viewer.displayUsers(table);
		int nonUserID = reader.nextInt();

		for (int i = 0; i < table.size(); i++) {
			if (table.get(i).getCid() == nonUserID)
				actedUser = new BankUser(table.get(i));
		}
		viewer.confirmUserChange(actedUser);// expensive
		switch (reader.next()) {
		case "yes":
			data.grantAccess(nonUserID);
			break;
		case "no":
			break;
		default:
			System.out.println("Invalid input.");
		}

	}
	/*----------------------Methods-------------------------*/
}
