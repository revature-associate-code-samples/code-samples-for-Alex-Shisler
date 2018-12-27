package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.user.Employee;
import com.revature.user.Reimbursement;
import com.revature.util.ConnectionUtilV1;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	final static Logger Log = Logger.getLogger(ReimbursementDAOImpl.class);
	private static ConnectionUtilV1 cu = ConnectionUtilV1.getInstance();
	private static ReimbursementDAOImpl rDAO;
	
	private ReimbursementDAOImpl() {
		;
	}

	public static ReimbursementDAOImpl getRDAO() {
		if (rDAO== null) {
			rDAO = new ReimbursementDAOImpl();
		}
		return rDAO;
	}// getBankDao
	
	@Override
	public boolean insertReimbursements(Reimbursement reimburse) {
		// TODO Auto-generated method stub
		ConnectionUtilV1 conn1 = ConnectionUtilV1.getInstance();
		System.out.println(reimburse.toString());
		try (Connection conn2 = conn1.getConnection()) {
			// ------------reimbursementTable insert---------//
			String sql = "insert into reimbursementTable values (?, sequence_R.nextval, ?, ?)";
			PreparedStatement cs = conn2.prepareStatement(sql);
			cs.setInt(1, reimburse.getId());
			cs.setDouble(2, reimburse.getAmount());
			cs.setString(3, reimburse.getStatus());
			if (cs.executeUpdate() > 0) {
				System.out.println("Executed successfully!");
				return true;
			}
		} catch (SQLException s) {
			Log.error("Catch block in insertReimbursements - DAO Implementation - occured");
			System.out.println(s.getMessage());
			// s.printStackTrace();
		} finally {
			;// Log.warn("executed finally block");
		}

		return false;
	}

	@Override
	public Reimbursement deleteReimbursement(int id) {
		// TODO Auto-generated method stub
		
		return null;
	}//deleteReimbursment

	@Override
	public Reimbursement searchReimbursements(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "select * from reimbursementTable where R_ID = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				return new Reimbursement(rs.getInt("R_ID"), rs.getString("R_STATUS"), rs.getDouble("R_AMOUNT"));
			}//WHILE
		}catch(SQLException s) {
			Log.error("Catch block in searchReimbursement- DAO Implementation - occured");
			System.out.println(s.getMessage());
		}
		
		return null;
	}//searchReimbursement

	@Override
	public ArrayList<Reimbursement> getReimbursList(int E_ID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "select * from reimbursementTable where E_ID = ? ORDER BY R_ID";//  where E_ID = ? ORDER BY R_ID
		System.out.println(E_ID);
		try {
			ArrayList<Reimbursement> remList;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, E_ID);
			ResultSet rs = pstmt.executeQuery();
			remList = new ArrayList<Reimbursement>();
			while (rs.next()) {
				remList.add(new Reimbursement(rs.getInt("R_ID"), rs.getString("R_STATUS"), rs.getDouble("R_AMOUNT")));
			} // while still has stuff
			return remList;
		} catch (SQLException s) {
			System.out.println("Error");
			Log.error("Catch block in getEmployeeList- DAO Implementation - occured");
			System.out.println(s.getMessage());
		} finally {
			;// Log.warn("Executed finally block");
		}

		return new ArrayList<Reimbursement>();// never return null

	}

	@Override
	public boolean updateReimbursement(int id, String status) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "update reimbursementTable set R_STATUS = ? where R_ID = ?";
		try { // parameters
			PreparedStatement ps = conn.prepareStatement(sql);
			// ps.setInt(1, banker.getCid());
			ps.setString(1, status);
			ps.setInt(2, id);
			
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
