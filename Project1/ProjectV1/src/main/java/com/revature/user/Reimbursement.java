package com.revature.user;

public class Reimbursement {
	private int id;
	private String status;
	private double amount;
	
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(String status, double amount) {
		super();
		this.status = status;
		this.amount = amount;
	}
	public Reimbursement(int id, String status, double amount) {
		super();
		this.id = id;
		this.status = status;
		this.amount = amount;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		if(amount > 0)
			this.amount = amount;
	}
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", status=" + status + ", amount=" + amount + "]";
	}
	
	
}
