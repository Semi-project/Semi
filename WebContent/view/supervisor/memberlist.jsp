<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/view/layout/header.jsp" />
<script type="text/javascript">
	$(document).ready(
			function() {

				$("#btnSearch").click(
						function() {
							$(location)
									.attr(
											"href",
											"/member/list?search="
													+ $("#search").val());
						});

			});
</script>

<style type="text/css">
.board_total {
	padding: 30px 0 15px 0;
	font-size: 11px;
	color: #727272;
}

.board_total span {
	color: #fb5d40;
	vertical-align: -1px;
}

.board_total .board_select {
	border: 1px solid #ececec;
	width: 115px;
	height: 29px;
	line-height: 29px;
	padding-left: 15px;
}

.board_total input {
	height: 27px;
	line-height: 27px;
	border: 1px solid #ececec;
	padding-left: 10px;
	width: 138px;
	margin: 0 10px;
}

.board_total a {
	width: 65px;
	height: 29px;
	line-height: 29px;
	text-align: center;
	background: #283444;
	color: #fff;
	display: inline-block;
}

.board {
	border-top: 2px solid #283444;
	border-bottom: 1px solid #283444;
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 27px;
}

.board th {
	border-bottom: 1px solid #283444;
	color: #727272;
	height: 42px;
	font-weight: normal;
}

.board td {
	border-bottom: 1px solid #ececec;
	color: #6d6e72;
	font-size: 12px;
	height: 40px;
	text-align: center;
}

.board td.board_title {
	text-align: left;
}

.board td a {
	font-size: 12px;
}

.board .board_subject {
	text-align: left;
	padding-left: 80px;
}

.board .board_subject a {
	color: #6d6e72;
}

.board .board_subject a:hover {
	color: #283444;
}

.board .board_subject img {
	padding-left: 3px;
	vertical-align: -1px;
}

.board .board_last td {
	border: 0;
}

#pagingBox {
	position: relative;
}
</style>

<div class="container">

	<h3>회원 목록</h3>
	<hr>

	<table class="board">
		<thead>
			<tr>

				<th style="width: 10%; text-align: center;">아이디</th>
				<th style="width: 10%; text-align: center;">이름</th>
				<th style="width: 15%; text-align: center;">전화번호</th>
				<th style="width: 35%; text-align: center;">주소</th>
				<th style="width: 10%; text-align: center;">이메일</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${memberList }" var="member">
				<tr>

					<td>${member.userid }</td>
					<td >${member.name }</td>
					<td>${member.phone }</td>
					<td>${member.address }</td>
					<td>${member.email }</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

	<div id="pagingBox" class="text-center">
		<ul class="pagination pagination-sm">
			<!-- 처음으로 가기 -->
			<c:if test="${paging.curPage ne 1 }">
				<li><a href="/member/list?search=${paging.search }"
					aria-label="First"> <span aria-hidden="true">&larr;처음</span>
				</a></li>
			</c:if>





			<!-- 이전 페이지 -->
			<!-- 첫 페이지라면 버튼 동작 안 되게 만들기 -->
			<c:if test="${paging.curPage eq 1 }">
				<li class="disabled"><span aria-hidden="true">&laquo;</span></li>
			</c:if>

			<c:if test="${paging.curPage ne 1 }">
				<li><a
					href="/member/list?curPage=${paging.curPage-1 }&search=${paging.search }"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>





			<!-- 페이징 리스트 -->
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
				var="i">

				<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
				<c:if test="${paging.curPage eq i}">
					<li class="active"><a
						href="/member/list?curPage=${i }&search=${paging.search }">${i }</a></li>
				</c:if>
				<c:if test="${paging.curPage ne i}">
					<li><a
						href="/member/list?curPage=${i }&search=${paging.search }">${i }</a></li>
				</c:if>
			</c:forEach>





			<!-- 다음 페이지 -->
			<c:if test="${paging.curPage eq paging.totalPage }">
				<li class="disabled"><span aria-hidden="true">&raquo;</span></li>
			</c:if>

			<c:if test="${paging.curPage ne paging.totalPage }">
				<li><a
					href="/member/list?curPage=${paging.curPage+1 }&search=${paging.search }"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
		</ul>
		<!-- 		<div id="btnDeleteBox"> -->
		<!-- 			<button id="btnDelete">삭제</button> -->
		<!-- 		</div> -->
		<!-- 		<div id="btnWriteBox"> -->
		<!-- 			<button id="btnWrite">글쓰기</button> -->
		<!-- 		</div> -->
	</div>
	<div id="searchBox" class="text-center">
		<input type="text" id="search" />
		<button id="btnSearch">검색</button>
	</div>

</div>

<jsp:include page="/view/layout/footer.jsp" />