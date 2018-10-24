<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
div.list {
	margin-top: 10px;
	text-align: center;
}
</style>
<script>
	$(document).ready(function() {
		$("#btnWrite").click(function() {
			location.href = "/review/write";
		});
	});
</script>
<div class="container">

	<hr>
	<div class="list">
		<h3>입양후기</h3>
		<div id="btnBox" style="float: right; margin-top: 10px">
			<button id="btnWrite">글쓰기</button>
		</div>

		<hr>
		<table class="table table-hover table-striped ">
			<thead>
				<tr>
					<th style="width: 10%; text-align: center;">번호</th>
					<th style="width: 35%; text-align: center;">제목</th>
					<th style="width: 20%; text-align: center;">작성자</th>
					<th style="width: 10%; text-align: center;">조회수</th>
					<th style="width: 20%; text-align: center;">작성일</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${boardList }" var="board">
					<tr>
						<td>${board.boardno }</td>
						<td><a
							href="/review/view?boardno=${board.boardno }&curPage=${paging.curPage}">${board.title }</a></td>
						<td>${board.userid }</td>
						<td>${board.hit }</td>
						<td><fmt:formatDate value="${board.insert_dat }"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

		<div id="pagingBox" class="text-center">
			<ul class="pagination pagination-sm">
				<!-- 처음으로 가기 -->
				<c:if test="${paging.curPage ne 1 }">
					<li><a href="/review/list" aria-label="First"> <span
							aria-hidden="true">&larr;처음</span>
					</a></li>
				</c:if>





				<!-- 이전 페이지 -->
				<!-- 첫 페이지라면 버튼 동작 안 되게 만들기 -->
				<c:if test="${paging.curPage eq 1 }">
					<li class="disabled"><span aria-hidden="true">&laquo;</span></li>
				</c:if>

				<c:if test="${paging.curPage ne 1 }">
					<li><a href="/review/list?curPage=${paging.curPage-1 }"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>





				<!-- 페이징 리스트 -->
				<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
					var="i">

					<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
					<c:if test="${paging.curPage eq i}">
						<li class="active"><a href="/review/list?curPage=${i }">${i }</a></li>
					</c:if>
					<c:if test="${paging.curPage ne i}">
						<li><a href="/review/list?curPage=${i }">${i }</a></li>
					</c:if>
				</c:forEach>





				<!-- 다음 페이지 -->
				<c:if test="${paging.curPage eq paging.totalPage }">
					<li class="disabled"><span aria-hidden="true">&raquo;</span></li>
				</c:if>

				<c:if test="${paging.curPage ne paging.totalPage }">
					<li><a href="/review/list?curPage=${paging.curPage+1 }"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>


		</div>

	</div>
</div>
<jsp:include page="/view/layout/footer.jsp" />