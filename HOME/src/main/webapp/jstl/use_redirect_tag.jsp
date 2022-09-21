<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.dest == '1' }">
	<c:redirect url="/use_c_set.jsp"/>
</c:if>


<c:if test="${param.dest == '2' }">
	<c:redirect url="use_c_set.jsp"/>
</c:if>


<c:if test="${param.dest == '3' }">
	<c:redirect url="/el/viewToday.jsp" context="/HOME"/>
</c:if>

<c:if test="${param.dest == '4' }">
	<c:redirect url="http://www.youtube.com/results">
		<c:param name="search_query" value="forseason"/>
	</c:redirect>
</c:if>