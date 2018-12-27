package com.revature.view;

public class BankViewer {
	
	
	public BankViewer() {
		
	}
	
	public void displayLogo() {
		System.out.println("Welcome to Bank of Cash!");
	}
	
	public void signInOptions() {
		System.out.println("Please make a selection.");
		System.out.println("1. Create an account.\n2.Log In.");
	}
	
	public void accountOptions() {
		System.out.println("Please make a selection");
		System.out.println("1.Deposit\n2.Withdrawal.\n3.Check Balance.\n4.Logout.");
	}
	
	public void depositDisplay() {
		System.out.println("How much would you like to deposit?");
	}
	public void withdrawalDisplay() {
		System.out.println("How much would you like to withdrawal?");
	}
	
}
