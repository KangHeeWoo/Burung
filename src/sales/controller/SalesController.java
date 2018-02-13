package sales.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import sales.dao.SalesDao;
import sales.vo.SalesVo;

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
		case "loadData":
			loadData(request, response);
			break;
		}
	}
	
	private void choiceName (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String model = request.getParameter("model");
		String name = request.getParameter("name");
		String path = "/jsp/layout.jsp?spage=sales/sales.jsp&model=" + model;
		
		if(name != null) {
			path += "&name=" + name;
		}
		
		response.sendRedirect(request.getContextPath() + path);
	}
	
	private void loadData (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String model = request.getParameter("model");
		String mName = request.getParameter("mName");

		SalesDao dao = SalesDao.getInstance();
		SalesVo vo = dao.search(mName);
		ArrayList<String> list = dao.modelList(model);
		
		response.setContentType("text/plain;utf-8");
		PrintWriter pw = response.getWriter();
		
		JSONArray arr = new JSONArray();
		
		for(String name : list) {
			arr.put(name);
		}
		
		JSONObject json = new JSONObject();
		json.put("name", vo.getsCarName());
		json.put("mainImg", vo.getsMainImg());
		json.put("subImg", vo.getsSubImg());
		json.put("list", arr);
		
		pw.print(json);
		pw.close();
	}
}