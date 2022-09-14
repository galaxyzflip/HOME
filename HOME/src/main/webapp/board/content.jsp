<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="color.jsp" %>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	try{
		BoardDAO manager = BoardDAO.getInstance();
		BoardDTO article = manager.getArticle(num);
		
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
%>		
		 				
<body bgcolor="<%=bodyback_c%>">		
<center>
<b>글 내용 보기</b><br>

<form>

	<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="<%=bodyback_c %>" align="center">
	
		<tr height="30">
			<td align="center" width="125" bgcolor="<%=value_c %>"> 글 번호 </td>
			<td align="center" width="125" align="center"> <%= article.getNum() %> </td>
			<td align="center" width="125" bgcolor="<%=value_c %>">조회수</td>
			<td align="center" width="125" align="center"><%=article.getReadcount() %></td>
		</tr>
		
		
		<tr height="30">
			<td align="center" width="125" bgcolor="<%=value_c%>">작성자</td>
			<td align="center" width="125" align="center"> <%= article.getWriter() %> </td>
			<td align="center" width="125" bgcolor="<%=value_c %>">작성일</td>
			<td align="center" width="125" align="center"><%=article.getReg_date() %></td>
		</tr>
		
		<tr height="30">
			<td align="center" width="125" bgcolor=<%=value_c %> > 글 제목 </td>
			<td align="center" width="375" colspan="3"><%=article.getSubject() %></td>
		</tr>
			
		<tr>
			<td align="center" width="125" bgcolor="<%=value_c %>">글 내용</td>
			<td align="center" width="375" colspan="3"><pre><%=article.getContent() %></pre></td>
		</tr>		
		
		
		<tr height="30">
			<td colspan="4" bgcolor="<%=value_c %>" align="right">
				<input type="button" value="글 수정" onclick="document.location.href='updateForm.jsp?num=<%=article.getNum() %>'">
				<input type="button" value="글 삭제" onclick="document.location.href='deleteForm.jsp?num=<%=article.getNum() %>'">
				<input type="button" value="답글쓰기" onclick="document.location.href='list.jsp?pageNum=<%=pageNum %>'">
			</td>
		</tr>
	
	</table>	
</form>


	
	
	
	
	
	<%
	
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>


</center>


</body>
</html>