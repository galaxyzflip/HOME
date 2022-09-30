<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="gallery.Theme" %>
<%@ page import="gallery.GalleryDAO" %>
<%@ page import="gallery.ThemeManagerException" %>

<%@  page import="java.text.SimpleDateFormat" %>
<%@ page import="board.*"  %>
<%@ page import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
<link rel="stylesheet" type="text/css" href="style.css"/>

	
	<script type="text/javascript">

function fnImgPop(url){
  var img=new Image();
  img.src=url;
  var img_width=img.width;
  var win_width=img.width+25;
  var img_height=img.height;
  var win=img.height+30;
  var OpenWindow=window.open('','_blank', 'width='+img_width+', height='+img_height+', menubars=no, scrollbars=auto');
  OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
    
 }

</script>

	</head>

</html>

<%
	String themeId = request.getParameter("id");
	GalleryDAO manager = GalleryDAO.getInstance();
	Theme theme = manager.select(Integer.parseInt(themeId));

%>



<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	int contentNum =  Integer.parseInt(themeId);
	String boardClass = "boardClass";
	

	
	
	//현재 페이지
	int currentPage = 1;
	
	String cPageStr = request.getParameter("currentPage");
	if(cPageStr != null && !cPageStr.equals("null")){
		currentPage = Integer.parseInt(cPageStr);
	}
	//..현재 페이지
	
	int startRow = (currentPage * PAGE_SIZE) - (PAGE_SIZE-1);
	int endRow = currentPage  * PAGE_SIZE;
	
	CommentDAO cManager = CommentDAO.getInstance();
	int count = cManager.getCommentCount(contentNum, boardClass);
	ArrayList<CommentDTO> comments = cManager.getComments(contentNum,startRow,endRow, boardClass);
			
	
	
	


%>


<c:set var="theme" value="<%=theme %>"/>
<c:if test="${empty theme }">
존재하지 않는 테마 이미지입니다.
</c:if>


<c:if test="${!empty theme }">
	
	<table class="main">
		<tr>
			<td>제목</td>
			<td>${theme.title }</td>
		</tr>
		
		
		<tr>
			<td>작성자</td>
			<td>${theme.name }
				 <c:if test="${empty theme.email }">
					<a href="mailto"${theme.email }>[이메일]</a>
				</c:if>
			</td>
		</tr>
		
		<c:if test="${!empty theme.image }">
			<tr>
				<td colspan="2" align="center">
					<a href="javascript:viewLarge('/HOME/image/${theme.image }')">
						<%-- <img src="/HOME/image/${theme.image }" width="200" border="0"> --%>
					<img id="imgControll" name="imgControll" src="/HOME/image/${theme.image }" width="150" height="100" onclick="fnImgPop(this.src)">
						
						
						
					</a>
					
				</td>
			</tr>
		
		</c:if>
		
		<tr>
			<td>내용</td>
			<td><pre>${theme.content }</pre></td>
		</tr>
		
		<tr>
			<td colspan="2">
			<a href="javascript:goReply()">[답변]</a>
			<a href="javascript:goModify()">[수정]</a>
			<a href="javascript:goDelete()">[삭제]</a>
			<a href="javascript:goList()">[목록]</a>
			</td>
		</tr>
		
	</table>
</c:if>

<script language="javascript">
	
		function goReply(){
			document.move.action="writeForm.jsp";
			document.move.submit();
		}
		
		function goModify(){
			document.move.action="updateForm.jsp";
			document.move.submit();
		}
		
		function goDelete(){
			document.move.action="deleteForm.jsp";
			document.move.submit();
		}
		
		function goList(){
			document.move.action="list.jsp";
			document.move.submit();
		}
		function viewLarge(imgUrl){
			
		}
		
</script>

<!-- 댓글입력창 -->


<form name="move" method="post">
	<input type="hidden" name="id" value="${theme.id }">
	<input type="hidden" name="parentId" value="${theme.id }">
	<input type="hidden" name="groupId" value="${theme.groupId }">
	
	<input type="hidden" name="page" value="${param.page }">
	
	<c:forEach var="searchCond" items="${paramValues.search_cond }">
		
		<c:if test="${searchCond == 'title' }">
			<input type="hidden" name="search_cond" value="title">
		</c:if>
		
		<c:if test="${searchCond == 'name' }">
			<input type="hidden" name="search_cond" value="name">
		</c:if>
	
	</c:forEach>


	<c:if test="${!empty param.search_key }">
		<input type="hidden" name="search_key" value="${param.search_key }">
	</c:if>


</form>



<br>



			
			
<%! static final int PAGE_SIZE = 5; %>


<!-- 댓글 출력 -->

<table class="comment">

	<tr>
		<td colspan="2" align="center">코멘트 수 : <%=count %></td>
	</tr>
	

	<form method="post" action="commentPro.jsp" name="comment" onsubmit="return writeSave()">


	<tr align="center">
		<td colspan="2" align="center">
			<textarea name="commentt" rows = "6" cols="40" align="center"></textarea>
			<input type="hidden" name="con_num" value=<%=contentNum %>>
			<input type="hidden" name="boardClass" value=<%=boardClass %>>
			<input type="hidden" name="currentPage" value="<%=currentPage %>">
		</td>
	<tr align="center" height="30">
		<td align=center colspan="2">
			작성자<input type="text" name=commenter size=10>
			비밀번호<input type="password" name=passwd size=10>
			<input type="submit" value="코멘트달기">
		</td>
	</tr>


</form>
	
<br>
	
	<%
	if(count > 0){
		for(int i=0;i<comments.size();i++){
			CommentDTO cmt = comments.get(i);%>
			
			<tr align="center">
				<td align=left size=250 >
				<b><%=cmt.getCommenter() %> 님</b> (<%=sdf.format(cmt.getReg_date()) %>)
				</td>
				
				<td align=riglt size=250 > 접속 IP : <%=cmt.getIp() %>  
				<a href="delCommentForm.jsp?ctn=<%=cmt.getContent_num() %>&cmn=<%=cmt.getComment_num()%>&p_num=<%=currentPage%>">삭제</a>
				</td>
			</tr>
			
			<tr>
				<td colspan=2><%=cmt.getCommentt() %></td>
			
		<%} 
	}%>
	
</table>


	


<center>


	<!--  페이징 -->
	<% 
		if(count>0){
			int pageCount = count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
			//					1					
			int pageBlock = 5;
			int startPage = (int)(currentPage/5) * 5 + 1;
			int endPage = startPage + pageBlock - 1;
			if(endPage > pageCount) endPage = pageCount;
			
			
			
			if(startPage > 5){%>
				 <a href="read.jsp?id=${theme.id }&currentPage=<%=startPage - 5 %>">[<%=currentPage %>]</a>
			<%}
			for(int i=startPage;i<=endPage;i++){%>
				
				<a href="read.jsp?id=${theme.id }&currentPage=<%=currentPage %>">[<%=i %>]</a>
			
			<%}
			
			if(endPage < pageCount){ %>
			<a href="read.jsp?id=${theme.id }&currentPage=<%=startPage + 5 %>">[<%=currentPage %>]</a>
			
			
			<%}
		}
	
	%>
	
</center>	
