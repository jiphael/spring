package com.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String className = 
				"oracle.jdbc.driver.OracleDriver";		
		Connection con = null;		
		//1)JDBC드라이버 로드
		Class.forName(className);
		System.out.println("JDBC드라이버 로드 성공");
		
		//2)DB연결
		String url, user, password;
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "test";
		password = "test";
		con = DriverManager.getConnection(url
				, user
				, password);		
		return con;
	}

	public static void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	public static void close(Statement stmt
			              , Connection con) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(con);		
	}
	public static void close(ResultSet rs
			, Statement stmt
			, Connection con) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(stmt, con);
	}
}
