<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<c:forEach var="menu" items="${userMenus }">
${menu.name }<br>
</c:forEach>

<tiles:importAttribute name="adminMenus"/>

<c:forEach var="menu" items="${adminMenus }">
${menu.name }<br>
</c:forEach>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>