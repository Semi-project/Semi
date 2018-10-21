<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript"
	src="/resource/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#save").click(function() {
			oEditors.getById["feature"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#form").submit();
		});
	});

	var oEditors = [];
	$(function() {
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "feature",
			//SmartEditor2Skin.html 파일이 존재하는 경로 
			sSkinURI : "/resource/smarteditor/SmartEditor2Skin.html",
			htParams : { // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseVerticalResizer : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseModeChanger : true,
				fOnBeforeUnload : function() {
				}
			},
			fOnAppLoad : function() { //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용 
				oEditors.getById["feature"].exec("PASTE_HTML", [ "" ]);
			},
			fCreator : "createSEditor2"
		});
	});
</script>
</head>
<body>
	<div class="wrapper">
		<div class="row">
			<div class="col-lg-4">여기는 왼쪽;</div>
			<div class="col-lg-4">
				<form id="form" action="/adoption/form" method="post">
					<table width="100%">
						<tr>

							<td><label for="name">동물이름 </label></td>
							<td><input type="text" name="name" id="name" size="20px">
							</td>
						</tr>
						<tr>
							<td><label for="age">나이 숫자 </label></td>
							<td><input type="text" id="age"></td>
						</tr>
						<tr>
							<td><label for="gender">성별</label></td>
							<td><input type="radio" name="gender" value="남">남 <input
								type="radio" name="gender" value="여" checked>여</td>
						</tr>
						<tr>
							<td><label for="gr">무게 </label></td>
							<td><input type="text" id="gr"></td>
						</tr>
						<tr>
							<td><label for="neuters">중성화 </label></td>
							<td><input type="radio" name="neuters" value="됨" checked>중성화
								안됨 <input type="radio" name="neuters" value="안됨">중성화 안됨</td>
						</tr>
						<tr>

							<td><label for="species">품종 </label></td>
							<td><input type="text" name="species" id="species"
								size="20px"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea rows="10" cols="50" id="feature"
									name="feature" style="width: 766px; height: 412px;"></textarea></td>
						<tr>
						<tr>
							<td colspan="2"><input type="button" id="save" value="저장" />
								<input type="button" value="취소" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

</body>
</html>