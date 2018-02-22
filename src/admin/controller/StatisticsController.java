package admin.controller;

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

import admin.dao.StatisticsDao;
import admin.vo.StatisticsVo;
@WebServlet("/semi/statistics.do")
public class StatisticsController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		
		StatisticsDao dao=StatisticsDao.getInstance();
		ArrayList<StatisticsVo> list=dao.getCount();
		
		JSONArray arr=new JSONArray();
		for(int i=0;i<list.size();i++) {
			JSONObject json=new JSONObject();
			StatisticsVo vo=list.get(i);
			json.put("cnt", vo.getCnt());
			json.put("rcarName", vo.getRcarName());
			arr.put(json);
		}
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println(arr);
		pw.close();
	}
}
