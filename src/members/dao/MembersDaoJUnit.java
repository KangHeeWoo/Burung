package members.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import members.vo.MembersVo;
import test.DBCConnection;

public class MembersDaoJUnit {
	public MembersVo list(String memId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCConnection.getConn();
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
			DBCConnection.closeConn(con, pstmt, rs);
		}
	}
}
