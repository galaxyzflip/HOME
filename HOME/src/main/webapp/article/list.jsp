<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="article.model.ArticleListModel" %>
<%@ page import="article.service.ListArticleService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8"); %>


<%
	//검색어 관련 설정
	
	String target = request.getParameter("target");
	String searchValue = request.getParameter("searchValue");
	
	
	request.setAttribute("target", target);
	request.setAttribute("searchValue", searchValue);
	//검색어 관련 설정
	

	String pageNumberString = request.getParameter("p");
	int pageNumber = 1;
	
	if(pageNumberString != null && pageNumberString.length() > 0){
		pageNumber = Integer.parseInt(pageNumberString);
	}
	
	ListArticleService listService = ListArticleService.getInstance();
	
	ArticleListModel articleListModel = listService.getArticleList(pageNumber, target, searchValue);
	
	
	request.setAttribute("listModel", articleListModel);
	
	if(articleListModel.getTotalPageCount() > 0){
		int beginPageNumber = (articleListModel.getRequestPage() - 1) / ListArticleService.COUNT_PER_PAGE * ListArticleService.COUNT_PER_PAGE + 1;
		int endPageNumber = beginPageNumber + (ListArticleService.COUNT_PER_PAGE - 1);
		
		if(endPageNumber > articleListModel.getTotalPageCount()){
			endPageNumber = articleListModel.getTotalPageCount();
		}
		
		request.setAttribute("beginPage", beginPageNumber);
		request.setAttribute("endPage", endPageNumber);
	}
	

 %>

<jsp:forward page="list_view.jsp?"/>
<!-- request 객체를 가져가기 위해 sendRedirect가 아닌 forward 사용~ -->