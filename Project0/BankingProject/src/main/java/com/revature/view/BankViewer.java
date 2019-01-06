package com.revature.view;

import java.util.ArrayList;

import com.revature.project0.BankUser;
import com.revature.services.BankServices;

public class BankViewer {
	
	private BankServices services;
	
	public BankViewer() 
	{
	}
	
	public void displayLogo() {
		for(int i = 0; i < 32; i++) {
			System.out.print("*");
		}
		System.out.println("\nWelcome to The Bank of Revature!");
		for(int i = 0; i < 32; i++) {
			System.out.print("*");
		}
		System.out.println();
	}
	
	public void signInOptions() {
		System.out.println("Please make a selection.");
		System.out.println("1. Create an account.\n2.Login.\n3.Exit.");
	}
	
	public void namePrompt() {
		System.out.println("Please enter your username: ");
	}
	
	public void passwordPrompt() {
		System.out.println("Please enter your password: ");
	}
	
	public void firstNamePrompt() {
		System.out.println("Please enter your first name.");
	}
	
	public void lastNamePrompt() {
		System.out.println("Please enter your last name");
	}
	
	public void accountConfirmation() {
		System.out.println("Account has been created, once an admin grants acccess you will be able to access your account.");
	}
	public void accountOptions(BankUser user) {
		System.out.println("Hello " + user.getFirstName() + " " + user.getLastName() + ".");
		System.out.println("Please make a selection");
		System.out.println("1.Deposit\n2.Withdrawal.\n3.Check Balance.\n4.Logout.");
	}
	
	public void adminAcctOptions(BankUser user) {
		System.out.println("Hello " + user.getFirstName() + " " + user.getLastName() + ".");
		System.out.println("Please make a selection");
		System.out.println("1.Deposit\n2.Withdrawal.\n3.Check Balance.\n4.Grant Account Access.\n5.Logout.");
	}
	
	public void depositDisplay() {
		System.out.println("How much would you like to deposit?");
	}
	public void withdrawalDisplay() {
		System.out.println("How much would you like to withdrawal?");
	}
	public void displayBalance(double balance) {
		
		System.out.print("Current balance is : $");
		System.out.printf("%.2f", balance);
		System.out.println();
	}
	
	public void logOutView() {
		System.out.println("You are now logged out");
	}
	public void exitView() {
		System.out.println("Goodbye");
	}
	public void displayUsers(ArrayList<BankUser> table) {
		for(int i = 0; i < table.size(); i++)
			System.out.println(table.get(i).toString());
		System.out.println("Please select the CID of the client you would like to modify access for.");
	}
	
	public void confirmUserChange(BankUser user) {
		if(user.getIsAccessible().equals("n"))
			System.out.println("Allow access for CID: " + user.getCid()+ ", " + user.getFirstName() + " " + user.getLastName() + "?");
		else
			System.out.println("Freeze access for CID: " + user.getCid()+ ", " + user.getFirstName() + " " + user.getLastName() + "?");
	}
	
}
