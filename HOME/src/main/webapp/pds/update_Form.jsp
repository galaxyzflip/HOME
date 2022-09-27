<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pds.service.GetPdsItemService"%>
<%@ page import="pds.model.PdsItem" %>
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css"/>
<title>수정 화면</title>


</head>
<body>

<!-- request.setAttribute("pdsItem", pdsItem); -->

<div id="wrap">
	<form action="update.jsp?id=${pdsItem.id }" method="post" encType="Multipart/form-data">
		<div id="center">
			<div class="uploadForm">
				<dl>
					<dt>파일</dt>
					<dd><input type="file" name="file" class="fileName"/></dd>
					<dt>설명</dt>
					<dd><input type="text" name="description" value="${pdsItem.description }"/></dd>
				</dl>
			</div>
		</div>
		<div id="footer">
			<input type="submit" value="업로드(수정)"/>
		</div>
	</form>
</div>



</body>
</html>