<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dateFormat 태그 사용</title>
</head>
<body>

<c:set var="now" value="<%=new Date() %>"/>

<fmt:formatDate value="${now }" type="date" dateStyle="full"/><br>
<fmt:formatDate value="${now }" type="date" dateStyle="short"/><br>
<fmt:formatDate value="${now }" type="time"/><br>
<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br><br>
<fmt:formatDate value="${now }" pattern="z a h:mm"/>





</body>
</html>