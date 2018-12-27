package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtilV1 {
	private static ConnectionUtilV1 cu = null;
	private static Properties prop = new Properties();
	private ConnectionUtilV1() {
		super();
		InputStream dbProps = ConnectionUtilV1.class.getClassLoader()
				.getResourceAsStream("database.properties");
		try {
			prop.load(dbProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static ConnectionUtilV1 getInstance() {
		if(cu==null)
			cu=new ConnectionUtilV1();
		return cu;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			// We have to register the driver class
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pwd"));
		} catch(Exception e) {
			System.out.println("OH no");
			e.printStackTrace();
		}
		
		return conn;
	}
}
