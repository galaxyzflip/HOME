<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage = "../error/error_view.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테마 갤러리</title>
<style>
	A{color: blue; font-weight: bold; text-decoration: none}
	A:hover{color:blue; font-weight:bold; text-decration: underline}

</style>
</head>
<body>

<table width="100%" bvorder="1" cellpadding="2" cellspacing="0">
	<tr>
		<td>
			<jsp:include page="../module/top.jsp" flush="false"/>
		</td>
	</tr>
	
	<tr>
		<td>
			<jsp:include page="${param.CONTENTPAGE }" flush="false"/>
		</td>
	</tr>

	<tr>
		<td>
			<jsp:include page="../module/bottom.jsp" flush="false"/>
		</td>
	</tr>

</table>


</body>
</html>