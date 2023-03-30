<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
		Jsp의 binding (속성을 저장할 수 있는 영역)
		1. pageContext : this(this로 따지자면 현재객체, 이파일자체가 this, 현재 페이지라고 생각하면 된다 이름에도 page가 들어감) 현재 Jsp 페이지에서 접근할 수 있다.
		2. request	   : HttpServletRequest, 하나의 요청에서 접근할 수 있다.(일회용)  
		3. session	   : HttpSession, 		 브라우저 종료까지 접근할 수 있다.(기본설정은 30분)
		4. application : ServletContext, 	 애플리케이션 종료까지 접근할 수 있다.(서버가 종료될때까지)
	 --%>	

	<%--
		Jsp binding 우선 순위
		1. 같은 이름의 속성이 서로 다른 영역에 저장될 수 있다.(같은이름을 호출하면 누가 호출될 것 이냐, 우선순위를 정해두면 된다.)
		2. 접근 범위(Scope)가 좁을 수록 높은 우선 순위를 가진다, 우선순위에 따라 나오는 순서가 다를뿐 없어지진 않는다.
			높음 <-								  -> 낮음
			pageContext > request > session > application
		3. 바인딩영역(scope를 지정하는법) 각 영역을 지정하는 표현언어(EL, 인코딩 속성값이 있다 인코딩 변수에있는걸 가져와서 쓴다) 내장 객체가 있다.
			   영역  	   : 내장객체
			1) pageContext : pageScope
			2) request	   : requestScope
			3) session	   : sessionScope
			4) application : applicationScope	
	 --%>
	 
	 <%
	 	pageContext.setAttribute("a", 1);
	 	request.setAttribute("a", 10);
	 	session.setAttribute("a", 100);
	 	application.setAttribute("a", 1000);
	 %>
	 
	 <h1><%=pageContext.getAttribute("a")%></h1>
	 <h1><%=request.getAttribute("a")%></h1>
	 <h1><%=session.getAttribute("a")%></h1>
	 <h1><%=application.getAttribute("a")%></h1>
	 <%-- 위 표현식 -> 표현언어 로 바뀐다고 함(${a}) --%>
	 
	 

</body>
</html>