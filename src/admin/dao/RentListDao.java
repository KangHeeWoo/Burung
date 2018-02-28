package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import admin.vo.RentListVo;
import burung.dbcp.DbcpBean;
import members.vo.MemRentListVo;


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
	
	public int getCount() {//전체 렌트차량수
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(rlistnum) cnt from rentlist";
		try {
			con= DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
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
	//렌트 전체 리스트
	public ArrayList<RentListVo> rentlist(int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rl.*,rownum rnum from(select * from rentlist order by rlistnum desc)rl)rl,rentcar rc where rc.rennum=rl.rennum and rnum>=? and rnum<=? order by rlistnum desc";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
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
				int memNum=rs.getInt("memNum");
				RentListVo vo=new RentListVo(rListNum, rStartDate, rEndDate, rTotPrice, renState, renNum,rCarName,memNum);
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
	//렌트세부내역
	public ArrayList<RentListVo> rentDetail(int memNum,int startRow,int endRow) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rl.*,rownum rnum from(select * from rentlist where memnum=? order by rlistnum desc)rl)rl, rentcar rc where rl.rennum=rc.rennum and rnum>=? and rnum<=? order by rlistnum desc";
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
	
	public ArrayList<MemRentListVo> rentDetail1(int memNum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<MemRentListVo> list=new ArrayList<>();
		try {
			con=DbcpBean.getConn();
			
			String sql = "select rListNum, to_char(rStartDate, 'YYYY-MM-DD HH24:MI') as rStartDate,"
					+ "to_char(rEndDate, 'YYYY-MM-DD HH24:MI') as rEndDate, rTotPrice, renState,"
					+ "l.renNum, rCarName"
					+ " from rentlist l, rentcar c where l.memnum=? and l.rennum=c.rennum order by rlistnum desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNum);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int rListNum=rs.getInt(1);
				String rStartDate = rs.getString(2);
				String rEndDate = rs.getString(3);
				int rTotPrice=rs.getInt(4);
				String renState=rs.getString(5);
				int renNum=rs.getInt(6);
				String rCarName=rs.getString(7);
				MemRentListVo vo = new MemRentListVo(rListNum, rStartDate, rEndDate, rTotPrice, renState, renNum, rCarName, memNum);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
			return null;
		}finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
	
	public int stateChange(int rlistnum,int state) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="";
		if(state==1) {
			sql="update rentlist set renstate='렌트대기' where rlistnum=?";
		}else if(state==2) {
			sql="update rentlist set renstate='렌트중' where rlistnum=?";
		}else {
			sql="update rentlist set renstate='반납' where rlistnum=?";
		}
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rlistnum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
	
	public ArrayList<RentListVo> rentlist(String sysdate){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from rentlist rl,rentcar rc where rl.rennum=rc.rennum and rstartdate between to_date(?, 'YYYY/MM/DD HH24:MI') and to_date(?, 'YYYY/MM/DD HH24:MI')";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sysdate+"00:00");
			pstmt.setString(2, sysdate+"23:59");
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
				int memNum=rs.getInt("memNum");
				RentListVo vo=new RentListVo(rListNum, rStartDate, rEndDate, rTotPrice, renState, renNum,rCarName,memNum);
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
