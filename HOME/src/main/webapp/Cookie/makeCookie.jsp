<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.net.*" %>

<%
	Cookie cookie = new Cookie("name", URLEncoder.encode("�ֹ���", "utf-8"));
	response.addCookie(cookie);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��Ű����</title>
</head>
<body>

<%= cookie.getName() %> ��Ű�� �� = "<%= cookie.getValue() %>" <br>
<%= cookie.getName() %> ��Ű�� �� = "<%= cookie.getDomain() %>" <br>
<%= cookie.getName() %> ��Ű�� �� = "<%= cookie.getPath() %>" <br>
<%= cookie.getName() %> ��Ű�� �� = "<%= cookie.getMaxAge() %>" <br>



</body>
</html>