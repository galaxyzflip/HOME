<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="pds.service.ListPdsItemService" %>
<%@ page import="pds.model.PdsItemListModel" %>
<% request.setCharacterEncoding("utf-8"); %>


<%
	String pageNumberString = request.getParameter("P");
	
	String search = request.getParameter("search");
	if(search == null || search.equals("null")){
		search="";
	}
	
	
	int pageNumber = 1;
	if(pageNumberString != null && !pageNumberString.isBlank()){
		pageNumber = Integer.parseInt(pageNumberString);
	}
	
	ListPdsItemService listService = ListPdsItemService.getInstance();
	PdsItemListModel itemListModel;
	
	itemListModel = listService.getPdsItemList(pageNumber, search);
	request.setAttribute("listModel", itemListModel);
	
	if(itemListModel.getTotalPageCount() > 0){
		int beginpageNumber = (itemListModel.getRequestPage() - 1) / ListPdsItemService.COUNT_PER_PAGE * ListPdsItemService.COUNT_PER_PAGE + 1; 
		int endpageNumber = beginpageNumber + (ListPdsItemService.COUNT_PER_PAGE - 1);
		
		if(endpageNumber > itemListModel.getTotalPageCount()){
			endpageNumber = itemListModel.getTotalPageCount();
		}
		request.setAttribute("beginPage", beginpageNumber);
		request.setAttribute("endPage", endpageNumber);
	}

%>
<jsp:forward page="listView.jsp"/>