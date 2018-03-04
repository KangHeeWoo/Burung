package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Detail;

import org.junit.Test;

import board.dao.BoardDao;
import board.vo.BoardMemVo;
import board.vo.BoardVo;

@WebServlet("/board.do")
public class BoardController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		switch (cmd) {
		case "boardDetail":
			detail(request, response);
			break;
		case "boardinsertOk":
			insert(request, response);
			break;
		case "boardinsert":
			response.sendRedirect("jsp/layout.jsp?spage=Board/boardinsert.jsp");
			break;
		case "boardlist":
			boardList(request, response);
			break;
		case "boarddelete":
			boarddelete(request, response);
			break;
		case "boardupdate":
			boardupdate(request, response);
			break;
		case "boardupdateOk":
			boardupdateOk(request, response);
			break;
		}
	}

	protected void boardupdateOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int boanum = Integer.parseInt(request.getParameter("boanum"));
		String boatitle = request.getParameter("boatitle");
		String boacontent = request.getParameter("boacontent");
		BoardDao dao = BoardDao.getInstance();
		int n = dao.boardupdate(boanum, boatitle, boacontent);
		if (n > 0) {
			boardList(request, response);
		}
	}

	protected void boardupdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		int boanum = Integer.parseInt(request.getParameter("boanum"));
		String memid = request.getParameter("memid");

		BoardDao dao = BoardDao.getInstance();
		// 내용보기
		BoardVo listdetail = dao.detaillist(boanum);
		request.setAttribute("listdetail", listdetail);
		request.getRequestDispatcher("jsp/layout.jsp?spage=Board/boardupdate.jsp").forward(request, response);

	}

	protected void boarddelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int boanum = Integer.parseInt(request.getParameter("boanum"));
		BoardDao dao = BoardDao.getInstance();
		int n = dao.boarddelete(boanum);
		if (n > 0) {
			boardList(request, response);
		} else {
			response.sendRedirect("#");
		}
	}

	protected void detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int boanum = Integer.parseInt(request.getParameter("boanum"));
		String memid = request.getParameter("memid");

		BoardDao dao = BoardDao.getInstance();

		// hit업데이트
		int n = dao.boardhitupdate(boanum);
		// System.out.println(boanum+","+n);
		if (n > 0) {
			// 내용보기
			BoardVo listdetail = dao.detaillist(boanum);
			// System.out.println(listdetail);
			request.setAttribute("listdetail", listdetail);
			request.getRequestDispatcher("jsp/layout.jsp?spage=Board/boardDetail.jsp").forward(request, response);
		}
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String boatitle = request.getParameter("boatitle");
		String boacontent = request.getParameter("boacontent");
		String id = request.getParameter("writer");
		// 해당아이디에 맞는 회원번호

		BoardDao dao = BoardDao.getInstance();
		int memnum = dao.memNum(id);

		int n = dao.boardinsert(boatitle, boacontent, memnum);
		if (n > 0) {
			boardList(request, response);
		} else {
			response.sendRedirect("#");
		}

	}

	protected void boardList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spageNum = request.getParameter("pageNum");
		// 검색기능
		String search = request.getParameter("search");
		String searchValue = request.getParameter("searchValue");
		int pageNum = 1;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		int startRow = (pageNum - 1) * 10 + 1;
		int endRow = startRow + 9;

		if (search == null || searchValue == null) {
			search = "1";
			searchValue = "1";
		} else {
			request.setAttribute("search", search);
			request.setAttribute("searchValue", searchValue);
		}

		BoardDao dao = BoardDao.getInstance();
		ArrayList<BoardMemVo> listAll = dao.listAll(startRow, endRow, search, searchValue);
		// System.out.println("listAll"+listAll);
		int pageCount = (int) Math.ceil(dao.getCount() / 10.0);
		int startPage = ((pageNum - 1) / 10 * 10) + 1;
		int endPage = startPage + 9;// 끝페이지
		// System.out.println(endPage);
		if (pageCount < endPage) {
			endPage = pageCount;

		}
		// System.out.println(endPage+"," +pageCount);
		request.setAttribute("listAll", listAll);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("jsp/layout.jsp?spage=Board/boardlist.jsp").forward(request, response);

	}
}
