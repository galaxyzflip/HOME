<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<center>
<div class=wrap>


<h1 class="header">글목록(전체 글:${count })</h1>

<div class="left-box">
			<a href="/HOME/MVC/writeForm.do">글쓰기</a>
</div>

<c:if test="${count == 0 }">
	<table>
		<tr>
			<td align="center">
				게시판에 저장된 글이 없습니다.
			</td>
		</tr>
	</table>
</c:if>



<c:if test="${count > 0 }">
	<table class="main">
	<tr>
	
		<td align="center" width="50">번호</td>
		<td align="center" width="250">제목</td>
		<td align="center" width="100">작성자</td>
		<td align="center" width="150">작성자</td>
		<td align="center" width="50">조회</td>
		<td align="center" width="100">IP</td>
	</tr>
	
	<c:forEach var="article" items="${articleList }">
		<tr>
			<td>
			<c:out value="${number }"/>
			<c:set var="number" value="${number-1 }"/>
			</td>
			
			<td>
				<c:if test="${article.re_level > 0 }">
				<img src="/HOME/board/images/level.gif" width="${5 * article.re_level }" height="16">
				<img src="/HOME/board/images/re.gif">
				</c:if>
				
				<c:if test="${article.re_level == 0 }">
					<img src="/HOME/board/images/level.gif" width="${5 * article.re_level }" height="16">
				</c:if>
				
				<a href="/HOME/MVC/content.do?num=${article.num }&pageNum=${currentPage }">${article.subject}</a>
				<c:if test="${article.readcount >= 20 }">
					<img src="/HOME/board/images/hot.gif" border="0" height="16">
				</c:if>
			</td>
			
			<td>
				<a href="mailto:${article.email }">${article.writer }</a>
			</td>
			
			<td>${article.reg_date }</td>
			<td>${article.readcount}</td>
			<td>${article.ip }</td>
		</tr>
	</c:forEach>
	</table>

</c:if>



<!-- 페이징 -->

	<c:if test="${count > 0 }">
		<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1) }"/>
		<c:set var="pageBlock" value="${10 }"/>
		
		<fmt:parseNumber var="result" value="${currentPage / 10 }" integerOnly="true"/>
		
		<c:set var="startPage" value="${result * 10 + 1 }"/>
		<c:set var="endPage" value="${startPage + pageBlock-1 }"/>
		
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount }"/>
		</c:if>
		
		<c:if test="${endPage > pageCount }">
			<a href="/HOME/MVC/list.do?pageNum=${startPage - 10 }&target=${target }&keyword=${keyword}">[이전]</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="/HOME/MVC/list.do?pageNum=${i }&target=${target }&keyword=${keyword}">[${i }]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount } ">
			<a href="/HOME/MVC/list.do?pageNum=${startPage + 10 }&target=${target }&keyword=${keyword}">[다음]</a>
		</c:if>
		
	</c:if>
	
	<br><br>
	
	<form method="post" action="/HOME/MVC/list.do">
	
	<select name="target">
		<option value="subject">제목</option>
		<option value="content">내용</option>
		<option value="writer">작성자</option>
	</select>
	
	<input type="text" name="keyword" style="width:120px">
	<input type="submit" value="검색">
	<input type="button" value="전체글보기" onclick="document.location.href='/HOME/MVC/list.do'">
	
	</form> 
</div>
</center>
</body>
</html>