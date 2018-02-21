package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import board.dao.ReviewCommentDao;

import board.vo.ReviewCommentVo;

@WebServlet("/reviewcomment.do")
public class ReviewCommentController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    String cmd = request.getParameter("cmd");
	    
	    switch(cmd) {
	      case "comminsert":reviewcomminsert(request,response);
	        break;
	      case "commlist": reviewcommlist(request,response);
	      	break;
	      case "commdelete": reviewcommdelete(request,response);
	      	break;
	      }
	    
	}
	protected void reviewcommdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rcomnum=Integer.parseInt(request.getParameter("rcomnum"));
		ReviewCommentDao dao=ReviewCommentDao.getinstance();
		int n=dao.commdelete(rcomnum);
		
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
	protected void reviewcommlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReviewCommentDao dao=ReviewCommentDao.getinstance();
		int revnum=Integer.parseInt(request.getParameter("revnum"));
		ArrayList<ReviewCommentVo> list=dao.list(revnum);
	
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.println("<result>");
		for(ReviewCommentVo vo:list) {
			String id=dao.insertmem(vo.getMemNum());
			pw.println("<comm>");
			pw.println("<id>"+ id +"</id>");
			pw.println("<rcomnum>"+ vo.getrComNum() +"</rcomnum>");
			pw.println("<comcon>"+ vo.getrComCon() +"</comcon>");
			pw.println("<comregd>"+ vo.getrComRegd()+"</comregd>");
			pw.println("</comm>");
		}	
		pw.println("</result>");
		pw.close();
	}
	protected void reviewcomminsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=(String)request.getSession().getAttribute("id");
		String rcomcon=request.getParameter("comment");
		int revnum=Integer.parseInt(request.getParameter("revnum"));
		
		ReviewCommentDao dao=ReviewCommentDao.getinstance();

		//세션아이디로 회원 번호 찾기
		int memnum=dao.memnum(id);
		
		//System.out.println("회원번호"+memnum);
		
		int n=dao.rcommentinsert(rcomcon, memnum, revnum);
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

