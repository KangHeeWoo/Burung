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
@WebServlet("/semi/list.do")
public class MemberList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd.equals("memberlist")) {
			MemberDao dao=new MemberDao();
			ArrayList<MemberVo> list= dao.listAll();
			request.setAttribute("list", list);
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
		//
	}
}
