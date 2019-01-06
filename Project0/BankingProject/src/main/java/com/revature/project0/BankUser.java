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
	private String role;
	private String isAccessible;
	private double accountBal;
	
	public BankUser() {
		super();
		cid = -1;
		// TODO Auto-generated constructor stub
	}

	public BankUser(int cid, String accountName, String password, String firstName, String lastName, String role, String isAccessible) {
		super();
		this.cid = cid;
		this.accountName = accountName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
		this.isAccessible = isAccessible;
		accountBal = 0.00;
	}
	
	public BankUser(BankUser user) {
		this.cid = user.getCid();
		this.accountName = user.getAccountName();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.isAccessible = user.getIsAccessible();
		this.accountBal = user.getAccountBal();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIsAccessible() {
		return isAccessible;
	}

	public void setIsAccessible(String isAccessible) {
		this.isAccessible = isAccessible;
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

	public void deposit(double amount) {
		accountBal += amount;
	}
	
	public void withdrawal(double amount) {
		accountBal -= amount;
	}

	@Override
	public String toString() {
		return "User: CID=" + cid + ", Username = " + accountName + ", " + firstName + " "
				+ lastName + " Role = " + role + ", Account Access =" + isAccessible + "";
	}//display for admin

	
	
	
	
}
