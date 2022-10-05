<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
<link href="style.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="script.js"></script>

</head>
<body>

<c:if test="${check == 1 }">

	<table>
		<tr>
			<td>${id }는 이미 사용중인 아이디 입니다.</td>
		</tr>
	</table>
	
	
	<form name="checkForm" method="post" action="/HOME/logon2/confirmId.do">
	
	<table>
		<tr>
			<td>다른 아이디를 선택하세요.
				<input type="text" size="10" maxlength="12" name="id">
				<input type="submit" value="ID중복확인">
			</td>
		</tr>
	</table>
	
	</form>
	
</c:if>


<c:if test="${check == -1 }">

<table>
	<tr>
		<td>
			입력하신 ${id }는 사용할 수 있는 ID 입니다.
			<input type="button" value="닫기" onclick="setId()">
		</td>
	</tr>
</table>






</c:if>
</body>
</html>























