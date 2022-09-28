<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="gallery.Theme" %>
<%@ page import="gallery.GalleryDAO" %>
<%@ page import="gallery.ThemeManagerException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String themeId = request.getParameter("id");
	GalleryDAO manager = GalleryDAO.getInstance();
	Theme theme = manager.select(Integer.parseInt(themeId));
 %>
    
    <c:set var="theme" value="<%=theme %>"/>
    <c:if test="${!empry theme }">
    
    <script language="JavaScript">
    	function validate(form){
    		if(form.password.value == ""){
    			alert("암호를 입력하세요.");
    			return false;
    		}
    	}
    </script>
    
    <form action="delete.jsp" method="post" onsubmit="return validate(this)">
    	<input type="hidden" name="id" value="${theme.id }">
    	
    	<table width="100%" border=1 cellpadding=1 cellspacing=0>
    	
    		<tr>
    			<td>제목</td>
    			<td>${theme.title }</td>
    		</tr>
    		
    		<tr>
    			<td>작성자</td>
    			<td>${theme.name }</td>
    		</tr>
    	
    		<tr>
    			<td>암호</td>
    			<td><input type="password" name="password" size="10"></td>
    		</tr>
    		
    		<c:if test="${!empty theme.image }">
    			<tr>
    				<td>이미지</td>
    				<td>
    					<img src="/HOME/image/${theme.image }" width="150" vorder="0">
    				</td>
    			</tr>
    		</c:if>

			<tr>
				<td colspan="2">
					<input type="submit" value="삭제">
					<input type="button" value="취소" onclick="javascript:history.go(-1)">
				</td>
			</tr>
    	</table>
    </form>
    </c:if>
    
    <c:if test="${empty theme }">
    글이 존재하지 않습니다.
    </c:if>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>