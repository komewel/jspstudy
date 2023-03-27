<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- 동적 include : 포함되는 페이지에 파라미터를 전달할 수 있다. (jsp 액션 태그) -->
	<jsp:include page="header.jsp">
		<jsp:param value="body1" name="title"/>
	</jsp:include> <!-- include와 파라미터 정보 넘기기가 동시에 가능하다, header.jsp 전달하고 싶은 파라미터를 태그사이에 정보로 넘겨줄 수 있다. --> 
	
	<h1>body1</h1>


<!-- 정적 include : 항상 같은 모습의 페이지를 포함한다. (include 지시어) -->
	<%@ include file= "footer.jsp" %>
