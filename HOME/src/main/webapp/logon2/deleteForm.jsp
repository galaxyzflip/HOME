<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<body onload="begin()">

<form name="myform" action="/HOME/logon2/deletePro.do" method="post" onsubmit="return checkIt()">

<table>

	<tr>
		<td colspan="2"><font size="+1"><b>회원 탈퇴</b></font></td>
	</tr>
	
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" size="15" maxlength="15"></td>
			<input type="hidden" name="id" value="${sessionScope.memId }">
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="회원탈퇴">
			<input typoe="button" value="취소" onclick="docuemtn.location.href='/HOME/logon2.main.do'">
		</td>
	</tr>

</table>


</form>
</body>
</html>