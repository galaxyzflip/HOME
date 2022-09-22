<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>

글을 수정했습니다.
</br>

<a href="list.jsp?p=${param.p }">목록보기</a>
<a href="read.jsp?articleId=${updateArticle.id}&p=${param.p}">게시글 읽기</a>

</body>
</html>