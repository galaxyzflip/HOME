<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변글 작성</title>

</head>
<body>

답변글을 등록했습니다. <br>

<a href="list.jsp?p=${param.p }">목록보기</a>
<a href="read.jsp?articleId=${postedArticle.id }&p=${param.p }">게시글 읽기</a>

</body>
</html>