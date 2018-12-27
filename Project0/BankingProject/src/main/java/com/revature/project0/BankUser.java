package com.revature.project0;

/*
 * Contains User info
 */

public class BankUser {
	private int cid;
	private String accountName;
	private String firstName;
	private String lastName;
	private String password;
	private double accountBal;
	
	public BankUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankUser(int cid, String accountName, String firstName, String lastName, String password,
			double accountBal) {
		super();
		this.cid = cid;
		this.accountName = accountName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.accountBal = accountBal;
	}
	
	

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}

	@Override
	public String toString() {
		return "\n-------------------------------\n CID = " + cid + "\n Usename = " + accountName + "\n First Name = " + firstName + "\n Last Name = "
				+ lastName + "\n Password =" + password + "\n Account Balance = $" + accountBal + "\n";
	}
	
	
	
}
