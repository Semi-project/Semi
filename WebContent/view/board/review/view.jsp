<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#btnList")
								.click(
										function() {
											$(location)
													.attr("href",
															"/review/list?curPage=${paging.curPage}");
										});
						$("#btnUpdate")
								.click(
										function() {
											$(location)
													.attr("href",
															"/review/update?boardno=${boardView.boardno }");
										});
						$("#btnDelete")
								.click(
										function() {
											$(location)
													.attr("href",
															"/review/delete?boardno=${boardView.boardno }");
										});
						
						$("#insert").click(function(){

							$form = $("<form>").attr({
								action: "/review/comment/insert",
								method: "post"
							}).append(
								$("<input>").attr({
									type:"hidden",
									name:"boardno",
									value:"${boardView.boardno }"
								})
							).append(
								$("<textarea>")
									.attr("name", "content")
									.css("display", "none")
									.text($("#commentcontent").val())
							);
							$(document.body).append($form);
							$form.submit();

						});

					});
</script>
<div class="container">

	<h4>회원공간</h4>
	<hr>
	<div class="container">

		<div class="row">

			<div class="col-lg-2">.....</div>

			<div class="col-lg-8">
				<h3>${boardView.title }</h3>
				<ul class="list-inline ">
					<li>글쓴이 : ${boardView.userid }</li>
					<li>|</li>
					<li>글번호 : ${boardView.boardno }</li>
					<li>|</li>
					<li>조회수 : ${boardView.hit }</li>
					<li>|</li>
					<li>추천수 :${boardView.recomend }</li>
					<li>|</li>
					<li>작성일 :${boardView.insert_dat }</li>
				</ul>
				<ul class="list-inline">
				</ul>
				<div id="content">${boardView.content}<hr>
				</div>
				<c:forEach items="${boardFile }" var="f">
					<a href="/file/download?fileno=${f.fileno}">${f.file_OriginName}</a>
				</c:forEach>
				<div class="text-right">
					<button id="btnList" class="btn btn-primary">목록</button>
					<button id="btnUpdate" class="btn btn-info">수정</button>
					<button id="btnDelete" class="btn btn-danger">삭제</button>
				</div>

				<hr>
				<div class="comment">
<!-- 						<input id="commet_id" name="comment_id" value="" type="hidden" /> -->
						<h3>댓글남기기- 로그인 필요</h3>
						<div class="comment_text">
							<c:if test="${not login}">

								<textarea rows="3" cols="100" id="text" name="text"
									style="resize: none;" disabled="disabled"></textarea>
							</c:if>
							<c:if test="${login}">

								<textarea rows="3" cols="100" id="commentcontent" name="content"
									style="resize: none;"></textarea>
							</c:if>

							<small>1000 자 이내로 입력해주세요</small>

						</div>
						<div class="text-right">
							<button id="insert" class="btn btn-danger">댓글등록</button>
						</div>
						<div></div>
					<!-- 댓글리스트 -->
					<div class="g-py-30 g-mb-10">
						<div class="g-brd-y g-brd-gray-light-v4 g-py-30 mb-5">
							<h3
								class="h6 g-color-black g-font-weight-600 text-uppercase mb-0">댓글
							</h3>

						</div>
						<div id="comment-wrapper">
							<c:forEach items="${commentList }" var="comment">
								<ul class="list-inline " data-commentno="${comment.commentNo }">
									<li>번호 : ${comment.rnum }</li>
									<li>|</li>
									<li>글쓴이 : ${comment.userid }</li>
									<li>|</li>
									<li>작성일 :<fmt:formatDate value="${comment.insertDat }"
											pattern="yy-MM-dd hh:mm:ss" /></li>
								</ul>
							${comment.content }
							<hr>
							<c:if test="${sessionScope.writer eq comment.userid }">
									<button class="btn btn-default btn-xs"
										onclick="deleteComment(${comment.commentno });">삭제</button>
								</c:if>
							</c:forEach>
						</div>
						<hr>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-2">....</div>
	</div>
</div>
</div>
<jsp:include page="/view/layout/footer.jsp" />