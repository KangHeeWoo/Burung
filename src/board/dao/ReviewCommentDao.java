package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.ReviewCommentVo;
import burung.dbcp.DbcpBean;

public class ReviewCommentDao {
	private static ReviewCommentDao instance=new ReviewCommentDao();
	private ReviewCommentDao() {}
	public static ReviewCommentDao getinstance() {
		return instance;
	}
	public int rcommentinsert(String rcomcon,int memnum,int revnum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DbcpBean.getConn();
			String sql="insert into reviewcomment values(0,?,sysdate,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,rcomcon);
			pstmt.setInt(2, memnum);
			pstmt.setInt(3, revnum);
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
	public ArrayList<ReviewCommentVo> list(int revnum){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=DbcpBean.getConn();
			String sql="select * from reviewcomment  where revnum=? order by rcomregd desc";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, revnum);
			rs=pstmt.executeQuery();
			ArrayList<ReviewCommentVo> list=new ArrayList<>();
			while(rs.next()) {
				
				int rComNum=rs.getInt("rcomnum");
				String rComCon=rs.getString("rcomcon");
				Date rComRegd=rs.getDate("rcomregd");
				int memNum=rs.getInt("memnum");
				int revNum=rs.getInt("revnum");
						
				ReviewCommentVo vo=new ReviewCommentVo(rComNum, rComCon, rComRegd, memNum, revNum);
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
	public int commdelete(int rcomnum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DbcpBean.getConn();
			String sql="delete from reviewcomment where rcomnum=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rcomnum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
}

