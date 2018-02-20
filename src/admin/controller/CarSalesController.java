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
			int startRow=(pageNum-1)*10+1;//�������ȣ
			int endRow=startRow+9;//�����ȣ
			
			int lstartRow=(lpageNum-1)*5+1;//�ֱٱ���
			int lendRow=lstartRow+4;
			
			SalesCarDao dao=new SalesCarDao();
			ArrayList<SalesCarVo> list=dao.list(startRow,endRow);
			SaleListDao listdao=new SaleListDao();
			ArrayList<SaleListVo> saleslist=listdao.list(lstartRow, lendRow);
			
			//��ü������ ���� ���ϱ�(�������)
			int pageCount=(int)Math.ceil(dao.getCount()/10.0);
			int startPage=((pageNum-1)/4*4)+1;//���������� ��ȣ
			int endPage=startPage+3;// �������� ��ȣ	//4������
			if(pageCount<endPage) {
				endPage=pageCount;
			}
			//��ü������ ���� ���ϱ�(�ֱ��Ǹŵ�����)
			int lpageCount=(int)Math.ceil(listdao.getCount()/5.0);
			int lstartPage=((lpageNum-1)/4*4)+1;//���������� ��ȣ
			int lendPage=lstartPage+3;// �������� ��ȣ	//4������
			if(lpageCount<lendPage) {
				lendPage=lpageCount;
			}
			
			request.setAttribute("list", list);
			request.setAttribute("saleslist", saleslist);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("lpageCount", lpageCount);//�ֱ�
			request.setAttribute("startPage", startPage);
			request.setAttribute("lstartPage", lstartPage);//�ֱ�
			request.setAttribute("endPage", endPage);
			request.setAttribute("lendPage", lendPage);//�ֱ�
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("lpageNum", lpageNum);//�ֱ�
			request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminSalesCar/salescar.jsp").forward(request, response);	
		}else if(cmd.equals("carinsert")) {
			response.sendRedirect(request.getContextPath()+"/admin/adminSalesCar/salescarinsert.jsp");
		}else if(cmd.equals("insertOk")) {
			request.setCharacterEncoding("utf-8");
			String uploadPath=request.getServletContext().getRealPath("/admin/img");
			MultipartRequest mr=new MultipartRequest(
					request, //request��ü
					uploadPath,	//���ε��� ���� ���
					1024*1024*5,	//�ִ� ���ε� ũ��(����Ʈ ������ ����)
					"utf-8",	//���ڵ����
					new DefaultFileRenamePolicy());	//������ �����̸��� ������ ���ϸ�ڿ� 1,2,..���� ���̱�
					String scarName=mr.getParameter("scarName");
					String scarModel=mr.getParameter("scarModel");
					int salCnt=Integer.parseInt(mr.getParameter("salCnt"));
					int scarPrice=Integer.parseInt(mr.getParameter("scarPrice"));

					SalesCarVo vo=new SalesCarVo(0, scarName, scarModel, salCnt, scarPrice);
					SalesCarDao dao=new SalesCarDao();
					int n=dao.carinsert(vo);
					int salNum=dao.searchNum(vo.getScarName());
					//������ ���ϸ� ������
					String mainorg=mr.getOriginalFileName("main");
					String suborg=mr.getOriginalFileName("sub");
					//����ũ�� ���ϱ�(java.io.File)
					File main=new File(uploadPath+File.separator+mainorg);
					File sub=new File(uploadPath+File.separator+suborg);
					//���۵� ���� VO��ü�� ���
					ScarImgVo mainvo=new ScarImgVo(0,mainorg,salNum);
					ScarImgVo subvo=new ScarImgVo(0,suborg,salNum);
					//DB�� �������� �����ϱ�
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
