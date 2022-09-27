<%@page import="pds.service.PdsItemNotFoundException"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%@ page import="java.net.URLEncoder" %>

<%@ page import="pds.service.IncreaseDownloadCountService" %>
<%@ page import="pds.file.FileDownloadHelper" %>
<%@ page import="pds.model.PdsItem" %>
<%@ page import="pds.service.GetPdsItemService" %>

<%
	int id = Integer.parseInt(request.getParameter("id"));
	try{
		PdsItem item = GetPdsItemService.getInstance().getPdsItem(id);
		
		response.reset();
		
		String fileName = new String(item.getFileName(). getBytes("utf-8"), "iso-8859-1");
		response.setContentType("application/dctet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Tranfer-Encoding", "binary");
		response.setContentLength((int)item.getFileSize());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		
		FileDownloadHelper.copy(item.getRealPath(), response.getOutputStream());
		
		response.getOutputStream().close();
		IncreaseDownloadCountService.getInstance().increaseCount(id);
	}catch(PdsItemNotFoundException ex){
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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