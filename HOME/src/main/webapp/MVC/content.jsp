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
<link href="style.css" rel="stylesheet" type="text/css">


</head>
<body>
<center>
<h1 class="main">글내용 보기</h1>

<form>

	<table class="main">
	
		<tr>
			<td>글번호</td>
			<td>${article.num }</td>
			<td>조회수</td>
			<td>${article.readcount }</td>
		</tr>
		
		<tr>
			<td>작성자</td>
			<td>			
				<a href="/HOME/MVC/list.do?target=writer&keyword=${article.writer }">${article.writer }</a>
			</td>
			<td>작성일</td>
			<td></td>
		</tr>
		
		<tr>
			<td>글내용</td>
			<td colspan="3"><pre>${article.content }</pre></td>
		</tr>
		
		<tr>
			<td colspan="4">
				<input type="button" value="글수정" onclick="document.location.href='/HOME/MVC/updateForm.do?num=${article.num}&pageNum=${pageNum }'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글삭제" onclick="document.location.href='/HOME/MVC/deleteForm.do?num=${article.num}&pageNum=${pageNum }'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="답글쓰기" onclick="document.location.href='/HOME/MVC/writeForm.do?num=${article.num}&ref=${article.ref}&re_step=${article.re_step }&re_level=${article.re_level }'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글목록" onclick="document.location.href='/HOME/MVC/list.do?pageNum=${pageNum }'">
			</td>
		</tr>
	
	</table>

</form>
<br><br>

<form action="/HOME/MVC/commentPro.do" method="post">

<input type="hidden" name="contentNum" value="${article.num }">
	<table>
		<tr>
			<td>이름 <input type="text" name="commenter" ></td>
			<td>비밀번호 <input type="password" name="cPassword"></td>
			
		</tr>
		
		<tr>
			<td colspan="2"><textarea rows="4" cols="80" name="commentt" ></textarea></td>
			<td><input type="submit" value="댓글달기"></td>
			
		</tr>
	</table>
</form>



<!-- commentList -->

<c:if test="${count == 0 }">
	댓글이 없습니다.
</c:if>

<c:if test="${count > 0 }">


<br><br>

<table class="comment">

<c:forEach var="comment" items="${commentList }">

	<tr>
		<td>작성자 : ${comment.commenter }</td>
		<td>ip : ${comment.ip }</td>
		<td>작성일 : <fmt:formatDate value="${comment.reg_date }" pattern="yy-MM-dd hh:mm"/></td>
		<%-- <td>작성일 : ${comment.reg_date }</td> --%>
	</tr>
	<tr>
		<td colspan="3">${comment.commentt }</td>
	</tr>
	
</c:forEach>
</table>


</c:if>

<!-- 페이징 -->


<c:if test="${count > 0 }">
		<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1) }"/>
		<c:set var="pageBlock" value="${10 }"/>
		
		<fmt:parseNumber var="result" value="${currentPage / pageSize }" integerOnly="true"/>
		
		<c:set var="startPage" value="${result * pageSize + 1 }"/>
		<c:set var="endPage" value="${startPage + pageBlock-1 }"/>
		
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount }"/>
		</c:if>
		
		<c:if test="${endPage > pageCount }">
			<a href="/HOME/MVC/content.do?pageNum=${startPage - 10 }&num=${article.num }">[이전]</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="/HOME/MVC/content.do?pageNum=${i }&num=${article.num }">[${i }]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount } ">
			<a href="/HOME/MVC/content.do?pageNum=${startPage + 10 }&num=${article.num }">[다음]</a>
		</c:if>
		
	</c:if>


</center>
</body>
</html>
















