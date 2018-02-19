<%@page import="admin.dao.SalesCarDao"%>
<%@page import="admin.vo.SalesCarVo"%>
<%@page import="admin.dao.ScarImgDao"%>
<%@page import="admin.vo.ScarImgVo"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//upload폴더의 실제 경로 구하기
	request.setCharacterEncoding("utf-8");
	String uploadPath=application.getRealPath("/admin/img");
	System.out.println(uploadPath);
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
			if(n2>0 && n1>0){
%>
			<h1>DB저장성공!!!!</h1>
<%
			}else{
%>
			<h1>DB저장실패!!!!</h1>
<%
			}
%>
</body>
</html>