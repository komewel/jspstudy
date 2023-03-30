<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse(null);
%>
<title><%=title %></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/prac/prac.css">
<script src="<%=request.getContextPath()%>/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>
	
	<%if(session.getAttribute("loginId") == null){%>
		<div>
			<form action="prac1.jsp" method="post">
				<div><input type="text" name="id" placeholder="아이디"></div>
				<div><input type="password" name="pw" placeholder="비밀번호"></div>
				<div><button>로그인</button></div>
			</form>
		</div>
	<% } else { %>
		<div>
			<%=session.getAttribute("loginId")%> 님 반갑습ㄴ디ㅏ
		</div>
		
		<% } %>
		
		<script>
			$('#btn_logout')
		</script>
		
</body>
</html>