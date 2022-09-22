<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	Exception deleteException = (Exception)request.getAttribute("deleteException");
	String exceptionType = deleteException.getClass().getSimpleName();
	request.setAttribute("exceptionType", exceptionType);

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 실패</title>
</head>
<body>

에러 : 

<c:choose>
	<c:when test="${exceptionType == 'ArticleNotFouncException' }">
		삭제할 게시글이 존재하지 않습니다.
	</c:when>
	
	<c:when test="${exceptionType == 'InvalidPasswordException' }">
		암호를 잘못 입력하셨습니다.
	</c:when>

</c:choose>

<a href="list.jsp">목록보기</a>


</body>
</html>