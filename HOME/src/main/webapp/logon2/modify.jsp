<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
<link href="style.css" rel="stylesheet" type="text/css">

</head>
<body>
<table>

	<tr>
		<td>
			<form name="myform" action="/HOME/logon2/modifyForm.do" method="post">
				<input type="hidden" name="id" value="${sessionScope.memId }">
				<input type="submit" value="회원정보 수정">
			</form>
		</td>
		
		<td>
			<form name="myform" action="/HOME/logon2/deleteForm.do" method="post">
				<input type="hidden" name="id" value="${sessionScope.memId }">
				<input type="submit" value="회원탈퇴">
			</form>
		</td>
		
		<td>
			<form name="myform" action="/HOME/logon2/main.do" method="post">
				<input type="submit" value="메인으로">
			</form>
		</td>
</table>

</body>
</html>