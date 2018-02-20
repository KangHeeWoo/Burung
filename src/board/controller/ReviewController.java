package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.ReviewDao;
import board.vo.ReviewVo;
import members.dao.MembersDao;

@WebServlet("/review.do")
public class ReviewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    String cmd = request.getParameter("cmd");
	    
	    switch(cmd) {
	      case "reviewinsert":
	    	  int num=Integer.parseInt(request.getParameter("num"));
		    	  if(num==1) {
		    		  reviewsinsert(request,response);
		    	  }else if(num==2) {
		    		  reviewrinsert(request,response);
		    	  }
	        break;
	      case "reviewinsertOk": reviewinsertOk(request,response);
	      	break;
	      
	      }
	 
	}
	protected void reviewinsertOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uploadPath=request.getServletContext().getRealPath("/jsp/Board/img");
		MultipartRequest mr=new MultipartRequest(
				request, //request객체
				uploadPath,	//업로드할 파일 경로
				1024*1024*5,	//최대 업로드 크기(바이트 단위로 설정)
				"utf-8",	//인코딩방식
				new DefaultFileRenamePolicy());	//동일한 파일이름의 들어오면 파일명뒤에 1,2,..숫자 붙이기
				
				int memNum=Integer.parseInt(mr.getParameter("memnum"));
				String memid=mr.getParameter("memid");
				String revTitle=mr.getParameter("title");
				String revContent=mr.getParameter("content");
				int revScore=Integer.parseInt(mr.getParameter("revscore"));
				String carname=mr.getParameter("carname");
				
				
				
				//System.out.println("파일 업로드 정보 받아오는가 번호"+memNum+"아이디"+memid+" 제목"+revTitle+"내용"+revContent+"스코어"+revScore+"차종"+carname);
				
				
				ReviewVo vo=new ReviewVo(0, revTitle, revContent, revScore, 0, null, memNum);
				ReviewDao dao=ReviewDao.getInstance();
				int n=dao.reviewinsert(vo);
				
				
				
				
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
		
		//System.out.println("번호"+memnum);
		
		//insert로 넘기기
		request.setAttribute("memnum", memnum);
		request.setAttribute("carname", scarname);
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
		request.setAttribute("carname", rcarname);
		request.setAttribute("num", Integer.parseInt(request.getParameter("num")));
		request.getRequestDispatcher("/jsp/layout.jsp?spage=Board/reviewinsert.jsp").forward(request, response);
	}
}
