<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<jsp:include page="/view/layout/header.jsp" />
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

	<script type="text/javascript">
	
$(document).ready(function() {
	
	$("#btnCommInsert").click(function(){

		$form = $("<form>").attr({
			action: "/freeboardcomment/insert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"boardno",
				value:"${freeboardService.boardno }"
			})
		).append(
			$("<textarea>")
				.attr("name", "content")
				.css("display", "none")
				.text($("#commentContent").val())
		);
		$(document.body).append($form);
		$form.submit();
	});


if(${recommend }) { //추천상태
	$("#btnRecommend")
		.addClass("btn-danger")
		.removeClass("btn-primary")
		.text("추천 취소");
} else {	//추천 안 한상태
	$("#btnRecommend")
		.addClass("btn-primary")
		.removeClass("btn-danger")
		.text("추천");
}
console.log(${recommend});
// 추천 버튼 클릭 이벤트 처리
$("#btnRecommend").click(function() {
	$.ajax({
		type: "get"
		, url: "/freeboard/recommend"
		, dataType: "json"
		, data: {
			boardno: '${freeboardService.boardno }'
		}
		, success: function(data) {
//				console.log("success");
			console.log(data);
			
			//추천 버튼 색상 변경
			$("#btnRecommend")
				.toggleClass("btn-primary")
				.toggleClass("btn-danger");

			//추천수 갱신
			$("#recommend").text(data.recommend);
			
			//추천 버튼 텍스트 변경
			if(data.result) {
				$("#btnRecommend").text("추천 취소");
			} else {
				$("#btnRecommend").text("추천");
			}
		}
		, error: function(e) {
			console.log("fail");
			
			console.log(e.responseText);
		}
	});
});

});
function deleteComment( comment_no ) {
	$.ajax({
		type: "post"
		, url:"/freeboardcomment/delete"
		, dataType: "json"
		, data: {
			comment_no: comment_no
		}
		, success: function(data){
			
			if(data.success) {
				
				$("[data-comment_no='"+comment_no+"']").remove();
				
		
				
			} else {
				alert("댓글 삭제 실패");
			}
		}
		, error: function() {
			console.log("error");
		}

	});
	

};



</script>
<h3>게시글 상세보기</h3>
<hr>



<c:if test="${login }">
<button id="btnRecommend" class="btn pull-right" style="margin-top: 30px;"></button>
</c:if>


<div>
<div id="board_view">
<div class="board_view_subject">

				<p class="board_view_subject_left">${freeboardService.boardno }|${freeboardService.title }</p>
				<p class="board_view_subject_right">${freeboardService.userid }|${freeboardService.insert_Dat }|${freeboardService.hit }|${freeboardService.recommend }
				</p>
				</div>
	<div class="board_view_content">${freeboardService.content }</div>
	<hr>
			
				<c:if test="${fileno.fileno eq 0 }">
				</c:if>
				<c:if test="${fileno.fileno ne 0 }">
				첨부파일 :<a href="/freeboardfile/download?fileno=${fileno.fileno }" >
				${fileno.file_SaveName }</a>
				</c:if>
			</div>
</div>
</div>
		

	

	</div>

	<div class="text-center">
		<button class="ui blue basic button">
			<a href="list">목록</a>
		</button>
		<c:if test="${userid eq freeboardService.userid}">
		<button class="ui green basic button"
			onclick='location.href="/freeboard/update?boardno=${freeboardService.boardno }";'>
			수정</a>
		</button>
		<button class="ui red basic button"
			onclick='location.href="/freeboard/delete?boardno=${freeboardService.boardno }";'>삭제</button>
			</c:if>
	</div>
<!-- </div> -->
<!-- <div> -->


	<!-- 댓글 처리 시작 -->
	<div>

		<hr>
		<!-- 댓글 입력 -->
		<div class="form-inline text-center">
			<input type="text" size="10" class="form-control" id="commentWriter"
				value="${sessionScope.userid }" readonly="readonly" />
			<textarea rows="2" cols="60" class="form-control" id="commentContent" style="resize: none"></textarea>
			<button id="btnCommInsert" class="btn">입력</button>
		</div>
		<!-- 댓글 입력 end -->

		<!-- 댓글 리스트 -->
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
				<c:forEach items="${commentList }" var="ii">
					<tr data-comment_no="${ii.comment_no }">
					<td>${ii.rnum }</td>
						<td>${ii.userid }</td>
						<td>${ii.content }</td>
						<td><fmt:formatDate value="${ii.insert_dat }"
								pattern="yy-MM-dd hh:mm:ss" /></td> 
						<td><c:if test="${sessionScope.userid eq ii.userid }"> 
								<button class=" ui negative basic button btn btn-default btn-xs"
 								onclick="deleteComment(${ii.comment_no });">삭제</button>
		</c:if>
	</td>
					</tr> 
				</c:forEach>
			</tbody> 
		</table>
		<!-- 댓글 리스트 end -->

	</div>
	<!-- 댓글 처리 끝 -->

</div>

<jsp:include page="/view/layout/footer.jsp" />