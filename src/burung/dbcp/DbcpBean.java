package burung.dbcp;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class DbcpBean {
	private static DataSource ds;
	
	static { //static멤버를 초기화 할떄는 static블록을 초기화한다
			
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException e) {
			
		}				
	}
	
	public static Connection getConn() throws SQLException{
		Connection conn=ds.getConnection();
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn!=null) {
				conn.close();
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null) {
				stmt.close();
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	
	public static void close(Connection conn,Statement stmt,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}
