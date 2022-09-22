<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.service.DeleteArticleService" %>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="deleteRequest" class="article.model.DeleteRequest"/>
	<jsp:setProperty name="deleteRequest" property="*"/>
	
<%
	String viewPage = null;

	try{
		DeleteArticleService.getInstanc().deleteArticle(deleteRequest);
		viewPage = "delete_success.jsp";
		
		
	}catch(Exception ex){
		request.setAttribute("deleteException", ex);
		viewPage = "delete_error.jsp";
	}
%>

<jsp:forward page="<%=viewPage %>"/>