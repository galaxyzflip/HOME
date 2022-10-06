<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="messagebook.MessageDTO" %>
<%@ page import="messagebook.UpdateMessageService" %>
<%@ page import="messagebook.InvalidMessagePasswordException" %>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="message" class="messagebook.MessageDTO">
	<jsp:setProperty name="message" property="*"/>
</jsp:useBean>

<%
	boolean invalidPassword = false;

	try{
		UpdateMessageService updateService = UpdateMessageService.getInstance();
		updateService.updateMessage(message.getId(), message.getPassword(), message.getGuestName(), message.getMessage());
		
	}catch(InvalidMessagePasswordException ex){
		invalidPassword = true;
	}
	
	%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 메시지 수정함</title>
</head>
<body>


<%
	if(!invalidPassword){ %>
		메시지를 수정하였습니다.
		
		
	<%} else{%>
		입력한 암호가 올바르지 않습니다. 다시 확인해주세요.
	
	<%}
%>
 <a href="list.jsp">[목록 보기]</a>

</body>
</html>