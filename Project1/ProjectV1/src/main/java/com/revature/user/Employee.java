package com.revature.user;

import java.util.ArrayList;

public class Employee {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String title;
	private String desc;
	private int E_ID;
	private ArrayList<Reimbursement> reimburseList;
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String username, String password, String firstName, String lastName, String email, String title, String desc, int E_ID) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.title = title;
		this.desc = desc;
		this.E_ID = E_ID;
		this.reimburseList = new ArrayList<Reimbursement>();
	}
	
	
	public int getE_ID() {
		return E_ID;
	}


	public ArrayList<Reimbursement> getReimburseList() {
		return reimburseList;
	}

	public void setReimburseList(ArrayList<Reimbursement> reimburseList) {
		this.reimburseList = reimburseList;
		//for(int i = 0; i < reimburseList.size(); i++)
			//this.reimburseList.add(reimburseList.get(i));
	}
	public void addReimbursement(String status, double amount) {
		Reimbursement reBurst = new Reimbursement(E_ID, status, amount);
		reimburseList.add(reBurst);
	}
	
	public void removeReimbursement(int id) {
		reimburseList.remove(id);
	}
	public String getEmail() {
		return email;
	}

	

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", title=" + title + ", desc=" + desc + "]";
	}

}
