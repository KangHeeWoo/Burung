package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardCommentDao;

@WebServlet("/boardcomment.do")
public class BoardCommentController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    String cmd = request.getParameter("cmd");
	    
	    switch(cmd) {
	      case "comminsert":boardcomminsert(request,response);
	         break;
	      
	      }
	 }
	protected void boardcomminsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=(String)request.getSession().getAttribute("id");
		String bcomcon=request.getParameter("comment");
		int boanum=Integer.parseInt(request.getParameter("boanum"));
		
		BoardCommentDao dao=BoardCommentDao.getinstance();
		//세션아이디로 회원 번호 찾기
		int memnum=dao.memnum(id);
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
