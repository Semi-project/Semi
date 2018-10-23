<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/view/layout/header.jsp" />
<script type="text/javascript"
	src="/resource/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery 2.2.4 -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- jQuery Form Plugin -->
<script src="http://malsup.github.com/min/jquery.form.min.js"></script>


<style>
#content {
	width: 98%;
}
</style>
<script>
	$(document)
			.ready(
					function() {
						
						var count = $(this).attr("id");
						$("button[name^='button']")
								.on(
										"click",
										function(e) {
											$(this).parent("div").empty();
											$
													.ajax({
														type : "post",
														url : "/notice/file/update",
														data : {
															fileno : $(this)
																	.attr("id"),
															boardno : "${boardView.boardno}"
														},
														dataType : "text",
														success : function(d) {
															console
																	.log($(this)
																			.parent(
																					"div"));
															$("#file")
																	.append(
																			"<input type='file' name='userfile' id='userfile' />");
														},
														error : function() {
															console.log("실패");
														}
													});

											//console.log($(this).attr("id"));
											//console.log("${boardView.boardno}");
										});

						// 	$("#file").on("click", "button[name^='btn']",
						// 								function(e) {
						// 									$(this).parent("form").ajaxForm({
						// 										// 										type : "post",
						// 										// 										urlㅌ : "/notice/file/write",
						// 										// 										data : {
						// 										// 											boardno : $(this).attr("id"),
						// 										// 											name : $("#userfile").val()
						// 										// 										},
						// 										dataType : "json",
						// 										success : function(d) {
						// 											console.log("성공");
						// 											$(this).parent

						// 										},
						// 										error : function() {
						// 											console.log("실패");
						// 										}
						// 									});
						// 								});

						$("#btnUpdate").on("click", function(e) {
							submitContents();
							
							$(this).parent("form").ajaxForm({
						         
						         	
								// 										type : "post",
								// 										urlㅌ : "/notice/file/write",
								// 										data : {
								// 											boardno : $(this).attr("id"),
								// 											name : $("#userfile").val()
								// 										},
								dataType : "json",
								success : function(d) {
									console.log("성공");
									$(this).parent

								},
								error : function() {
									console.log("실패");
								}
							});
						});
					});
</script>
<div class="container">
	<h3>공지사항</h3>
	<hr>
	<div>
		<form action="/notice/update" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="boardno" value="${boardView.boardno }" />
			<table class="table table-bordered">
				<tr>
					<td class="info">아이디</td>
					<td>${boardView.userid }</td>
				</tr>
				<tr>
					<td class="info">닉네임</td>
					<td>${nick }</td>
				</tr>
				<tr>
					<td class="info">제목</td>
					<td><input type="text" name="title" style="width: 100%"
						value="${boardView.title }" /></td>
				</tr>
				<tr>
					<td class="info" colspan="2">본문</td>
				</tr>
				<tr>
					<td colspan="2"><textarea id="content" name="content">${boardView.content }</textarea></td>
				</tr>
			</table>
			<div id="file">
				<c:forEach items="${boardFile }" var="f" varStatus="status">
					<div>${f.file_OriginName }<button id="${f.fileno}"
							type="button" name="button_${status.index}">Delete</button>
						<br>
					</div>

				</c:forEach>
			</div>
			<div class="text-center">
				<button id="btnUpdate" class="btn btn-info">수정 적용</button>
				<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
			</div>
		</form>

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