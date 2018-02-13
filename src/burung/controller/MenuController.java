package burung.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import board.vo.BoardMemVo;
import board.vo.BoardVo;

@WebServlet("/menu.do")
public class MenuController extends HttpServlet{
   private static final long serialVersionUID = 1L;

   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      String cmd = request.getParameter("cmd");
      
      switch(cmd) {
      case "intro":
         break;
      case "sales":
         break;
      case "rent":
         break;
      case "board": boardList(request,response);
         break;
         
      }
   }
   protected void boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String spageNum=request.getParameter("pageNum"); 
      int pageNum=1;
      if(spageNum!=null) {
         pageNum=Integer.parseInt(spageNum);
      }
      int startRow=(pageNum-1)*10+1; 
      int endRow=startRow+9;
      
      BoardDao dao=BoardDao.getInstance();
      ArrayList<BoardMemVo> listAll=dao.listAll(startRow, endRow);
      
   }
}