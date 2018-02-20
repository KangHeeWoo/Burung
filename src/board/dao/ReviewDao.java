package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import board.vo.ReviewVo;
import burung.dbcp.DbcpBean;

public class ReviewDao {
	private static ReviewDao instance = new ReviewDao();

	private ReviewDao() {
	}

	public static ReviewDao getInstance() {
		return instance;
	}

	public int getMaxNum() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "select NVL(max(revnum),0) maxnum from review";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int maxnum = rs.getInt("maxnum");
			return maxnum;
		} catch (SQLException se) {
			return -1;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}

	public int getCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "select NVL(count(revnum),0) count from review";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt("count");
			System.out.println(count);
			return count;
		} catch (SQLException se) {
			return -1;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}

	public String scarname(int slistnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "select scarname from salescar s, saleslist l where s.salnum=l.salnum and slistnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, slistnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("scarname");
			}
			return null;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}
	public String rcarname(int rlistnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "select rcarname from rentcar r, rentlist l where r.rennum=l.rennum and rlistnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rlistnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("rcarname");
			}
			return null;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}
	public int reviewinsert(ReviewVo vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=DbcpBean.getConn();
			String sql="insert into review values(0,?,?,?,0,sysdate,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getRevTitle());
			pstmt.setString(2, vo.getRevContent());
			pstmt.setInt(3, vo.getRevScore());
			pstmt.setInt(4, vo.getMemNum());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
	
}
