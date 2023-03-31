<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- <fn: > 이렇게 시작하는게 아니라(태그 구성이 아니다), ${fn: }이렇게 쓰인다. --%>
	<c:set var="str" value="Hello World" scope="page" />
	
	<h3>${fn:length(str)}</h3>
	<h3>${fn:substring(str, 0, 5)}</h3>
	<h3>${fn:substringBefore(str, ' ')}</h3>	<%-- '' 이전에 텍스트: Hello --%>
	<h3>${fn:substringAfter(str, ' ')}</h3>		<%-- '' 이후에 텍스트: World --%>
	
	<h3>${fn:indexOf(str, 'l')}</h3>	 <%-- lastIndexOf() 메소드는 없다(JSP에서는 없다) --%>
	<h3>${fn:replace(str,'l', 'z')}</h3> <%-- replaceAll()이 따로 필요없이 다 바꿔준다. --%>
	
	<c:set var="str2" value="&lt;script&gt;location.href='/';</script>" scope="page" /> <%-- value 값이 중요하다 --%>
	<h3>${fn:escapeXml(str2)}</h3> <%-- 여기 알아서 역할을 해주는 기능이 있는 함수가 있다. --%>
	<%-- &lt;script&gt;, 이것처럼태그로 인식이 안되게 하여서 미연의 불상사를 대비할수있다 저것을 독단적으로 쓰면 태그로 역할은 안한다.  --%>

	<c:if test="${fn:startsWith(str, 'Hello')}">	<%-- str이 Hello로 시작하느냐 --%>
		<h3>Hello로 시작한다.</h3>
	</c:if>

	<c:if test="${fn:endsWith(str, 'World')}">
		<h3>World로 끝난다.</h3>
	</c:if>

	<c:if test="${fn:contains(str, 'h')}">
		<h3>h를 포함한다.</h3>
	</c:if>

	<c:if test="${fn:containsIgnoreCase(str, 'h')}"> <%-- 대소문자 안가리고 입력된 문자를 점검한다. --%>
		<h3>H, h를 포함한다.</h3>
	</c:if>

	<c:set var="str3" value="a,b,c,d,e,f,g,h,i,j,k" scope="page" />
	<c:set var="items" value="${fn:split(str3, ',')}" scope="page" />
		
	<c:forEach var="item" items="${items}" varStatus="vs"> <%-- status() 메소드를 쓰기위해 varStatus값을 주는건가봄 --%>
		<div>${vs.index} - ${item}</div>
	</c:forEach>
	
	<c:set var="str4" value="${fn:join(items, ',')}" scope="page" /> <%-- 원상복구를 시켜주는 메소드이다. --%>
	<h3>${str4}</h3>
	
</body>
</html>