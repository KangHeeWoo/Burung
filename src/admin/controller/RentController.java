package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.RentCarDao;
import admin.vo.RentCarVo;
@WebServlet("/semi/rent.do")
public class RentController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		if(cmd.equals("rentlist")) {
			rentlist(request,response);
		}
	}
	private void rentlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rpagenum1=request.getParameter("rpagenum");
		
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
		
		request.setAttribute("rlist", rlist);//렌트등록차량
		request.setAttribute("rpagenum2", rpagenum2);
		request.setAttribute("rpageCount", rpageCount);
		request.setAttribute("rstartPage", rstartPage);
		request.setAttribute("rendPage", rendPage);
		request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminRent/rentcar.jsp").forward(request, response);
	}
}
