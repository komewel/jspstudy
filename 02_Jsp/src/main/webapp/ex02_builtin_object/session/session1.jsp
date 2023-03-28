<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String sessionId = session.getId();
	%>
	<h1><%=sessionId%></h1><%-- 세션아이디값을 한번이라도 기억하고있다면 자기자신이라는건 자명하니까 자동로그인이 된다
								db에 저장을 하는데 db에만 저장하는게 아니라 쿠키(쿠키에 적어둔다)에도 저장한다.
								쿠키에 있는값과 db에있는값을 대조해 파악한다. --%>
								
</body>
</html>