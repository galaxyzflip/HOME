<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="color.jspf" %>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<body bgcolor="${bodyback_c }">

<center>


<b>글목록(전체 글:${count })</b>

<table width="700">
	<tr>
		<td align=right bgcolor="${value_c }">
			<a href="/EZEN/MVC/writeForm.do">글쓰기</a>
		</td>
	</tr>
</table>

<c:if test="${count == 0 }">
	<table width="700" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">
				게시판에 저장된 글이 없습니다.
			</td>
		</tr>
	</table>
</c:if>



<c:if test="${count > 0 }">
	<table border="1" width="700" cellpading="0" cellspacing="0" align="center">
	<tr height="30" bgcolor="${value_c }">
	
		<td align="center" width="50">번호</td>
		<td align="center" width="250">제목</td>
		<td align="center" width="100">작성자</td>
		<td align="center" width="150">작성자</td>
		<td align="center" width="50">조회</td>
		<td align="center" width="100">IP</td>
	</tr>
	
	<c:forEach var="article" items="${articleList }">
		<tr height="30">
			<td align="center" width="50">
			<c:out value="${number }"/>
	
	
		</tr>
	</c:forEach>
	</table>

</c:if>






</center>
</body>
</html>