package company.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		}
	}
}
