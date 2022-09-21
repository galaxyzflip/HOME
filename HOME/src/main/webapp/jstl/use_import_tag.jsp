<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>

<c:choose>
	<c:when test="${param.type == 'fickr' }">
		<c:import url="https://www.flickr.com/search">
			<c:param name="f" value="hp"/>
			<c:param name="q" value="보라매공원"/>
		</c:import>
	</c:when>
	
	<c:when test="${param.type == 'youtube' }">
		<c:import url="https://www.youtube.com/results">
			<c:param name="search_query" value="beyonce"/>
		</c:import>
	</c:when>
	
	<c:otherwise>
		<c:import url="use_import_tag_help.jsp">
			<c:param name="message" value="선택해주세요"/>
		</c:import>
	</c:otherwise>




</c:choose>