package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.NoticeVo;
import burung.dbcp.DbcpBean;

public class NoticeDao {
	private static NoticeDao instance=new NoticeDao();
	private NoticeDao() {}
	public static NoticeDao getInstance() {
		return instance;
	}
	
	 public int getMaxNum() {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         conn =DbcpBean.getConn();
	         String sql = "select NVL(max(notnum),0) maxnum from notice";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         rs.next();
	         int maxnum = rs.getInt("maxnum");
	         return maxnum;
	      }catch (SQLException se) {
	         return -1;
	      }finally {
	         DbcpBean.close(conn, pstmt, rs);
	      }
	   }


	   public int getCount() {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         conn = DbcpBean.getConn();
	         String sql = "select NVL(count(notnum),0) count from notice";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         rs.next();
	         int count = rs.getInt("count");
	         System.out.println(count);
	         return count;
	      } catch (SQLException se) {
	         return -1;
	      } finally {
	         DbcpBean.close(conn, pstmt, rs);
	      }
	   }
	   
	   public ArrayList<NoticeVo> noticelist(int startRow,int endRow){

		   Connection conn=null;
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   ArrayList<NoticeVo> list=new ArrayList<>();
		   try {
			   conn=DbcpBean.getConn();
			   String sql="select * from(select aa.*,rownum rnum from (select notnum, nottitle,nothit,notRegd,m.memid memid,notcontent,m.memnum memnum from notice n,members m where m.memNum=n.memNum order by notregd desc)aa)where rnum>=? and rnum<=?";
			   pstmt=conn.prepareStatement(sql);
			   pstmt.setInt(1, startRow);
			   pstmt.setInt(2, endRow);
			   rs=pstmt.executeQuery();
			   while(rs.next()) {

				   NoticeVo vo=new NoticeVo(rs.getInt("notnum"), rs.getString("notTitle"),rs.getString("notcontent"),rs.getInt("nothit"),
						   rs.getDate("notRegd"),rs.getInt("memnum"),rs.getString("memid"));

				   list.add(vo);
			   }
			   return list;
		   }catch(SQLException se) {
			   se.printStackTrace();
			   return null;
		   }finally {
			   DbcpBean.close(conn, pstmt, rs);
		   }
	   }
	   public NoticeVo detail(int notnum) {
		   Connection conn=null;
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   try {
			   conn=DbcpBean.getConn();
			   String sql="update notice set nothit=nothit+1 where notnum=? ";
			   pstmt=conn.prepareStatement(sql);
			   pstmt.setInt(1, notnum);
			   int n=pstmt.executeUpdate();
			   if(n>0) {
				   String sql1="select nottitle,notcontent,nothit,notregd from notice where notnum=?";
				   pstmt=conn.prepareStatement(sql1);
				   pstmt.setInt(1, notnum);
				   rs=pstmt.executeQuery();
				   rs.next();
				   NoticeVo vo=new NoticeVo(0, rs.getString("nottitle"), rs.getString("notContent"), rs.getInt("notHit"), rs.getDate("notRegd"), 0, null);
				   System.out.println("noticeµðÅ×ÀÏ"+vo);
				   return vo;
			   }
			   return null;
		   }catch(SQLException se) {
			   se.printStackTrace();
			   return null;
		   }finally {
			   DbcpBean.close(conn, pstmt, rs);
		   }
		   
	   }
}
