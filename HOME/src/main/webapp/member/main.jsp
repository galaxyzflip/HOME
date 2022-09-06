<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인입니다.</title>
<link href="style.css" rel="stylesheet" type="text/css">

<%
	try{
		if(session.getAttribute("memId") == null){ %>
<script language="javascript">

	function focusIt(){
		document.inform.id.focus();
	}

function chekIt()
{
	inputForm=eval("document.inform");
	if(!inputForm.id.value){
		alert("아이디를 입력하세요...");
		inputForm.passwd.focus();
		return flase;
	}
	}


</script>


</head>
<body onLoad="focusIt();" bgcolor="<%=bodyback_c %>">
	<table width=500 cellpadding="0" cellspacing="0" align="center" border="1">
	
		<tr>
			<td width="300" bgcolor="<%= bodyback_c %>" height="20">
			&nbsp;
			</td>
			
			<form name="inform" method="post" action="loginPro.jsp" onsubmit = "return checkIt();">
			
			<td bgcolor="<%=title_c %> width="100" align="right"> 아이디 </td>
			<td width="100" bcolor="<%= value_c %>">
				<input type="text" name="id" size="15" maxlength="10"/></td>
		</tr>
		<tr>
			<td rowspan="2" bgcolor="<%= bodyback_c %>" width="300"> 메인입니다. </td>
			<td bgcolor="<%=title_c %>" width="100" align="right"> 패스워드 </td>
			<td width="100" bgcolor="<%= value_c %>">
				<input type="password" name="passwd" size="15" maxlength="10"></td>
		</tr>
			
			
			
			
			</form>
			
	
	
	
	
	
	
	
	</table>








<% 					
		}
	}
%>
</body>
</html>