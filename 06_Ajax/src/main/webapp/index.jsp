<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	
</script>
</head>
<body>

	<div class="wrap">
	
		<h1>회원 관리</h1>
		<form id="frm_member"> <!-- ajax처리에서는 서브밋이 필요없으므로 action속성이 필요없다 -->
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" name="id">
				<span id="id1">아이디가 맞지 않습니다.</span>
				<span id="id2">아이디가 맞습니다.</span>
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<input type="radio" id="male" name="gender" value="M"> <!-- 밸류값이 존재해야 데이터베이스와 연동시 유용하게 쓰인다. -->
				<label for="male">남자</label>
				<input type="radio" id="female" name="gender" value="F">
				<label for="female">여자</label>
			</div>
			<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address">
			</div>
			<div>
				<input type="hidden" name="memberNo" id="memberNo"> <!-- 보이지는 않지만 데이터를 주고 싶을땐 hidden -->
				<input type="button" value="초기화" onclick="fnInit()">
				<input type="button" value="신규등록" onclick="fnAddMember()">
				<input type="button" value="변경저장" onclick="fnModifyMember()">
				<input type="button" value="삭제" onclick="fnRemoveMember()">
			</div>
		</form>
	
		<hr>
		
		<table border="1">
			<caption>전체 회원 수 : <span id="member_count"></span>명</caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td>버튼</td>
				</tr>
			</thead>
			<tbody id="member_list"></tbody>
		</table>
	
	</div>
	<script>
	
	
		
	/* 함수 호출 */
	fnInit();
	fnGetAllMember();
	
	/* 함수 정의 */
	function fnInit(){
		$('#id').val('').prop("disabled", false);
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);
		$('#address').val('');
	}
	
	function fnGetAllMember(){ // function은 호이스팅 대상이므로 호출할시 순서 상관이 없다
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/list.do',
			// 응답
			dataType: 'json',
			success: function(resData){  // 응답 데이터는 resData로 전달된다.
				/*
					resData <--- out.println(obj.toString())
					resData = {
						"memberCount": 2,
						"memberList": [
							{ },
							{ }
						]
					}
				*/
				
				$('#member_count').text(resData.memberCount);
				
				let memberList = $('#member_list');
				memberList.empty();  // 기존의 회원 목록을 지운다는뜻 혹시 모를 데이터 처리
				
				if(resData.memberCount === 0){
					memberList.append('<tr><td colspan="6">회원이 없습니다.</td></tr>');
				} else {
					/* $.each(배열, (인덱스, 요소)->{}), 배열의 요소 하나씩 자동으로 꺼낸다 */
					/* $.each(배열, function(인덱스, 요소){}) */
					$.each(resData.memberList, function(i, element){ // element는 한 회원 객체를 의미한다. 
						let str = '<tr>';
						str += '<td>' + element.memberNo + '</td>';
						str += '<td>' + element.id + '</td>';
						str += '<td>' + element.name + '</td>';
						str += '<td>' + (element.gender === 'M' ? '남자' : '여자') + '</td>';
						str += '<td>' + element.address + '</td>';
						str += '<td><input type="button" value="조회" class="btn_detail" onclick="fnGetMember(' + element.memberNo + ')"></td>';
						// onclick="fnGetMember() 이런식으로 온클릭 기능을 추가해서 쓸수도 있다
						memberList.append(str);
					})
				}
			}
		})
	}
	
	function fnAddMember(){
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/add.do',
			data: $('#frm_member').serialize(), // 폼의 모든 요소를 파라미터로 전송한다. (입력 요소에는 name 속성이 필요하다.)
			// 응답
			datatype: 'json',
			success: function(resData){ // try문의 응답이 resData에 저장된다. resData = {"insertResult" : 1}
				if(resData.insertResult === 1){
					alert('신규 회원이 등록되었습니다.');
					fnGetAllMember(); // 새로운 회원 목록으로 갱신하기
					fnInit();
				}else {
					alert('신규 회원 등록이 실패했습니다.');
				}
			},
			error: function(jqXHR) { // jqXHR 객체에는 예외코드(응답코드: 404, 500등)와 예외메시지 등이 저장된다.
									 // catch문의 응답 코드는 jqXHR 객체의 status 속성에 저장된다. 
									 // catch문의 응답은 jqXHR 객체의 responseText 속성에 전달된다.
				alert(jqXHR.responseText + '(' + jqXHR.status + ')'); 
				
			}
			
		})
		
	}
	
	
	
	// onclick="fnGetMember(element.memberNo)"
	// fnGetMember() 함수 호출할 때 회원번호(element.memberNo)를 인수로 전달하면 매개변수 memberNo가 받는다. 
	function fnGetMember(memberNo) {
		// 자바 스크립트에서는 변수 타입 선언을 따로 안한다.
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/detail.do',
			data: 'memberNo=' + memberNo, // 파라미터를 의미한다.
			// 응답
			dataType: 'json',
			success: function(resData){ // resData = {"member" : {"memberNo":회원번호,"gender" : "M"}}
				alert('회원 정보가 조회되었습니다.');
				$('#id').val(resData.member.id).prop("disabled", true);
				$('#name').val(resData.member.name);
				$(':radio[name=gender][value=' + resData.member.gender + ']').prop('checked', true); // name=gender 이거로만 알기에는 데이터가 두개라서 추정하기 힘들다, value 값으로  고로 이렇게 조건을 한번 더 넣는다 변수처리로
				$('#address').val(resData.member.address);
				$('#memberNo').val(resData.member.memberNo); // <input type="hidden">에 저장하는값
				
			}
			
		})
		
		
	}
	
	function fnModifyMember(memberNo){
		
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/modify.do',
			data: $('#frm_member').serialize(),
			// 응답
			dataType: 'json',
			success: function(resData){ // resData = {"updateResult" : 1}
				if(resData.updateResult === 1) {
					alert('회원 정보가 수정되었습니다.');
					fnGetAllMember(); // 새로운 회원 정보로 갱신
				}else {
					alert("회원 정보 수정이 실패했습니다.")
				}
				
			}
			
			
		})
	}
	
	function fnRemoveMember(memberNo) {
		
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/remove.do',
			data: 'memberNo=' + memberNo,
			// 응답
			dataType: 'json',
			success: function(resData){
				alert('회원 정보가 삭제되었습니다. ');
				$('#id').val()
			}
		})
	}
	
	$('#id1').on('keyup', function(){
		var ava = '';
		if(id1.val().length() == 10){
			id1.text('아이디 형식이 맞지 않습니다.')
		}
	})
		
	}
	
	

	
</script>

</body>
</html>