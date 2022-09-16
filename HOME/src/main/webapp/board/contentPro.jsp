<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.CommentDAO" %>
<%@ page import="java.sql.Timestamp" %>
<%request.setCharacterEncoding("utf-8");%>

<jsp:useBean id="cmt" scope="page" class="board.CommentDTO">
	<jsp:setProperty name="cmt" property="*"/>
</jsp:useBean>


<%
	CommentDAO manager = CommentDAO.getInstance();
	cmt.setReg_date(new Timestamp(System.currentTimeMillis()));
	int con_num = Integer.parseInt(request.getParameter("con_num"));
	cmt.setComment_num(con_num);
	
	manager.insertComment(cmt);
	
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