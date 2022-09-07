<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="logon.LogonDataBean" %>
<%@ page import="logon.LogonDBBean" %>
<%@ include file="color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link href="style.css" rel="stylesheet" type="text/css">

<script language="Javascript">
	<function checkIt(){
		vat userinput = eval("document.userinput");
		
		if(!userinput.passwd.value){
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		if(userinput.passwd.value != userinput.passwd2.value)
			{
			alert("비밀번호를 동일하게 입력하세요");
			return false;
			}
		
		if(!userinput.username.value){
			alert("이름을 입력해주세요.");
			return false;
		}
		
		if(!userinput.birthday.value){
			alert("생일을 입력해주세요.");
			return false;
		}
		
		if(!userinput.male.value){
			alert("성별을 입력해주세요.");
			return false;
		}
		
	}

</script>
</head>

<%
	String id = (String)session.getAttribute("memId");

	LogonDBBean manager = LogonDBBean.getInstance();
	LogonDataBean c = manager.getMember(id);
	
	
	try{
%>
	<body bgcolor="<%=bodyback_c %>">
	<form method="post" action="modifyPro.jsp" name="userinput" onsubmit="return checkIt()">
	
		<table width="600" border="1" cellspacing="0" cellpadding="3" align="center">
		
			<tr>
				<td colspan="2" height="39" bgcolor="<%=title_c %>" align="center">
				<font size="+1"><b>회원정보수정</b></font>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" class="normal" align="center">회원의 정보를 수정합니다.</td>
			</tr>
			
			<tr>
				<td width="200" bgcolor="<%=value_c %>"><b>아이디 입력</b></td>
				<td width="400" bgcolor="<%=value_c %>"></td>
			</tr>
			
			<tr>
				<td width="200"> 사용자 ID </td>
				<td width="400"><%=c.getId() %></td>
			</tr>
			
			<tr>
				<td width="200"> 비밀번호 </td>
				<td width="400">
					<input type="password" name="passwd" size="10" maxlength="10" value="<%=c.getPasswd() %>">
				</td>
			</tr>
			
			<tr>
				<td width="200">이름</td>
				<td width="400">
					<input type="text" name="name" size="10" maxlength="10" value="<%=c.getName() %>">
				</td>
			</tr>
			<tr>
				<td width="200">성별</td>
				<td width="400">
				<%
					if(c.getMale().equals("ma")){
				%>
					남자
				<% 		
					} else if(c.getMale().equals("fe")){
						%>
						여자
					<% 				
					} else{
						%>
					없음
					<%} %>
				</td>
			</tr>
			
			<tr>
				<td width="200">생년월일 </td>
				<td width="400">
					<%=c.getBirthday() %>
				</td>
			</tr>
			
			<tr>
				<td width="200">E-Mail</td>
				<td width="400">
				<%
					if(c.getEmail() == null){
				%>
						<input type="text" name="email" size="40" maxlength="30">
				<%} else { %>
						<input type="text" name="email" size="40" maxlength="30" value="<%=c.getEmail() %>">
				<%}%>	
				
				</td>
			</tr>
			
			<tr>
				<td width="200">Blog</td>
				<td width="400">
				<%
					if(c.getBlog() == null){
				%>
						<input type="text" name="blog" size="50" maxlength="30">
				<%} else { %>
						<input type="text" name="blog" size="50" maxlength="30" value="<%=c.getBlog() %>">
				<%}%>	
				
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center" bgcolor="<%=value_c %>">
					<input type="submit" name="modify" value="수정">				
					<input type="button" value="취소" onclick = "javascript:window.location='mail.jsp'">
				</td>
			</tr>	
		
		</table>
	</form>



</body>
<% 		
	}catch(Exception ex){}
%>
</html>