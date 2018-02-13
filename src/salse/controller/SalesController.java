package salse.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sales.do")
public class SalesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		
		switch (cmd) {
		case "choiceName":
			choiceName(request, response);
			break;
		//case "
		}
	}
	
	private void choiceName (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String model = request.getParameter("model");
		String name = request.getParameter("name");
		String path = "/jsp/layout.jsp?spage=sales/sales.jsp&model=" + model;
		
		System.out.println(name);
		
		if(name != null) {
			path += "&name=" + name;
		}
		
		response.sendRedirect(request.getContextPath() + path);
	}
}
