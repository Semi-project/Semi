<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#btnList").click(
								function() {

									var userControl = "${role_id}";

									if (userControl == 0) {
										$(location).attr("href",
												"/adoption/send/list");
									} else {
										$(location).attr("href",
												"/adoption/send/mlist");
									}
								});

						$("#btnDelete")
								.click(
										function() {

											$(location)
													.attr("href",
															"/adoption/send/delete?animal_code=${animal.animal_Code }");
										});
						$("#btnadoption")
								.click(
										function() {
											$(location)
													.attr("href",
															"/adoption/application/insert?animal_Name=${animal.animal_Name }");
										});
					});
</script>
<style>
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

</style>
<div class="container">

	<h3>입양 보내기 신청</h3>
	<hr>

	<div>
		<div id="board_view">
			<div class="board_view_subject">
				<p class="board_view_subject_left">NO:${animal.animal_Code }|${animal.animal_Name }</p>
				<p class="board_view_subject_right">${animal.userid }</p>

			</div>
			<div class="board_view_content">
				<table class="table table-bordered">
					<tr>
						<td class="info">동물번호</td>
						<td>${animal.animal_Code }</td>
					</tr>
					<tr>
						<td class="info">입양 보내는 분</td>
						<td>${animal.userid }</td>
					</tr>
					<tr>
						<td class="info">이름</td>
						<td colspan="2">${animal.animal_Name }</td>
					</tr>
					<tr>
						<td class="info">나이</td>
						<td colspan="2">${animal.animal_Age }</td>
					</tr>
					<tr>
						<td class="info">성별</td>
						<td colspan="2">${animal.animal_Gender_Code }</td>
					</tr>
					<tr>
						<td class="info">체중</td>
						<td colspan="2">${animal.animal_Gr }</td>
					</tr>
					<tr>
						<td class="info">중성화여부</td>
						<td colspan="2">${animal.animal_Neuters }</td>
					</tr>
					<tr>
						<td class="info">품종</td>
						<td colspan="2">${animal.species_Name }</td>
					</tr>
					<tr>
						<td class="info">특징</td>
						<td colspan="2">${animal.animal_Feature }</td>
					</tr>
				</table>
				<hr>

			</div>
		</div>

	</div>

	<div class="text-center">
		<button id="btnList" class="btn btn-primary">목록</button>
		<c:if test="${role_id eq 0 ||animal.userid eq userid }">
			<button id="btnDelete" class="btn btn-danger">삭제</button>
		</c:if>
		<c:if test="${role_id eq 1}">
			<button id="btnadoption" class="btn btn-primary">입양하기</button>
		</c:if>
	</div>
</div>

<jsp:include page="/view/layout/footer.jsp" />