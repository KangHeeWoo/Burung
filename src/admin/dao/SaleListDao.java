package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.SaleListVo;
import burung.dbcp.DbcpBean;

public class SaleListDao {
	public int getCount(int memNum) {//전체 
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(slistnum) cnt from saleslist where memnum=?";
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
	
	public int getCount() {//전체 
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(slistnum) cnt from saleslist";
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
	//
	public ArrayList<SaleListVo> saleDetatil(int memNum,int startRow,int endRow){	//회원 구매내역 상세보기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//order by 삭제함
		String sql="select * from (select sl.*,rownum rnum from(select * from saleslist where memnum=? order by slistnum desc)sl)sl, salescar sc where sl.salnum=sc.salnum and rnum>=? and rnum<=? order by slistnum desc";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			ArrayList<SaleListVo> list=new ArrayList<>();
			while(rs.next()) {
				int sListNum=rs.getInt("sListNum");
				int salPrice=rs.getInt("salPrice");
				String salState=rs.getString("salState");
				int salNum=rs.getInt("salNum");
				Date salDate=rs.getDate("salDate");
				String sCarModel=rs.getString("scarName");
				SaleListVo vo=new SaleListVo(sListNum, salPrice, salState, salNum, salDate, sCarModel);
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
	
	public ArrayList<SaleListVo> saleDetatil(int memNum){	//회원 구매내역 상세보기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SaleListVo> list = new ArrayList<>();
		
		try {
			con = DbcpBean.getConn();
			
			String sql = "select * from saleslist l, salescar c where l.memnum=? and l.salnum = c.salnum order by l.salnum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int sListNum=rs.getInt("sListNum");
				int salPrice=rs.getInt("salPrice");
				String salState=rs.getString("salState");
				int salNum=rs.getInt("salNum");
				Date salDate=rs.getDate("salDate");
				String sCarModel=rs.getString("scarName");
				SaleListVo vo=new SaleListVo(sListNum, salPrice, salState, salNum, salDate, sCarModel);
				list.add(vo);
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<SaleListVo> list(int startRow,int endRow){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select sl.*,rownum rnum from(select * from saleslist order by slistnum desc)sl)sl, salescar sc where sl.salnum=sc.salnum and rnum>=? and rnum<=?";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<SaleListVo> list=new ArrayList<>();
			while(rs.next()) {
				int sListNum=rs.getInt("sListNum");
				int salPrice=rs.getInt("salPrice");
				String salState=rs.getString("salState");
				int salNum=rs.getInt("salNum");
				Date salDate=rs.getDate("salDate");
				String sCarModel=rs.getString("scarName");
				SaleListVo vo=new SaleListVo(sListNum, salPrice, salState, salNum, salDate, sCarModel);
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
	
	public int stateChange(int slistNum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update saleslist set salstate='인수완료' where slistnum=?";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, slistNum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
}
