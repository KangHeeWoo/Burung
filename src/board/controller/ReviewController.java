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
				request, //request��ü
				uploadPath,	//���ε��� ���� ���
				1024*1024*5,	//�ִ� ���ε� ũ��(����Ʈ ������ ����)
				"utf-8",	//���ڵ����
				new DefaultFileRenamePolicy());	//������ �����̸��� ������ ���ϸ�ڿ� 1,2,..���� ���̱�
				
				int memNum=Integer.parseInt(mr.getParameter("memnum"));
				String memid=mr.getParameter("memid");
				String revTitle=mr.getParameter("title");
				String revContent=mr.getParameter("content");
				int revScore=Integer.parseInt(mr.getParameter("revscore"));
				String carname=mr.getParameter("carname");
				
				
				
				//System.out.println("���� ���ε� ���� �޾ƿ��°� ��ȣ"+memNum+"���̵�"+memid+" ����"+revTitle+"����"+revContent+"���ھ�"+revScore+"����"+carname);
				
				
				ReviewVo vo=new ReviewVo(0, revTitle, revContent, revScore, 0, null, memNum);
				ReviewDao dao=ReviewDao.getInstance();
				int n=dao.reviewinsert(vo);
				
				
				
				
	}
	//�������� insert
	protected void reviewsinsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������������� �޾ƿ� �������� ��ȣ
		int slistnum=Integer.parseInt(request.getParameter("slistnum"));
		ReviewDao dao=ReviewDao.getInstance();
		String scarname=dao.scarname(slistnum);
		
		//�ۼ��� num ������
		String id=(String)request.getSession().getAttribute("id");
		MembersDao daomem=MembersDao.getInstance();
		int memnum=daomem.memnum(id);
		
		//System.out.println("��ȣ"+memnum);
		
		//insert�� �ѱ��
		request.setAttribute("memnum", memnum);
		request.setAttribute("carname", scarname);
		request.setAttribute("num", Integer.parseInt(request.getParameter("num")));
		
		request.getRequestDispatcher("/jsp/layout.jsp?spage=Board/reviewinsert.jsp").forward(request, response);
	}
	
	//��Ʈ���� insert
	protected void reviewrinsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��Ʈ ���� ��ȣ
		int rlistnum=Integer.parseInt(request.getParameter("rlistnum"));
		ReviewDao dao=ReviewDao.getInstance();
		String rcarname=dao.rcarname(rlistnum);
		
		//�ۼ��� num ������
		String id=(String)request.getSession().getAttribute("id");
		MembersDao daomem=MembersDao.getInstance();
		int memnum=daomem.memnum(id);
		
		//insert�� �ѱ��
		request.setAttribute("memnum", memnum);
		request.setAttribute("carname", rcarname);
		request.setAttribute("num", Integer.parseInt(request.getParameter("num")));
		request.getRequestDispatcher("/jsp/layout.jsp?spage=Board/reviewinsert.jsp").forward(request, response);
	}
}
