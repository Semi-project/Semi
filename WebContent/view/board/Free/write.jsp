<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript" src="/resource/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<jsp:include page="/view/layout/header.jsp" />


<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnWrite").click(function() {
		// 스마트에디터 내용으로 <textarea>적용
		var confirm = ("입력할꺼임?");
		
		
		if($('[name="title"]').val()==""){
			alert("제목을 채우세요.");
		}
		
	
		else{
			alert("작성하였습니다.");
			submitContents($("#btnWrite"));
			$("form").submit();
		}
		//submitContents($("#btnWrite"));
		
		// submit
		//$("form").submit();
	});
	
	$("#btnCancel").click(function() {
		history.go(-1);
	});
});
</script>

<style type="text/css">
#content {
	width: 98%;
}
</style>

<div class="container">

<h3>게시글 쓰기</h3>
<hr>

<div>
<form action="/freeboard/write" method="post" enctype="multipart/form-data">
<table class="table table-bordered">
<tr><td class="info">아이디</td><td>${userid }</td></tr>
<tr><td class="info">닉네임</td><td>${name }</td></tr>
<tr><td class="info">제목</td><td><input type="text" name="title" style="width:100%"/></td></tr>
<tr>
	<td>
		<!-- 비밀글을 하기위해 : dto int secret -->
		<input type="checkbox" id="secretChk" >
			<input type="hidden" name="secret" id="secretId" value="0">
			<!--  
				1)실재 jsp에 노출되지 않음
				2) hidden을 쓰는이유 : controller 에서 변수를 넘겨받기 위해 사용
				3) jsp 에서 노출되지 않는데 변수를 넘거받을필요가있음
				
			-->
		<script>
		//secret number(1) not null table변수로 새로추가
		// 
		$('#secretChk').click(function() {
			if($("#secretChk").prop("checked")){
				alert("체크됨");
			
				
			}
			else{
				alert("해제됨");
			
			}
		});
		</script>
	</td>
</tr>
<tr><td class="info" colspan="2">본문</td></tr>
<tr><td colspan="2"><textarea id="content" name="content"></textarea></td></tr>
</table>

<label>첨부파일 : <input type="file" name="file" /></label>

</form>
</div>

<div class="text-center">	
	<!-- <button type="button" id="btnWrite" class="btn btn-info">작성</button> -->
	<input type="button" id="btnWrite" class="btn btn-info" value="작성">
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>
</div>

<script type="text/javascript">
// 스마트에디터 스킨 적용
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //<textarea>의 id 입력
	sSkinURI: "/resource/smarteditor/SmartEditor2Skin.html",
	fCreator: "createSEditor2",
	htParams: {
		bUseToolbar: true, //툴바 사용여부
		bUseVerticalResizer: false, //입력창 크기 조절 바
		bUseModeChanger: true //모드 탭
	}
});

//<form>의 submit에 맞춰 스마트에디터 내용 적용
function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}
</script>
<jsp:include page="/view/layout/footer.jsp" />
















