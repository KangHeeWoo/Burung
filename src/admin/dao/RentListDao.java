package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.RentListVo;
import burung.dbcp.DbcpBean;


public class RentListDao {
	//
	public ArrayList<RentListVo> rentDetail(int memNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from rentList where memNum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNum);
			rs=pstmt.executeQuery();
			ArrayList<RentListVo> list=new ArrayList<>();
			while(rs.next()) {
				int rListNum=rs.getInt("rListNum");
				Date rStartDate=rs.getDate("rStartDate");
				Date rEndDate=rs.getDate("rEndDate");
				int rTotPrice=rs.getInt("rTotPrice");
				String renState=rs.getString("renState");
				int renNum=rs.getInt("renNum");
				RentListVo vo=new RentListVo(rListNum, rStartDate, rEndDate, rTotPrice, renState, renNum);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
}
