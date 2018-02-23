package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.vo.NoticeVo;
import board.dao.NoticeDao;

@WebServlet("/notice.do")
public class NoticeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		switch(cmd) {
			case "noticelist":noticelist(request,response);
			break;
			case "noticeDetail" :noticedetail(request,response);
			break;
		}
	}
	
	protected void noticedetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int notnum=Integer.parseInt(request.getParameter("notNum"));
		String memname=request.getParameter("memName");
		NoticeDao dao=NoticeDao.getInstance();
		NoticeVo detail=dao.detail(notnum);
		
		request.setAttribute("detail", detail);
		request.setAttribute("memname", memname);
		request.getRequestDispatcher("jsp/layout.jsp?spage=Board/noticeDetail.jsp").forward(request, response);
		
		
	}
	protected void noticelist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String spageNum=request.getParameter("pageNum"); 
		  int pageNum=1;
		  if(spageNum!=null) {
		         pageNum=Integer.parseInt(spageNum);
		  }
		  int startRow=(pageNum-1)*10+1; 
		  int endRow=startRow+9;
		  
		  
		  NoticeDao dao=NoticeDao.getInstance();
		  ArrayList<NoticeVo> listAll=dao.noticelist(startRow, endRow);
		  int pageCount=(int)Math.ceil(dao.getCount()/10.0);
	      int startPage=((pageNum-1)/10*10)+1;
	      int endPage=startPage+9;//끝페이지
	     
	      if(pageCount<endPage) {
				endPage=pageCount;
	      }
	      
	      request.setAttribute("listAll", listAll);
	      request.setAttribute("pageCount", pageCount);
	      request.setAttribute("startPage", startPage);
	      request.setAttribute("endPage", endPage);
	      request.setAttribute("pageNum", pageNum);
	      request.getRequestDispatcher("jsp/layout.jsp?spage=Board/noticelist.jsp").forward(request, response);
	}
}
