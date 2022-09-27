<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="article.model.ArticleDTO" %>
<%@ page import="article.comment.service.CommentListService" %>
<%@ page import="article.comment.model.CommentListModel" %>
<link href="style.css" rel="stylesheet" type="text/css">
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 1L);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 읽기</title>
</head>
<body>

<table>
	<tr>
		<td>제목</td>
		<td>${article.title }</td>
	</tr>
	
	<tr>
		<td>작성자</td>
		<td>${article.writerName }</td>
	</tr>
	
	<tr>
		<td>작성일</td>
		<td><fmt:formatDate value="${article.postingDate }" pattern="yyyy-MM-dd"/></td>
	</tr>

	<tr>
		<td>
			<pre><c:out value="${article.content }"/></pre>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
		<a href="list.jsp?p=${param.p }">목록보기</a>
		<a href="reply_form.jsp?parentId=${article.id }&p=${param.p}">답변쓰기</a>
		<a href="update_form.jsp?articleId=${article.id }&p=${param.p}">수정하기</a>
		<a href="delete_form.jsp?articleId=${article.id }">삭제하기</a>

</table>
<br><br>

<form action="../articleComment/writeComment.jsp" method="post" class="commentForm">

내용 : <textarea cols="40" rows=4 name="commentContent"></textarea><br>
작성자 : <input type="text" name="commenter"/>
작성자 : <input type="password" name="password"/> <br>

<input type="hidden" name="articleNumber" value="${article.id }"/>


<input type="hidden" name="ip" value="<%=request.getRemoteAddr() %>"/>
<input type="submit" value="댓글달기"/>
<input type="reset" value="초기화"/>

</form>


<%
	//comment 페이징 관련
	
	
	//read_view  화면에서 댓글(comment) 보여줄때 페이징 용 페이지 번호
	int comPageNumber = 1;
	String comPageNumberString = request.getParameter("comP");
	
	if(comPageNumberString != null && comPageNumberString.length() > 0){
		comPageNumber = Integer.parseInt(comPageNumberString);
	}
	
	
	//가져올 댓글의 article_id
	int articleNumber = 1;
	String articleNumberString = request.getParameter("articleId");
	
	if(!(articleNumberString == null || articleNumberString.length() >0)){
		articleNumber = Integer.parseInt(articleNumberString);
	}
	

	CommentListService commentListService = CommentListService.getInstance();
	CommentListModel commentListModel = commentListService.getCommentLists(articleNumber, comPageNumber);
	request.setAttribute("ComListModel", commentListModel);
	
%>

<table>
	<tr>
		<td>작성자</td>
		<td>내용</td>
		<td>등록일</td>
		<td>IP</td>
	</tr>
	
	<c:choose>
		<c:when test="${ComListModel.hasComment == false}">
		<tr>
			<td>등록된 댓글이 없습니다.</td>
		</tr>
		</c:when>
		
		<c:otherwise>
			<c:forEach var="comment" items="${ComListModel.commentLists}">
			<tr>
				<td>${comment.commenter }</td>
				<td>${comment.commenterContent }</td>
				<td>${comment.regDate }</td>
				<td>${comment.ip }</td>
			</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
</table>



</body>
</html>









