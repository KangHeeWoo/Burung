package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.SaleListVo;
import burung.dbcp.DbcpBean;

public class SaleListDao {
	//
	public ArrayList<SaleListVo> saleDetatil(int memNum){	//회원 구매내역 상세보기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from salesList where memNum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNum);
			rs=pstmt.executeQuery();
			ArrayList<SaleListVo> list=new ArrayList<>();
			while(rs.next()) {
				int sListNum=rs.getInt("sListNum");
				int salPrice=rs.getInt("salPrice");
				String salState=rs.getString("salState");
				int salNum=rs.getInt("salNum");
				SaleListVo vo=new SaleListVo(sListNum, salPrice, salState, salNum);
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
