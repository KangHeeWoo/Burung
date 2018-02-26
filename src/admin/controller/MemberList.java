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
			int startRow=(pageNum-1)*10+1;//�������ȣ
			int endRow=startRow+9;//�����ȣ
			
			MemberDao dao=new MemberDao();
			ArrayList<MemberVo> list= dao.listAll(startRow,endRow);
			
			//��ü������ ���� ���ϱ�
			int pageCount=(int)Math.ceil(dao.getCount()/10.0);
			int startPage=((pageNum-1)/4*4)+1;//���������� ��ȣ
			int endPage=startPage+3;// �������� ��ȣ	//4������
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
			String spageNum=request.getParameter("pageNum");//����
			String rpageNum=request.getParameter("rpageNum");//��Ʈ
			int pageNum=1;//���ų���������
			int pageNum2=1;//��Ʈ����������
			if(spageNum!=null) {//����
				pageNum=Integer.parseInt(spageNum);
			}
			if(rpageNum!=null) {//��Ʈ
				pageNum2=Integer.parseInt(rpageNum);
			}
			
			
			int sstartRow=(pageNum-1)*5+1;//�������ȣ ����
			int sendRow=sstartRow+4;//�����ȣ ����
			int rstartRow=(pageNum2-1)*5+1;//�������ȣ ��Ʈ
			int rendRow=rstartRow+4;//�����ȣ ��Ʈ
		
			
			MemberDao dao=new MemberDao();
			ArrayList<MemberVo> detail= dao.memDetail(memNum);
			RentListDao rdao=new RentListDao();
			ArrayList<RentListVo> rentdetail= rdao.rentDetail(memNum,rstartRow,rendRow);
			SaleListDao sdao=new SaleListDao();
			ArrayList<SaleListVo> saledetail=sdao.saleDetatil(memNum,sstartRow,sendRow);
			
			
			//��ü������ ���� ���ϱ�(��Ʈ)
			int rpageCount=(int)Math.ceil(rdao.getCount(memNum)/5.0);
			//��ü������ ���� ���ϱ�(����)
			int spageCount=(int)Math.ceil(sdao.getCount(memNum)/5.0);
			int sstartPage=((pageNum-1)/4*4)+1;//���Ž��������� ��ȣ
			int rstartPage=((pageNum2-1)/4*4)+1;//��Ʈ����
			int rendPage=rstartPage+3;// �������� ��ȣ	//4������
			int sendPage=sstartPage+3;
			if(rpageCount<rendPage) {
				rendPage=rpageCount;
			}
			if(spageCount<sendPage) {
				sendPage=spageCount;
			}
			System.out.println(rentdetail.size());
			request.setAttribute("rpageCount", rpageCount);//��Ʈ��������ü
			request.setAttribute("spageCount", spageCount);//������������ü
			request.setAttribute("sstartPage", sstartPage);//����
			request.setAttribute("rstartPage", rstartPage);//��Ʈ
			request.setAttribute("rendPage", rendPage);//��Ʈ��������
			request.setAttribute("sendPage", sendPage);//���ų�������
			request.setAttribute("pageNum", pageNum);//����
			request.setAttribute("rpageNum", pageNum2);//��Ʈ
			request.setAttribute("detail", detail);
			request.setAttribute("rentlist", rentdetail);
			request.setAttribute("salelist", saledetail);
			request.setAttribute("memNum", memNum);
			request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminMember/memberdetail.jsp").forward(request, response);
		}else if(cmd.equals("search")){//ȸ���˻�
			request.setCharacterEncoding("utf-8");
			String spagenum=request.getParameter("spagenum");
			String search=request.getParameter("search");
			int find=Integer.parseInt(request.getParameter("find"));
			ArrayList<MemberVo> list=new ArrayList<>();
			int pagenum=1;
			if(spagenum!=null) {
				pagenum=Integer.parseInt(spagenum);
			}
			int startRow=(pagenum-1)*10+1;//�������ȣ
			int endRow=startRow+9;//�����ȣ
			
			
			MemberDao dao=new MemberDao();
			if(find==1) {
				list=dao.searchmember(search,startRow,endRow);
			}else if(find==2) {
				list=dao.searchmemid(search,startRow,endRow);
			}
			
			//��ü������ ���� ���ϱ�
			int pageCount=(int)Math.ceil(dao.searchCount(search, find)/10.0);
			int startPage=((pagenum-1)/4*4)+1;//���������� ��ȣ
			int endPage=startPage+3;// �������� ��ȣ	//4������
			if(pageCount<endPage) {
				endPage=pageCount;
			}
			
			request.setAttribute("spageNum", spagenum);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/admin/layout.jsp?spage=/admin/adminMember/membersearch.jsp").forward(request, response);
		}
	}
}
