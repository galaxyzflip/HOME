<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴대폰번호 중복 확인</title>

<script language="JavaScript">
function setPhone() {
	var checkPhone = '${phone}' 
	opener.document.userinput.phone.value = checkPhone;
	self.close();
}
</script>


</head>
<body>

<c:if test="${check == 1 }">
	사용 가능한 휴대폰번호 입니다.
	<c:set var="phoen" value="${phone }"/>
	<input type="button" value="닫기" onclick="setPhone()">
</c:if>





<c:if test="${check == -1 }">
	중복된 휴대폰번호 입니다.
	
	<form name="checkForm" method="post" action="/HOME/logon2/confirmPhone.do">
		<table>
			<tr>
				<td>휴대폰번호를 다시 입력해주세요.
					<input type="text" size="15" maxlength="15" name="phone">
					<input type="submit" value="중복확인">
				</td>
			</tr>
		</table>

	</form>
	
</c:if>




</body>
</html>