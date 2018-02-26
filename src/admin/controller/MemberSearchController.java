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

@WebServlet("/semi/search.do")
public class MemberSearchController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String spagenum=request.getParameter("spageNum");
		String search=request.getParameter("search");
		int find=Integer.parseInt(request.getParameter("find"));
		ArrayList<MemberVo> list=new ArrayList<>();
		int pagenum=1;
		if(spagenum!=null) {
			pagenum=Integer.parseInt(spagenum);
		}
		int startRow=(pagenum-1)*10+1;//�������ȣ
		int endRow=startRow+9;//�����ȣ
		
		
		MemberDao dao=new MemberDao();
		if(find==1) {
			list=dao.searchmember(search,startRow,endRow);
		}else if(find==2) {
			list=dao.searchmemid(search,startRow,endRow);
		}
		
		//��ü������ ���� ���ϱ�
		int pageCount=(int)Math.ceil(dao.searchCount(search, find)/10.0);
		int startPage=((pagenum-1)/4*4)+1;//���������� ��ȣ
		int endPage=startPage+3;// �������� ��ȣ	//4������
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		
		request.setAttribute("spageNum", pagenum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("search", search);
		request.setAttribute("find", find);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminMember/membersearch.jsp").forward(request, response);
	}
}
