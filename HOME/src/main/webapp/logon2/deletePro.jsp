<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="Javascript" src="script"></script>

</head>
<body>

<c:if test="${check == 1 }">
	<c:remove var="memId" scope="session"/>
	
	<form method="post" action="/HOME/logon2/main.do" name="userinput">
	
	<table>
			
		<tr>
			<td><font size="+1"><b>회원정보가 삭제되었습니다.</b></font></td>
		</tr>
		
		<tr>
			<td>안녕히가세요.
				<meta http-equiv="Refresh" content="5;url=/HOME/logon2/main.do">
			</td>
		</tr>
		
		<tr>
			<td><input type="submit" value="확인"></td>
		</tr>
	</table>
	
	</form>

</c:if>

<c:if test="${check == 0 }">
	<script>
		alert("비밀번호가 맞지 않습니다");
		history.go(-1);
	</script>
</c:if>


</body>
</html>