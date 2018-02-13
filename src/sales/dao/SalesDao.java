package sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import burung.dbcp.DbcpBean;
import sales.vo.SalesVo;

public class SalesDao {
	private static SalesDao instance = new SalesDao();
	
	private SalesDao() {}
	
	public static SalesDao getInstance() {
		return instance;
	}
	
	public ArrayList<String> modelList(String sCarModel){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<>();

		try {
			con = DbcpBean.getConn();

			String sql = "select scarname from salescar where scarmodel = ?";
			st = con.prepareStatement(sql);
			st.setString(1, sCarModel);
			rs = st.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}

			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(con, st, rs);
		}
	}
	
	public SalesVo search(String sCarName) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			con = DbcpBean.getConn();

			String sql = "select * from "
					+ "(select salNum, sCarName, sCarModel, salCnt from SALESCAR where SCARNAME=?), "
					+ "(select scarimgname smainimg from scarimg s where s.salNum in("
					+ "select salNum from SALESCAR where SCARNAME=?) and SCARIMGNAME like '%main%'), "
					+ "(select scarimgname ssubimg from scarimg s where s.salNum in("
					+ "select salNum from SALESCAR where SCARNAME=?) and SCARIMGNAME like '%sub%')";
			st = con.prepareStatement(sql);
			st.setString(1, sCarName);
			st.setString(2, sCarName);
			st.setString(3, sCarName);
			rs = st.executeQuery();
			rs.next();

			return new SalesVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
					rs.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(con, st, rs);
		}
	}
}
