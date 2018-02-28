package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCConnection {
	public static Connection getConn() throws SQLException { //독립적으로 작업 static
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.27:1521:xe", "burung1", "burung");
			return conn;		
		}catch(ClassNotFoundException ce) {
			System.out.println("드라이버로딩 실패: "+ce.getMessage());
		}
		return conn;
	}
	public static void closeConn(Connection conn) {
		try {
			if(conn!=null) {
				conn.close();
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	public static void closeConn(Connection conn,Statement stmt,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}
