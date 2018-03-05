package admin.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import admin.vo.SalesCarVo;
import burung.dbcp.DbcpBean;

public class SalesCarDao {
	public int getCount() {//
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(salnum) cnt from salescar";
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
	
	public ArrayList<SalesCarVo> list(int startRow,int endRow){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select sc.*,rownum rnum from(select * from salescar order by salnum desc)sc) where rnum>=? and rnum<=?";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<SalesCarVo> list=new ArrayList<>();
			while(rs.next()) {
				int salNum=rs.getInt("salNum");
				String scarName=rs.getString("scarName");
				String scarModel=rs.getString("scarModel");
				int salCnt=rs.getInt("salCnt");
				int scarPrice=rs.getInt("scarPrice");
				SalesCarVo vo=new SalesCarVo(salNum, scarName, scarModel, salCnt, scarPrice);
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
	
	public int carinsert(SalesCarVo vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into salescar values(SEQ_salesCar_salNum.nextval,?,?,?,?)";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getScarName());
			pstmt.setString(2, vo.getScarModel());
			pstmt.setInt(3, vo.getSalCnt());
			pstmt.setInt(4, vo.getScarPrice());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
	public int searchNum(String scarName) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select salNum from salescar where scarName=?";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, scarName);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt("salNum");
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}
	
	public int carUpdate(String carName,int price,int cnt) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update salescar set salcnt=salcnt+?,scarprice=? where scarname=?";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cnt);
			pstmt.setInt(2, price);
			pstmt.setString(3, carName);
			
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
}
