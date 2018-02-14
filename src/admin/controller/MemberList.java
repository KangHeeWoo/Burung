package admin.controller;
//
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.MemberDao;
import admin.dao.RentListDao;
import admin.dao.SaleListDao;
import admin.vo.MemberVo;
import admin.vo.RentListVo;
import admin.vo.SaleListVo;
import board.dao.BoardDao;
import board.vo.BoardVo;
@WebServlet("/semi/list.do")
public class MemberList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd.equals("memberlist")) {
			String spageNum=request.getParameter("pageNum");
			int pageNum=1;
			if(spageNum!=null) {
				pageNum=Integer.parseInt(spageNum);
			}
			int startRow=(pageNum-1)*10+1;//�������ȣ
			int endRow=startRow+9;//�����ȣ
			
			MemberDao dao=new MemberDao();
			ArrayList<MemberVo> list= dao.listAll(startRow,endRow);
			
			//��ü������ ���� ���ϱ�
			int pageCount=(int)Math.ceil(dao.getCount()/10.0);
			int startPage=((pageNum-1)/4*4)+1;//���������� ��ȣ
			int endPage=startPage+3;// �������� ��ȣ	//4������
			if(pageCount<endPage) {
				endPage=pageCount;
			}
			
			request.setAttribute("list", list);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageNum", pageNum);
			request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminMember/memberlist.jsp").forward(request, response);
		}else if(cmd.equals("memberdetail")) {
			int memNum=Integer.parseInt(request.getParameter("memNum"));
			MemberDao dao=new MemberDao();
			ArrayList<MemberVo> detail= dao.memDetail(memNum);
			RentListDao rdao=new RentListDao();
			ArrayList<RentListVo> rentdetail= rdao.rentDetail(memNum);
			SaleListDao sdao=new SaleListDao();
			ArrayList<SaleListVo> saledetail=sdao.saleDetatil(memNum);
			request.setAttribute("detail", detail);
			request.setAttribute("rentlist", rentdetail);
			request.setAttribute("salelist", saledetail);
			request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminMember/memberdetail.jsp").forward(request, response);
		}
	}
}
