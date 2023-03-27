<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>

	<div>
		<!-- 파일 첨부폼 필수 속성 : method="post" enctype="multipart/form-data" -->
		<form id="frm_upload" action="/01_Servlet/UploadServlet" method="post" enctype="multipart/form-data">
			<div>
				<label for="uploader">작성자</label>
				<input type="text" id="uploader" name="uploader">
			</div>
			<div>
				<label for="filename">파일첨부</label>
				<input type="file" id="filename" name="filename">
			</div>
			<div>
				<button>첨부하기</button>
				<input type="reset" value="다시작성">
			</div>
		</form>
	</div>
	<script>
		$('#filename').on('change', function(){ // change 이벤트 파일이 바뀌면 체인지 된다해서 이벤트 이름이 이렇다고함
			
			// 확장자 제한하기, 파일에서는 첨부된 파일명이 value가 된다
			var filename = $(this).val(); // 여기서 this 는 이벤트 대상이된다  $(this).val()는 첨부된 파일명 
			var extname = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();  // 첨부된 파일 확장자 소문자하여(toLowerCase()) 분리하는 작업
			var acceptList = ['jpg', 'jpeg', 'png', 'gif', 'tif'];  // 허용할 확장자 목록
			
			if($.inArray(extname, acceptList) == -1){ // 없으면 -1 
				alert('이미지만 첨부할 수 있습니다.');
				$(this).val('');  // 이름을 빈문자열로 바꾼다, 첨부된 파일이 없어짐
				return;
			}
			
			// 파일 크기 제한하기
			var filesize = this.files[0].size;  // 첨부된 파일 크기
			var maxsize = 1024 * 1024 * 10; //최대크기, 브라우저 element properties에서 size가 여기서 말하는 size와 동일한 값이다. 
			if(filesize > maxsize){
				alert('첨부파일의 최대 크기는 10MB입니다.');
				$(this).val('');
				return;
			}
			
		})
	</script>

</body>
</html>