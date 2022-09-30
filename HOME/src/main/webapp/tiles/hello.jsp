<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%
	request.setAttribute("greeting", "안녕하세요");
%>
<tiles:insertDefinition name="hello"/>

