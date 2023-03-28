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
	
	<div>
		<input type="text" >
	</div>
	<ul>
		<li>1</li>
		<li>2</li>
		<li>3</li>
	</ul>
	
		
</body>
</html>