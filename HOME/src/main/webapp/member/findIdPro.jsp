<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="logon.LogonDBBean" %>


<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	
	LogonDBBean manager = LogonDBBean.getInstance();
	String id = manager.findId(name, phone);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>

아이디는 <%=id %> 입니다.

<input type="button" value="닫기" onclick="setid()">




</body>
</html>