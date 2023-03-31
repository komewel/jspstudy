<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${today != null}">
		<h1>오늘은 ${today}입니다.</h1>
	</c:if>
	<c:if test="${age != null}">
		<h1>나이는 ${age}살입니다.</h1>
	</c:if>
	<c:if test="${bmi != null}">
		<h1>BMI ${bmi}는 ${health}입니다.</h1>
	</c:if>
	<%-- MybmiService에서 가져왔음 request에 저장되어 있어서
		el로 따로 객체화나 임포트를 안해도 정보가 넘어올 수 있는거다 --%>
</body>
</html>