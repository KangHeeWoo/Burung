package company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import burung.dbcp.DbcpBean;
import company.vo.CompanyVo;

public class CompanyDao {
	private static CompanyDao instance = new CompanyDao();
	private CompanyDao() {}
	public static CompanyDao getInstance() {
		return instance;
	}
	
	public CompanyVo popup(int offNum){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbcpBean.getConn();
			String sql = "select * from office where offnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, offNum);
			rs = pstmt.executeQuery();
			rs.next();
			CompanyVo vo = new CompanyVo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			return vo;
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
}
