package members.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import burung.dbcp.DbcpBean;
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

		String sql = "insert into members values(user_seq.nextval,?,?,?,?,?,?,?)";
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
			pstmt.setString(1,"%" + address + "%");
			rs = pstmt.executeQuery();
		
			if(rs.next()) { 
				return rs.getString("address");
			}else{
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