<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						// 	$("table").on("click", "tr", function() {
						// 		//클릭이벤트가 발생한 <tr>의 첫번째 <td>자식의 텍스트
						// 		var animal_code = $(this).children("td").eq(0).text();

						// 		$(location).attr("href","/adoption/send/view?animal_code="+animal_code);
						// 	});

						$("#btnWrite").click(function() {
							$(location).attr("href", "/adoption/send/insert");
						});

						////////////////////지금 작업 부분////////////////////////
						// 선택체크 삭제
						$("#btnDelete")
								.click(
										function() {
											// 선택된 체크박스
											var $checkboxes = $("input:checkbox[name='checkRow']:checked");

											var map = $checkboxes
													.map(function() {
														return $(this).val();
													});
											var codes = map.get().join(",");

											// 전송 폼
											var $form = $("<form>")
													.attr("action",
															"/adoption/send/deleteList")
													.attr("method", "post")
													.append(
															$("<input>")
																	.attr(
																			"type",
																			"hidden")
																	.attr(
																			"name",
																			"codes")
																	.attr(
																			"value",
																			codes));
											$(document.body).append($form);
											$form.submit();

										});

						$("#btnOk")
								.click(
										function() {
											// 선택된 체크박스
											var $checkboxes = $("input:checkbox[name='checkRow']:checked");

											var map = $checkboxes
													.map(function() {
														return $(this).val();
													});
											var codes = map.get().join(",");

											// 전송 폼
											var $form = $("<form>")
													.attr("action",
															"/adoption/send/accpetList")
													.attr("method", "post")
													.append(
															$("<input>")
																	.attr(
																			"type",
																			"hidden")
																	.attr(
																			"name",
																			"codes")
																	.attr(
																			"value",
																			codes));
											$(document.body).append($form);
											$form.submit();

										});

					});

	// checkbox 전체 선택
	function checkAll() {

		// 아직 수락되지 않은 동물들	
		var $checkboxes = $("input:checkbox[name='checkRow']:enabled");

		// checkAll 체크상태 (true:전체선택, false:전체해제)
		var check_status = $("#checkAll").is(":checked");

		if (check_status) {
			// 전체 enabled 체크박스를 checked로 바꾸기
			$checkboxes.each(function() {
				this.checked = true;
				console.log(this.val)
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

#btnDeleteBox {
	float: left;
}

#btnOkBox {
	display: inline-block;
}

#btnWriteBox {
	float: right;
}
</style>

<div class="container">

	<h3>게시글 목록</h3>
	<hr>

	<table class="table table-hover table-striped table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" onclick="checkAll();" />
				</th>
				<th style="width: 45%">동물 이름</th>
				<th style="width: 20%">성별</th>
				<th style="width: 10%">품종</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${animalList }" var="animal">
				
					<tr>


						<c:choose>
							<%-- checkbox 대기상태 = enabled, 수락됨 = disabled --%>
							<c:when test="${animal.status eq 0 }">
								<td><input type="checkbox" name="checkRow"
									value="${animal.animal_Code }" /></td>
							</c:when>
							<c:when test="${animal.status eq 1 }">
								<td><input type="checkbox" name="checkRow"
									value="${animal.animal_Code }" disabled /></td>
							</c:when>
						</c:choose>
						<c:forEach items="${boardList }" var="board">
						<c:if test="${animal.animal_Code eq board.animalCode }">
							<c:if test="${board.status eq 1 }">
							dddddd
							</c:if>
						</c:if>
						</c:forEach>
						<td><a
							href="/adoption/send/view?animal_code=${animal.animal_Code }">${animal.animal_Name }</a></td>
						<td>${animal.animal_Gender_Code }</td>
						<td>${animal.species_Name }</td>
					</tr>

				</c:forEach>
			
		</tbody>

	</table>



	<div class="text-center">
		<ul class="pagination pagination-sm">

			<!-- 처음으로 가기 -->
			<c:if test="${paging.curPage ne 1 }">
				<li><a href="/adoption/send/list" aria-label="First"> <span
						aria-hidden="true">&larr;처음</span>
				</a></li>
			</c:if>

			<!-- 이전페이지 -->
			<!-- 첫 페이지라면 버튼동작 안되게 만들기 -->
			<c:if test="${paging.curPage eq 1 }">
				<li class="disabled"><span aria-hidden="true">&laquo;</span></li>
			</c:if>

			<c:if test="${paging.curPage ne 1 }">
				<li><a href="/adoption/send/list?curPage=${paging.curPage-1 }"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a>
				<li>
			</c:if>

			<!-- 페이징 리스트 -->
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
				var="i">

				<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
				<c:if test="${paging.curPage eq i }">
					<li class="active"><a href="/adoption/send/list?curPage=${i }">${i }</a></li>
				</c:if>
				<c:if test="${paging.curPage ne i }">
					<li><a href="/adoption/send/list?curPage=${i }">${i }</a></li>
				</c:if>
			</c:forEach>

			<!-- 다음 페이지 -->
			<c:if test="${paging.curPage eq paging.totalPage }">
				<li class="disabled"><span aria-hidden="true">&raquo;</span></li>
			</c:if>

			<c:if test="${paging.curPage ne pagin.totalPage }">
				<li><a href="/adoption/send/list?curPage=${paging.curPage+1 }"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>

		</ul>
		<div style="width: 50%">
			<div id="btnDeleteBox">
				<button id="btnDelete">거절</button>
			</div>
			<div id="btnOkBox">
				<button id="btnOk">수락</button>
			</div>
			<div id="btnWriteBox">
				<button id="btnWrite">글쓰기</button>
			</div>
		</div>
	</div>

</div>

<jsp:include page="/view/layout/footer.jsp" />