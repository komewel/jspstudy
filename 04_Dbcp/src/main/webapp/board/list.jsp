<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	$(function(){
		// 작성 화면으로 이동
		$('#btn_write').on('click', function(){
			location.href = '${contextPath}/writeBoard.do';
		})
		// 삭제 링크 클릭, 삭제 클릭한걸 하나 선택하기 위해 this가 유용하게 쓰인다 판별하기 위해
		$('.link_remove').on('click', function(event){ // event객체를 쓰므로 event.preventDefault()를 쓸수 있는듯
			if(confirm('삭제할까요?') == false){ // false, 취소를 눌렀다면
				event.preventDefault(); // <button> 태그의 기본 동작은 submit 속성의 동작을 막는다, 클릭을 했음에도 링크이동을 안한다
				return;
			}
		})

	})
</script>
</head>
<body>

	<div>
		<h1>게시글 목록</h1>
		<div>
			<input type="button" value="작성하기" id="btn_write">
		</div>
		<div>
			<table border="1">
				<thead>
					<tr>
						<td>게시글번호</td>
						<td>제목</td>
						<td>작성일자</td>
						<td>삭제</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${boardList}" var="board" varStatus="vs">
						<tr>
							<td><fmt:formatNumber value="${boardListCount - vs.index}" pattern="#,##0" /></td>
							<td><a href="${contextPath}/getBoardByNo.do?board_no=${board.board_no}">${board.title}</a></td>
							<td><fmt:formatDate value="${board.created_date}" pattern="yy.MM.dd" /></td>
							<td>
								<form method="post" action="${contextPath}/removeBoard.do">
									<input type="hidden" name="board_no" value="${board.board_no}">
									<button class="link_remove"><i class="fa-solid fa-x"></i></button>
							</form>
							</td>
						
						</tr>	 <%-- 반복문에선 함부로 id값을 주면 안된다 동일한 id를 가지게 되므로 고로 class --%>
								 <%-- Controller에서 한번 거친다음에 또 분류해준다 브라우저에서 파라미터 값이 제대로 넘어가는지 확인 --%>
					</c:forEach>
				</tbody>
			</table>
		</div>
<%-- 고질적인 문제는 이런식으로 주소창에 파라미터를 보내면 보안상 문제가 된다 해결책은 post인데 이방식은 form태그에다가 method를 post로 해서 보내면 된다.
	 허나 jsp에서는 내부적인 오류인지 post가 제대로 안먹는다, form요소는 null로 처리 불가하다 무조건 가는거다 고로 다른방식으로 접근해야한다 --%>
	</div>
</body>
</html>