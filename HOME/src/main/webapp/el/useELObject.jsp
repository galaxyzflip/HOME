<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("name", "최창선"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Object</title>
</head>
<body>

요청 URI : ${pageContext.request.requestURI }<br>
표현식 <%= request.getRequestURI() %><br>

request의 name 속성 : ${requestScope.name }<br>

request의 name 속성 : <%=request.getAttribute("name") %><br>


code 파라미터 : ${param.code }<br>
code 파라미터 : <%=request.getParameter("code") %>

</body>
</html>