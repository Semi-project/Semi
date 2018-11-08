<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	response.setHeader("Cache-Controll", "no-cache");
%>

<jsp:include page="/view/layout/header.jsp" />

<script>
	function qnaBoardno(qna) {

		location.href = "/qnaboard/paginglist?boardno=" + boardno;
	}

	function search(frm) {
		if (frm.keyWord.value == "") {
			alert("검색 단어를 입력하세요");
			frm.keyWord.focus();
			return;
		}
		frm.sumbit();
	}
</script>

<script type="text/javascript">
	$(document).ready(
			function() {

				var result = '${result}';
				var successMsg = '${successMsg}';
				var failMsg = '${failMsg}';

				if (result == 'success') {
					alert(successMsg);
				} else if (result == 'fali') {
					alert(failMsg);
				}

				$("table").on(
						"click",
						"tr",
						function() {
							//클릭이벤트가 발생한 <tr>의 첫번째 <td>자식의 텍스트
							var boardno = $(this).children("td").eq(0).text();

							$(location).attr("href",
									"/qnaboard/view?boardno=" + boardno);
						});

				$("#btnSearch").click(
						function() {

							var searchVal = $("#searchVal").val();
							var searchTxt = $("searchTxt").val();

							$(location).attr(
									"href",
									"/qnaboard/paginglist?searchVal="
											+ searchVal + "searchTxt="
											+ searchTxt);

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

#btn {
	float: right;
}
</style>




<div class="container">

	<h3>QnA 목록</h3>
	<hr>
	<form id="frm" name="frm" method="GET">
		<table class="board">
			<thead>
				<tr>
					<th style="width: 10%; text-align: center;">번호</th>
					<th style="width: 45%; text-align: center;">제목</th>
					<th style="width: 20%; text-align: center;">작성자</th>
					<th style="width: 20%; text-align: center;">작성일</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${qnaList }" var="qna">
					<tr>

						<td>${qna.boardno }</td>
						<td class="board_subject" style="padding-left: 50px;"><a
							href="/qnaboard/view?boardno=${qna.boardno }">${qna.title }</a></td>
						<td>${qna.userid }</td>
						<td><fmt:formatDate value="${qna.insert_Dat }"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</tbody>



		</table>






		<div id="searchBox" class="text-center">
			<tr>
				<td><select id="searchVal" name="searchVal">
						<option value="title" selected="selected">제목</option>
						<option value="content">내용</option>
						<option value="userid">작성자</option>
				</select></td>
			</tr>
			<input type="text" id="searchTxt" name="searchTxt" />
			<button id="btnSearch">검색</button>
		</div>
	</form>
	<button id="btn" onclick='location.href="/qnaboard/write";'>글쓰기</button>

	<div id="pagingBox" class="text-center">
		<ul class="pagination pagination-sm">
			<!-- 처음으로 가기 -->
			<c:if test="${paging.curPage ne 1 }">
				<li><a href="/qnaboard/paginglist?search=${paging.search }"
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
					href="/qnaboard/paginglist?curPage=${paging.curPage-1 }&search=${paging.search }"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>





			<!-- 페이징 리스트 -->
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
				var="i">

				<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
				<c:if test="${paging.curPage eq i}">
					<li class="active"><a
						href="/qnaboard/paginglist?curPage=${i }&search=${paging.search }">${i }</a></li>
				</c:if>
				<c:if test="${paging.curPage ne i}">
					<li><a
						href="/qnaboard/paginglist?curPage=${i }&search=${paging.search }">${i }</a></li>
				</c:if>
			</c:forEach>





			<!-- 다음 페이지 -->
			<c:if test="${paging.curPage eq paging.totalPage }">
				<li class="disabled"><span aria-hidden="true">&raquo;</span></li>
			</c:if>

			<c:if test="${paging.curPage ne paging.totalPage }">
				<li><a
					href="/qnaboard/paginglist?curPage=${paging.curPage+1 }&search=${paging.search }"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
		</ul>
	</div>
</div>

<jsp:include page="/view/layout/footer.jsp" />


















