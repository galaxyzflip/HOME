<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="logon.LogonDBBean" %>
<%@ page import="java.sql.Timestamp" %>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="member" class="logon.LogonDataBean">
	<jsp:setProperty name="member" property="*"/>
</jsp:useBean>	

<%-- <%
	
	member.setReg_date(new Timestamp(System.currentTimeMillis()));
	LogonDBBean manager = LogonDBBean.getInstance();
	manager.insertMember(member);
	
	response.sendRedirect("loginForm.jsp");
%> --%>

<%

	try{

	member.setReg_date(new Timestamp(System.currentTimeMillis()));
	LogonDBBean manager = LogonDBBean.getInstance();
	manager.insertMember(member);
%>	
	<script>
		alert("회원가입되었습니다.");
	</script>
<%	
	//회원가입 후 로그인, 세션생성
	session.setAttribute("memId", member.getId());
	response.sendRedirect("main.jsp");

	//회원가입 후 로그인 페이지로
	//response.sendRedirect("loginForm.jsp");
	}catch(Exception ex){}



%>

