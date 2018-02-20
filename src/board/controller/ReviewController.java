package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.ReviewDao;
import members.dao.MembersDao;

@WebServlet("/review.do")
public class ReviewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    String cmd = request.getParameter("cmd");
	    int num=Integer.parseInt(request.getParameter("num"));
	    System.out.println("cmd:"+cmd+"num:"+request.getParameter("num"));
	    switch(cmd) {
	      case "reviewinsert":
		    	  if(num==1) {
		    		  reviewsinsert(request,response);
		    	  }else if(num==2) {
		    		  reviewrinsert(request,response);
		    	  }
	        break;
	      
	      }
	 
	}
	
	//구매차량 insert
	protected void reviewsinsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//마이페이지에서 받아온 구매차량 번호
		int slistnum=Integer.parseInt(request.getParameter("slistnum"));
		ReviewDao dao=ReviewDao.getInstance();
		String scarname=dao.scarname(slistnum);
		
		//작성자 num 얻어오기
		String id=(String)request.getSession().getAttribute("id");
		MembersDao daomem=MembersDao.getInstance();
		int memnum=daomem.memnum(id);
		
		//insert로 넘기기
		request.setAttribute("memnum", memnum);
		request.setAttribute("scarname", scarname);
		request.setAttribute("num", Integer.parseInt(request.getParameter("num")));
		
		request.getRequestDispatcher("/jsp/layout.jsp?spage=Board/reviewinsert.jsp").forward(request, response);
	}
	
	//렌트차량 insert
	protected void reviewrinsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//렌트 차량 번호
		int rlistnum=Integer.parseInt(request.getParameter("rlistnum"));
		ReviewDao dao=ReviewDao.getInstance();
		String rcarname=dao.rcarname(rlistnum);
		
		//작성자 num 얻어오기
		String id=(String)request.getSession().getAttribute("id");
		MembersDao daomem=MembersDao.getInstance();
		int memnum=daomem.memnum(id);
		
		//insert로 넘기기
		request.setAttribute("memnum", memnum);
		request.setAttribute("rcarname", rcarname);
		request.setAttribute("num", Integer.parseInt(request.getParameter("num")));
		request.getRequestDispatcher("/jsp/layout.jsp?spage=Board/reviewinsert.jsp").forward(request, response);
	}
}
