package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.RevImgDao;
import board.dao.ReviewDao;
import board.vo.ReviewVo;
import board.vo.Review_ImgVo;
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
	      case "reviewlist":reviewlist(request,response);
	      	break;
	      case "reviewdetaile":reviewdetail(request,response);
	      
	      }
	 
	}
	protected void reviewdetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		 int revnum=Integer.parseInt(request.getParameter("revnum"));
		// String memid=request.getParameter("memid");
		 
		 ReviewDao dao=ReviewDao.getInstance();
		 // hit ������Ʈ
		 int n=dao.reviewhitupdate(revnum);

		 if(n>0) {
			 //�⺻ carimgname �̿��� ������ ��������
			 Review_ImgVo reviewdetail=dao.detail(revnum);
			 // revnum�� ���� carimgname�� �ҷ�����
			 ArrayList<String> recarimglist=dao.imglist(revnum);
			 
			 System.out.println("carlist�̸���:"+recarimglist);
			 
			 request.setAttribute("reviewdetail", reviewdetail);
			 request.setAttribute("recarimglist", recarimglist);
			 request.getRequestDispatcher("jsp/layout.jsp?spage=Board/reviewDetail.jsp").forward(request, response);
			 
		 }
		 
		 
	}
	protected void reviewlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spageNum=request.getParameter("pageNum");
		//�˻���ɳֱ�
		
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		
		//�˻� ���ǰ˻�
		
		ReviewDao dao=ReviewDao.getInstance();
		ArrayList<ReviewVo> listAll=dao.listAll(startRow, endRow);
		
		System.out.println("listAll:"+listAll);
		
		int pageCount=(int)Math.ceil(dao.getCount()/10.0);
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		request.setAttribute("listAll",listAll );
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("jsp/layout.jsp?spage=Board/reviewlist.jsp").forward(request, response);
	}
	
	protected void reviewinsertOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uploadPath=request.getServletContext().getRealPath("/jsp/Board/img");
		MultipartRequest mr=new MultipartRequest(
				request, //request��ü
				uploadPath,	//���ε��� ���� ���
				1024*1024*5,	//�ִ� ���ε� ũ��(����Ʈ ������ ����)
				"utf-8",	//���ڵ����
				new DefaultFileRenamePolicy());	//������ �����̸��� ������ ���ϸ��ڿ� 1,2,..���� ���̱�
				
				int memNum=Integer.parseInt(mr.getParameter("memnum"));
				String memid=mr.getParameter("memid");
				String revTitle=mr.getParameter("title");
				String revContent=mr.getParameter("content");
				int revScore=Integer.parseInt(mr.getParameter("revscore"));
				String carname=mr.getParameter("carname");
				
				
				
				//System.out.println("���� ���ε� ���� �޾ƿ��°� ��ȣ"+memNum+"���̵�"+memid+" ����"+revTitle+"����"+revContent+"���ھ�"+revScore+"����"+carname);
				
				ReviewVo vo=new ReviewVo(0, revTitle, revContent, revScore, 0, null, memNum, carname);
				ReviewDao dao=ReviewDao.getInstance();
				//������ revnum������
				int revnum=dao.reviewinsert(vo);
				//�̹��� insert
				RevImgDao daoimg=RevImgDao.getInstance();
				
				int cnt=Integer.parseInt(mr.getParameter("cnt"));
				
				for(int i=0;i<cnt;i++) {
					String recarimgname=mr.getFilesystemName("file"+i);
					daoimg.revimginsert(recarimgname, revnum);
				}
				response.sendRedirect(request.getContextPath()+"/review.do?cmd=reviewlist");
				
				
				
				
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