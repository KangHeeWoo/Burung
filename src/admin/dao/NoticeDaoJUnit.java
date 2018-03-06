package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.vo.NoticeVo;
import test.DBCConnection;

public class NoticeDaoJUnit {
	public ArrayList<NoticeVo> list(int startRow,int endRow){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select aa.*,rownum rnum from(select * from notice order by notnum desc)aa)aa,members ms where ms.memnum=aa.memnum and rnum>=? and rnum<=? order by notnum desc";
		try {
			conn=DBCConnection.getConn();
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
			DBCConnection.closeConn(conn, pstmt, rs);
		}
	}
}
