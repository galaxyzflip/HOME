<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.service.ReadArticleService" %>
<%@ page import="article.service.ArticleNotFoundException" %>
<%@ page import="article.model.ArticleDTO"%>
<%
	int articleId = Integer.parseInt(request.getParameter("articleId"));
	String viewPage = null;
	
	try{
		ArticleDTO article = ReadArticleService.getInstance().readArticle(articleId);
		request.setAttribute("article", article);
		viewPage = "read_view.jsp";
		
		
	}catch(ArticleNotFoundException ex){
		viewPage = "article_not_found.jsp";
		
	}
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

<jsp:forward page="<%=viewPage %>"/>