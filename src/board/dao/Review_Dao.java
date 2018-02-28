package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import test.DBCConnection;

public class Review_Dao {
	public ArrayList<String> imglist(int revnum){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		ArrayList<String> imglist=new ArrayList<>();
		try {
			conn=DBCConnection.getConn();
			String sql="select recarimgname from revimg where revnum=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, revnum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				imglist.add(rs.getString("recarimgname"));
			}
			return imglist;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCConnection.closeConn(conn, pstmt, rs);
		}
	}
}
