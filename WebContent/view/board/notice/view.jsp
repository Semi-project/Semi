<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />

<div class="container">


	<script type="text/javascript">
$(document).ready(function() {
	$("#btnCommInsert").click(function(){

		$form = $("<form>").attr({
			action: "/noticeboardcomment/insert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"boardno",
				value:"${notice_boardView.boardno }"
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
});

function deleteComment( comment_no ) {
	$.ajax({
		type: "post"
		, url: "/noticeboardcomment/delete"
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
}

</script>
	<h3>게시글 상세보기</h3>
	<hr>

	<div>
		<table class="table table-bordered" border="1">
			<tr>
				<td class="info">글번호</td>
				<td>${notice_boardView.boardno }</td>
				<td class="info">제목</td>
				<td colspan="2">${notice_boardView.title }</td>
			</tr>

			<tr>
				<td class="info">아이디</td>
				<td>${notice_boardView.userid }</td>
				<td class="info"><a
					href="/noticeboardfile/download?fileno=${fileno.fileno }" />첨부파일</td>
				<td>${fileno.file_SaveName }
	
				</td>
			</tr>

			<tr>
				<td class="info">본문</td>
				<td colspan="4">${notice_boardView.content }</td>
			</tr>

			<tr>
				<td class="info">조회수</td>
				<td>${notice_boardView.hit }</td>
				<td class="info">추천수</td>
				<td>[추후 추가]</td>
			</tr>

			<tr>
				<td class="info">작성일</td>
				<td colspan="4">${notice_boardView.insert_dat }</td>

			</tr>

		</table>
	</div>

	<div class="text-center">
		<button class="ui pink basic button">
			<a href="list">목록</a>
		</button>
		<button class="ui pink basic button"
			onclick='location.href="/noticeboard/update?boardno=${notice_boardView.boardno }";'>
			수정</a>
		</button>
		<button class="ui pink basic button"
			onclick='location.href="/noticeboard/delete?boardno=${notice_boardView.boardno }";'>삭제</button>
	</div>
</div>
<div>


	<!-- 댓글 처리 시작 -->
	<div>

		<hr>
		<!-- 댓글 입력 -->
		<div class="form-inline text-center">
			<input type="text" size="10" class="form-control" id="commentWriter"
				value="${sessionScope.userid }" readonly="readonly" />
			<textarea rows="2" cols="60" class="form-control" id="commentContent"></textarea>
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
					<tr data-commentno="${ii.comment_no }">
					<td>${ii.rnum }</td>
						<td>${ii.userid }</td>
						<td>${ii.content }</td>
						<td><fmt:formatDate value="${ii.insert_dat }"
								pattern="yy-MM-dd hh:mm:ss" /></td> 
						
						
						<td><c:if test="${sessionScope.userid eq ii.userid }"> 
								<button class="btn btn-default btn-xs"
 									onclick='location.href="/noticeboardcomment/delete?=${ii.comment_no }";'>삭제</button> 
 														</c:if></td> 
							
					</tr> 
				</c:forEach>
			</tbody> 
		</table>
		<!-- 댓글 리스트 end -->

	</div>
	<!-- 댓글 처리 끝 -->

</div>

<jsp:include page="/view/layout/footer.jsp" />