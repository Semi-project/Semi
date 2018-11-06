<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Controll", "no-cache");
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	var result = '${result}';
	var successMsg = '${successMsg}';
	var failMsg = '${failMsg}';
	
	if(result == 'success'){
		alert(successMsg);
	}
	else if(result == 'fali'){
		alert(failMsg);
	}
	
	$("#btnList").click(function() {
		$(location).attr("href", "/qnaboard/paginglist");
	});
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/qnaboard/update?boardno=${qnaView.boardno }");
	});
	$("#btnDelete").click(function() {
		
		var userid = '${qnaView.userid}'; // 글을 작성한 사용자 
		var loginId = '${loginId}'; // 실제로 로그인 한 사용자 
		
		if(userid == loginId){
			$(location).attr("href", "/qnaboard/delete?boardno=${qnaView.boardno}&userid=${qnaView.userid}");
		}
		else{
			alert("글을 삭제할수 없습니다.");
			return false;
		}
		
	});
	$("#btnCommInsert").click(function(){
		$form = $("<form>").attr({
			action: "/qnacomment/write",
			method: "post"
		}).append(
				$("<input>").attr({
					type:"hidden",
					name:"boardno",
					value:"${qnaView.boardno}"
				})
			).append(
				$("<textarea>")
				.attr("name","content")
				.css("display","none")
				.text($("#qna_commentContent").val())
			);
		$(document.body).append($form);
		$form.submit();
	
});

	
});

function deleteComment(comment_No) {
// 	console.log(comment_No);

	$.ajax({
		type: "post"
		, url: "/qnacomment/delete"
		, dataType: "json"
		, data: {comment_No: comment_No}
		,success: function(data){
		
			if(data.result == 'success'){
				alert("댓글 삭제 성공!!! ");
				location.href = "/qnaboard/view?boardno="+${qnaView.boardno };
			}else{
				alert("댓글 삭제 실패!!");
				return false;
			} 
		}
		, error: function(){
			console.log("error");
		}
	})
}

</script>
<style>
#board_view {
	padding-top: 30px;
}

.board_view_subject {
	overflow: hidden;
	border-top: 2px solid #283444;
	border-bottom: 1px solid #283444;
	height: 42px;
	padding: 0 20px;
	line-height: 42px;
}

.board_view_subject_left {
	float: left;
	color: #283444;
}

.board_view_subject_right {
	float: right;
	color: #6d6e72;
}

.board_view_content {
	padding: 40px;
	color: #6d6e72;
	line-height: 1.5;
}

.tutorial_content {
	padding: 40px;
	color: #6d6e72;
	line-height: 1.5;
}

.photo_content {
	padding: 40px 0;
}

.industrialEdu_content {
	padding: 40px 0;
}

.board_view_prev {
	border-top: 1px solid #283444;
	border-bottom: 1px solid #ececec;
	background: url('/resources/images/common/point_view_prev.jpg');
	background-repeat: no-repeat;
	padding-left: 42px;
	height: 40px;
	line-height: 38px;
}

.board_view_next {
	border-bottom: 1px solid #283444;
	background: url('/resources/images/common/point_view_next.jpg');
	background-repeat: no-repeat;
	padding-left: 42px;
	height: 40px;
	line-height: 38px;
}

.board_view_prev span, .board_view_next span {
	margin-right: 38px;
	color: #283444;
}

.board_view_prev a, .board_view_next a {
	color: #6d6e72;
}

.board_view_prev a:hover, .board_view_next a:hover {
	color: #283444;
}

.board_view_botton {
	text-align: right;
	padding: 10px 0 50px 0;
	height: 34px;
	overflow: hidden;
}

.board_view_botton a {
	width: 69px;
	height: 34px;
	font-size: 12px;
	line-height: 34px;
	background: #283444;
	text-align: center;
	color: #fff;
	display: block;
	margin-left: 10px;
	float: right;
}

#board_reply {
	background: #f8f8f8;
	border-top: 1px solid #ececec;
	padding: 30px;
}

#board_reply span {
	color: #b2b2b2;
	font-size: 11px;
}

.board_reply_frm {
	overflow: hidden;
}

.board_reply_frm textarea {
	width: 622px;
	height: 57px;
	border: 1px solid #ececec;
	color: #6d6e72;
	line-height: 1.5;
	padding: 5px;
	resize: none;
	float: left;
}

.board_reply_frm a {
	display: block;
	width: 87px;
	height: 69px;
	background: #fa5c3f;
	color: #fff;
	line-height: 69px;
	text-align: center;
	float: right;
	font-size: 16px;
}

.board_reply_frm span {
	float: left;
	display: block;
	width: 628px;
	padding-top: 15px;
}

.board_replay_content {
	padding: 10px 0;
	color: #6d6e72;
}

.board_replay_content a {
	color: #b2b2b2;
	font-size: 11px;
}

.board_replay_content .into {
	overflow: hidden;
}

.board_replay_content .left {
	float: left;
	padding-bottom: 3px;
}

.board_replay_content .left b {
	font-weight: normal;
	font-size: 11px;
}

.board_replay_content .right {
	float: right;
	color: #b2b2b2;
	font-size: 11px;
}

.board_replay_content .text {
	clear: both;
	line-height: 1.5;
	font-size: 12px;
}
</style>
<div class="container">

	<h3>QnA 상세보기</h3>
	<hr>
	<div id="board_view">
		<div class="board_view_subject">
			<p class="board_view_subject_left">NO:${qnaView.boardno }|${qnaView.title }</p>
			<p class="board_view_subject_right">${qnaView.userid }|${qnaView.insert_Dat }</p>
		</div>
		<input type="hidden" id="loginId" name="loginId" value="${loginId}">
		<div class="board_view_content">${qnaView.content }
			<hr>

			첨부파일 : <a href="/qnaboard/file/download?qna_file=${qna_file.fileno}">
				${qna_file.file_savename }</a>

		</div>
	</div>

	<div class="text-center">
		<button id="btnList" class="btn btn-primary">목록</button>
		<button id="btnUpdate" class="btn btn-info">수정</button>
		<button id="btnDelete" class="btn btn-danger">삭제</button>
	</div>


	<div>

		<hr>

		<div class="form-inline text-center">
			<input type="text" size="10" class="form-control"
				id="qna_commentWriter" value="${sessionScope.userid }"
				readonly="readonly" />
			<textarea rows="2" cols="60" class="form-control"
				id="qna_commentContent"></textarea>
			<button id="btnCommInsert" class="btn">입력</button>
		</div>

		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th style="width: 5%;">번호</th>
					<th style="width: 10%;">작성자</th>
					<th style="width: 50%;">댓글</th>
					<th style="width: 20%;">작성일</th>
					<th style="width: 5%;"></th>
				</tr>
			</thead>
			<tbody id="commentBody">
				<c:forEach items="${qna_commentList }" var="qna_comment">
					<tr data-commentNo="${qna_comment.comment_No }">
						<td>${qna_comment.rnum }</td>
						<td>${qna_comment.userid }</td>
						<td>${qna_comment.content }</td>
						<td><fmt:formatDate value="${qna_comment.insertDat}"
								pattern="yy-MM-dd hh:mm:ss" /></td>
						<td><c:if
								test="${sessionScope.userid eq qna_comment.userid }">
								<button class="btn btn-default btn-xs"
									onclick="deleteComment(${qna_comment.comment_No });">삭제</button>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>


<jsp:include page="/view/layout/footer.jsp" />
















