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

import admin.dao.RentListDao;
import admin.dao.SaleListDao;
import admin.vo.SaleListVo;
import members.dao.MembersDao;
import members.vo.MemRentListVo;
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
		} else if (cmd.equals("findId")) {
			findId(request, response);
		} else if (cmd.equals("addrInquiry")) {
			addr(request, response);
		} else if (cmd.equals("updatepage")) {
			updatepage(request, response);
		} else if (cmd.equals("findid")) {
			findid(request, response);
		} else if (cmd.equals("findpwd")) {
			findpwd(request, response);
			}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		String memAddr = request.getParameter("addr3") + request.getParameter("addr4");
		String memPhone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
				+ request.getParameter("phone3");
		String memEmail = request.getParameter("email1") + "@" + request.getParameter("email2");
		//String memBirth = request.getParameter("Birth1") + request.getParameter("Birth2")
		//		+ request.getParameter("Birth3");
		String memBirth = request.getParameter("Birth");
		String memName = request.getParameter("memName");

		members.vo.MembersVo members = new MembersVo(0, memId, memPwd, memAddr, memPhone, memEmail, memBirth, memName);

		System.out.println(memEmail);
		
		MembersDao dao = MembersDao.getInstance();

		int n = dao.insert(members);
		// int n = dao.insert(members);
		// System.out.println("n값"+n);
		if (n > 0) {
			response.sendRedirect(request.getContextPath() + "/jsp/layout.jsp");
			// request.setAttribute("result", "success");
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
			request.setAttribute("errMsg", "아이디 또는 비밀번호가 일치하지 않아요");
			request.getRequestDispatcher("/jsp/layout.jsp?spage=members/login.jsp").forward(request, response);
		} else {
			request.setAttribute("errMsg", "오류로 인해 로그인에 실패했습니다.");
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
		String memAddr = request.getParameter("addr4");
		String memPhone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
				+ request.getParameter("phone3");
		String memEmail = request.getParameter("email1") + "@" + request.getParameter("email2");
		String memBirth = request.getParameter("birth");
		String memName = request.getParameter("memName");

		MembersDao dao = MembersDao.getInstance();

		System.out.println("dao : " + dao);

		members.vo.MembersVo members = new MembersVo(memId, memPwd, memAddr, memPhone, memEmail, memBirth, memName);

		System.out.println("members : " + members);

		int n = dao.update(members);

		list(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memId = (String) request.getSession().getAttribute("id");

		MembersDao Mdao = MembersDao.getInstance();
		RentListDao Rdao = new RentListDao();
		SaleListDao Sdao = new SaleListDao();

		MembersVo membersvo = Mdao.list(memId);
		ArrayList<MemRentListVo> rentlistvo = Rdao.rentDetail1(membersvo.getMemNum());
		ArrayList<SaleListVo> salelistvo = Sdao.saleDetatil(membersvo.getMemNum());

		request.setAttribute("members", membersvo);
		request.setAttribute("rentlist", rentlistvo);
		request.setAttribute("salelist", salelistvo);

		request.getRequestDispatcher("/jsp/layout.jsp?spage=members/list.jsp").forward(request, response);
	}

	private void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject json = new JSONObject();

		String id = request.getParameter("memId");

		MembersDao dao = MembersDao.getInstance();
		boolean using = dao.findId(id);

		json.put("using", using);

		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();
	}

	private void addr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject json = new JSONObject();
		MembersDao dao = MembersDao.getInstance();

		String addr1 = request.getParameter("addr1");
		String getAddr = dao.addr(addr1);

		json.put("addr1", getAddr);

		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();
	}

	private void updatepage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memId = (String) request.getSession().getAttribute("id");

		MembersDao dao = MembersDao.getInstance();
		members.vo.MembersVo members = dao.list(memId);

		String[] phone = members.getMemPhone().split("-");
		String[] email = members.getMemEmail().split("@");

		request.setAttribute("pwd", members.getMemPwd());
		request.setAttribute("Addr", members.getMemAddr());
		request.setAttribute("phone1", phone[0]);
		request.setAttribute("phone2", phone[1]);
		request.setAttribute("phone3", phone[2]);
		request.setAttribute("Email", email[0]);
		request.setAttribute("domain", email[1]);
		request.setAttribute("Birth", members.getMemBirth());
		request.setAttribute("Name", members.getMemName());

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/layout.jsp?spage=members/update.jsp");
		rd.forward(request, response);
	}

	private void findid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject json = new JSONObject();
		MembersDao dao = MembersDao.getInstance();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		String getId = dao.findid(name, email);

		json.put("id", getId);

		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();

	}
	private void findpwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject json = new JSONObject();
		MembersDao dao = MembersDao.getInstance();
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");

		String getpwd = dao.findpwd(id, email);

		json.put("pwd", getpwd);
			
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();

	}
}
