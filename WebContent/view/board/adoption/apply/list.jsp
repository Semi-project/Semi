<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						// 	$("table").on("click", "tr", function() {
						// 		//클릭이벤트가 발생한 <tr>의 첫번째 <td>자식의 텍스트
						// 		var boardno = $(this).children("td").eq(0).text();

						// 		$(location).attr("href","/board/view?boardno="+boardno);
						// 	});

						// 	$("#btnUpdate").click(function() {
						// 		location.href="/board/write";
						// 	});

						$("#btnSearch").click(
								function() {
									$(location).attr(
											"href",
											"/adoption/application/list?search="
													+ $("#search").val());
								});

						// 선택체크 삭제
						$("#btnDelete")
								.click(
										function() {
											// 선택된 체크박스
											var $checkboxes = $("input:checkbox[name='checkRow']:checked");

											//방법1
											// 체크된 대상들을 하나씩 꺼내서 문자열로 합치기
											// 			var names = "";
											// 			var len = $checkboxes.length;
											// 			$checkboxes.each( function(idx) {
											// 				names += $(this).val();

											// 				if( len-1 != idx ) {
											// 					names += ",";
											// 				}
											// 			});
											// 			console.log(names);

											//방법2
											// 체크된 대상들을 map으로 만들고 map을 문자열로 만들기
											var map = $checkboxes
													.map(function() {
														return $(this).val();
													});
											var names = map.get().join(",");
											// 		console.log("names : " + names);

											// 		console.log( "map:" + map );	// 맵
											// 		console.log( "map->array : " + map.get() );	// 맵->배열
											// 		console.log( "array tostring : " + map.get().join(",") ); // toString

											// 전송 폼
											var $form = $("<form>")
													.attr("action",
															"/adoption/application/deletelist")
													.attr("method", "post")
													.append(
															$("<input>")
																	.attr(
																			"type",
																			"hidden")
																	.attr(
																			"name",
																			"names")
																	.attr(
																			"value",
																			names));
											$(document.body).append($form);
											$form.submit();

										});

						$("#btnUpdate")
								.click(
										function() {
											// 선택된 체크박스
											var $checkboxes = $("input:checkbox[name='checkRow']:checked");

											//방법1
											// 체크된 대상들을 하나씩 꺼내서 문자열로 합치기
											// 			var names = "";
											// 			var len = $checkboxes.length;
											// 			$checkboxes.each( function(idx) {
											// 				names += $(this).val();

											// 				if( len-1 != idx ) {
											// 					names += ",";
											// 				}
											// 			});
											// 			console.log(names);

											//방법2
											// 체크된 대상들을 map으로 만들고 map을 문자열로 만들기
											var map = $checkboxes
													.map(function() {
														return $(this).val();
													});
											var names = map.get().join(",");
											// 		console.log("names : " + names);

											// 		console.log( "map:" + map );	// 맵
											// 		console.log( "map->array : " + map.get() );	// 맵->배열
											// 		console.log( "array tostring : " + map.get().join(",") ); // toString

											// 전송 폼
											var $form = $("<form>")
													.attr("action",
															"/adoption/application/updatelist")
													.attr("method", "post")
													.append(
															$("<input>")
																	.attr(
																			"type",
																			"hidden")
																	.attr(
																			"name",
																			"names")
																	.attr(
																			"value",
																			names));
											$(document.body).append($form);
											$form.submit();

										});
					});

	//전체 체크/해제
	function checkAll() {
		// checkbox들
		var $checkboxes = $("input:checkbox[name='checkRow']:enabled");

		// checkAll 체크상태 (true:전체선택, false:전체해제)
		var check_status = $("#checkAll").is(":checked");

		if (check_status) {
			// 전체 체크박스를 checked로 바꾸기
			$checkboxes.each(function() {
				this.checked = true;
			});
		} else {
			// 전체 체크박스를 checked 해제하기
			$checkboxes.each(function() {
				this.checked = false;
			});
		}
	}
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

/* 버튼 좌우정렬 및 상하정렬 */
#btnUpdateBox, #btnDeleteBox {
	top: 0;
	bottom: 0;
	height: 30px;
	margin: 0 auto;
	display: inline-block;
}

#btnUpdateBox {
	right: 10px;
}

#btnDeleteBox {
	left: 10px;
}

#btnDelete, #btnUpdate {
	height: 25px;
}
</style>

<div class="container">

	<h3>입양신청서</h3>
	<div id="searchBox" class="text-right">
		<input type="text" id="search" />
		<button id="btnSearch">검색</button>
	</div>

	<hr>

	<table class="board">
		<thead>
			<tr>
				<th style="width: 10%; text-align: center;"><input
					type="checkbox" id="checkAll" onclick="checkAll();" /></th>
				<th style="width: 10%; text-align: center;">입양코드</th>
				<th style="width: 25%; text-align: center;">동물이름</th>
				<th style="width: 20%; text-align: center;">작성자</th>
				<th style="width: 10%; text-align: center;">동물코드</th>
				<th style="width: 10%; text-align: center;">상태</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${boardList }" var="board">
				<tr>
					<c:if test="${board.status eq 0 }">
						<td><input type="checkbox" name="checkRow"
							value="${board.adoptionCode }" /></td>
					</c:if>
					<c:if test="${board.status eq 1 }">
						<td><input type="checkbox" name="checkRow"
							value="${board.adoptionCode }" disabled /></td>
					</c:if>
					<td>${board.adoptionCode }</td>
					<td class="board_subject"><a
						href="/adoption/application/view?adoption_code=${board.adoptionCode }">${board.animalName }</a></td>
					<td>${board.userid }</td>
					<td>${board.animalCode }</td>
					<c:if test="${board.status==1 }">
						<td>입양완료</td>
					</c:if>
					<c:if test="${board.status==0 }">
						<td>입양대기</td>
					</c:if>

				</tr>
			</c:forEach>
		</tbody>

	</table>
	<div id="btnDeleteBox">
		<button id="btnDelete">삭제</button>
	</div>
	<div id="btnUpdateBox">
		<button id="btnUpdate">입양수락</button>
	</div>
	<div id="pagingBox" class="text-center">
		<ul class="pagination pagination-sm">
			<!-- 처음으로 가기 -->
			<c:if test="${paging.curPage ne 1 }">
				<li><a
					href="/adoption/application/list?search=${paging.search }"
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
					href="/adoption/application/list?curPage=${paging.curPage-1 }&search=${paging.search }"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>





			<!-- 페이징 리스트 -->
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
				var="i">

				<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
				<c:if test="${paging.curPage eq i}">
					<li class="active"><a
						href="/adoption/application/list?curPage=${i }&search=${paging.search }">${i }</a></li>
				</c:if>
				<c:if test="${paging.curPage ne i}">
					<li><a
						href="/adoption/application/list?curPage=${i }&search=${paging.search }">${i }</a></li>
				</c:if>
			</c:forEach>





			<!-- 다음 페이지 -->
			<c:if test="${paging.curPage eq paging.totalPage }">
				<li class="disabled"><span aria-hidden="true">&raquo;</span></li>
			</c:if>

			<c:if test="${paging.curPage ne paging.totalPage }">
				<li><a
					href="/adoption/application/list?curPage=${paging.curPage+1 }&search=${paging.search }"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
		</ul>

	</div>

</div>

<jsp:include page="/view/layout/footer.jsp" />