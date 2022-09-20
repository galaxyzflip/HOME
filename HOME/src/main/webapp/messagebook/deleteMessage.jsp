<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="errorView.jsp" %>
<%@ page import="messagebook.DeleteMessageService" %>
<%@ page import="messagebook.InvalidMessagePasswordException" %>

<%
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	String password = request.getParameter("password");
	boolean invalidPassword = false;
	
	try{
		DeleteMessageService deleteService = DeleteMessageService.getInstance();
		deleteService.deleteMessage(messageId, password);
		
	}catch(InvalidMessagePasswordException ex){
		invalidPassword = true;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 메시지 삭제함</title>
</head>
<body>


<%
	if(!invalidPassword){ %>
		메시지를 삭제하였습니다.
	
		
	<%} else{ %>
		입력한 암호가 올바르지 않습니다. 암호를 확인해주세요
	
	<%}
	
%>
<a href="list.jsp">[목록 보기]</a>

</body>
</html>