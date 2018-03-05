package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardCommentDao;
import board.vo.BoardCommentVo;

@WebServlet("/boardcomment.do")
public class BoardCommentController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    String cmd = request.getParameter("cmd");
	    
	    switch(cmd) {
	      case "comminsert":boardcomminsert(request,response);
	        break;
	      case "commlist": boardcommlist(request,response);
	      	break;
	      case "commdelete": boardcommdelete(request,response);
	      	break;
	      }
	 }
	protected void boardcommdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bcomnum=Integer.parseInt(request.getParameter("bcomnum"));
		BoardCommentDao dao=BoardCommentDao.getinstance();
		int n=dao.commdelete(bcomnum);
		
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.println("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.println("</result>");
		pw.close();
	}
	protected void boardcommlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardCommentDao dao=BoardCommentDao.getinstance();
		System.out.println(request.getParameter("boanum"));
		int boanum=Integer.parseInt(request.getParameter("boanum"));
		ArrayList<BoardCommentVo> list=dao.list(boanum);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.println("<result>");
		for(BoardCommentVo vo:list) {
			String id=dao.insertmem(vo.getMemNum());
			pw.println("<comm>");
			pw.println("<id>"+ id +"</id>");
			pw.println("<bcomnum>"+ vo.getbComNum() +"</bcomnum>");
			pw.println("<comcon>"+ vo.getbComCon() +"</comcon>");
			pw.println("<comregd>"+ vo.getbComRegd()+"</comregd>");
			pw.println("</comm>");
		}	
		pw.println("</result>");
		pw.close();
	}
	protected void boardcomminsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=(String)request.getSession().getAttribute("id");
		String bcomcon=request.getParameter("comment");
		int boanum=Integer.parseInt(request.getParameter("boanum"));
		
		BoardCommentDao dao=BoardCommentDao.getinstance();
		//세션아이디로 회원 번호 찾기
		int memnum=dao.memnum(id);
		
		System.out.println("회원번호"+memnum);
		
		int n=dao.bcommentinsert(bcomcon, memnum, boanum);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.print("<result>");
		if(n>0) {
			pw.print("<code>success</code>");
		}else {
			pw.print("<code>fail</code>");
		}
		pw.print("</result>");
		pw.close();
		
	}
}
