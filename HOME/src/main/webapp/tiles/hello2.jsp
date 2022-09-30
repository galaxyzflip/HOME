<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%
	request.setAttribute("greeting", "동해물과 백두산이 마르고 닳도록");
%>

<tiles:insertDefinition name="hello2"/>