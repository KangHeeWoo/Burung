package rent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import test.DBCConnection;

public class RentDaoJUnit {
	public ArrayList<String> rentList(String sDate, String eDate, String model){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<>();
		
		try {
			con = DBCConnection.getConn();
			
			String sql = "select distinct rcarname from rentcar where rennum not in(select rennum from rentlist where rstartdate < to_date(?, 'YYYY-MM-DD HH24:MI') and renddate > to_date(?, 'YYYY-MM-DD HH24:MI')) and rcarmodel like ? and state='Á¤»ó' order by rcarname";
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
			DBCConnection.closeConn(con, st, rs);
		}
	}
}
