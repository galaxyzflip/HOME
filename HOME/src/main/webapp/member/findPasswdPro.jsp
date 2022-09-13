<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="logon.LogonDBBean" %>
    
    <%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String phone = request.getParameter("phone");
	
	LogonDBBean manager = LogonDBBean.getInstance();
	String passwd = manager.findPasswd(id, phone);

	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>

비밀번호는 <%= passwd %> 입니다.

<input type="button" value="닫기" onclick="setpasswd()">


</body>
</html>