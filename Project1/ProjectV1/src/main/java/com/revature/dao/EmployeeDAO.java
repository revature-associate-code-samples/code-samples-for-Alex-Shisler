package com.revature.dao;

import java.util.ArrayList;

import com.revature.user.Employee;

public interface EmployeeDAO {
	public Employee getEmployee(String username);//used for both logging in and getting data
	
	public boolean updateEmployee(Employee employee);
	
	public  ArrayList<Employee> getEmployeeList();//for manager
}
