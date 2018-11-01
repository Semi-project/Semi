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
th
,
td

:not



(
:nth-child(2)



)
{
text-align



:


center
;
}
td {
	border-left: 1px solid white;
	border-right: 1px solid white;
}

#pagingBox {
	position: relative;
}

/* 버튼 좌우정렬 및 상하정렬 */
#btnWriteBox, #btnDeleteBox {
	position: absolute;
	top: 0;
	bottom: 0;
	height: 30px;
	margin: auto;
}

#btnWriteBox {
	right: 10px;
}

#btnDeleteBox {
	left: 10px;
}

#btnDelete, #btnWrite {
	height: 25px;
}
</style>

<div class="container">

	<h3>회원 목록</h3>
	<hr>

	<table class="table table-hover table-striped table-condensed">
		<thead>
			<tr>

				<th style="width: 10%">아이디</th>
				<th style="width: 25%">이름</th>
				<th style="width: 15%">전화번호</th>
				<th style="width: 25%">주소</th>
				<th style="width: 10%">이메일</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${memberList }" var="member">
				<tr>

					<td>${member.userid }</td>
					<td>${member.name }</td>
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