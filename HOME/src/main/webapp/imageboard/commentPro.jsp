<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.CommentDAO" %>
<%@ page import="java.sql.Timestamp" %>
<%request.setCharacterEncoding("utf-8");%>

<jsp:useBean id="cmt" scope="page" class="board.CommentDTO">
	<jsp:setProperty name="cmt" property="*"/>
</jsp:useBean>


<%
	String boardClass = "boardClass";

	CommentDAO manager = CommentDAO.getInstance();
	cmt.setReg_date(new Timestamp(System.currentTimeMillis()));
	cmt.setIp(request.getRemoteAddr());
	
	String conNum = request.getParameter("con_num");
	int con_num = Integer.parseInt(conNum);
	
	cmt.setContent_num(con_num);
	
	cmt.setComment_num((manager.getCommentCount(con_num, boardClass))+1);
	manager.insertComment(cmt, boardClass);
	
	
	//id, currentPage 받아서 넘겨줘야함
	
	int currentPage = Integer.parseInt(request.getParameter("currentPage"));
	
	
	
	
	
/* 	String url = "/imageboard/read_view.jsp?num="+con_num+"&pageNum="+p_num;
	response.sendRedirect(url); */
	String url = "read.jsp?id=" + con_num+ "&currentPage=" + currentPage;
	request.setAttribute("addUri", "?"+url);
	response.sendRedirect(url);
	
	
	
	
	
	
	
	
	
	
	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>