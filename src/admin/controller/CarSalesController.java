package admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.dao.MemberDao;
import admin.dao.SaleListDao;
import admin.dao.SalesCarDao;
import admin.dao.ScarImgDao;
import admin.vo.MemberVo;
import admin.vo.SaleListVo;
import admin.vo.SalesCarVo;
import admin.vo.ScarImgVo;
@WebServlet("/semi/sales.do")
public class CarSalesController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd.equals("carlist")) {
			String spageNum=request.getParameter("pageNum");
			String spageNum2=request.getParameter("lpageNum");
			int pageNum=1;
			int lpageNum=1;
			if(spageNum!=null) {
				pageNum=Integer.parseInt(spageNum);
			}
			if(spageNum2!=null) {
				lpageNum=Integer.parseInt(spageNum2);
			}
			int startRow=(pageNum-1)*10+1;//시작행번호
			int endRow=startRow+9;//끝행번호
			
			int lstartRow=(lpageNum-1)*5+1;//최근구매
			int lendRow=lstartRow+4;
			
			SalesCarDao dao=new SalesCarDao();
			ArrayList<SalesCarVo> list=dao.list(startRow,endRow);
			SaleListDao listdao=new SaleListDao();
			ArrayList<SaleListVo> saleslist=listdao.list(lstartRow, lendRow);
			
			//전체페이지 갯수 구하기(차량등록)
			int pageCount=(int)Math.ceil(dao.getCount()/10.0);
			int startPage=((pageNum-1)/4*4)+1;//시작페이지 번호
			int endPage=startPage+3;// 끝페이지 번호	//4페이지
			if(pageCount<endPage) {
				endPage=pageCount;
			}
			//전체페이지 갯수 구하기(최근판매된차량)
			int lpageCount=(int)Math.ceil(listdao.getCount()/5.0);
			int lstartPage=((lpageNum-1)/4*4)+1;//시작페이지 번호
			int lendPage=lstartPage+3;// 끝페이지 번호	//4페이지
			if(lpageCount<lendPage) {
				lendPage=lpageCount;
			}
			
			request.setAttribute("list", list);
			request.setAttribute("saleslist", saleslist);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("lpageCount", lpageCount);//최근
			request.setAttribute("startPage", startPage);
			request.setAttribute("lstartPage", lstartPage);//최근
			request.setAttribute("endPage", endPage);
			request.setAttribute("lendPage", lendPage);//최근
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("lpageNum", lpageNum);//최근
			request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminSalesCar/salescar.jsp").forward(request, response);	
		}else if(cmd.equals("carinsert")) {
			response.sendRedirect(request.getContextPath()+"/admin/adminSalesCar/salescarinsert.jsp");
		}else if(cmd.equals("insertOk")) {
			request.setCharacterEncoding("utf-8");
			String uploadPath=request.getServletContext().getRealPath("/admin/img");
			MultipartRequest mr=new MultipartRequest(
					request, //request객체
					uploadPath,	//업로드할 파일 경로
					1024*1024*5,	//최대 업로드 크기(바이트 단위로 설정)
					"utf-8",	//인코딩방식
					new DefaultFileRenamePolicy());	//동일한 파일이름의 들어오면 파일명뒤에 1,2,..숫자 붙이기
					String scarName=mr.getParameter("scarName");
					String scarModel=mr.getParameter("scarModel");
					int salCnt=Integer.parseInt(mr.getParameter("salCnt"));
					int scarPrice=Integer.parseInt(mr.getParameter("scarPrice"));

					SalesCarVo vo=new SalesCarVo(0, scarName, scarModel, salCnt, scarPrice);
					SalesCarDao dao=new SalesCarDao();
					int n=dao.carinsert(vo);
					int salNum=dao.searchNum(vo.getScarName());
					//전송한 파일명 얻어오기
					String mainorg=mr.getOriginalFileName("main");
					String suborg=mr.getOriginalFileName("sub");
					//파일크기 구하기(java.io.File)
					File main=new File(uploadPath+File.separator+mainorg);
					File sub=new File(uploadPath+File.separator+suborg);
					//전송된 정보 VO객체에 담기
					ScarImgVo mainvo=new ScarImgVo(0,mainorg,salNum);
					ScarImgVo subvo=new ScarImgVo(0,suborg,salNum);
					//DB에 파일정보 저장하기
					ScarImgDao imgdao=new ScarImgDao();
					int n1=imgdao.insert(mainvo);
					int n2=imgdao.insert(subvo);
					if(n1>0 && n2>0) {
						response.sendRedirect(request.getContextPath()+"/semi/sales.do?cmd=carlist");
					}else {
						
					}
		}else if(cmd.equals("stateChange")) {
			int slistNum=Integer.parseInt(request.getParameter("slistNum"));
			System.out.println(slistNum);
			SaleListDao dao=new SaleListDao();
			int n=dao.stateChange(slistNum);
			if(n>0) {
				response.sendRedirect(request.getContextPath()+"/semi/sales.do?cmd=carlist");
			}else {
				
			}
		}
	}
}
