<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link href="style.css" rel="stylesheet" type="text/css">

</head>
<body>

<table>
	<tr>
		<td><font size="+1"><b>회원정보가 수정되었습니다.</b></font></td>
	</tr>
	
	<tr>
		<td><p>수정이 완료되었습니다.</p></td>
	</tr>
	
	<tr>
		<td>
			<input type="button" value="메인으로" onclick="document.location.href='/HOME/logon2/main.do'">
			5초 후에 메인으로 이동합니다.
			<meta http-equiv="Refresh" content="5;url=/HOME/logon2.main.do">
		</td>
	</tr>

</table>

</body>
</html>