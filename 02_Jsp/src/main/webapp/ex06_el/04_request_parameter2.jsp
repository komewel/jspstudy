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
		파라미터 확인을 위한 EL 객체(이 객체가 있어야만 받은 파라미터값을 확인할 수 있다.)
		1. param	   
		2. paramValues : 배열일때 
	 --%>
	 
	 <h1>${a}</h1>
	 <h1>${param.a}</h1>
	 <h1>${paramValues.b[0]}</h1>
	 <h1>${paramValues.b[1]}</h1>
	 <h1>${paramValues.b[2]}</h1>
	 
	 <%-- 파라미터 x, y의 크기 비교, 파라미터는 ※무조건 String※이라서 제대로된 크기비교가 불가하다
	 	  (String을 비교하는건 사전 편찬 순으로 크기를 비교한다. --%>
	 <ul>
	 	<li>${param.x lt param.y}</li>
	 	<li>${param.x le param.y}</li>
	 	<li>${param.x gt param.y}</li>
	 	<li>${param.x ge param.y}</li>
	 </ul>

	<%-- 파라미터 x, y의 크기 비교 해결(EL에서 "10" - "5"== 5이다.), 이상하지만 받아들이자 자바에서는 안해주지만 알아서 해준다 --%>
	<ul>
	 	<li>${param.x - param.y lt 0}</li>
	 	<li>${param.x - param.y le 0}</li>
	 	<li>${param.x - param.y gt 0}</li>
	 	<li>${param.x - param.y ge 0}</li>
	 </ul>
</body>
</html>