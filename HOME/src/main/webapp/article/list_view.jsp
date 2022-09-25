<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%request.setCharacterEncoding("utf-8"); %>

<%
	
%>


<c:set var="todayDate" value="<%=new java.util.Date() %>"/>


<%-- <%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Contril", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expries", 1L);

%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css">


<title>게시글 목록</title>
</head>
<body>




<center>

<jsp:include page="header.jsp" flush="false"/>


<table class="main">
<c:if test="${listModel.totalPageCount > 0 }">
	<tr>
		<td colspan="5">
		${listModel.startRow} - ${listModel.endRow }&nbsp;&nbsp;
		[${listModel.requestPage } / ${listModel.totalPageCount }]  
		</td>
	</tr>
</c:if>

<tr>
<b>
	<td class="hno">번호</td>
	<td class="hwriter">작성자</td>
	<td class="htitle">제목</td>
	<td class="hregDate">작성일</td>
	<td class="hreadCount">조회수</td>
</b>
</tr>
<c:choose>
	
	<c:when test="${listModel.hasArticle == false }">
	<tr>
		<td colspan="5">게시글이 없습니다.</td>
	</tr>
	</c:when>
	
	
	<c:otherwise>
		<c:forEach var="article" items="${listModel.articleList }">
		<tr>
			
			<td class="no">${article.id }</td>
			<td class="writer">${article.writerName }</td>
			<td class="title">
				<c:if test="${article.level > 0 }">
					<c:forEach begin="1" end="${article.level }"> - </c:forEach> &gt
				</c:if>
				<c:set var="query" value="articleId=${article.id }&p=${listModel.requestPage }"/>
		
				<a href="<c:url value="read.jsp?${query }"/>">
					${article.title }
				</a>
			</td>
			<td class="regDate"><fmt:formatDate pattern="yyyy/MM/dd" value="${article.postingDate }"/></td>
			<td class="readCount">${article.readCount }</td>
		</tr>
		</c:forEach>
		
		
	</c:otherwise>

</c:choose>
</table>

<tr>
			<td colspan="5" align=center>
			
			<c:if test="${beginPage > 10 }">
				<a href="<c:url value="list.jsp?p=${beginPage-1 }&target=${target }&searchValue=${searchValue }"/>">이전</a>
			</c:if>
			
			<c:forEach var="pno" begin="${beginPage }" end="${endPage }">
				<a href="<c:url value="list.jsp?p=${pno }&target=${target }&searchValue=${searchValue }"/>">[${pno }]</a>
			</c:forEach>
			
			<c:if test="${endPage < listModel.totalPageCount }">
				<a href="<c:url value="list.jsp?p=${endPage + 1 }&target=${target }&searchValue=${searchValue }"/>"> 다음</a>
			</c:if>
			
			</td>
		</tr>	

<br><br>
<div>
			<a href="writeForm.jsp">글쓰기</a>&nbsp;&nbsp;&nbsp;
			<a href="list.jsp">전체글보기</a>
</div>
</center>




<form action="list.jsp" method="post">
<center>


<div class="search" align="center">
	<select name="target">
		<option value="0">작성자</option>
		<option value="1">제목</option>
		<option value="2">내용</option>
	</select>
	
	<input type="text" name="searchValue" value="${searchValue }" >
	<input type="submit" value="검색">
			
</div>
</center>
</form>


</body>



</html>