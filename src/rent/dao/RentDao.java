package rent.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import burung.dbcp.DbcpBean;
import rent.vo.RentListVo;
import rent.vo.RentVo;

public class RentDao {
	private static RentDao instance = new RentDao();
	
	private RentDao() {}
	
	public static RentDao getInstance() {
		return instance;
	}
	
	public int addRentList(RentListVo vo) {
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			con = DbcpBean.getConn();
			
			String sql = "insert into rentlist values(0, ?, ?, ?, '렌트대기', ?, ?)";
			st = con.prepareStatement(sql);
			st.setDate(1, vo.getrStartDate());
			st.setDate(2, vo.getrEndDate());
			st.setInt(3, vo.getrTotPrice());
			st.setInt(4, vo.getMemNum());
			st.setInt(5, vo.getRenNum());
						
			return st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}finally {
			DbcpBean.close(con, st, null);
		}
	}
	
	public int getCarNum(String cName, String sDate, String eDate) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = DbcpBean.getConn();
			String sql = "select min(rennum) from rentcar where rennum not in(select rennum from rentlist where rstartdate < to_date(?, 'YYYY-MM-DD HH24:MI') and renddate > to_date(?, 'YYYY-MM-DD HH24:MI')) and rcarname = ? and state='정상' order by rcarname";
			st = con.prepareStatement(sql);
			st.setString(1, eDate);
			st.setString(2, sDate);
			st.setString(3, cName);
			rs = st.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DbcpBean.close(con, st, rs);
		}
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
		
		System.out.println(sDate);
		System.out.println(eDate);
		System.out.println(model);
		
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