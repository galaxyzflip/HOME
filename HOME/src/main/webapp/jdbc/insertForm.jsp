<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테이블 레코드 삽입</title>
</head>
<body>

<form action="insert.jsp" method="post">

<table border="1">
	<tr>
		<td>아이디</td>
		<td><input type="text" name="id" size="10"></td>
		<td>암호</td>
		<td><input type="password" name="password" size="10"></td>
	</tr>
	
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" size="10"></td>
		<td>이메일</td>
		<td><input type="text" name="email" size="10"></td>
	</tr>
	
	<tr>
		<td>번호</td>
		<td><input type="text" name="tel" size="10"></td>
		
	</tr>
	
	<tr>
		<td colspan="4"><input type="submit" value="삽입"></td>
	</tr>


</table>
</form>


</body>
</html>