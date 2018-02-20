package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			conn=DbcpBean.getConn();
			String sql="insert into review values(0,?,?,?,0,sysdate,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getRevTitle());
			pstmt.setString(2, vo.getRevContent());
			pstmt.setInt(3, vo.getRevScore());
			pstmt.setInt(4, vo.getMemNum());
			pstmt.executeUpdate();
			
			String sql2="select max(revnum) from review";
			pstmt2=conn.prepareStatement(sql2);
			rs=pstmt2.executeQuery();
			rs.next();
						
			//insert후 마지막 revnum리턴
			return rs.getInt(1); 
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
	public ArrayList<ReviewVo> listAll(int startRow,int endRow){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ReviewVo> list=new ArrayList<>();
		
		try {
			conn=DbcpBean.getConn();
			String sql="select * from(select aa.*,rownum rnum from(select carname,revnum,revcontent,revscore, revtitle,revhit,revRegd,m.memid memid from review r,members m where m.memNum=r.memNum order by revregd desc)aa)where rnum>=? and rnum<=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int revnum=rs.getInt("revnum");
				String revtitle=rs.getString("revtitle");
				String revcontent=rs.getString("revcontent");
				int revhit=rs.getInt("revhit");
				int revscore=rs.getInt("revscore");
				Date revregd=rs.getDate("revregd");
				int memnum=rs.getInt("memnum");
				String memid=rs.getString("memid");
				String carname=rs.getString("carname");
				
				ReviewVo vo=new ReviewVo(revnum, revtitle, revcontent, revscore, revhit, revregd, memnum, memid, carname);
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
	
}
