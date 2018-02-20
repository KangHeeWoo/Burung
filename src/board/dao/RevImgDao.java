package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import board.vo.RevImgVo;
import burung.dbcp.DbcpBean;

public class RevImgDao {
	private static RevImgDao instance = new RevImgDao();

	private RevImgDao() {
	}

	public static RevImgDao getInstance() {
		return instance;
	}
	
	public void revimginsert(String recarimgname,int revnum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DbcpBean.getConn();
			String sql="insert into revimg values(0,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, recarimgname);
			pstmt.setInt(2, revnum);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
}
