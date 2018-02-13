package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.MemberVo;
import burung.dbcp.DbcpBean;


public class MemberDao {
	public int getCount() {//전체 회원수
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(num) cnt from members";
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
	
	public ArrayList<MemberVo> listAll(){	//전체 회원 리스트
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from members";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<MemberVo> list=new ArrayList<>();
			while(rs.next()) {
				int memNum=rs.getInt("memNum");
				String memId=rs.getString("memId");
				String memAddr=rs.getString("memAddr");
				String memPhone=rs.getString("memPhone");
				String memEmail=rs.getString("memEmail");
				String memBirth=rs.getString("memBirth");
				String memName=rs.getString("memName");
				
				MemberVo vo=new MemberVo(memNum, memId, memAddr, memPhone, memEmail, memBirth, memName);
				list.add(vo);
			}
			return list;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
	
	public MemberVo memDetail(int memNum) {	//회원정보 상세보기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from members where memNum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNum);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String memId=rs.getString("memId");
				String memAddr=rs.getString("memAddr");
				String memPhone=rs.getString("memPhone");
				String memEmail=rs.getString("memEmail");
				String memBirth=rs.getString("memBirth");
				String memName=rs.getString("memName");
				MemberVo vo=new MemberVo(memNum, memId, memAddr, memPhone, memEmail, memBirth, memName);
				return vo;
			}else {
				return null;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
	

}
