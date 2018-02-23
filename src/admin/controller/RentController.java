package admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.dao.RcarImgDao;
import admin.dao.RentCarDao;
import admin.dao.RentListDao;
import admin.dao.SalesCarDao;
import admin.dao.ScarImgDao;
import admin.vo.RcarImgVo;
import admin.vo.RentCarVo;
import admin.vo.RentListVo;
import admin.vo.SalesCarVo;
import admin.vo.ScarImgVo;
import members.vo.MemRentListVo;
@WebServlet("/semi/rent.do")
public class RentController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		if(cmd.equals("rentlist")) {
			rentcarlist(request,response);
			//rentlist(request,response);
		}else if(cmd.equals("insert")) {
			request.setCharacterEncoding("utf-8");
			String uploadPath=request.getServletContext().getRealPath("/admin/img");
			MultipartRequest mr=new MultipartRequest(
			request, //request��ü
			uploadPath,	//���ε��� ���� ���
			1024*1024*5,	//�ִ� ���ε� ũ��(����Ʈ ������ ����)
			"utf-8",	//���ڵ����
			new DefaultFileRenamePolicy());	//������ �����̸��� ������ ���ϸ�ڿ� 1,2,..���� ���̱�
			String rcarName=mr.getParameter("rcarName");
			String rcarModel=mr.getParameter("rcarModel");
			int timePay=Integer.parseInt(mr.getParameter("timePay"));

			RentCarVo vo=new RentCarVo(0, rcarName, rcarModel, timePay, null);
			RentCarDao dao=RentCarDao.getInstance();
			dao.insert(vo);
			//������ ���ϸ� ������
			String mainorg=mr.getOriginalFileName("main");
			String suborg=mr.getOriginalFileName("sub");
			//���۵� ���� VO��ü�� ���
			RcarImgVo mainvo=new RcarImgVo(0, mainorg);
			RcarImgVo subvo=new RcarImgVo(0, suborg);
			//DB�� �������� �����ϱ�
			RcarImgDao imgdao=new RcarImgDao();
			int n1=imgdao.insert(mainvo);
			int n2=imgdao.insert(subvo);
			if(n1>0 && n2>0) {
				response.sendRedirect(request.getContextPath()+"/semi/rent.do?cmd=rentlist");
			}else {
					
			}
		}
	}
	private void rentcarlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rpagenum1=request.getParameter("rpagenum"); //��ϵ� ��Ʈ����
		
		int rpagenum2=1;
		if(rpagenum1!=null) {
			rpagenum2=Integer.parseInt(rpagenum1);
		}
		int rstartRow=(rpagenum2-1)*10+1;
		int rendRow=rstartRow+9;
		
		RentCarDao rdao=RentCarDao.getInstance();
		ArrayList<RentCarVo> rlist=rdao.list(rstartRow, rendRow);
		int rpageCount=(int)Math.ceil(rdao.getCount()/10.0);
		int rstartPage=((rpagenum2-1)/4*4)+1;
		int rendPage=rstartPage+4;
		if(rpageCount<rendPage) {
			rendPage=rpageCount;
		}
		
		request.setAttribute("rlist", rlist);//��Ʈ�������
		request.setAttribute("rpagenum2", rpagenum2);
		request.setAttribute("rpageCount", rpageCount);
		request.setAttribute("rstartPage", rstartPage);
		request.setAttribute("rendPage", rendPage);
		
		String rlpagenum1=request.getParameter("rlpagenum");	//�ֱ� ��Ʈ����������
		int rlpagenum2=1;
		if(rlpagenum1!=null) {
			rlpagenum2=Integer.parseInt(rlpagenum1);
		}
		int rlstartRow=(rlpagenum2-1)*5+1;
		int rlendRow=rlstartRow+4;
		
		RentListDao rldao=new RentListDao();
		ArrayList<RentListVo> rllist= rldao.rentlist(rlstartRow, rlendRow);
		//ArrayList<MemRentListVo> rllist=rldao.
		int rlpageCount=(int)Math.ceil(rldao.getCount()/5.0);
		int rlstartPage=((rlpagenum2-1)/5*5)+1;
		int rlendPage=rlstartPage+4;
		if(rlendPage>rlpageCount) {
			rlendPage=rlpageCount;
		}
		
		request.setAttribute("rentlist", rllist);
		request.setAttribute("rlpagenum2", rlpagenum2);
		request.setAttribute("rlpageCount", rlpageCount);
		request.setAttribute("rlstartPage", rlstartPage);
		request.setAttribute("rlendPage", rlendPage);
		
		request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminRent/rentcar.jsp").forward(request, response);
	}
}
