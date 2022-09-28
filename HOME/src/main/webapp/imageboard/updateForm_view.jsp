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
	
	<c:if test="${!empty theme }">
	
		<script language="JavaScript">
			function validate(form){
				
				if(form.title.value == ""){
					alert("제목을 입력하세요.");
					return false;
				}
				
				if(form.name.value == ""){
					alert("이름을 입력하세요.");
					return false;
				}
				
				if(form.password.value == ""){
					alert("비밀번호를 입력하세요.");
					return false;
				}
				
				if(form.content.value == ""){
					alert("내용을 입력하세요.");
					return false;
				}
				
			}
		
		</script>
	
	<form action="upload.jsp" method="post" enctype="multipart/form-data" onSubmit = "return validate(this)">
	
		<input type="hidden" name="id" value="${theme.id }">
		
		<table width="100%" border="1" cellpadding="1" cellspacing="0">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" size="40" value="${theme.title }"></td>
			</tr>
		
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="10" value="${theme.name }"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="10" value="${theme.email }"></td>
			</tr>
		
			<tr>
				<td>암호</td>
				<td><input type="password" name="password" size="10"></td>
			</tr>
		
			<tr>
				<td>이미지</td>
				<td><input type="file" name="imageFile">
					<c:if test="${!empty theme.image }">
						<br>
						<img src="/HOME/image/${theme.image}" width="150" border="0">
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea name="content" cols="40" rows="8">${theme.content }</textarea>
				</td>
			</tr>
		
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="javascript:history.go(-1)">
				</td>
			</tr>
		</table>
	
		<input type="hidden" name="page" value="${param.page}">
		
		<c:forEach var="searchCond" items="${paramValues.search_cond }">
			
			<c:if test="${searchCond == 'title' }">
				<input type="hidden" name="search_cond" value="title">
			</c:if>
			
			<c:if test="${searchCond == 'name' }">
				<input type="hidden" name="search_cond" value="name">
			</c:if>
		
		</c:forEach>
	
		<c:if test="${!empty param.search_key}">
			<input type="hidden" name="search_key" value="${param.search_key }">
		</c:if>
	
	</form>	
	
	</c:if>
	
	<c:if test="${empty theme }">
	글이 존재하지 않습니다.
	</c:if>
	