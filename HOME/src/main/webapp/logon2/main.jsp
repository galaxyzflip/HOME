<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="color.jspf" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인입니다.</title>
<link href="style.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor="${bodyback_c }">


<c:if test="${empty sesstionScope.memId }">

	<form name="infoem" method="post" action="/HOME/logon2/loginPro.do">
	<table width="300" cellpadding=0 cellspacing=0 align=center border=1>
	
		<tr>
			<td width="300" bgcolor="${bodyback_c }" height="20">&nbsp;</td>
			
			
			<td bgcolor="${title_c }" width="100" align="right">아이디</td>
			<td width="100" bgcolor="${value_c }">
				<input type="text" name="id" size="15" maxlength="10"></td>
		</tr>
		
		<tr>
			<td roswpan="2" bgcolor="${bodyback_c }" width="300">메인입니다.</td>
			<td bgcolor="${title_c }" width="100" align="right">패스워드</td>
			<td width="100" bgcolor="${value_c }">
				<input type="password" name="passwd" size="15"	maxlength="10"></td>
		</tr>
			
		<tr>
			<td colspan="3" bgcolor="${title_c }" align="center">
				<input type="submit" name="Submit" value="로그인">
				<input type="button" value="회원가입" onclick="document.location.href='/HOME/logon2/inputForm.do'"	>
			</td>
		</tr>			
	</table>
	</form>

</c:if>


<c:if test="${!empty sessionScope.memId }">

	<table width="500" cellpadding="0" cellspacing=0 align=center border=1>
	
		<tr>
			<td width="300" bgcolor="${bodyback_c }" height="20">하하하</td>
			
			<td rowspan="3" bgcolor="${value_c }" align="center">
				${sessionScope.memId }님이 <br> 방문하셨습니다.
			
			<form method="post" action="/HOME/logon2/logout.do">
				<input type="submit" value="로그아웃">
				<input type="button" value="회원정보변경" onclick="document.location.href='/HOME/logon2/modify.do'">
			</form>
			</td>	
		</tr>
		
		<tr>
			<td rowspan="2" bgcolor="${bodyback_c }" width="300">메인입니다.</td>
		</tr>
		
	</table>

</c:if>






</body>
</html>