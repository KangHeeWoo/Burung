package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.ReviewVo;
import board.vo.Review_ImgVo;
import board.vo.reviewBatch;
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

	public int reviewdelete(int revnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {
			conn = DbcpBean.getConn();
			String sql1 = "delete from revimg where revnum=?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, revnum);
			pstmt1.executeUpdate();

			String sql = "delete from review where revnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, revnum);
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DbcpBean.close(pstmt1);
			DbcpBean.close(conn, pstmt, null);
		}
	}

	public int getCount(String search,String searchValue) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "select NVL(count(revnum),0) count from review r,members m where r.memnum=m.memnum and upper("+search+") like '%' || upper(?) || '%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
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
	public int getCount(String[] carValue) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "select NVL(count(revnum),0) count from review r,salescar c where r.carname=c.scarname and scarmodel in(";
			
			for (int i = 0; i < carValue.length; i++) {
				sql += "?";
				if (carValue.length != i + 1) {
					sql += ",";
				}
			}
			
			sql += ")";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < carValue.length; i++) {
				pstmt.setString(i + 1, carValue[i]);
			}
			rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt("count");
			
			//System.out.println("체크박스 count"+count);
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "insert into review values(0,?,?,?,0,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getRevTitle());
			pstmt.setString(2, vo.getRevContent());
			pstmt.setInt(3, vo.getRevScore());
			pstmt.setInt(4, vo.getMemNum());
			pstmt.setString(5, vo.getCarname());
			pstmt.executeUpdate();

			String sql2 = "select max(revnum) from review";
			pstmt2 = conn.prepareStatement(sql2);
			rs = pstmt2.executeQuery();
			rs.next();

			// insert후 마지막 revnum리턴
			return rs.getInt(1);

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DbcpBean.close(pstmt2);
			DbcpBean.close(conn, pstmt, null);
		}
	}

	public ArrayList<ReviewVo> carValueList(int startRow, int endRow, String[] carValue) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewVo> list = new ArrayList<>();
		try {
			conn = DbcpBean.getConn();
			String sql = "select * from(select aa.*,rownum rnum from(select carname,revnum,revcontent,revscore, revtitle,revhit,revRegd,m.memid memid ,m.memnum memnum from review r,members m, salescar s where m.memNum=r.memNum and scarmodel in(";
			
			for (int i = 0; i < carValue.length; i++) {
				sql += "?";
				if (carValue.length != i + 1) {
					sql += ",";
				}
			}
			
			sql += ") and carname = scarname order by revnum desc)aa)where rnum>=? and rnum<=?";
			
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			for (int i = 0; i < carValue.length; i++) {
				pstmt.setString(i + 1, carValue[i]);
			}
			pstmt.setInt(carValue.length + 1, startRow);
			pstmt.setInt(carValue.length + 2, endRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int revnum = rs.getInt("revnum");
				String revtitle = rs.getString("revtitle");
				String revcontent = rs.getString("revcontent");
				int revhit = rs.getInt("revhit");
				int revscore = rs.getInt("revscore");
				Date revregd = rs.getDate("revregd");
				int memnum = rs.getInt("memnum");
				String memid = rs.getString("memid");
				String carname = rs.getString("carname");

				ReviewVo vo = new ReviewVo(revnum, revtitle, revcontent, revscore, revhit, revregd, memnum, memid,
						carname);
				list.add(vo);

				System.out.println("체크박스 vo:" + vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}
	
	public ArrayList<ReviewVo> carValueList(int startRow, int endRow, String[] carValue, String searchBy) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewVo> list = new ArrayList<>();
		try {
			conn = DbcpBean.getConn();
			String sql = "select * from(select aa.*,rownum rnum from(select carname,revnum,revcontent,revscore, revtitle,revhit,revRegd,m.memid memid ,m.memnum memnum from review r,members m, salescar s where m.memNum=r.memNum and scarmodel in(";
			
			for (int i = 0; i < carValue.length; i++) {
				sql += "?";
				if (carValue.length != i + 1) {
					sql += ",";
				}
			}

			sql += ") and carname = scarname order by " + searchBy + " desc)aa)where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			for (int i = 0; i < carValue.length; i++) {
				pstmt.setString(i + 1, carValue[i]);
			}
			
			pstmt.setInt(carValue.length + 1, startRow);
			pstmt.setInt(carValue.length + 2, endRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int revnum = rs.getInt("revnum");
				String revtitle = rs.getString("revtitle");
				String revcontent = rs.getString("revcontent");
				int revhit = rs.getInt("revhit");
				int revscore = rs.getInt("revscore");
				Date revregd = rs.getDate("revregd");
				int memnum = rs.getInt("memnum");
				String memid = rs.getString("memid");
				String carname = rs.getString("carname");

				ReviewVo vo = new ReviewVo(revnum, revtitle, revcontent, revscore, revhit, revregd, memnum, memid,
						carname);
				list.add(vo);

				System.out.println("체크박스 vo:" + vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}

	public ArrayList<ReviewVo> listAll(int startRow, int endRow, String search, String searchValue, String searchBy) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewVo> list = new ArrayList<>();

		try {
			conn = DbcpBean.getConn();
			String sql = "";
			if (search.equals("1")) {
				sql = "select * from(select aa.*,rownum rnum from(select carname,revnum,revcontent,revscore, revtitle,revhit,revRegd,m.memid memid ,m.memnum memnum from review r,members m where m.memNum=r.memNum order by "
						+ searchBy + " desc)aa)where rnum>=? and rnum<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			} else {
				sql = "select * from(select aa.*,rownum rnum from(select carname,revnum,revcontent,revscore,revtitle,revhit,revregd,m.memid memid, m.memnum memnum from review r, members m where m.memnum=r.memnum and upper( "
						+ search + ") like '%' || upper(?) || '%' order by " + searchBy + " desc)aa)where rnum>=? and rnum<=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchValue );
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int revnum = rs.getInt("revnum");
				String revtitle = rs.getString("revtitle");
				String revcontent = rs.getString("revcontent");
				int revhit = rs.getInt("revhit");
				int revscore = rs.getInt("revscore");
				Date revregd = rs.getDate("revregd");
				int memnum = rs.getInt("memnum");
				String memid = rs.getString("memid");
				String carname = rs.getString("carname");

				ReviewVo vo = new ReviewVo(revnum, revtitle, revcontent, revscore, revhit, revregd, memnum, memid,
						carname);
				list.add(vo);

				System.out.println("리뷰 vo:" + vo);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}

	public int reviewhitupdate(int revnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "update review set revhit=revhit+1 where revnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, revnum);
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}

	public Review_ImgVo detail(int revnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "select revnum, revtitle, revcontent, revscore, revhit, revregd, m.memnum memnum, memid, carname from review r, members m where r.memnum = m.memnum and revnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, revnum);
			rs = pstmt.executeQuery();
			rs.next();
			Review_ImgVo vo = new Review_ImgVo(rs.getInt("revnum"), rs.getString("revcontent"),
					rs.getString("revtitle"), rs.getInt("revscore"), rs.getDate("revregd"), rs.getString("memid"),
					rs.getString("carname"), rs.getInt("revHit"), rs.getInt("memNum"));

			return vo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}

	// 해당 게시글번호에 들어가있는 carimgname들을 arraylist에 담아 리턴
	public ArrayList<String> imglist(int revnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<String> imglist = new ArrayList<>();
		try {
			conn = DbcpBean.getConn();
			String sql = "select recarimgname from revimg where revnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, revnum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				imglist.add(rs.getString("recarimgname"));
			}
			return imglist;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}

	// 해당 날짜에 저장된 글
	public ArrayList<reviewBatch> reviewLog(String date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<reviewBatch> list = new ArrayList<>();
		try {
			conn = DbcpBean.getConn();
			String sql = "select revnum,revtitle,revcontent,revscore,revregd,memid,carname from review r,members m where r.memnum=m.memnum and revregd between to_date(?, 'YYYYMMDD HH24:MI') and to_date(?, 'YYYYMMDD HH24:MI')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date + " 00:00");
			pstmt.setString(2, date + " 23:59");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int revnum = rs.getInt("revnum");
				String revtitle = rs.getString("revtitle");
				String revcontent = rs.getString("revcontent");
				int revscore = rs.getInt("revscore");
				Date revregd = rs.getDate("revregd");
				String memid = rs.getString("memid");
				String carname = rs.getString("carname");
				reviewBatch vo = new reviewBatch(revnum, revtitle, revcontent, revscore, revregd, carname, memid);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}
}
