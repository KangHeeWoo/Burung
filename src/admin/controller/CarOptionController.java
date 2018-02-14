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

import admin.dao.SelectOptionDao;
import admin.vo.SelectOptionVo;
@WebServlet("/semi/option.do")
public class CarOptionController extends HttpServlet{
	//
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int slistnum=Integer.parseInt(request.getParameter("slistnum"));
		SelectOptionDao dao=new SelectOptionDao();
		ArrayList<SelectOptionVo> list=dao.show(slistnum);
		JSONArray arr=new JSONArray();
		JSONObject sumjson=new JSONObject();
		for(int i=0;i<list.size();i++) {
			SelectOptionVo vo=list.get(i);
			JSONObject json=new JSONObject();
			json.put("sListNum",vo.getsListNum());
			json.put("optType", vo.getOptType());
			json.put("optInfo", vo.getOptInfo());
			arr.put(json);
		}
		sumjson.put("list", arr);
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println(sumjson);
		pw.close();
	}
}
