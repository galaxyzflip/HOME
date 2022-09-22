<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.service.ReplyArticleService" %>
<%@ page import="article.model.ArticleDTO" %>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="replyingRequest" class="article.model.ReplyingRequest">
	<jsp:setProperty name="replyingRequest" property="*"/>
</jsp:useBean>

<%
	String viewPage = null;
	
	try{
		ArticleDTO postedArticle = ReplyArticleService.getInstance().reply(replyingRequest);
		request.setAttribute("postedArticle", postedArticle);
		viewPage = "reply_success.jsp";
		
		
	}catch(Exception ex){
		viewPage = "reply_error.jsp";
		request.setAttribute("replyException", ex);
		
	}
%>

<jsp:forward page="<%=viewPage%>"/>