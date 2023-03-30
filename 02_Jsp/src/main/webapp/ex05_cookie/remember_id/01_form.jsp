<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>

<% if(session.getAttribute("loginId") == null) { %>
	<div>
		<form method="post" action="02_login.jsp">
			<div><input type="text" name="id" id="id" placeholder="아이디"></div>
			<div><input type="password" name="pw" placeholder="비밀번호"></div>
			<div>
				<input type="checkbox" name="chk_auto_login" id="chk_auto_login">
				<label for="chk_auto_login">자동 로그인</label>
			</div>
			<div><button>로그인</button></div>
		</form>
	</div>
<% } else { %>
	<div>
		<%=session.getAttribute("loginId")%>님 반갑습니다
		<input type="button" value="로그아웃" onclick="fnLogout()">
	</div>
<% } %>

<script>
	function fnLogout(){
		location.href = '03_logout.jsp';
	}
</script>

</body>
</html>