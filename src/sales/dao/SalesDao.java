package sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import burung.dbcp.DbcpBean;
import sales.vo.SalesListVo;
import sales.vo.SalesLogVo;
import sales.vo.SalesVo;

public class SalesDao {
	private static SalesDao instance = new SalesDao();

	private SalesDao() {
	}

	public static SalesDao getInstance() {
		return instance;
	}

	public int sales(SalesListVo vo) {
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;

		try {
			con = DbcpBean.getConn();

			String sql = "insert into saleslist values(0, ?, ?, ?, ?, sysdate)";
			st = con.prepareStatement(sql);
			st.setInt(1, vo.getSalPrice());
			st.setString(2, "인수대기");
			st.setInt(3, vo.getMemNum());
			st.setInt(4, vo.getSalNum());
			st.executeUpdate();

			sql = "select max(slistnum) from saleslist";
			st2 = con.prepareStatement(sql);
			rs = st2.executeQuery();
			rs.next();

			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			DbcpBean.close(null, st2, rs);
			DbcpBean.close(con, st, null);
		}
	}

	public int sSelOpt(String opt, int sNum) {
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;

		try {
			con = DbcpBean.getConn();

			String sql = "select optnum from caroption where optinfo = ?";
			st = con.prepareStatement(sql);
			st.setString(1, opt);
			rs = st.executeQuery();
			rs.next();

			sql = "insert into selectoption values(0, ?, ?)";
			st2 = con.prepareStatement(sql);
			st2.setInt(1, sNum);
			st2.setInt(2, rs.getInt(1));

			return st2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			DbcpBean.close(st2);
			DbcpBean.close(con, st, rs);
		}
	}

	public int sMemNum(String id) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			con = DbcpBean.getConn();

			String sql = "select memnum from members where memid = ?";
			st = con.prepareStatement(sql);
			st.setString(1, id);
			rs = st.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			DbcpBean.close(con, st, rs);
		}
	}

	public HashMap<String, Integer> sCar(String name) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		HashMap<String, Integer> carInfo = new HashMap<>();

		try {
			con = DbcpBean.getConn();

			String sql = "select salnum, scarprice from salescar where scarname = ?";
			st = con.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();
			rs.next();
			carInfo.put("salNum", rs.getInt(1));
			carInfo.put("sCarPrice", rs.getInt(2));

			return carInfo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(con, st, rs);
		}
	}

	public ArrayList<String> modelList(String sCarModel) {
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

			while (rs.next()) {
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
					+ "(select salNum, sCarName, sCarModel, salCnt, scarprice from SALESCAR where SCARNAME=?), "
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

			return new SalesVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
					rs.getString(6), rs.getString(7));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(con, st, rs);
		}
	}

	public ArrayList<SalesLogVo> logList(String sysdate) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<SalesLogVo> list = new ArrayList<>();

		System.out.println("sysdate : " + sysdate);
		
		try {
			con = DbcpBean.getConn();

			String sql = "select memid, scarname, salprice, saldate from saleslist l, salescar s, members m where l.salnum = s.salnum and l.memnum = m.memnum and saldate between to_date(?, 'YYYYMMDD HH24:MI') and to_date(?, 'YYYYMMDD HH24:MI')";

			st = con.prepareStatement(sql);
			st.setString(1, sysdate + " 00:00");
			st.setString(2, sysdate + " 23:59");
			rs = st.executeQuery();

			while (rs.next()) {
				list.add(new SalesLogVo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(con, st, rs);
		}
	}
}
