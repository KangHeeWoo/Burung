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
			pstmt.setInt(3, vo.getMemNum());
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
		String sql="update set notice hit=hit+1 where notnum=?";
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
		String sql="select * from (select aa.*,rownum rnum from(select * from notice order by notnum desc)aa) where rnum>=? and rnum<=?";
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
				NoticeVo vo=new NoticeVo(notNum, notTitle, notContent, notHit, notRegd, memNum);
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
	
	public ArrayList<NoticeVo> detail(int notNum){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from notice where notnum=?";
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
				NoticeVo vo=new NoticeVo(notNum, notTitle, notContent, notHit, notRegd, memNum);
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
