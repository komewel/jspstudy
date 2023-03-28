<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html></html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse("환영합니다");
%>
<title><%=title%></title>
	<%-- request.getContextPath() == /02_Jsp ,/02_Jsp여기를 참조해서주소창에 값은 /02_Jsp로 나오지만 실제로는 webapp이 Contextpath로 인식된다 --%> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/header.css">
<script src="<%=request.getContextPath()%>/resource/js/lib/jquery-3.6.4.min.js"></script> <%-- Contextpath가 변수 처리되서 설령 null이 나온다해도 알아서 처리된다. --%>
</head>
<body>
	
	<nav>
		<ul>
			<li><a href="body1.jsp">body1</a></li>
			<li><a href="body2.jsp">body2</a></li>
			<li><a href="body3.jsp">body3</a></li>
		</ul>
	</nav>
	
	<hr>
	
