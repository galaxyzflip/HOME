<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pds.service.UpdatePdsItemService" %>
<%@ page import="pds.file.FileSaveHelper" %>
<%@ page import="pds.model.AddRequest" %>
<%@ page import="pds.model.PdsItem" %>

<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>

<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>

<%
	request.setCharacterEncoding("utf-8");
	int pdsId = Integer.parseInt(request.getParameter("id"));


	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	if(!isMultipart){
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return;
	} //Multipart 체크
	
	
	
	DiskFileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	List<FileItem> items = upload.parseRequest(request);
	
	AddRequest addRequest = new AddRequest();
	Iterator<FileItem> iter = items.iterator();
	
	
	while(iter.hasNext()){
		FileItem item = iter.next();
		if(item.isFormField()){
			String name = item.getFieldName();
			if(name.equals("description")){
				String value = item.getString("utf-8");
				addRequest.setDescription(value);
			}
		}else{
			String name = item.getFieldName();
			if(name.equals("file")){
				String realPath = FileSaveHelper.save("c:\\pds", item.getInputStream());
				addRequest.setFileName(item.getName());
				addRequest.setFileSize(item.getSize());
				addRequest.setRealPath(realPath);
			}
		}
		
				
		
	}
	/* PdsItem pdsItem = AddPdsItemService.getInstance().add(addRequest); */
	
	PdsItem pdsItem = UpdatePdsItemService.getInstance().update(addRequest, pdsId);
	
	
	//이 부분을 updateservice 로 작업
	//DAO 에서 update 쿼리 수정한것으로 작업

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 성공</title>
</head>
<body>

<%=pdsItem.getFileName() %> 파일을 업로드 했습니다. <br>
<a href="list.jsp">목록보기</a>



</body>
</html>