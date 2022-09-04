<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>

<jsp:useBean id="memberInfo" class="member.MemberInfo"/>
<jsp:setProperty name="memberInfo" property="*"/>
<jsp:setProperty name="memberInfo" property="password" value="<%= memberInfo.getId() %>"/>


<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
</head>
<body>

<table width="400" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td>아이디</td>
		<td><jsp:getProperty name="memberInfo" property="id"/></td>
		<td>암호</td>
		<td><jsp:getProperty name="memberInfo" property="password"/></td>
	</tr>
	
	<tr>
		<td>이름</td>
		<td><jsp:getProperty name="memberInfo" property="name"/></td>
		<td>이메일</td>
		<td><jsp:getProperty name="memberInfo" property="email"/></td>
	</tr>
	
	<tr>
		<td>주소</td>
		<td colspan="3"><jsp:getProperty name="memberInfo" property="address"/></td>
	</tr>

</table>



</body>
</html>