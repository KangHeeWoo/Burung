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
	public int getCount(int memNum) {//전체 회원수
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(rlistnum) cnt from rentlist where memnum=?";
		try {
			con= DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNum);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt("cnt");
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
	//
	public ArrayList<RentListVo> rentDetail(int memNum,int startRow,int endRow) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rl.*,rownum rnum from(select * from rentlist order by rlistnum desc)rl)rl, rentcar rc where memnum=? and rl.rennum=rc.rennum and rnum>=? and rnum<=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			ArrayList<RentListVo> list=new ArrayList<>();
			while(rs.next()) {
				int rListNum=rs.getInt("rListNum");
				Date rStartDate=rs.getDate("rStartDate");
				Date rEndDate=rs.getDate("rEndDate");
				int rTotPrice=rs.getInt("rTotPrice");
				String renState=rs.getString("renState");
				int renNum=rs.getInt("renNum");
				String rCarName=rs.getString("rCarName");
				RentListVo vo=new RentListVo(rListNum, rStartDate, rEndDate, rTotPrice, renState, renNum,rCarName);
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
	
	public ArrayList<RentListVo> rentDetail1(int memNum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rl.*,rownum rnum from(select * from rentlist order by rlistnum desc)rl)rl, rentcar rc where memnum=? and rl.rennum=rc.rennum and rnum>=1 and rnum<=100";
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
				String rCarName=rs.getString("rCarName");
				RentListVo vo=new RentListVo(rListNum, rStartDate, rEndDate, rTotPrice, renState, renNum,rCarName);
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
