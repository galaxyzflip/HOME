<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%! static final int PAGE_SIZE = 5; %>


<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	int contentNum =  Integer.parseInt(request.getParameter("id"));
	String boardClass = request.getParameter("boardClass");

	
	
	//현재 페이지
	int currentPage = 1;
	
	String cPageStr = request.getParameter("currentPage");
	if(cPageStr != null && !cPageStr.equals("null")){
		currentPage = Integer.parseInt(cPageStr);
	}
	//..현재 페이지
	
	int startRow = (currentPage * PAGE_SIZE) - (PAGE_SIZE-1);
	int endRow = currentPage  * PAGE_SIZE;
	
	CommentDAO manager = CommentDAO.getInstance();
	int count = manager.getCommentCount(contentNum, boardClass);
	ArrayList<CommentDTO> comments = manager.getComments(contentNum,startRow,endRow, boardClass);
			
	
	
	


%>

<table width="500" border=0 cellspacing=0 cellpadding=0 align=center>

	<tr>
		<td>코멘트 수 : <%=count %></td>
	</tr>
	
	
	<form method="post" action="commentPro.jsp" name="comment" onsubmit="return writeSave()">
<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">

	<tr align="center">
		<td colpsan="2">
			<textarea name="commentt" rows = "6" cols="40"></textarea>
			<input type="hidden" name="con_num" value=<%=contentNum %>>
			<input type="hidden" name="boardClass" value=<%=boardClass %>>
			<input type="hidden" name="uri" value="<%=request.getRequestURI() %>">
			<%-- <input type="hidden" name="p_num" value=<%=pageNum %>> --%>
		</td>
	<tr align="center">
		<td align=center>
			작성자<input type="text" name=commenter size=10>
			비밀번호<input type="password" name=passwd size=10>
			<input type="submit" value="코멘트달기">
		</td>
	</tr>

</table>
</form>
	
	
	
	
	
	
	<%
	if(count > 0){
		for(int i=0;i<comments.size();i++){
			CommentDTO cmt = comments.get(i);%>
			
			<tr>
				<td align=left size=250 >
				<b><%=cmt.getCommenter() %> 님</b> (<%=sdf.format(cmt.getReg_date()) %>)
				</td>
				
				<td align=riglt size=250 > 접속 IP : <%=cmt.getIp() %>  
				<a href="delCommentForm.jsp?ctn=<%=cmt.getContent_num() %>&cmn=<%=cmt.getComment_num()%>&p_num=<%=currentPage%>">삭제</a>
				</td>
			</tr>
			
			<tr>
				<td colspan=2><%=cmt.getCommentt() %></td>
			
		<%} 
	}%>
	
	
	
	
	
</table>
	
<table width=500 border=0 cellspacing=0 cellpadding=0>
<center>

	<tr>
	<% 
		if(count>0){
			int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
			//					1					
			int pageBlock = 5;
			int startPage = (int)(currentPage/5) * 5 + 1;
			int endPage = startPage + pageBlock - 1;
			if(endPage > pageCount) endPage = pageCount;
			
			
			if(startPage > 5){%>
				 <a href="content.jsp?contentNum=<%=contentNum %>&currentPage=<%=startPage - 5 %>">[이전]</a>
			<%}
			for(int i=startPage;i<=endPage;i++){%>
				<a href="content.jsp?contentNum=<%=contentNum %>&currentPage=<%=i %>">[<%=i %>]</a>
			
			<%}
			
			if(endPage < pageCount){ %>
				<a href="content.jsp?contentNum=<%=contentNum %>&currentPage=<%=startPage + 5 %>">[다음]</a>	
			
			
			<%}
		}
	
	%>
	
</center>	




</body>
</html>