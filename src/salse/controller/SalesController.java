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
		String model = request.getParameter("model");

		switch (model) {
		case "718":
			choiceName(request, response);
			break;			
		case "911":
			choiceName(request, response);
			break;
		case "Panamera":
			choiceName(request, response);
			break;
		case "Macan":
			choiceName(request, response);
			break;
		case "Cayenne":
			choiceName(request, response);
			break;
		}
	}
	
	private void choiceName (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String path = "jsp/layout.jsp?spage=sales/sales.jsp";
		
		if(name != null) {
			path += "&name" + name;
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
