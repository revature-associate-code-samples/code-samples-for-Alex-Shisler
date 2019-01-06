package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.project0.BankUser;
import com.revature.util.BankConnectionUtil;

/*
 * Database Access and Model
 */

public class BankImpl implements BankDao{
	private static BankImpl bankDao;
	
	final static Logger Log = Logger.getLogger(BankImpl.class);
	
	private BankImpl() {
		
	}
	public static BankImpl getBankDao() {
		if(bankDao == null) {
			bankDao = new BankImpl();
		}
		return bankDao;
	}//getBankDao
	
	
	@Override
	public boolean insertBankCust(BankUser banker) {
		// TODO Auto-generated method stub
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "insert into bankcustomer values (?, ?, ?, ?, ?)";//5 columns 5 ?'s, parameters
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, banker.getCid());
			ps.setString(2, banker.getAccountName());
			ps.setString(3,  banker.getFirstName());
			ps.setString(4,  banker.getLastName());
			ps.setString(5,  banker.getPassword());
			ps.setDouble(6, banker.getAccountBal());
			if(ps.executeUpdate() > 0) 
				return true;
		} catch (SQLException s) {
			Log.error("catch block in inserBankCust - Dao Implementation - occured");
			s.getMessage();
			//s.printStackTrace();
		} finally {
			Log.warn("executed finally block");
		}

		return false;
	}//insertBankCust

	

	@Override
	public BankUser getBankUser() {
		// TODO Auto-generated method stub
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "select * from bankcustomer";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Log.info("did the while loop execute?");
				
				return new BankUser(rs.getInt("B_ID"), rs.getString("B_FIRSTNAME"), rs.getString("B_LASTNAME"),
						rs.getString("B_USERNAME"), rs.getString("B_PASSWORD"), 0);
			}
		} catch (SQLException s) {
			Log.error("Catch block in getBankUser - DAO Implementation - occured");
			s.getMessage();
		} finally {
			Log.warn("Executed finally block");
		}

		return new BankUser();

	}//getBankUser

	@Override
	public List<BankUser> getAllBankCusts() {
		// TODO Auto-generated method stub
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "select * from bankcustomer";
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			List<BankUser> bankList = new ArrayList<BankUser>();
			while (rs.next()) {
				bankList.add(new BankUser(rs.getInt("B_ID"), rs.getString("B_FIRSTNAME"), rs.getString("B_LASTNAME"),
						rs.getString("B_USERNAME"), rs.getString("B_PASSWORD"), rs.getDouble(("B_Balance"))));
			}//while still has stuff
			return bankList;
		} catch (SQLException s) {
			Log.error("Catch block in getAllBankCusts - DAO Implementation - occured");
			s.getMessage();
		} finally {
			Log.warn("Executed finally block");
		}

		return new ArrayList<BankUser>();// never return null
	}//getAllBankCusts
		
	@Override
	public boolean insertBankCustomerProcedure(BankUser banker) {
		// TODO Auto-generated method stub
		try(Connection conn = BankConnectionUtil.getConnection()){
			//Calling the store procedure
			String storedProc = "CALL INSERT_BANKCUSTOMER(?,?,?,?,?,?)";

			//Using callable statement in itself is not vulnerable to SQL Injection
			CallableStatement cs = conn.prepareCall(storedProc);
			cs.setInt(1, banker.getCid());
			cs.setString(2, banker.getFirstName());
			cs.setString(3,  banker.getLastName());
			cs.setString(4,  banker.getAccountName());
			cs.setString(5,  banker.getPassword());
			//Set attributes to insert
			
			if(cs.executeUpdate() > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}//insertBankCustomerProcedure
	@Override
	public boolean updateBankCustomer() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
