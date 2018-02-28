package admin.controller;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import admin.dao.NoticeDao;
import admin.vo.NoticeVo;
@WebServlet("/semi/notice.do")
public class NoticeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd.equals("insert")) {
			insert(request,response);
		}else if(cmd.equals("noticelist")) {
			list(request,response);
		}else if(cmd.equals("write")) {
			request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminNotice/noticewrite.jsp").forward(request, response);
		}else if(cmd.equals("detail")) {
			detail(request,response);
		}
	}
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String spagenum=request.getParameter("pagenum");
		int pagenum=1;
		if(spagenum!=null) {
			pagenum=Integer.parseInt(spagenum);
		}
		int startRow=(pagenum-1)*10+1;
		int endRow=startRow+9;
		NoticeDao dao=NoticeDao.getInstance();
		ArrayList<NoticeVo> list=dao.list(startRow, endRow);
		
		int pageCount=(int)Math.ceil(dao.getCount()/10.0);
		int startPage=((pagenum-1)*5/5)+1;
		int endPage=startPage+4;
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		request.setAttribute("list", list);
		request.setAttribute("pagenum", pagenum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminNotice/adminnotice.jsp").forward(request, response);
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String notTitle=request.getParameter("notTitle");
		String notContent=request.getParameter("notContent");
		
		NoticeVo vo=new NoticeVo(0, notTitle, notContent, 0, null, 0);
		NoticeDao dao=NoticeDao.getInstance();
		int n=dao.insert(vo);
		if(n>0) {
			response.sendRedirect(request.getContextPath()+"/semi/notice.do?cmd=noticelist");
		}else {
			System.out.println("½ÇÆÐ");
		}
	}
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int notNum=Integer.parseInt(request.getParameter("notNum"));
		NoticeDao dao=NoticeDao.getInstance();
		NoticeVo vo=dao.detail(notNum);
		int memNum=vo.getMemNum();
		String notTitle=vo.getNotTitle();
		String notContent=vo.getNotContent();
		Date notRegd=vo.getNotRegd();
		int notHit=vo.getNotHit();
		String memName=vo.getMemName();
		request.setAttribute("notTitle", notTitle);
		request.setAttribute("notContent", notContent);
		request.setAttribute("notRegd", notRegd);
		request.setAttribute("notHit", notHit);
		request.setAttribute("memName", memName);
		request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminNotice/noticedetail.jsp").forward(request, response);
		
	}
}
