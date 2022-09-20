<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="messagebook.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	UpdateMessageService manager = UpdateMessageService.getInstance();
	MessageDTO messageDto = manager.getMessageDto(Integer.parseInt(request.getParameter("messageId")));
%>


<form action="updateMessage.jsp" method="post">
	<input type="hidden" name="id" value=<%= messageDto.getId()%>>
	작성자 : <input type="text" name="guestName" value=<%=messageDto.getGuestName() %>> <br>
	메시지 : <input type="text" name="message" value=<%=messageDto.getMessage() %>><br>
	비밀번호 : <input type="password" name="password" >
	<input type="submit" value="수정">
</form>

</body>
</html>