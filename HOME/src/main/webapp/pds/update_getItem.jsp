<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pds.service.GetPdsItemService"%>
<%@ page import="pds.model.PdsItem" %>
<% request.setCharacterEncoding("utf-8"); %>

<%
	String idStr = request.getParameter("id");
	int pdsId = Integer.parseInt(idStr);
	
	GetPdsItemService itemService = GetPdsItemService.getInstance();
	PdsItem pdsItem = itemService.getPdsItem(pdsId);
	
	request.setAttribute("pdsItem", pdsItem);

%>
<jsp:forward page="update_Form.jsp"/>
