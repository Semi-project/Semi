<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />
<script src="https://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="/view/adoption/send/se2/js/HuskyEZCreator.js" charset="UTF-8"></script>

<script type="text/javascript">
$(function(){
	var oEditors = [];

	nhn.husky.EZCreator.createInIFrame({

	    oAppRef: oEditors,
	    elPlaceHolder: "inputText",
	    sSkinURI: "/view/adoption/send/se2/SmartEditor2Skin.html",

	    htParams: {
	    	bUseToolbar: true,
	    	bUseVerticalResizer: true,
	    	bUseModeChanger: true
	    }

	});
	
	$("#save").click(function(){
		oEditors.getById["inputText"].exec("UPDATE_CONTENTS_FIELD", []);
// 		textarea 값 가져오기
// 		alert( $("#inputText").val() );
		$("#contents").submit();
		
	});
});

</script>

<h3>네이버 스마트 에디터 2.0</h3>
<hr>

<form id="contents" method="get">

<textarea name="feature" id="inputText" rows="10" cols="80"></textarea><br>

<button id="save">저장</button>

</form>

<jsp:include page="/view/layout/footer.jsp" />