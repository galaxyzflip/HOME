<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<link href="style.css" rel="style.css" type="text/css">
<script language="javascript" src="script.js"></script>

</head>
<body>

<form method="post" action="/HOME/logon2/modifyPro.do" name="userinput" onsubmit="return checkIt()">

<table>

	<tr>
		<td colspan="2"><font size="+1"><b>회원정보수정</b></font></td>
	</tr>
	
	<tr>
		<td colspan="2">회원 정보를 수정합니다</td>
	</tr>
	
	<tr>
		<td><b>아이디 입력</b></td>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td>사용자 ID</td>
		<td>${member.id }</td>
		<input type="hidden" name="id" value="${sessionScope.memId }">
	</tr>
	
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	
	<tr>
		<td><b>개인정보 입력</b></td>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td>사용자 이름</td>
		<td><input type="text" name="name" size="15" maxlength="20" value="${member.name }"></td>
	</tr>
	
	<tr>
		<td>생일</td>
		<td>${member.birthday }</td>
	</tr>
		
	<tr>
		<td>성별</td>
		<td>${member.male }</td>
	</tr>
	
	<tr>
		<td>E-mail</td>
		<td>
			<c:if test="${empty member.email }">
				<input type="text" name="email" size="40" maxlength="30">
			</c:if>

			<c:if test="${!empty member.email }">
				<input type="text" name="email" size="40" value="${member.email }" maxlength="30">
			</c:if>
		</td>
	</tr>

	<tr>
		<td>BLOG</td>
		<td>
			<c:if test="${empty member.blog }">
				<input type="text" name="blog" size="60" maxlength="40">
			</c:if>

			<c:if test="${!empty member.blog }">
				<input type="text" name="blog" size="60" value="${member.blog }" maxlength="40">
			</c:if>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="수정">
			<input type="button" value="취소" onclick="document.location.href='/HOME/logon2/main.do'">
		</td>
	</tr>

</table>



</form>
</body>
</html>