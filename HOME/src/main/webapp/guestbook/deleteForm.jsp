<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 삭제</title>
<script language="JavaScript" src="script.js"></script>
</head>
<body>

<form method="post" name="writeForm" action="/HOME/guestbook/deletePro.do" onsubmit="deleteSave()">

<table align="center" border=1>

	<tr>
		<td colspan="2">
					<input type="hidden" name="messageId" value="${getMessage.messageId }">
			작성자 : <input type="text" name="guestName" value="${getMessage.guestName }" >
			비밀번호 : <input type="password" name="password">
		</td>
	</tr>
	<tr>	
		<td width="80%">
			<textarea name="message" rows="4" cols="50" readonly>${getMessage.message }</textarea>
		</td>
		<td align="center">
			<input type="submit" value="삭제">
		</td>
	</tr>

</table>

</form>


</body>
</html>