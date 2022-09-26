<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>

<form action="<c:url value='write.jsp'/>" method="post">
제목 : <input type="text" name="title" size="20"/><br>
작성자 : <input type="text" name="writerName"/><br>
글암호 : <input type="password" name="password"/><br>
글 내용 <br>
<textarea name="content" cols="40" rows="5"></textarea>
<br>
<input type="submit" value="전송">
<input type="button" value="목록으로" onClick="document.location.href='list.jsp'">

</form>


</body>
</html>