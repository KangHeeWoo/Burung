package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import admin.vo.ScarImgVo;
import burung.dbcp.DbcpBean;

public class ScarImgDao {
	public int insert(ScarImgVo vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into scarimg values(SEQ_sCarImg_sCarImgNum.nextval,?,?)";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getScarimgName());
			pstmt.setInt(2, vo.getSalNum());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
}
