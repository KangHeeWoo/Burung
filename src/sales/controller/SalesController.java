package sales.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import sales.dao.SalesDao;
import sales.vo.SalesListVo;
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
		case "buy":
			buy(request, response);
			break;
		}
	}
	
	private void buy (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getSession().getAttribute("id");
		String carName = request.getParameter("name");
		String[] color = request.getParameter("color").split(":");
		String[] wheel = request.getParameter("wheel").split(":");
		String[] seet = request.getParameter("seet").split(":");
		String[] light = request.getParameter("light").split(":");
		String[] audio = request.getParameter("audio").split(":");
		
		SalesDao dao = SalesDao.getInstance();
		int sMemNum = dao.sMemNum(id);
		HashMap<String, Integer> carInfo = dao.sCar(carName);
		int price = carInfo.get("sCarPrice")
					+ Integer.parseInt(color[1].trim())
					+ Integer.parseInt(wheel[1].trim())
					+ Integer.parseInt(seet[1].trim())
					+ Integer.parseInt(light[1].trim())
					+ Integer.parseInt(audio[1].trim());
		
		int sNum = dao.sales(new SalesListVo(0, price, null, sMemNum, carInfo.get("salNum"), null));
		
		dao.sSelOpt(color[0].trim(), sNum);
		dao.sSelOpt(wheel[0].trim(), sNum);
		dao.sSelOpt(seet[0].trim(), sNum);
		dao.sSelOpt(light[0].trim(), sNum);
		dao.sSelOpt(audio[0].trim(), sNum);
		
		response.sendRedirect(request.getContextPath() + "/jsp/layout.jsp");
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
		
		if(list != null) {
			for (String name : list) {
				arr.put(name);
			}
		}
		
		JSONObject json = new JSONObject();
		json.put("name", vo.getsCarName());
		json.put("mainImg", vo.getsMainImg());
		json.put("subImg", vo.getsSubImg());
		json.put("price", vo.getsCarPrice());
		json.put("list", arr);
		
		pw.print(json);
		pw.close();
	}
}
