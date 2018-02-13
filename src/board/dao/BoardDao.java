package board.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.BoardMemVo;
import board.vo.BoardVo;
import burung.dbcp.DbcpBean;

   
public class BoardDao {
   
   private static BoardDao instance=new BoardDao();
   private BoardDao() {}
   public static BoardDao getInstance() {
      return instance;
   }
   public int getMaxNum() {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         conn =DbcpBean.getConn();
         String sql = "select NVL(max(num),0) maxnum from board";
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
         String sql = "select NVL(count(num),0) count from board";
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
   public ArrayList<BoardVo> detaillist(int boanum){
	   Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      ArrayList<BoardVo> list=new ArrayList<>();
	      try {
	         conn=DbcpBean.getConn();
	         String sql="select * from board where boanum=?";
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setInt(1, boanum);
	         rs=pstmt.executeQuery();
	         if(rs.next()) {
	        	int boanum1=rs.getInt(1);
	        	String boatitle=rs.getString(2);
	        	String boacontent=rs.getString(3);
	        	int boahit=rs.getInt(4);
	        	Date boaregd=rs.getDate(5);
	        	int memnum=rs.getInt(6);
	        	BoardVo vo=new BoardVo(boanum1, boatitle, boacontent, boahit+1, boaregd, memnum);
	        	list.add(vo);
	         }
	         return list;
	      }catch(SQLException se) {
	         System.out.println(se.getMessage());
	         se.printStackTrace();
	         return null;
	      }finally {
	         DbcpBean.close(conn, pstmt, rs);
	      }
   }
   public int memNum(String id) {
	   Connection conn=null;
	   PreparedStatement pstmt=null;
	   ResultSet rs=null;
	   try {
		   conn=DbcpBean.getConn();
		   String sql="select memnum from members where memid=? ";
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setString(1, id);
		   rs=pstmt.executeQuery();
		   rs.next();
		   return rs.getInt("memnum");
	   
	   }catch(SQLException se) {
		   System.out.println(se.getMessage());
		   return -1;
	   }finally {
		   DbcpBean.close(conn, pstmt, rs);
	   }
   }
   public int boardinsert(String boatitle,String boacontent,int memnum) {
	   Connection conn=null;
	   PreparedStatement pstmt=null;

	   try {
		   conn=DbcpBean.getConn();
		   String sql="insert into board values(seq_board_boanum.nextval,?,?,0,sysdate,?) ";
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setString(1,boatitle);
		   pstmt.setString(2, boacontent);
		   pstmt.setInt(3, memnum);
		   int n=pstmt.executeUpdate();
		   return n;
	   }catch(SQLException se) {
		   System.out.println(se.getMessage());
		   return -1;
	   }finally {
		   DbcpBean.close(conn, pstmt, null);
	   }
   }
   
   public ArrayList<BoardMemVo> listAll(int startRow,int endRow){
      Connection conn=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      ArrayList<BoardMemVo> list=new ArrayList<>();
      try {
         conn=DbcpBean.getConn();
         String sql="select * from(select aa.*,rownum rnum from(select boanum, boatitle,boahit,boaRegd,m.memid memid from board b,members m where m.memNum=b.memNum)aa)where rnum>=? and rnum<=?";
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
         rs=pstmt.executeQuery();
         while(rs.next()) {
        	int boanum=rs.getInt("boanum");
            String boatitle=rs.getString("boatitle");
            int boahit=rs.getInt("boahit");
            Date boaRegd=rs.getDate("boaRegd");
            String memid=rs.getString("memid");
            BoardMemVo vo=new BoardMemVo(boanum,boatitle, boahit, boaRegd, memid);
            list.add(vo);
         }
         System.out.println(list);
         return list;
         
      }catch(SQLException se) {
         System.out.println(se.getMessage());
         se.printStackTrace();
         return null;
      }finally {
         DbcpBean.close(conn, pstmt, rs);
      }
   }
}