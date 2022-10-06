<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>

</head>
<body>

<h1 align="center">방명록</h1>

<div align="center"><b>전체글수 : ${count } </b></div>

<br>
<!-- 방명록 작성 폼 -->
<form action="/HOME/guestbook/writeForm.do" method="post" onsubmit="guestWriteSave()">

<table align="center" width="700" border="1">

	<tr>
		<td colspan="2">
			이름 <input type="text" name="guestName" size="15" maxlegnth="20"> &nbsp;&nbsp;
			패스워드 <input type="password" name="password" size="15" maxlegnth="20"></td>
	</tr>
	<tr>
		<td width="80%"><textarea name="message" rows="6" cols="80" placeholder="방명록을 남겨주세요" ></textarea></td>
		<td>
			<input type="submit" value="글남기기">
			<input type="reset" value="다시작성">
		</td>	
</table>

</form>

<br><br>
<!-- 방명록 작성 폼 끝-->




<!-- 방명록 글 없을때 -->
<c:if test="${empty messageList }">
	작성된 방명록이 없습니다.
</c:if>

<!-- 방명록 글 있을때 -->
<c:if test="${! empty messageList }">

<table border="1" width="700" align="center">

	<c:forEach var="list" items="${messageList }">
		<tr>
			<td colspan="2">작성자 : ${list.guestName }</td>
		</tr>
			
		<tr>
			<td height="80" width="80%">내용 : ${list.message }</td>
			<td>
				<input type="button" value="수정" onclick="document.location.href='/HOME/guestbook/modifyForm.do?messageId=${list.messageId}'">
				<input type="button" value="삭제" onclick="document.location.href='/HOME/guestbook/deleteForm.do?messageId=${list.messageId}'">
			</td>
		</tr>	
		
	</c:forEach>
</table>


</c:if>

<br>
<div align="center">
<c:if test="${count > 0 }">
		<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1) }"/>
		<c:set var="pageBlock" value="${5 }"/>
		
		<fmt:parseNumber var="result" value="${currentPage / 5 }" integerOnly="true"/>
		
		<c:set var="startPage" value="${result * 5 + 1 }"/>
		<c:set var="endPage" value="${startPage + pageBlock-1 }"/>
		
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount }"/>
		</c:if>
		
		<c:if test="${endPage > pageCount }">
			<a href="/HOME/guestbook/list.do?pageNum=${startPage - 10 }">[이전]</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="/HOME/guestbook/list.do?pageNum=${i }">[${i }]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount } ">
			<a href="/HOME/guestbook/list.do?pageNum=${startPage + 10 }">[다음]</a>
		</c:if>
		
	</c:if>

</div>










</body>
</html>