<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
/* 폰트안보이게(배경깔때) */
.blind {
	visibility: hidden;
	overflow: hidden;
	position: absolute;
	top: 0;
	left: 0;
	width: 1px;
	height: 1px;
	font-size: 0;
	line-height: 0
}

/* 메인 타이틀 */
.title_area {
	float: none;
	position: relative;
	*height: 34px;
	*zoom: 1
}

.title_area:after {
	display: block;
	clear: both;
	content: ''
}

.obj_section .title_area h4 {
	float: left;
	height: 21px;
	margin: 5px 10px 0 0;
	background: url(htp://제목이배경으로 깔리는 이미지 주소) no-repeat -9999px -9999px
}

.obj_section .title_area h4.hh_photo {
	width: 54px;
	background-position: 0 -88px
}

/* 갤러리 */
.sub_photo li {
	float: left;
	position: relative;
	width: 160px;
	margin-right: 8px;
	margin-bottom: 30px;
	display: block;
}

.sub_photo li.last {
	margin-right: 0
}

.sub_photo li a strong {
	display: block;
	overflow: hidden;
	padding-right: 8px;
	white-space: nowrap;
	text-overflow: ellipsis;
	font-size: 14px;
}

.sub_photo li img {
	width: 160px;
	height: 120px;
}

.sub_photo p.thmb {
	margin-bottom: 3px
}

.sub_photo .tx_brief {
	margin-top: 2px;
	padding-right: 13px
}

.sub_photo .tx_brief a {
	color: #666
}

.thumbnail {
	background-color: gray;
	margin: 10px;
	width: 50px;
	height: 50px;
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center center;
}
/* //갤러리 */


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



/* 버튼 좌우정렬 및 상하정렬 */
#btnWriteBox {
	position: absolute;
	top: 0;
	bottom: 0;
	height: 30px;
	margin: auto;
}

#btnWriteBox {
	right: 10px;
}

#btnWrite {
	height: 25px;
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
		<div style="margin-top: 20px;">
			<h3>입양후기</h3>
		</div>


		<hr>
		<table class="board ">
			<thead>
				<tr>
					<th style="width: 10%; text-align: center;">번호</th>
					<th style="width: 5%; text-align: center;"></th>
					<th style="width: 25%; text-align: center;">제목</th>
					<th style="width: 20%; text-align: center;">작성자</th>
					<th style="width: 10%; text-align: center;">조회수</th>
					<th style="width: 20%; text-align: center;">작성일</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${boardList }" var="board" varStatus="status">
					<tr>
						<td>${board.boardno }</td>
						<td><div class="thumbnail"
								style="background-image:url('${fileList[status.index].file_SaveName}')"></div>
						</td>
						<td style="text-align: center;"><a
							href="/review/view?boardno=${board.boardno }&curPage=${paging.curPage}">${board.title }</a></td>
						<td>${board.userid }</td>
						<td>${board.hit }</td>
						<td><fmt:formatDate value="${board.insert_dat }"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>

			</tbody>

		</table>
		<div id="btnBox" style="float: right; margin-top: 10px">
			<button id="btnWrite">글쓰기</button>
		</div>
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