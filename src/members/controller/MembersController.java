package members.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.omg.CORBA.PrincipalHolder;

import admin.dao.RentListDao;
import admin.dao.SaleListDao;
import admin.vo.RentListVo;
import admin.vo.SaleListVo;
import members.dao.MembersDao;
import members.vo.MembersVo;

@WebServlet("/members.do")

public class MembersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		String context = request.getContextPath();
		if (cmd.equals("insert")) {
			response.sendRedirect(context + "/jsp/layout.jsp?spage=members/insert.jsp");
		} else if (cmd.equals("insertOk")) {
			insert(request, response);
		} else if (cmd.equals("login")) {
			login(request, response);
		} else if (cmd.equals("logout")) {
			logout(request, response);
		} else if (cmd.equals("update")) {
			update(request, response);
		} else if (cmd.equals("loginpage")) {
			response.sendRedirect(context + "/jsp/layout.jsp?spage=members/login.jsp");
		} else if (cmd.equals("updateOk")) {
			updateOk(request, response);
		} else if (cmd.equals("listpage")) {
			list(request, response);
		}  else if (cmd.equals("findId")) {
			findId(request, response);
		} else if ( cmd.equals("addrInquiry")) {
			addr(request,response);
		}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		String memAddr = request.getParameter("addr3") + request.getParameter("addr4");
		String memPhone = request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");
		String memEmail = request.getParameter("email1") + request.getParameter("email2");
		String memBirth = request.getParameter("Birth1") + request.getParameter("Birth2") + request.getParameter("Birth3");
		String memName = request.getParameter("memName");
	
		
		members.vo.MembersVo members = new MembersVo(0,memId, memPwd, memAddr, memPhone, memEmail, memBirth, memName);

		MembersDao dao = MembersDao.getInstance();

		int n=dao.insert(members);
		// int n = dao.insert(members);
		// System.out.println("n��"+n);
		if (n > 0) {
			response.sendRedirect(request.getContextPath() + "/jsp/layout.jsp");
			//request.setAttribute("result", "success");
		} else {
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/jsp/layout.jsp?spage=members/login.jsp").forward(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");

		HashMap<String, String> map = new HashMap<>();
		map.put("memId", memId);
		map.put("memPwd", memPwd);

		MembersDao dao = MembersDao.getInstance();

		int n = dao.login(map);

		if (n == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", memId);
			if (!memId.equals("admin")) {
				response.sendRedirect(request.getContextPath() + "/jsp/layout.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/layout.jsp");
			}
		} else if (n == 0) {
			request.setAttribute("errMsg", "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʾƿ�");
			request.getRequestDispatcher("/jsp/layout.jsp?spage=members/login.jsp").forward(request, response);
		} else {
			request.setAttribute("errMsg", "������ ���� �α��ο� �����߽��ϴ�.");
			request.getRequestDispatcher("/jsp/layout.jsp?spage=members/login.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/jsp/layout.jsp");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memId = (String) request.getSession().getAttribute("id");

		MembersDao dao = MembersDao.getInstance();

		members.vo.MembersVo members = dao.list(memId);
		request.setAttribute("pwd", members.getMemPwd());
		request.setAttribute("Addr", members.getMemAddr());
		request.setAttribute("Phone", members.getMemPhone());
		request.setAttribute("Email", members.getMemEmail());
		request.setAttribute("Birth", members.getMemBirth());
		request.setAttribute("Name", members.getMemName());
		
		request.setAttribute("members", members);
		request.getRequestDispatcher("/jsp/layout.jsp?spage=members/update.jsp").forward(request, response);
	}

	private void updateOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		String memAddr = request.getParameter("memAddr");
		String memPhone = request.getParameter("memPhone");
		String memEmail = request.getParameter("memEmail");
		String memBirth = request.getParameter("memBirth");
		String memName = request.getParameter("memName");

		MembersDao dao = MembersDao.getInstance();
		members.vo.MembersVo members = new MembersVo(memId, memPwd, memAddr, memPhone, memEmail, memBirth, memName);
		int n = dao.update(members);
		if (n > 0) {
			request.setAttribute("result", "success");
		} else {
			request.setAttribute("result", "fail");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/layout.jsp?spage=members/updateOk.jsp");
		rd.forward(request, response);
	}

	/*
	 * private void list(HttpServletRequest request, HttpServletResponse response)
	 * throws ServletException, IOException {
	 * 
	 * MembersDao dao = new MembersDao(); ArrayList<members.vo.MembersVo> mlist =
	 * dao.listAll();
	 * 
	 * request.setAttribute("mlist", mlist);
	 * 
	 * RequestDispatcher rd =
	 * request.getRequestDispatcher("/jsp/layout.jsp?/list.jsp");
	 * rd.forward(request, response); }
	 */
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memId = (String) request.getSession().getAttribute("id"); 
	
		MembersDao Mdao = MembersDao.getInstance();
		RentListDao Rdao = new RentListDao();
		SaleListDao Sdao = new SaleListDao();
		
		MembersVo membersvo = Mdao.list(memId);
		ArrayList<RentListVo> rentlistvo = Rdao.rentDetail1(membersvo.getMemNum());
		ArrayList<SaleListVo> salelistvo = Sdao.saleDetatil(membersvo.getMemNum());
		
		request.setAttribute("members", membersvo);
		request.setAttribute("rentlist", rentlistvo);
		request.setAttribute("salelist", salelistvo);
		
		request.getRequestDispatcher("/jsp/layout.jsp?spage=members/list.jsp").forward(request, response);
	}
	private void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject json = new JSONObject();
		
		String id=request.getParameter("memId");
		
		MembersDao dao = MembersDao.getInstance();
		boolean using = dao.findId(id);
		
		json.put("using", using); // json �ȿ� Ű�� �������� �ִ´�.
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(json); // �ش簴ü�� json�� ��´�.
		pw.close(); // ���� 
	}
	 private void addr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		JSONObject json = new JSONObject();
		MembersDao dao = MembersDao.getInstance();
		
		String addr1 = request.getParameter("addr1");
		String getAddr = dao.addr(addr1);
		// System.out.println("�޾ƿ���"+getAddr);
		json.put("addr1",getAddr);
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close(); 
	} 
}
