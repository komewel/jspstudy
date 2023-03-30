<%@page import="ex06_el.Book"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
		4. Jsp binding 영역에 저장된 값은 모두 EL로 표현할 수 있다.
	 --%>
	<%-- int a = 1; --%> 
	<%-- ${a}, 자바변수 그냥 불러다 쓰는건 안된다 --%>
	 
	<%
		pageContext.setAttribute("a", 1);
		request.setAttribute("a", 10);
		session.setAttribute("a", 100);
		application.setAttribute("a", 1000);
	%>
	 
	<%-- 우선순위 확인 --%>
	<h1>${a}</h1>
	
	<%-- 모든 영역의 속성 a 확인 --%>
	<h3>${pageScope.a}</h3>
	<h3>${requestScope.a}</h3>
	<h3>${sessionScope.a}</h3>
	<h3>${applicationScope.a}</h3>
	 
	 <%-- 
	 	EL 연산자(자바가 해석한다, 자바랑 연동되는것이 많다)
	 	1. 산술연산 : + - * /(div) %(mod)
	 	2. 관계연산 : <(lt) <=(le) >(gt) >=(ge) ==(eq) !=(ne)
	 	3. 논리연산 : &&(and) ||(or) !(not)
	 	4. 조건연산 : (조건식) ? 참 : 거짓
	 	5. 연결연산 : "문자열1" +  "문자열2" +  "문자열1" +  "문자열" ("", '' 둘다가능)
	  --%>
	  
	<%
		// EL 사용을 위해서 pageContext에 저장한다.
		pageContext.setAttribute("x", 5);
		pageContext.setAttribute("y", 2);
	%>
	
	<ul>
		<li>${x + y}</li>
		<li>${x - y}</li>
		<li>${x * y}</li>
		<li>${x div y}</li>
		<li>${x mod y}</li>
	</ul>
	
	<ul>
		<li>${x lt y}</li>
		<li>${x le y}</li>
		<li>${x gt y}</li>
		<li>${x ge y}</li>
		<li>${x eq y}</li>
		<li>${x ne y}</li>
	</ul>
	
	<ul>
		<li>${x eq 5 and y eq 2}</li>
		<li>${x eq 6 or y eq 2}</li>
		<li>${not (x eq 5)}</li>
	</ul>

	<%-- Map 사용하기 --%>
	<%
		Map<String, Object> book = new HashMap<>();
		book.put("author", "생텍쥐베리");
		book.put("title", "어린왕자");
		book.put("price", 10000);
		pageContext.setAttribute("book", book);  // EL 사용을 위해서 pageContext에 저장한다.
	%>
	
	<ul>
		<li>저자: ${book.author}</li>
		<li>제목: ${book.title}</li>
		<li>가격: ${book.price}</li>
	</ul>
		
		<%-- 객체 사용하기, 실제 개발 환경에선 Map과 객체가 동급이다 --%>
		<%
		Book book2 = new Book();
		book2.setAuthor("황순원");
		book2.setTitle("소나기");
		book2.setPrice(10000);
		pageContext.setAttribute("book2", book2);
		%>
		<ul>
			<li>저자: ${book2.author}</li> <%-- 자동으로 ${book2.getAuthor()} 호출되어 실행된다. --%>
			<li>제목: ${book2.title}</li>  <%-- 자동으로 ${book2.getTitle()} 호출되어 실행된다. --%>
			<li>가격: ${book2.price}</li>  <%-- 자동으로 ${book2.getPrice()} 호출되어 실행된다. --%>
		</ul> 
		
</body>
</html>