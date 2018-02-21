package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import admin.vo.RcarImgVo;
import admin.vo.ScarImgVo;
import burung.dbcp.DbcpBean;

public class RcarImgDao {
	public int insert(RcarImgVo vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into rcarimg values(SEQ_rCarImg_rCarImgNum.nextval,?)";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getRcarImgName());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
}
