package admin.controller;

import java.io.IOException;
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
		String cmd=request.getParameter("cmd");
		if(cmd.equals("insert")) {
			
		}else if(cmd.equals("noticelist")) {
			list(request,response);
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
}
