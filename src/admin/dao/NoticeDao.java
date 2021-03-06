package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.NoticeVo;
import burung.dbcp.DbcpBean;

public class NoticeDao {
	private static NoticeDao instance=new NoticeDao();
	
	private NoticeDao() {
	}
	public static NoticeDao getInstance() {
		return instance;
	}
	
	public int getCount() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(notnum) cnt from notice";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt("cnt");
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}
	
	public int insert(NoticeVo vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into notice values(SEQ_notice_notNum.nextval,?,?,0,sysdate,0)";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNotTitle());
			pstmt.setString(2, vo.getNotContent());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
	
	public int hit(int notnum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update notice set nothit=nothit+1 where notnum=?";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, notnum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
	
	public ArrayList<NoticeVo> list(int startRow,int endRow){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select aa.*,rownum rnum from(select * from notice order by notnum desc)aa)aa,members ms where ms.memnum=aa.memnum and rnum>=? and rnum<=? order by notnum desc";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<NoticeVo> list=new ArrayList<>();
			while(rs.next()) {
				int notNum=rs.getInt("notNum");
				String notTitle=rs.getString("notTitle");
				String notContent=rs.getString("notContent");
				int notHit=rs.getInt("notHit");
				Date notRegd=rs.getDate("notRegd");
				int memNum=rs.getInt("memNum");
				String memName=rs.getString("memName");
				NoticeVo vo=new NoticeVo(notNum, notTitle, notContent, notHit, notRegd, memNum,memName);
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
	
	public NoticeVo detail(int notNum){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from notice nt,members ms where nt.memnum=ms.memnum and notnum=?";
		hit(notNum);
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, notNum);
			rs=pstmt.executeQuery();
			ArrayList<NoticeVo> list=new ArrayList<>();
			while(rs.next()) {
				String notTitle=rs.getString("notTitle");
				String notContent=rs.getString("notContent");
				int notHit=rs.getInt("notHit");
				Date notRegd=rs.getDate("notRegd");
				int memNum=rs.getInt("memNum");
				String memName=rs.getString("memName");
				NoticeVo vo=new NoticeVo(notNum, notTitle, notContent, notHit, notRegd, memNum,memName);
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.close(conn, pstmt, rs);
		}
	}
	public int update(NoticeVo vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update notice set nottitle=?,notcontent=? where notnum=?";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNotTitle());
			pstmt.setString(2, vo.getNotContent());
			pstmt.setInt(3, vo.getNotNum());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
	
	public int delete(int notNum) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from notice where notnum=?";
		try {
			conn=DbcpBean.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, notNum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.close(conn, pstmt, null);
		}
	}
	
}
