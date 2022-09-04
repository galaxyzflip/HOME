<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<% String memberID = request.getParameter("memberID"); %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try{
		
		String jdbcDriver = "jdbc:oracle:thin:@192.168.0.6:1521:xe?" + "useUnicode=true&characterEncoding=utf8";
		String dbUser = "ez";
		String dbPass = "oracle";
		String query = "select * from member where id='" + memberID +"'";
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		if(rs.next()){
%>			
<table border="1">

	<tr>
		<td>아이디</td><td><%= memberID %></td>
	</tr>
	
	<tr>
		<td>암호</td><td><%=rs.getString("password") %></td>
	<tr>

	<tr>
		<td>이름</td><td><%=rs.getString("id") %></td>
	<tr>

	<tr>
		<td>이메일주소</td><td><%=rs.getString("email") %></td>
	<tr>

	<tr>
		<td>전화번호</td><td><%=rs.getString("tel") %></td>
	<tr>
</table>
	
<%		
	} else{
%>
<%= memberID %> 에 해당하는 정보가 존재하지 않습니다.
<% 	
	}
}finally{
	if(rs != null) try{rs.close();} catch(SQLException es) {};
	if(stmt != null) try{stmt.close();} catch(SQLException ex){};
	if(conn != null) try{conn.close();} catch(SQLException ex){};
}

%>
 

</body>
</html>