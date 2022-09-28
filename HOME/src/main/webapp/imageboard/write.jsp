<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="../error/error_view.jsp" %>

<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.io.File" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="gallery.ImageUtil" %>
<%@ page import="gallery.FileUploadRequestWrapper" %>

<%@ page import="gallery.Theme" %>
<%@ page import="gallery.GalleryDAO" %>
<%@ page import="gallery.ThemeManagerException" %>

<%
	FileUploadRequestWrapper requestWrap = new FileUploadRequestWrapper
	(request, -1, -1, "C:\\Users\\EZEN\\git\\HOME\\HOME\\src\\main\\webapp\\temp");
	HttpServletRequest tempRequest = request;
	request = requestWrap;

%>


<jsp:useBean id="theme" class="gallery.Theme">
	<jsp:setProperty name="theme" property="*"/>
</jsp:useBean>


<%
	FileItem imageFileItem = requestWrap.getFileItem("imageFile");
	String image = "";
	
	if(imageFileItem.getSize() > 0){
		image = Long.toString(System.currentTimeMillis());
		
		//이미지를 지정한 경로에 저장
		
		File imageFile = new File("C:\\Users\\EZEN\\git\\HOME\\HOME\\src\\main\\webapp\\image");
		
		//같은 이름의 파일이름 처리
		
		if(imageFile.exists()){
			for(int i=0; true; i++){
				
			}
		}
		
		
	}

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>