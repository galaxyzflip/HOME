<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 하기</title>
</head>
<body>

<form action="<c:url value='delete.jsp'/>" method="post">
	<input type="hidden" name="articleId" value="${param.articleId }"/>
	비밀번호 : <input type="password" name="password"/><br>
	<input type="submit" value="삭제"/>

</form> 


</body>
</html>