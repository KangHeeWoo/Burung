package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.MemberDao;
import admin.vo.MemberVo;
@WebServlet("/semi/list.do")
public class MemberList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		if(cmd.equals("memberlist")) {
			MemberDao dao=new MemberDao();
			ArrayList<MemberVo> list= dao.listAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/layout.jsp?spage=/adminMember/memberlist.jsp").forward(request, response);
		}else if(cmd.equals("memberdetail")) {
			int memNum=Integer.parseInt(request.getParameter("memNum"));
			MemberDao dao=new MemberDao();
			MemberVo detail= dao.memDetail(memNum);
			request.setAttribute("detail", detail);
			request.getRequestDispatcher("/adminMember/memberdetail.jsp").forward(request, response);
		}
		//
	}
}
