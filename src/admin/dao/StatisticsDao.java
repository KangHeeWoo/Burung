package admin.dao;

import java.sql.Connection;
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
		String sql="select * from(select count(rlistnum)cnt,rennum from rentlist group by rennum)rl,rentcar rc where rl.rennum=rc.rennum";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<StatisticsVo> list=new ArrayList<>();
			while(rs.next()) {
				int cnt=rs.getInt("cnt");
				int renNum=rs.getInt("renNum");
				String rcarName=rs.getString("rcarName");
				int rlistNum=rs.getInt("renNum");
				String rcarModel=rs.getString("rcarModel");
				StatisticsVo vo=new StatisticsVo(rcarModel, rcarName, rlistNum, null, cnt);
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
