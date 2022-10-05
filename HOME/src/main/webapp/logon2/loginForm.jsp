<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script.js"></script>

</head>
<body onload="begin()">

<form name="myform" action="/HOME/logon2/loginPro.do" method="post" onsubmit="return loginCheck()">

<table>
	<tr>
		<td colspan="2"><strong>회원로그인</strong></td>
	</tr>
	
	<tr>
		<td>아이디</td>
		<td><input type="text" name="id"></td>
	</tr>
	
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="로그인">
			<input type="reset" value="다시입력">
			<input type="button" value="회원가입" onclick="document.location.href='/HOME/logon2/inputForm.do'">
		</td>
	</tr>

</table>
</form>



</body>
</html>