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
         String sql = "select NVL(count(boanum),0) count from board";
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
   public int boardupdate(int boanum,String boatitle,String boacontent) {
	   Connection conn=null;
	   PreparedStatement pstmt=null;
	   try {
		   conn=DbcpBean.getConn();
		   String sql="update board set boatitle=?,boacontent=? where boanum=?";
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setString(1, boatitle);
		   pstmt.setString(2, boacontent);
		   pstmt.setInt(3, boanum);
		   int n=pstmt.executeUpdate();
		   return n;
	   }catch(SQLException se) {
		   System.out.println(se.getMessage());
		   return -1;
	   }finally {
		   DbcpBean.close(conn, pstmt, null);
	   }
   }
   public int boardhitupdate(int boanum) {
	   Connection conn=null;
	   PreparedStatement pstmt=null;
	   try {
		   conn=DbcpBean.getConn();
		   String sql="update board set boahit=boahit+1 where boanum=?";
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setInt(1, boanum);
		   int n=pstmt.executeUpdate();
		   return n;
	   }catch(SQLException se) {
		   System.out.println(se.getMessage());
		   return -1;
	   }finally {
		   DbcpBean.close(conn, pstmt, null);
	   }
   }
   public BoardVo detaillist(int boanum){
	   Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      try {
	         conn=DbcpBean.getConn();
	         String sql="select * from board where boanum=?";
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setInt(1, boanum);
	         rs=pstmt.executeQuery();
	         rs.next();
	         BoardVo vo=new BoardVo(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4),rs.getDate(5), rs.getInt(6));
	         return vo;
	      }catch(SQLException se) {
	         System.out.println(se.getMessage());
	         se.printStackTrace();
	         return null;
	      }finally {
	         DbcpBean.close(conn, pstmt, rs);
	      }
   }
   public int boarddelete(int boanum) {
	   Connection conn=null;
	   PreparedStatement pstmt=null;
	   try {
		   conn=DbcpBean.getConn();
		   String sql="delete from board where boanum=?";
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setInt(1, boanum);
		   int n=pstmt.executeUpdate();
		   return n;
	   }catch(SQLException se) {
		   System.out.println(se.getMessage());
		   return -1;
	   }finally {
		   DbcpBean.close(conn, pstmt, null);
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
   
   public ArrayList<BoardMemVo> listAll(int startRow,int endRow,String search,String searchValue){
      Connection conn=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      ArrayList<BoardMemVo> list=new ArrayList<>();
      try {
         conn=DbcpBean.getConn();
         String sql="";
         if(search.equals("1")) {
        	 sql="select * from(select aa.*,rownum rnum from(select boanum, boatitle,boahit,boaRegd,m.memid memid from board b,members m where m.memNum=b.memNum order by boaregd desc)aa)where rnum>=? and rnum<=?";
        	 pstmt=conn.prepareStatement(sql);
        	 pstmt.setInt(1, startRow);
        	 pstmt.setInt(2, endRow);
         }else {
        	 sql="select * from(select aa.*,rownum rnum from(select boanum, boatitle,boahit,boaRegd,m.memid memid from board b,members m where m.memNum=b.memNum and " +search+ " like ? order by boaregd desc)aa)where rnum>=? and rnum<=?"; 
        	 pstmt=conn.prepareStatement(sql);
        	 pstmt.setString(1, "%"+searchValue+"%");
        	 pstmt.setInt(2, startRow);
        	 pstmt.setInt(3, endRow);
         }
         rs=pstmt.executeQuery();
         while(rs.next()) {
        	int boanum=rs.getInt("boanum");
            String boatitle=rs.getString("boatitle");
            int boahit=rs.getInt("boahit");
            Date boaRegd=rs.getDate("boaRegd");
            String memid=rs.getString("memid");
            BoardMemVo vo=new BoardMemVo(boanum,boatitle, boahit, boaRegd, memid);
            list.add(vo);
            System.out.println(vo);
            
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