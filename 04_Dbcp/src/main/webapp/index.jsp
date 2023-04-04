<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- <a href="/04_Dbcp/getAllBoardList.do">게시판</a>    게시판으로 넘어가면 목록보기로 넘어감 --%>
	
	<%-- 
	<a href="<%=request.getContextPath()%>/getAllBoardList.do">게시판</a>
	
	<%
		pageContext.setAttribute("contextPath", request.getContextPath());
	%>
	<a href="${contextPath}/getAllBoardList.do">게시판</a>
	 --%>
	   
	<%-- 자바코드를 사용하기 싫어서 최종적으로 쓰는게 <c:set>, 자바에서 쓰던걸 태그화 EL화해서 자바 요소를 모두 없애는 과정이였다, 지양하는 과정 --%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />
	<a href="${contextPath}/getAllBoardList.do">게시판</a>
	
	<%-- <a href="<c:url value="/getAllBoardList.do" />">게시판</a> --%>
	<%-- contextPath를 따로 안적어줘도 자기가 알아서 처리해준다. --%>
	
</body>
</html>