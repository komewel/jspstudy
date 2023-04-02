<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- prefix(앞에 고정하다, 앞에다 고정하는말) c:set -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		<c:set></c:set>
		1. 속성(Attribute)을 만드는 태그이다.
		2. binding 영역(page(디폴트 생략가능), request, session, application)을 지정할 수 있다.
		3. 형식
		<c:set var="속성명" value="값" scope="영역">
		4. 예시, 속성값을 추가해주는 (새로운)라이브러리방식
			<c:set var="a" value="1" scope="page"></c:set>
			<c:set var="a" value="10" scope="request"></c:set>
			<c:set var="a" value="100" scope="session"></c:set>
			<c:set var="a" value="1000" scope="application"></c:set>
	 --%>	
	 
	 <c:set var="age" value="46" scope="page"></c:set> <%-- 태그 속성을 ctrl+spacebar로 추가할 수 있다 --%> 
	 <c:set var="isAlive" value="${age <= 100}" scope="page"></c:set>

	 
	
	<c:set var="age" value="46" scope="page"></c:set>
	<c:set var="isAlive" value="${(age <= 100) ? '살았다' : '죽었다'}" scope="page"></c:set>
	<c:set var="height" value="1.86" scope="page"></c:set>
	<c:set var="weight" value="99" scope="page"></c:set>
	<c:set var="bmi" value="${weight div (height * height)}" scope="page"></c:set>
	<c:set var="health" value="${(bmi < 20) ? '저체중' : (bmi < 25) ? '정상' : '비만'}" scope="page"></c:set>
	
	<c:set var="name" value="김영환" scope="page"></c:set>
	<c:set var=""></c:set>
	
	<ul>
		<li>나이 : ${age}살</li>
		<li>생존 : ${isAlive}</li>
		<li>bmi지수  : ${bmi}</li>
		<li>건강상태 : ${health}</li>
	</ul>
	

</body>
</html>