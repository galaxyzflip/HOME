<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<title>timeZone 태그 사용</title>
</head>
<body>

<c:set var="now" value="<%=new java.util.Date() %>"/>

<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/>

<br>

<fmt:timeZone value="America/Argentina/Buenos_Aires">
<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/>

</fmt:timeZone>


</body>
</html>