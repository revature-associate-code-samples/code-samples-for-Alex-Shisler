package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Login;
import com.revature.util.ConnectionUtilV1;

public class LoginOracle implements LoginDao {
	private static ConnectionUtilV1 cu = ConnectionUtilV1.getInstance();
	
	@Override
	public Login login(String username, String password) {
		Login login = null;
		Connection conn = null;
		conn = cu.getConnection();
		String sql = "Select E_EMAIL, E_PASSWORD from EmployeeTable "
				+ "where E_EMAIL = ? AND E_PASSWORD = ?";
		//sql = "Select * from EmployeeTable";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				login = new Login(rs.getString("E_EMAIL"),
						rs.getString("E_PASSWORD"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}
	
}
