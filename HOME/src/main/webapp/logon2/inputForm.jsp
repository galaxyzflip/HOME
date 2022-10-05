<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="color.jspf" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


</head>
<body bgcolor="${bodyback_c }">

<form method="post" action="/HOME/logon2/inputPro.do" name="userinput" onsubmit="return checkId()">

<table width="600" border="1" cellpadding="3" align="center">

	<tr>
		<td colspan="2" height="39" align='center' bgcolor="${value_c }">
			<font size="+1"><b>회원가입</b></font>
		</td>
	</tr>
	
	<tr>
		<td width="200" bgcolor="${value_c }"><b>아이디 입력</b></td>
		<td width="400" bgcolor="${value_c }">&nbsp;</td>
	</tr>
	
	<tr>
		<td width="200">사용자 ID</td>
		<td width="400">
			<input type="text" name="id" size="10" maxlegnth="12">
			<input type="button" name="confirm_id" value="ID중복확인" onclick="openConfirmId(this.form)">
		</td>
	</tr>
	
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" size="15" maxlength="12"></td>
	</tr>
		
	<tr>
		<td>비밀번호 확인</td>
		<td><input type="password" name="passwd2" size="15" maxlength="12"></td>
	</tr>

	<tr>
		<td>개인정보 입력</td>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td>사용자 이름</td>
		<td><input type="text" name="name" size="15" maxlength="10"></td>
	</tr>
		
	<tr>
		<td>생일</td>
		<td><input type="date" name="birthday" value="2000-01-01"></td>
	</tr>
	
	<tr>
		<td>성별</td>
		<td>
			<select name="male">
				<option value="ma">남자</option>
				<option value="fe">여자</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td>휴대폰번호</td>
		<td>
			<input type="text" name="phone" size="15" maxlength="13" placeholder="-없이 숫자만 입력">
		</td>
	</tr>
	
	<tr> 
			<td width="200">우편번호</td>
			<td>
				<input type="text" name="zipcode" id="sample6_postcode" size="7">
				<input type="button" onclick="sample6_execDaumPostcode()" value="다음우편번호"><br></td>
		</tr>
   
		<tr> 
			<td>주소</td>
			<td><input type="text" name="address" id="sample6_address" size="70"><br>
				<input type="text" name="detailAddress" id="sample6_detailAddress" size="70">
			</td>
		</tr>
	
	<tr>
		<td>E-mail</td>
		<td><input type="text" name="email" size="40" maxlength="50"></td>
	</tr>

	<tr>
		<td>BLOG</td>
		<td><input type="text" name="blog" size="60" maxlength="60"></td>
	</tr>

	<tr>
		<td colspan="2"><input type="submit" value="회원가입">
						<input type="reset" value="다시입력">
						<input type="button" value="가입안함" onclick="document.location.href='/HOME/logon2/main.do'">
		</td>
	</tr>						

</table>

</form>

</body>
</html>