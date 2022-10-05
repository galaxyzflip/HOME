<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1 }">
    <c:set var="memId" value="${id}" scope="session"/>
    <meta http-equiv="Refresh" content="0;url=/HOME/logon2/main.do" />
</c:if>


<c:if test="${check == 0}">

	<script>
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>

</c:if>


<c:if test="${check == -1}">

	<script>
		alert("아이디가 맞지 않습니다.");
		history.go(-1);
	</script>

</c:if>