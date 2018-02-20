package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.RentCarVo;
import burung.dbcp.DbcpBean;


public class RentCarDao {
	private static RentCarDao instance=new RentCarDao();
	
	private RentCarDao() {
	}
	public static RentCarDao getInstance() {
		return instance;
	}
	public int getCount() {//전체 렌트등록차량수
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(rennum) cnt from rentcar";
		try {
			con= DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt("cnt");
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<RentCarVo> list(int startRow,int endRow){//렌트등록차량 리스트
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rc.*,rownum rnum from(select * from rentcar order by rennum desc)rc) where rnum>=? and rnum<=?";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<RentCarVo> list=new ArrayList<>();
			while(rs.next()) {
				int renNum=rs.getInt("renNum");
				String rcarName=rs.getString("rcarName");
				String rcarModel=rs.getString("rcarModel");
				int timePay=rs.getInt("timePay");
				String state=rs.getString("state");
				RentCarVo vo=new RentCarVo(renNum, rcarName, rcarModel, timePay, state);
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
