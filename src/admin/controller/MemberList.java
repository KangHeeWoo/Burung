package admin.controller;
//
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.MemberDao;
import admin.dao.RentListDao;
import admin.dao.SaleListDao;
import admin.vo.MemberVo;
import admin.vo.RentListVo;
import admin.vo.SaleListVo;
import board.dao.BoardDao;
import board.vo.BoardVo;
@WebServlet("/semi/list.do")
public class MemberList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd.equals("memberlist")) {
			String spageNum=request.getParameter("pageNum");
			int pageNum=1;
			if(spageNum!=null) {
				pageNum=Integer.parseInt(spageNum);
			}
			int startRow=(pageNum-1)*10+1;//시작행번호
			int endRow=startRow+9;//끝행번호
			
			MemberDao dao=new MemberDao();
			ArrayList<MemberVo> list= dao.listAll(startRow,endRow);
			
			//전체페이지 갯수 구하기
			int pageCount=(int)Math.ceil(dao.getCount()/10.0)-1;
			int startPage=((pageNum-1)/4*4)+1;//시작페이지 번호
			int endPage=startPage+3;// 끝페이지 번호	//4페이지
			if(pageCount<endPage) {
				endPage=pageCount;
			}
			
			request.setAttribute("list", list);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageNum", pageNum);
			request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminMember/memberlist.jsp").forward(request, response);
		}else if(cmd.equals("memberdetail")) {
			int memNum=Integer.parseInt(request.getParameter("memNum"));
			String spageNum=request.getParameter("pageNum");//구매
			String rpageNum=request.getParameter("rpageNum");//렌트
			int pageNum=1;//구매내역페이지
			int pageNum2=1;//렌트내역페이지
			if(spageNum!=null) {//구매
				pageNum=Integer.parseInt(spageNum);
			}
			if(rpageNum!=null) {//렌트
				pageNum2=Integer.parseInt(rpageNum);
			}
			
			
			int sstartRow=(pageNum-1)*5+1;//시작행번호 구매
			int sendRow=sstartRow+4;//끝행번호 구매
			int rstartRow=(pageNum2-1)*5+1;//시작행번호 렌트
			int rendRow=rstartRow+4;//끝행번호 렌트
		
			
			MemberDao dao=new MemberDao();
			ArrayList<MemberVo> detail= dao.memDetail(memNum);
			RentListDao rdao=new RentListDao();
			ArrayList<RentListVo> rentdetail= rdao.rentDetail(memNum,rstartRow,rendRow);
			SaleListDao sdao=new SaleListDao();
			ArrayList<SaleListVo> saledetail=sdao.saleDetatil(memNum,sstartRow,sendRow);
			
			
			//전체페이지 갯수 구하기(렌트)
			int rpageCount=(int)Math.ceil(rdao.getCount(memNum)/5.0);
			//전체페이지 갯수 구하기(구매)
			int spageCount=(int)Math.ceil(sdao.getCount(memNum)/5.0);
			int sstartPage=((pageNum-1)/4*4)+1;//구매시작페이지 번호
			int rstartPage=((pageNum2-1)/4*4)+1;//렌트시작
			int rendPage=rstartPage+3;// 끝페이지 번호	//4페이지
			int sendPage=sstartPage+3;
			if(rpageCount<rendPage) {
				rendPage=rpageCount;
			}
			if(spageCount<sendPage) {
				sendPage=spageCount;
			}
			
			request.setAttribute("rpageCount", rpageCount);//렌트페이지전체
			request.setAttribute("spageCount", spageCount);//구매페이지전체
			request.setAttribute("sstartPage", sstartPage);//구매
			request.setAttribute("rstartPage", rstartPage);//구매
			request.setAttribute("rendPage", rendPage);//렌트끝페이지
			request.setAttribute("sendPage", sendPage);//구매끝페이지
			request.setAttribute("pageNum", pageNum);//구매
			request.setAttribute("rpageNum", rpageNum);//렌트
			request.setAttribute("detail", detail);
			request.setAttribute("rentlist", rentdetail);
			request.setAttribute("salelist", saledetail);
			request.setAttribute("memNum", memNum);
			request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminMember/memberdetail.jsp").forward(request, response);
		}
	}
}
