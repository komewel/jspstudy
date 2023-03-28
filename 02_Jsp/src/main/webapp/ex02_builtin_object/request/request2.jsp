<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<!-- optional로 한번 싸가지고 두개의 값을 변동적으로 출력할 수 있다, if대신에 쓴다고 보면 된다. -->
	<% 
		request.setCharacterEncoding("UTF-8");
		String model = request.getParameter("model");
		Optional<String> opt = Optional.ofNullable(request.getParameter("price")); // 값이 null 일수도 있을때도 항상 싸준다		
		//Object strPrice = opt.orElse("0"); // 값이 있으면 get기반의 메소드를 써도 되는데 null 일수도 있을때 꺼내는 메소드 orElse()
		int price = Integer.parseInt(opt.orElse("0"));
	%>
	
	<h1>모델 : <%=model %></h1>
	<h1>가격 : <%=price %></h1>

</body>
</html>