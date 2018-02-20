package rent.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rent.dao.RentDao;
import rent.vo.RentVo;

@WebServlet("/rent.do")
public class RentController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		
		switch (cmd) {
		//렌트 페이지 진입
		case "rentlist":
			rentList(request, response);
			break;
		//모델 선택
		case "rentDetail":
			rentDetail(request, response);
			break;
		}
	}
	
	private void rentDetail (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		String sTime = request.getParameter("sTime");
		String eTime = request.getParameter("eTime");
		String cName = request.getParameter("cName");
		
		RentDao dao = RentDao.getInstance();
		RentVo rentCar = dao.rentCar(cName);
		
		long rentTime = 0l;
		
		try {
			rentTime = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(eDate + " " + eTime).getTime()
					- new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(sDate + " " + sTime).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rentTime = rentTime/(1000 * 60 * 60);
		
		System.out.println("총 대여시간 : " + rentTime);
		System.out.println("총 대여가격 : " + (rentTime*rentCar.getTimePay()));
		
		request.setAttribute("sDate", sDate);
		request.setAttribute("eDate", eDate);
		request.setAttribute("sTime", sTime);
		request.setAttribute("eTime", eTime);
		request.setAttribute("cName", cName);
		request.setAttribute("price", rentTime*rentCar.getTimePay());
		request.getRequestDispatcher("/jsp/layout.jsp?spage=rent/rentdetail.jsp").forward(request, response);
	}
	
	private void rentList (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		String sTime = request.getParameter("sTime");
		String eTime = request.getParameter("eTime");
		String model = request.getParameter("model");

		//최초 진입시 기본 날짜 지정
		if(sDate == null) {
			Calendar cal = Calendar.getInstance();
			
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 1);
			sDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 1);
			eDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			
			sTime = "09:00";
			eTime = "09:00";
			model = "";
		}
		
		RentDao dao = RentDao.getInstance();
		ArrayList<String> list = dao.rentList(sDate + " " + sTime, eDate + " " + eTime, model);
		System.out.println("list : " + list);
		request.setAttribute("sDate", sDate);
		request.setAttribute("eDate", eDate);
		request.setAttribute("sTime", sTime);
		request.setAttribute("eTime", eTime);
		request.setAttribute("model", model);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/layout.jsp?spage=rent/rent.jsp").forward(request, response);
	}
}
