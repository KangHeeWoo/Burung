package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.SelectOptionVo;
import burung.dbcp.DbcpBean;

public class SelectOptionDao {
	public ArrayList<SelectOptionVo> show(int sListNum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from selectoption so,caroption co where slistnum=? and so.optnum=co.optnum";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sListNum);
			rs=pstmt.executeQuery();
			ArrayList<SelectOptionVo> list=new ArrayList<>();
			while(rs.next()) {
				int optNum=rs.getInt("optNum");
				int sOptNum=rs.getInt("sOptNum");
				String optType=rs.getString("optType");
				String optInfo=rs.getString("optInfo");
				int optPrice=rs.getInt("optPrice");
				SelectOptionVo vo=new SelectOptionVo(sListNum, optNum, sOptNum, optType, optInfo, optPrice);
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
