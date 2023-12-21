package com.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


	//Class representing a database connection
	public class DBConnection {
	     // JDBC driver path for MySQL
		private static final String driver_path = "com.mysql.cj.jdbc.Driver";
		// Database connection URL
		private static final String url = "jdbc:mysql://localhost:3306/simplechatapplication";
		private static final String userName = "root";
		private static final String passWord = "root";

		public  DBConnection() {
			try {
				Class.forName(driver_path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public Connection getConnection() throws SQLException {
			return DriverManager.getConnection(url, userName, passWord);
		}

		public PreparedStatement prepareStatement(String query) {
			// TODO Auto-generated method stub
			return null;
		}

	}



