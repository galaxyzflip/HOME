<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 등록</title>
<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>

<div id="wrap">
	<form action="upload.jsp" method="post" enctype="Multipart/form-data">
	
		<div id="centent">
			<div class="uploadForm">
				<dl>
					<dt>파일</dt>
					<dd><input type="file" name="file"/></dd>
					<dt>설명</dt>
					<dd class="desc"><input type="text" name="description"/></dd>
				</dl>
			</div>
		</div>
		<div id="footer">
			<input type="submit" value="업로드"/>
		</div>
		
	</form>
</div>


</body>
</html>