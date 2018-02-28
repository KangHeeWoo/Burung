package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.StatisticsVo;
import burung.dbcp.DbcpBean;

public class StatisticsDao {
	private static StatisticsDao instance=new StatisticsDao();
	
	private StatisticsDao() {
	}
	public static StatisticsDao getInstance() {
		return instance;
	}
	public ArrayList<StatisticsVo> getCount() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//String sql="select * from(select count(rlistnum)cnt,rennum from rentlist group by rennum)rl,rentcar rc where rl.rennum=rc.rennum";
		String sql="select rc.rcarname,count(*) cnt from rentlist rl,rentcar rc where rl.rennum=rc.rennum group by rc.rcarname";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<StatisticsVo> list=new ArrayList<>();
			while(rs.next()) {
				int cnt=rs.getInt("cnt");
				String rcarName=rs.getString("rcarName");
		
				StatisticsVo vo=new StatisticsVo(null, rcarName, 0, null, cnt);
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
	//select * from(select count(rlistnum)cnt,rennum from rentlist where rstartdate between to_date('2018/02/18', 'YYYY/MM/DD') and to_date('2018/02/27', 'YYYY/MM/DD') group by rennum)rl,rentcar rc where rl.rennum=rc.rennum;
	
	public ArrayList<StatisticsVo> showCount(String sDate,String eDate) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select rc.rcarname,count(*) cnt from(select * from rentlist where rstartdate between to_date(?, 'YYYY/MM/DD HH24:MI') and to_date(?, 'YYYY/MM/DD HH24:MI'))rl,rentcar rc where rl.rennum=rc.rennum group by rc.rcarname";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, sDate+" 00:00");
			pstmt.setString(2, eDate+" 23:59");
			rs=pstmt.executeQuery();
			ArrayList<StatisticsVo> list=new ArrayList<>();
			while(rs.next()) {
				int cnt=rs.getInt("cnt");
				
				String rcarName=rs.getString("rcarName");
			
				StatisticsVo vo=new StatisticsVo(null, rcarName, 0, null, cnt);
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
