<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>함수사용</title>
</head>
<body>


<c:set var="str1" value="사용 Functions <태그>를 사용합니다."/>
<c:set var="str2" value="사용"/>
<c:set var="tokens" value="1,2,3,4,5,6,7,8,9,10"/>

length(str1) = ${fn:length(str1) } <br>
toUpperCase(str1) = ${fn:toUpperCase(str1) }<br>
toLowerCase(str1) = ${fn:toLowerCase(str1) }<br>
substring(str1, 3, 6) = ${fn:substring(str1, 3, 6 )}<br>
substringAfter(Str1, sTr2) = ${fn:substringAfter(str1, str2) }<br>
substringBefore(Str1, sTr2) = ${fn:substringBefore(str1, str2) }<br>
trim(str1) = ${fn:trim(str1) }<br>
replace(str1, src, dest) = ${fn:replace(str1, " ", "_" )}<br>
indexOf(str1, str2) = ${fn:indexOf(str1, str2) }<br>
startsWith(str1, str2) = ${fn:startsWith(str1, "fun") }<br>
endsWith(str1, str2) = ${fn:endsWith(str1, "합니다") }<br>
contains(str1, str2) = ${fn:contains(str1, str2) }<br>
containsIgnoreCase(str1, str2) = ${fn:containsIgnoreCase(str1, str2) }<br>

<c:set var="array" value="${fn:split(tokens, ',')}"/> <br>

join(array, "-") = ${fn:join(array, "-") } <br>
escapeXml(str1) = ${fn:escapeXml(str1) }




<br><br>
<br>
<br>


</body>
</html>