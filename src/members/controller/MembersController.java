package members.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		String memAddr = request.getParameter("memAddr");
		String memPhone = request.getParameter("memPhone");
		String memEmail = request.getParameter("memEmail");
		String memBirth = request.getParameter("memBirth");
		String memName = request.getParameter("memName");

		members.vo.MembersVo members = new MembersVo(memId, memPwd, memAddr, memPhone, memEmail, memBirth, memName);

		MembersDao dao = MembersDao.getInstance();

		int n = dao.insert(members);
		if (n > 0) {
			request.setAttribute("result", "success");
		} else {
			request.setAttribute("result", "fail");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/layout.jsp");
		rd.forward(request, response);
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
		String memAddr = (String) request.getSession().getAttribute("memAddr");
		String memPhone = (String) request.getSession().getAttribute("memPhone");
		String memEmail = (String) request.getSession().getAttribute("memEmail");
		String memBirth = (String) request.getSession().getAttribute("memBirth");
		String memName = (String) request.getSession().getAttribute("memName");

		MembersDao dao = MembersDao.getInstance();

		members.vo.MembersVo members = dao.list(memId);

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
		// 1. ������ �������ִ� memId ���� ��������
		String memId = (String) request.getSession().getAttribute("id"); 
		// 2. DB ����	
		MembersDao Mdao = MembersDao.getInstance();
		RentListDao Rdao = new RentListDao();
		SaleListDao Sdao = new SaleListDao();
		// 3. DB���� VO�� ����
		MembersVo membersvo = Mdao.list(memId);
		ArrayList<RentListVo> rentlistvo = Rdao.rentDetail1(membersvo.getMemNum());
		ArrayList<SaleListVo> salelistvo = Sdao.saleDetatil(membersvo.getMemNum());
		// 4. �������� �� ���
		request.setAttribute("members", membersvo);
		request.setAttribute("rentlist", rentlistvo);
		request.setAttribute("salelist", salelistvo);
		// 5. �ش簪�� �������� �̵��Ͽ� ���
		request.getRequestDispatcher("/jsp/layout.jsp?spage=members/list.jsp").forward(request, response);
	}
}
// ȸ����ȣ �������� �޼ҵ�
