package board.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.BoardMemVo;
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
   
   public ArrayList<BoardMemVo> listAll(int startRow,int endRow){
      Connection conn=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      ArrayList<BoardMemVo> list=new ArrayList<>();
      try {
         conn=DbcpBean.getConn();
         String sql="select * from(select aa.*,rownum rnum from(select boanum boatitle,boahit,boaRegd,m.memId memId from board b,members m where m.memNum=b.memNum)aa)where rnum>=? and rnum<=?";
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
         return list;
      }catch(SQLException se) {
         System.out.println(se.getMessage());
         return null;
      }finally {
         DbcpBean.close(conn, pstmt, rs);
      }
   }
}