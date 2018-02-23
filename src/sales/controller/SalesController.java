package sales.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		case "main":
			response.sendRedirect(request.getContextPath() + "/jsp/layout.jsp");
			break;
		}
	}

	private void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getSession().getAttribute("id");
		String carName = request.getParameter("name");
		String[] color = request.getParameter("color").split(":");
		String[] wheel = request.getParameter("wheel").split(":");
		String[] seet = request.getParameter("seet").split(":");
		String[] light = request.getParameter("light").split(":");
		String[] audio = request.getParameter("audio").split(":");

		SalesDao dao = SalesDao.getInstance();
		int sMemNum = dao.sMemNum(id);
		HashMap<String, Integer> carInfo = dao.sCar(carName);
		int price = carInfo.get("sCarPrice") + Integer.parseInt(color[1].trim()) + Integer.parseInt(wheel[1].trim())
				+ Integer.parseInt(seet[1].trim()) + Integer.parseInt(light[1].trim())
				+ Integer.parseInt(audio[1].trim());

		int sNum = dao.sales(new SalesListVo(0, price, null, sMemNum, carInfo.get("salNum"), null));

		dao.sSelOpt(color[0].trim(), sNum);
		dao.sSelOpt(wheel[0].trim(), sNum);
		dao.sSelOpt(seet[0].trim(), sNum);
		dao.sSelOpt(light[0].trim(), sNum);
		dao.sSelOpt(audio[0].trim(), sNum);

		response.sendRedirect(request.getContextPath() + "/jsp/layout.jsp");
	}

	private void choiceName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String model = request.getParameter("model");
		String name = request.getParameter("name");
		String path = "/jsp/layout.jsp?spage=sales/sales.jsp&model=" + model;
		
		if (name != null) {
			path += "&name=" + name;
		}

		if (name == null) {
			if (model.equals("718")) {
				name = "718 Cayman Models";
			} else if (model.equals("911")) {
				name = "911 Carrera Models";
			} else if (model.equals("Panamera")) {
				name = "Panamera Models";
			} else if (model.equals("Cayenne")) {
				name = "Macan Models";
			} else if (model.equals("Macan")) {
				name = "Cayenne Models";
			}
		}
		
		// 최근 본 상품 쿠키에 추가하기
		String item = URLEncoder.encode(name,"utf-8");
		
		//System.out.println(Models);
		Cookie[] cookies1 = request.getCookies();
		boolean cheCook = true;
		// ArrayList 생성
		System.out.println(cookies1.length);

		for (int i = 0; i < cookies1.length; i++) {
			if (cookies1[i].getName().startsWith("Models")) {
				if(cookies1[i].getValue().equals(item)) {
					cheCook = false;
				}
			}
		}
		
		if(cheCook) {
			String Models = null;
			String ckindex="1";
			while(true) {
				Cookie[] cookies = request.getCookies();
				for(int j=0;j<cookies.length;j++) {
					if(cookies[j].getName().equals("ckIndex")) {
						String value = cookies[j].getValue();
						//URLDecoder
						ckindex = URLDecoder.decode(value,"utf-8");
					}
				}
				if(ckindex.equals("4")) {
					ckindex = "1";
				}
				Models = "Models"+ckindex;
				System.out.println("index:"+ckindex);
				Cookie cook = new Cookie(Models, item);
				int ckin=Integer.parseInt(ckindex)+1;
				ckindex=String.valueOf(ckin);
				Cookie ckIndex = new Cookie("ckIndex", ckindex);
				cook.setPath("/");
				cook.setMaxAge(-1);
				response.addCookie(cook);
				response.addCookie(ckIndex);
				break;
			}
		}
		
		//System.out.println(Models);
		Cookie[] cookies = request.getCookies();
		//ArrayList 생성
		ArrayList<String> cooks = new ArrayList<>();
		System.out.println(cookies.length);
		
		for(int j=0;j<cookies.length;j++) {
			if(cookies[j].getName().startsWith("Models")) {
				String value = cookies[j].getValue();
				//URLDecoder
				String models = URLDecoder.decode(value,"utf-8");
				//ArrayList에 저장
				cooks.add(models);
			}
		}
	
		/*
		for(int j=0;j<cookies.length;j++) {
			if(cookies[j].getName().startsWith("Models")) {
				String value = cookies[j].getValue();
				System.out.println("value:"+value);
				for(int k=j+1;k<cookies.length;k++) {
					String value1 = cookies[k].getValue();
					System.out.println("value1:"+value1);
					if(value.equals(value1)) {
						String name1 = cookies[j].getName();
						Cookie cook=new Cookie(name1,"");
						cook.setMaxAge(0);
						response.addCookie(cook);
						System.out.println("name:"+cook.getName());
					}
					//URLDecoder
					String models = URLDecoder.decode(value,"utf-8");
					//ArrayList에 저장
					cooks.add(models);
				}
			}
		}
		*/
		//request ArrayList 담기
		request.setAttribute("cooks",cooks);
		
		//forword 방식으로 페이지 이동
		request.getRequestDispatcher(path).forward(request, response);;
	}

	private void loadData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String model = request.getParameter("model");
		String mName = request.getParameter("mName");

		SalesDao dao = SalesDao.getInstance();
		SalesVo vo = dao.search(mName);
		ArrayList<String> list = dao.modelList(model);

		response.setContentType("text/plain;utf-8");
		PrintWriter pw = response.getWriter();

		JSONArray arr = new JSONArray();

		if (list != null) {
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
