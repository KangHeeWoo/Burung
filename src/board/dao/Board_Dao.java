package board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.BoardMemVo;
import test.DBCConnection;

public class Board_Dao {
	public ArrayList<BoardMemVo> listAll(int startRow,int endRow,String search,String searchValue){
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      ArrayList<BoardMemVo> list=new ArrayList<>();
	      try {
	         conn=DBCConnection.getConn();
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
	            //System.out.println(vo);
	            
	         }
	         //System.out.println(list);
	         return list;
	         
	      }catch(SQLException se) {
	         System.out.println(se.getMessage());
	         se.printStackTrace();
	         return null;
	      }finally {
	         DBCConnection.closeConn(conn, pstmt, rs);
	      }
	}
}
