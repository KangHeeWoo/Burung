package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import board.vo.BoardCommentVo;
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
			String sql="insert into boardcomment values(0,?,sysdate,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,bcomcon);
			pstmt.setInt(2, boanum);
			pstmt.setInt(3, memnum);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
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
	public ArrayList<BoardCommentVo> list(int boanum){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=DbcpBean.getConn();
			String sql="select * from boardcomment  where boanum=? order by bcomregd desc";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boanum);
			rs=pstmt.executeQuery();
			ArrayList<BoardCommentVo> list=new ArrayList<>();
			while(rs.next()) {
				int bComNum=rs.getInt("bcomnum");
				String bComCon=rs.getString("bcomcon");
				Date bComRegd=rs.getDate("bcomregd");
				int memNum=rs.getInt("memnum");
				int boaNum=rs.getInt("boanum");
				BoardCommentVo vo=new BoardCommentVo(bComNum, bComCon, bComRegd, memNum, boaNum);
				list.add(vo);
				
				System.out.println(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}
	public String insertmem(int memnum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DbcpBean.getConn();
			String sql="select memid from members where memnum=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, memnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("memid");
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}
	public int commdelete(int bcomnum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DbcpBean.getConn();
			String sql="delete from boardcomment where bcomnum=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bcomnum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
}
