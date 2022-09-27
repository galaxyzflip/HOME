<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 목록</title>
<link rel="stylesheet" type="text/css" href="style.css"/>

</head>
<body>

<div id="wrap">
	<div id="header">
		<c:if test="${listModel.totalPageCount > 0 }">
			${listModel.startRow } - ${listModel.endRow }
			<span class="count">[<span class="spot">${listModel.requestPage }</span> / ${listModel.totalPageCount }]</span>
		</c:if>
		
	</div>
	
	<div id="content">
	
		<table class="listForm">
			<colgroup>
				<col width="10%"/>
				<col width="*"/>
				<col width="10%"/>
				<col width="15%"/>
				<col width="15%"/>
			</colgroup>
			
			<tr>
				<th>번호</th>
				<th>파일명</th>
				<th>파일크기</th>
				<th>다운로드회수</th>
				<th>다운로드</th>
			</tr>
		<c:choose>
		
			<c:when test="${listModel.hasPdsItem == false }">
			<tr>
				<td colspan="5" class="noData">게시글이 없습니다.</td>
			</tr>
			</c:when>
			
			
			
			<c:otherwise>
				<c:forEach var="item" items="${listModel.pdsItemLists }">
				
			<tr class="listCon">
				<td>${item.id }</td>
				<td class="subject">${item.fileName }</td>
				<td>${item.fileSize }</td>
				<td>${item.downloadCount }</td>
				<td><a href="download.jsp?id=${item.id }">[DOWNLOAD]</a></td>
			</tr>

				</c:forEach>
			</c:otherwise>
		
		</c:choose>	
		</table>
		
		<div class="paging">
		
			<c:if test="${beginPage > 10 }">
				<a href="<c:url value="list.jsp?p=${beginPage-1 }&search=${search}"/>">이전</a>
			</c:if>
		


			<c:forEach var="pno" begin="${beginPage }" end="${endpage }">
				<c:choose>
					<c:when test="${listModel.requestpage == pno }">
						<a href="<c:url value="list.jsp?p=${pno }&search=${search}"/>"><span class="currentPage">[${pno }]</span></a>
					</c:when>
					
					<c:otherwise>
						<a href="<c:url value="list.jsp>p=${pno }&search=${search}"/>">[${pno }]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${endpage < listModel.totalpageCount }">
				<a href="<c:url value="list.jsp?p=${endPage + 1 }&search=${search}"/>">다음</a>
			</c:if>
		
		</div>
	
	</div>
	
	
	<form action="list.jsp" method="get">
		<div class="searchWarp">
			<input type="text" name="search" value="${search }"/>
			<input type="submit" value="검색" class="btn"/>
		</div>
	</form>
	
	<div id="footer"><a href="uploadForm.jsp">파일첨부</a></div>
	
</div>

</body>
</html>