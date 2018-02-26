package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.MemberVo;
import burung.dbcp.DbcpBean;



public class MemberDao {
	//
	public int getCount() {//전체 회원수
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(memnum) cnt from members";
		try {
			con= DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt("cnt")-1;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
	
	public int searchCount(String search, int sel) {//검색회원수
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = DbcpBean.getConn();
			if (sel == 1) {
				sql = "select NVL(count(memnum),0) cnt from (select aa.*,rownum rnum from(select * from members order by memnum desc)aa where memname like ?)";

			} else if (sel == 2) {
				sql = "select NVL(count(memnum),0) cnt from (select aa.*,rownum rnum from(select * from members order by memnum desc)aa where memid like ?)";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			System.out.println("카운트:" + cnt);
			return cnt;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DbcpBean.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<MemberVo> listAll(int startRow,int endRow){	//전체 회원 리스트
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select aa.*,rownum rnum from(select * from members order by memnum desc)aa) where rnum>=? and rnum<=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
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
	
	public ArrayList<MemberVo> memDetail(int memNum) {	//회원정보 상세보기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from members where memNum=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNum);
			rs=pstmt.executeQuery();
			ArrayList<MemberVo> list=new ArrayList<>();
			if(rs.next()) {
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
	public ArrayList<MemberVo> searchmember(String searchname,int startRow,int endRow){//회원검색 이름으로
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select aa.*,rownum rnum from(select * from members where memname like ? order by memnum desc)aa) where rnum>=? and rnum<=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchname+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
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
	
	public ArrayList<MemberVo> searchmemid(String search,int startRow,int endRow){//회원검색 아이디
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select aa.*,rownum rnum from(select * from members where memid like ? order by memnum desc)aa) where rnum>=? and rnum<=?";
		try {
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
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
}
