<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.comment.service.InsertCommentService" %>
<%@ page import="article.comment.model.CommentDTO" %>
<%request.setCharacterEncoding("utf-8"); %>
    
<jsp:useBean id="model" class="article.comment.model.InsertCommentModel">
	<jsp:setProperty name="model" property="*"/>
</jsp:useBean>

<%
	int articleNumber = 0;
	String articleNumberStr = request.getParameter("articleNumber");
	
	if(articleNumberStr != null || !articleNumberStr.equals("null")){
		articleNumber = Integer.parseInt(articleNumberStr);
	}
	
	
	InsertCommentService service = InsertCommentService.getInstance();
	
	
	CommentDTO comment = service.insertComment(model, articleNumber); 
	request.setAttribute("commnetDAO", comment);
%>
<jsp:forward page="../article/read_view.jsp"/>
