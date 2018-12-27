package com.revature.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankConnectionUtil {
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:oracle:thin:@revature.cqtfgup04dcw.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "atshisler";
		String password = "Baconeatingsans2!";
		
		//2. establish the connection
		return DriverManager.getConnection(url, username, password);

	}
}
