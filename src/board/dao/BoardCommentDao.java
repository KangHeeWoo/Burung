package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import burung.dbcp.DbcpBean;

public class BoardCommentDao {
	private static BoardCommentDao instance=new BoardCommentDao();
	private BoardCommentDao() {}
	public static BoardCommentDao getinstance() {
		return instance;
	}
	
	public int bcommentinsert(String bcomcon,int memnum,int boanum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DbcpBean.getConn();
			String sql="insert into boardcomment values(seq_boardcomment_bcomnum.nextval,?,sysdata,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,bcomcon);
			pstmt.setInt(2, memnum);
			pstmt.setInt(3, boanum);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
		
	}
	public int memnum(String memid) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DbcpBean.getConn();
			String sql="select memnum from members where memid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,memid);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt("memnum");
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
	
}
