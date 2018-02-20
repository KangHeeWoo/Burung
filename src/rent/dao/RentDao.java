package rent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import burung.dbcp.DbcpBean;
import rent.vo.RentVo;

public class RentDao {
	private static RentDao instance = new RentDao();
	
	private RentDao() {}
	
	public static RentDao getInstance() {
		return instance;
	}
	
	public RentVo rentCar(String cName) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			
			String sql = "select * from rentcar where rennum=(select min(rennum) from rentcar where rcarname = ? and state='정상')";
			st = con.prepareStatement(sql);
			st.setString(1, cName);
			rs = st.executeQuery();
			
			rs.next();
			return new RentVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			DbcpBean.close(con, st, rs);
		}
	}
	
	public ArrayList<String> rentList(String sDate, String eDate, String model){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<>();
		
		try {
			con = DbcpBean.getConn();
			
			String sql = "select distinct rcarname from rentcar where rennum not in(select rennum from rentlist where rstartdate < to_date(?, 'YYYY-MM-DD HH24:MI') and renddate > to_date(?, 'YYYY-MM-DD HH24:MI')) and rcarmodel like ? and state='정상' order by rcarname";
			st = con.prepareStatement(sql);
			st.setString(1, eDate);
			st.setString(2, sDate);
			st.setString(3, "%" + model + "%");
			rs = st.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			DbcpBean.close(con, st, rs);
		}
	}
}
