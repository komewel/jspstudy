<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>
	<!-- web.xml파일에 웰콤 파일에 index.jsp파일이 등록되어 있어서 이거부터 참고한다 
	인덱스 파일에서 목록(list.jsp)를 내놓으라고 하면 BbsListService -> bbsList -> bbs/list.jsp 이런식으로 파일이 열린다. -->
	
	<a href="${contextPath}/list.do">BBS</a>
</body>
</html>