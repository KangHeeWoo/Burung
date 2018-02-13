package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.BoardMemVo;
import burung.dbcp.DbcpBean;

public class BoardDao {
	
	private static BoardDao instance=new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstance() {
		return instance;
	}
	
	public ArrayList<BoardMemVo> listAll(){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BoardMemVo> list=new ArrayList<>();
		try {
			conn=DbcpBean.getConn();
			String sql="select boatitle,boahit,boaRegd,m.memId memId from board b,members m where m.memNum=b.memNum";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String boatitle=rs.getString("boatitle");
				int boahit=rs.getInt("boahit");
				Date boaRegd=rs.getDate("boaRegd");
				String memid=rs.getString("memid");
				BoardMemVo vo=new BoardMemVo(boatitle, boahit, boaRegd, memid);
				list.add(vo);
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
