<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.*" %>
<%@ include file="color.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	try{
		BoardDAO manager = BoardDAO.getInstance();
		BoardDTO article = manager.updateGetArticle(num);
%>
<body bgcolor="<%=bodyback_c%>">
<b>글 수 정</b><br>

<form method="post" name="writeForm" action="updatePro.jsp?pageNum=<%=pageNum %>" onsubmit = "return writeSave()">

<table width="400" border="1" cellspacing="0" cellpadding="0" bgcolor="<%=bodyback_c %>" align="center">

	<tr>
		<td width="70" bgcolor="<%=value_c %>" align="center">이름</td>
		<td align="left" width="330">
			<input type="text" size="10" maxlength="10" name="write" value="<%=article.getWriter() %>">
			<input type="hidden" name="num" value="<%=article.getNum() %>"></td>
		</tr>
		
		


</table>







</form>
		
		<%		
	}catch(Exception ex){
		ex.printStackTrace();
	}

%>

<% %>





</body>
</html>