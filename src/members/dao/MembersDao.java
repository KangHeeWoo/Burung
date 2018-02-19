package members.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import burung.dbcp.DbcpBean;
import members.vo.MembersVo;


public class MembersDao {

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

	public int login(HashMap<String, String> map) { // hasmap �Ķ���͸� �޾ƿ´�
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
				return 1; // ȸ���� ������ 1����
			} else {
				return 0; // �ش� ������ ������ 0����
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1; // ������ �߻��ϸ� -1����
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
				String memPwd = rs.getString("memPwd");
				String memAddr = rs.getString("memAddr");
				String memPhone = rs.getString("memPhone");
				String memEmail = rs.getString("memEmail");
				String memBirth = rs.getString("memBirth");
				String memName = rs.getString("memName");
				MembersVo members = new MembersVo(memId, memPwd, memAddr, memPhone, memEmail, memBirth, memName);
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
}