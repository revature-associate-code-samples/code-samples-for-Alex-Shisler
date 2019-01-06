package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import org.apache.log4j.Logger;

import com.revature.project0.BankUser;
import com.revature.util.BankConnectionUtil;

/*
 * Database Access and Model
 */

public class BankImpl implements BankDao {
	private static BankImpl bankDao;

	private ArrayList<BankUser> bankList;
	private BankUser currentUser;
	
	final static Logger Log = Logger.getLogger(BankImpl.class);
/*----------------------Variables----------------------------------*/
	
	private BankImpl() {
		bankList = new ArrayList<BankUser>();
	}
//------------------------------------------------------------------
	public static BankImpl getBankDao() {
		if (bankDao == null) {
			bankDao = new BankImpl();
		}
		return bankDao;
	}// getBankDao

	@Override
	public boolean insertBankCust(BankUser banker) {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "insert into userInfo values (sequence_1.nextval, ?, ?, ?, ?, ?, ?)";// 5 columns 5 ?'s,
																								// parameters
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, banker.getAccountName());
			ps.setString(2, banker.getPassword());
			ps.setString(3, banker.getFirstName());
			ps.setString(4, banker.getLastName());
			ps.setString(5, banker.getRole());
			ps.setString(6, banker.getIsAccessible());
			// ------------userTable insert---------//
			sql = "insert into accountInfo values (sequence_1.currval, sequence_1.currval, ?, ?, ?)";
			PreparedStatement cs2 = conn.prepareStatement(sql);
			cs2.setDouble(1, 0.00);
			cs2.setDouble(2, 0.00);
			cs2.setDouble(3, 0.00);
			if (ps.executeUpdate() > 0 && cs2.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException s) {
			Log.error("Catch block in getCustomer - DAO Implementation - occured");
		} finally {
			;// Log.warn("executed finally block");
		}

		return false;
	}// insertBankCust
	
	public boolean isUsernameUnique(String name) {
		bankList = getAllBankCusts();
		for(int i = 0; i < bankList.size(); i++) {
			if(bankList.get(i).getAccountName().equals(name)) {
				return false;
			}//not unique
		}//search all names
		return true;
	}//check username for uniqueness
	
	public boolean sortTable() {
		try (Connection conn = BankConnectionUtil.getConnection()) {
		String sql = "select * from userInfo ORDER BY U_ID";
		PreparedStatement ps = conn.prepareStatement(sql);
		sql = "select * from accountInfo ORDER BY U_ID";
		PreparedStatement ps2 = conn.prepareStatement(sql);
		if (ps.executeUpdate() > 0 && ps2.executeUpdate() > 0)
			return true;
		
		}
		catch(SQLException s) {
			Log.error("Catch block in sortTable - DAO Implementation - occured");
			System.out.println(s.getMessage());
		}
		return false;
	}


	@Override
	public BankUser searchBankUser(String user) {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "select * from userInfo where B_USERNAME = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				currentUser = new BankUser(rs.getInt("U_ID"), rs.getString("B_USERNAME"), rs.getString("B_PASSWORD"),
						rs.getString("B_FIRSTNAME"), rs.getString("B_LASTNAME"), rs.getString("B_ROLE"),
						rs.getString("isAccessible"));
				return currentUser;
			}
		} catch (SQLException s) {
			Log.error("Catch block in searchBankUser - DAO Implementation - occured");
			System.out.println(s.getMessage());
		} finally {
			;// Log.warn("Executed finally block");
		}
		currentUser = new BankUser();
		return currentUser;

	}// getBankUser
	


	public BankUser searchByCID(int cid) {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "select * from userInfo where U_ID= ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				currentUser = new BankUser(rs.getInt("U_ID"), rs.getString("B_USERNAME"), rs.getString("B_PASSWORD"),
						rs.getString("B_FIRSTNAME"), rs.getString("B_LASTNAME"), rs.getString("B_ROLE"),
						rs.getString("isAccessible"));
				return currentUser;
			}
		} catch (SQLException s) {
			Log.error("Catch block in searchByCID - DAO Implementation - occured");
			System.out.println(s.getMessage());
		} finally {
			;// Log.warn("Executed finally block");
		}
		return currentUser;
	}

	@Override
	public ArrayList<BankUser> getAllBankCusts() {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "select * from userInfo ORDER BY U_ID";
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			bankList = new ArrayList<BankUser>();
			while (rs.next()) {
				bankList.add(new BankUser(rs.getInt("U_ID"), rs.getString("B_USERNAME"), rs.getString("B_PASSWORD"),
						rs.getString("B_FIRSTNAME"), rs.getString("B_LASTNAME"), rs.getString("B_ROLE"),
						rs.getString("isAccessible")));
			} // while still has stuff
			return bankList;
		} catch (SQLException s) {
			Log.error("Catch block in getAllBankCusts - DAO Implementation - occured");
			System.out.println(s.getMessage());
		} finally {
			;// Log.warn("Executed finally block");
		}

		return new ArrayList<BankUser>();// never return null
	}// getAllBankCusts

	@Override
	public boolean insertBankCustomerProcedure(BankUser banker) {
		// TODO Auto-generated method stub
		try (Connection conn = BankConnectionUtil.getConnection()) {
			// Calling the store procedure
			String storedProc = "CALL INSERT_USERINFO(sequence_1.nextval, ?,?,?,?,?,?)";

			// Using callable statement in itself is not vulnerable to SQL Injection
			CallableStatement cs = conn.prepareCall(storedProc);
			// cs.setInt(1, banker.getCid());
			cs.setString(1, banker.getAccountName());
			cs.setString(2, banker.getPassword());
			cs.setString(3, banker.getFirstName());
			cs.setString(4, banker.getLastName());
			cs.setString(5, banker.getRole());
			cs.setString(6, banker.getIsAccessible());
			// Set attributes to insert

			if (cs.executeUpdate() > 0) {
				cs.close();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			Log.error("Catch block in inserBankCustomerProcedure - DAO Implementation - occured");
		}

		return false;
	}// insertBankCustomerProcedure


	

	@Override
	public BankUser Login(String user, String pass) {
		// TODO Auto-generated method stub
		// ArrayList<BankUser> list = getAllBankCusts();

		currentUser = new BankUser(searchBankUser(user));
		getAccountInfo();
		if (pass.equals(currentUser.getPassword())) {
			return currentUser;
		}
		else {
			currentUser = new BankUser();
			return new BankUser();
		}
	}

	public void getAccountInfo() {
		if (currentUser.getCid() != -1) {
			try (Connection conn = BankConnectionUtil.getConnection()) {
				String sql = "select * from accountInfo where U_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, currentUser.getCid());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					currentUser.setAccountBal(rs.getDouble("B_BALANCE"));
				}
			} catch (SQLException s) {
				Log.error("Catch block in getAccountInfo - DAO Implementation - occured");
				System.out.println(s.getMessage());
			} finally {
				;// Log.warn("Executed finally block");
			}
		} // user exists
		else
			System.out.println("No account info is available.");

	}// getAccountInfo

	@Override
	public BankUser getBankUser() {
		// TODO Auto-generated method stub
		return currentUser;
	}

	public void deposit(double amount) {
		amount = Math.abs(amount);
		//getAccountInfo();
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "update accountInfo set B_BALANCE = ?, B_DEPOSIT = ? where U_ID = ?";
			CallableStatement cs = conn.prepareCall(sql);
			currentUser.deposit(amount);
			cs.setDouble(1, currentUser.getAccountBal());
			cs.setDouble(2, amount);
			cs.setInt(3, currentUser.getCid());
			if (cs.executeUpdate() > 0) {
				System.out.println("Deposit successful.");
				Log.warn("New Balance: " + currentUser.getAccountBal() + " Amount deposited: " + amount);
			}
			// ResultSet rs = pstmt.executeQuery();

		} catch (SQLException s) {
			Log.error("Catch block in deposit - DAO Implementation - occured");
			System.out.println(s.getMessage());
		} finally {
			;// Log.warn("Executed finally block");
		}
	}// deposit

	public void withdrawal(double amount) {
		if (amount < 0) {
			System.out.println("Cannot withdraw a negative amount of money");
		} else {
			//getAccountInfo();
			try (Connection conn = BankConnectionUtil.getConnection()) {
				String sql = "update accountInfo set B_BALANCE = ?, B_WITHDRAWAL = ? where U_ID = ?";
				CallableStatement cs = conn.prepareCall(sql);
				currentUser.withdrawal(amount);
				// PreparedStatement pstmt = conn.prepareStatement(sql);
				cs.setDouble(1, currentUser.getAccountBal());
				cs.setDouble(2, amount);
				cs.setInt(3, currentUser.getCid());
				if (cs.executeUpdate() > 0) {
					System.out.println("Withdrawal successful.");
					Log.error("New Balance: " + currentUser.getAccountBal() + "Amount withdrawn: " + amount);
				}
				// ResultSet rs = pstmt.executeQuery();

			} catch (SQLException s) {
				Log.error("Catch block in withdrawal - DAO Implementation - occured");
				System.out.println(s.getMessage());
			} finally {
				;// Log.warn("Executed finally block");
			}

		}

	}// withdrawal

	public void grantAccess(int cid) {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "update userInfo set isAccessible = ? where U_ID = ?";
			CallableStatement cs = conn.prepareCall(sql);
			// PreparedStatement pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < bankList.size(); i++) {
				if(bankList.get(i).getCid() == cid) {
					if (bankList.get(i).getIsAccessible().equals("y"))
						cs.setString(1, "n");
					else
						cs.setString(1, "y");
					break;
				}//client found changing access
			}//finding client
				
			cs.setInt(2, cid);

			if (cs.executeUpdate() > 0) {
				System.out.println("Access changed.");
			}
			// ResultSet rs = pstmt.executeQuery();

		} catch (SQLException s) {
			Log.error("Catch block in withdrawal - DAO Implementation - occured");
			System.out.println(s.getMessage());
		} finally {
			;// Log.warn("Executed finally block");
		}
	}// grantAccess
}
