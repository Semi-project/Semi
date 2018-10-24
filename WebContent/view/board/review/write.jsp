<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />
<style>
div.title {
	margin-top: 80px;
}
</style>
<script type="text/javascript"
	src="/resource/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery 2.2.4 -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- jQuery Form Plugin -->
<script src="http://malsup.github.com/min/jquery.form.min.js"></script>


<script type="text/javascript">
   $(document).ready(function() {
      //jquery.form.js 플러그인 사용
      //   http://malsup.com/jquery/form/

      $("#fileForm").submit(function() {
         submitContents();
         
         $("#fileForm").ajaxForm({
            
            dataType : "json",
            success : function(res) {
               console.log("성공");
            },
            error : function() {
               console.log("실패");
            }
   
         });
      });
   });
</script>

<style type="text/css">
#content {
	width: 98%;
}
</style>

<div class="container">

	<h3>공지사항</h3>
	<hr>

	<div>
		<form action="/review/write" id="fileForm" method="post"
			enctype="multipart/form-data">
			<table class="table table-bordered">
				<tr>
					<td class="info">아이디</td>
					<td>${userid }</td>
				</tr>
				<tr>
					<td class="info">권한</td>
					<td>${rold_id }</td>
				</tr>
				<tr>
					<td class="info">닉네임</td>
					<td>${nick }</td>
				</tr>
				<tr>
					<td class="info">제목</td>
					<td><input type="text" name="title" id="title"
						style="width: 100%" /></td>
				</tr>
				<tr>
					<td colspan="2"><textarea id="content" name="content"></textarea></td>
				</tr>
			</table>
			<label>첨부1 : <input type="file" name="file1" /></label><br> <label>첨부2
				: <input type="file" name="file2" />
			</label><br> <label>첨부3 : <input type="file" name="file3" />
			</label><br> <br>
			<div class="text-center">
				<button id="btnWrite" class="btn btn-info">작성</button>
			</div>
		</form>

	</div>

	<div class="text-center">
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
	</div>
</div>

<script type="text/javascript">
	// 스마트에디터 스킨 적용
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "content", //<textarea>의 id 입력
		sSkinURI : "/resource/smarteditor/SmartEditor2Skin.html",
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
<jsp:include page="/view/layout/footer.jsp" />

