<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp"></jsp:include>

<!-- Smart Editor -->
<script type="text/javascript" src="../se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<!-- <script type="text/javascript" src="../se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script> -->
 
 
<!-- Smart Editor -->
<script type="text/javascript">
 
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "textAreaContent",
    sSkinURI: "../se2/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});
 

 
</script>
<h1>스마트에디터 적용</h1>
<hr>
<h3>TESTING</h3>

<form>

<textarea style="width: 100%" rows="10" name="content" id="textAreaContent" cols="80"></textarea>

<br>
<button>저장하기</button>
</form>

<jsp:include page="/view/layout/footer.jsp"></jsp:include>