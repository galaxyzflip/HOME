<%@page import="article.service.ArticleNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.service.ReadArticleService" %>
<%@ page import="article.model.ArticleDTO" %>
<%@ page import="article.service.ArticleNotFoundException" %>

<%
	String viewPage = null;
	
	try{
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		ArticleDTO article = ReadArticleService.getInstance().getArticle(articleId);
		
		viewPage = "update_form_view.jsp";
		request.setAttribute("article", article);
		
		
	}catch(ArticleNotFoundException ex){
		viewPage = "article_not_found_jsp";
	}

%>

<jsp:forward page="<%=viewPage %>"/>