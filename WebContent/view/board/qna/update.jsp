<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js">
</script>


<script type="text/javascript" 
		src="/resource/smarteditor/js/HuskyEZCreator.js" 
		charset="utf-8">
</script>
<jsp:include page="/view/layout/header.jsp" />
<div style="text-align: center;"></div>

<style type="text/css">
#content {
	width: 98%;
}
</style>

<div class="container">
<h1>글쓰기</h1>
<hr>

<form action="update" method="post"> 
<input type="hidden" id="cateno" name="cateno" value="1002"/>
<input type="hidden" id="boardno" name="boardno" value="${qnaView.boardno}"/> 
<table class="table table-bordered">
<tr><td class=info>제목 :</td><td><input type="text" name="title" style="width:100%" value="${qnaView.title }" /></td></tr>
<tr><td class="info">작성자 :</td><td><input name="userid" value="${qnaView.userid }" /></td></tr>
<tr><td class="info">본문</td></tr>
 <tr><td colspan="2"><textarea  id="content" name="content" >${qnaView.content}</textarea></td></tr>
 
</table>
<div class="text-center">	
<button id="btnUpdate" onclick='location.href="/view/board/qna/view.jsp";'>수정하기</button>
</div>

</form>
</div>

<script type="text/javascript">
// 스마트에디터 스킨적용
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder : "content", //<textarea>의 id 입력
	sSkinURI : "/resource/smarteditor/SmartEditor2Skin.html",
	fCreator:"createSEditor2",
 	htParams: {
 		bUseToolbar : true, // 툴바 사용여부
 		bUseVerticalResizer : false, // 입력창 크기 조절 바
 		bUseModeChanger : true // 모드 탭
 	}
});
</script>

<script type="text/javascript">
// <form>의 submit에 맞춰 스마트에디터 내용 적용
function submitContents(elClickedObj){
	// 에디터의 내용이 <textarea>에 적용되도록 한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD",[]);
	
	// 에디터의 내용에 대한 값 검증 처리(추가 작업 작성)
	// document.getElementById("cotent").value를 이용한다
	// $("#content").val()를 이용한다
	
	// <form>내용 submit
	try{
		elClickedObj.form.submit();
	}catch(e){}
}

// 작성 버튼이 눌렸을 때 반응하도록 작성
// 작성버튼은 <textarea>와 같은 <form>을 부모요소로 가져야 함

$(document).ready(function(){
	$("#btnUpdate").click(function(){
		submitContents($(this));
	})
});
</script>
<jsp:include page="/view/layout/footer.jsp" />


