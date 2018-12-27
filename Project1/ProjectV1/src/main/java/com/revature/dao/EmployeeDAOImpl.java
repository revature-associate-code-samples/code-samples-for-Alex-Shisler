package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.revature.user.Employee;
import com.revature.util.ConnectionUtilV1;

public class EmployeeDAOImpl implements EmployeeDAO {
	final static Logger Log = Logger.getLogger(EmployeeDAOImpl.class);
	private static ConnectionUtilV1 cu = ConnectionUtilV1.getInstance();

	/*----------------------Variables----------------------------------*/

	public EmployeeDAOImpl() {
		;
	}

	public boolean insertEmployee(Employee employee) {
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "insert into employeeTable values (sequence_1.nextval, ?, ?, ?, ?)";
		try { // parameters
			PreparedStatement ps = conn.prepareStatement(sql);
			// ps.setInt(1, banker.getCid());
			ps.setString(1, employee.getUsername());
			ps.setString(2, employee.getPassword());
			ps.setString(3, employee.getFirstName());
			ps.setString(4, employee.getLastName());

			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException s) {
			Log.error("Catch block in insertEmployee - DAO Implementation - occured");
			System.out.println(s.getMessage());
			// s.printStackTrace();
		} finally {
			;// Log.warn("executed finally block");
		}

		return false;
	}

	public Employee getEmployee(String email) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "select * from EmployeeTable where E_EMAIL = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return new Employee(rs.getString("E_USERNAME"), rs.getString("E_PASSWORD"), rs.getString("E_FIRSTNAME"),
						rs.getString("E_LASTNAME"), rs.getString("E_EMAIL"), rs.getString("E_TITLE"), rs.getString("E_DESCRP"),
						rs.getInt("E_ID"));
			} // WHILE
		} catch (SQLException s) {
			Log.error("Catch block in getEmployee - DAO Implementation - occured");
			System.out.println(s.getMessage());
		}

		return null;
	}

	@Override
	public ArrayList<Employee> getEmployeeList() {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "select * from employeeTable ORDER BY E_ID";
		conn = cu.getConnection();
		try {
			ArrayList<Employee> empList;
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			empList = new ArrayList<Employee>();
			while (rs.next()) {
				empList.add(new Employee(rs.getString("E_USERNAME"), rs.getString("E_PASSWORD"),
						rs.getString("E_FIRSTNAME"), rs.getString("E_LASTNAME"), rs.getString("E_EMAIL"),
						rs.getString("E_TITLE"), rs.getString("E_DESCRP"), rs.getInt("E_ID")));
			} // while still has stuff
			return empList;
		} catch (SQLException s) {
			Log.error("Catch block in getEmployeeList- DAO Implementation - occured");
			System.out.println(s.getMessage());
		} finally {
			;// Log.warn("Executed finally block");
		}

		return new ArrayList<Employee>();// never return null
	}// getEmployeeList

	@Override
	public boolean updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "update employeeTable set E_USERNAME = ?, E_FIRSTNAME = ?, E_LASTNAME = ?, E_DESCRP = ? where E_ID = ?";
		try { // parameters
			PreparedStatement ps = conn.prepareStatement(sql);
			// ps.setInt(1, banker.getCid());
			ps.setString(1, employee.getUsername());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setString(4, employee.getDesc());
			ps.setInt(5, employee.getE_ID());

			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException s) {
			Log.error("Catch block in insertEmployee - DAO Implementation - occured");
			System.out.println(s.getMessage());
			// s.printStackTrace();
		} finally {
			;// Log.warn("executed finally block");
		}

		return false;
	}

}
