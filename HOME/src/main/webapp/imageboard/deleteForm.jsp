<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>


<jsp:forward page="template/template.jsp">
	<jsp:param name="CONTENTPAGE" value="../deleteForm.view.jsp"/>
</jsp:forward>
