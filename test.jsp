<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery Ajax Fileupload</title>
<script type="text/javascript"
	src="/FileUpload/resource/smarteditor/js/HuskyEZCreator.js"
	charset="utf-8"></script>


<!-- jQuery 2.2.4 -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- jQuery Form Plugin -->
<script src="http://malsup.github.com/min/jquery.form.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		//jquery.form.js 플러그인 사용
		//	http://malsup.com/jquery/form/

		$("#fileForm").ajaxForm({

			beforeSend : function(elClickedObj) {
				oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
				try {
					elClickedObj.form.submit();
				} catch (e) {
				}
			},
			dataType : "json",
			success : function(res) {
				console.log("성공");
			},
			error : function() {
				console.log("실패");
			}
//ddd
		});
	});
</script>

</head>
<body>

	<h1>jQuery 라이브러리를 이용한 Ajax 파일 업로드</h1>
	<hr>

	<form id="fileForm" action="/FileUpload/jquery" method="post"
		enctype="multipart/form-data">

		<label>제목 : <input type="text" name="title" /></label><br> <label>본문
			: <textarea name="content" id="content"> </textarea>
		</label><br> <label>첨부1 : <input type="file" name="file1" /></label><br>
		<label>첨부2 : <input type="file" name="file2" /></label><br> <label>첨부3
			: <input type="file" name="file3" />
		</label><br> <br>

		<button id="btnSend">보냅니다!</button>
	</form>
	<script>
		// 스마트에디터 스킨 적용
		var oEditors = [];
		nhn.husky.EZCreator
				.createInIFrame({
					oAppRef : oEditors,
					elPlaceHolder : "content", //<textarea>의 id 입력
					sSkinURI : "/FileUpload/resource/smarteditor/SmartEditor2Skin.html",
					fCreator : "createSEditor2",
					htParams : {

						bUseToolbar : true, //툴바 사용여부
						bUseVerticalResizer : false, //입력창 크기 조절 바
						bUseModeChanger : true
					//모드 탭
					}
				});

		//<form>의 submit에 맞춰 스마트에디터 내용 적용
		function submitContents(elClickedObj) {
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			try {
				elClickedObj.form.submit();
			} catch (e) {
			}
		}
	</script>
</body>
</html>