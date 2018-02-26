package company.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.dao.CompanyDao;
import company.vo.CompanyVo;

@WebServlet("/company.do")
public class CompanyController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd=req.getParameter("cmd");
		
		if(cmd.equals("introCompany")) {
			resp.sendRedirect("jsp/layout.jsp?spage=company/introCompany.jsp");
		}else if(cmd.equals("historyCompany")) {
			resp.sendRedirect("jsp/layout.jsp?spage=company/historyCompany.jsp");
		}else if(cmd.equals("mapCompany")) {
			resp.sendRedirect("jsp/layout.jsp?spage=company/mapCompany.jsp");
		}else if(cmd.equals("popup1")) {
			popup(req,resp);
		}else if(cmd.equals("popup2")) {
			popup(req,resp);
		}else if(cmd.equals("popup3")) {
			popup(req,resp);
		}else if(cmd.equals("popup4")) {
			popup(req,resp);
		}else if(cmd.equals("popup5")) {
			popup(req,resp);
		}
	}
	
	private void popup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CompanyDao dao = CompanyDao.getInstance();
		String offNum = req.getParameter("offnum");
		System.out.println(offNum);
		CompanyVo vo = dao.popup(Integer.parseInt(offNum));
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println("<result>");
		pw.println("<pop>");
		pw.println("<name>"+vo.getOffName()+"</name>");
		pw.println("<addr>"+vo.getOffAddr()+"</addr>");
		pw.println("<tel>"+vo.getOffTel()+"</tel>");
		pw.println("<email>"+vo.getOffEmail()+"</email>");
		pw.println("<info>"+vo.getOffInfo()+"</info>");
		pw.println("</pop>");
		pw.println("</result>");

		
	}
}
