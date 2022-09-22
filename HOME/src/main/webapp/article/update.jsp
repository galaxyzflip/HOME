<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.service.UpdateArticleService" %>
<%@ page import="article.model.ArticleDTO" %>
<%request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="updateRequest" class="article.model.UpdateRequest">
	<jsp:setProperty name="updateRequest" property="*"/>
</jsp:useBean>

<%
	String viewPage = null;

	try{
		ArticleDTO article = UpdateArticleService.getInstance().update(updateRequest);
		request.setAttribute("updateArticle", article);
		viewPage = "update_success.jsp";
		
	}catch(Exception ex){
		request.setAttribute("updateException", ex);
		viewPage = "update_error.jsp";
		
	}

%>
<jsp:forward page="<%=viewPage %>"/>