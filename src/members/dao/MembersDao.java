package members.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import burung.dbcp.DbcpBean;
import members.vo.MembersInsertVo;
import members.vo.MembersVo;

public class MembersDao {

	private static MembersDao instance = new MembersDao();

	private MembersDao() {
	}

	public static MembersDao getInstance() {
		return instance;
	}

	public int insert(MembersVo user) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "insert into members values(0,?,?,?,?,?,?,?,sysdate)";
		try {
			con = DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getMemId());
			pstmt.setString(2, user.getMemPwd());
			pstmt.setString(3, user.getMemAddr());
			pstmt.setString(4, user.getMemPhone());
			pstmt.setString(5, user.getMemEmail());
			pstmt.setString(6, user.getMemBirth());
			pstmt.setString(7, user.getMemName());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DbcpBean.close(con, pstmt, null);
		}
	}

	public int login(HashMap<String, String> map) { // hasmap 파라미터를 받아온다
		String memId = map.get("memId");
		String memPwd = map.get("memPwd");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DbcpBean.getConn();
			String sql = "select * from members where memId=? and memPwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return 1; // 회원이 맞으면 1리턴
			} else {
				return 0; // 해당 정보가 없으면 0리턴
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1; // 오류가 발생하면 -1리턴
		} finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}

	public int update(MembersVo members) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update members set memPwd=?,memAddr=?,memPhone=?,memEmail=?,memBirth=?,memName=? where memId=?";
		try {
			con = DbcpBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, members.getMemPwd());
			pstmt.setString(2, members.getMemAddr());
			pstmt.setString(3, members.getMemPhone());
			pstmt.setString(4, members.getMemEmail());
			pstmt.setString(5, members.getMemBirth());
			pstmt.setString(6, members.getMemName());
			pstmt.setString(7, members.getMemId());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DbcpBean.close(con, pstmt, null);
		}
	}

	public MembersVo list(String memId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from members where memId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int memNum = rs.getInt("memNum");
				String memPwd = rs.getString("memPwd");
				String memAddr = rs.getString("memAddr");
				String memPhone = rs.getString("memPhone");
				String memEmail = rs.getString("memEmail");
				String memBirth = rs.getString("memBirth");
				String memName = rs.getString("memName");
				MembersVo members = new MembersVo(memNum, memId, memPwd, memAddr, memPhone, memEmail, memBirth,
						memName);
				return members;
			}
			return null;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}

	public ArrayList<MembersInsertVo> memberList(String sysdate) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<MembersInsertVo> list = new ArrayList<>();

		try {
			con = DbcpBean.getConn();
			
			String sql = "select * from members where MEMREGDATE between to_date(?, 'YYYYMMDD HH24:MI') and to_date(?, 'YYYYMMDD HH24:MI')";
			st = con.prepareStatement(sql);
			st.setString(1, sysdate + " 00:00");
			st.setString(2, sysdate + " 23:59");
			rs = st.executeQuery();
			
			while(rs.next()) {
				list.add(new MembersInsertVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(con, st, rs);
		}
	}

	public ArrayList<MembersVo> listAll() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from members";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<MembersVo> list = new ArrayList<>();
			while (rs.next()) {
				String memId = rs.getString("memId");
				String memPwd = rs.getString("memPwd");
				String memAddr = rs.getString("memAddr");
				String memPhone = rs.getString("memPhone");
				String memEmail = rs.getString("memEmail");
				String memBirth = rs.getString("memBirth");
				String memName = rs.getString("memName");

				MembersVo user = new MembersVo(memId, memPwd, memAddr, memPhone, memEmail, memBirth, memName);
				list.add(user);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}

	// 세션아이디로 회원번호 리턴
	public int memnum(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "select memnum from members where memid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("memnum");
			}
			return -1;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}

	public boolean findId(String id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean using = false;

		try {
			con = DbcpBean.getConn();
			String sql = "select * from members where memId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				using = true;
			}
			return using;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return false;
		} finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}

	public String addr(String address) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DbcpBean.getConn();
			String sql = "select address from addr where address like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + address + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("address");
			} else {
				return null;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}

	public String findid(String name, String email) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DbcpBean.getConn();
			String sql = "select RPAD(substr(memId,1,3),length(memId),'*') as id from members where memName=? and memEmail=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("id");
			} else {
				return null;
			}
		} catch (SQLException se) { // 컨트럴러
			System.out.println(se.getMessage());
			return null;
		} finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}

	public String findpwd(String id, String email) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DbcpBean.getConn();
			String sql = "select RPAD(substr(memPwd,1,2),length(memPwd),'*') as pwd from members where memId=? and memEmail=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("pwd");
			} else {
				return null;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
}