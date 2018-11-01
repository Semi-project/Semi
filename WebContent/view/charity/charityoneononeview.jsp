<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
	$(document).ready(
			function() {
				$("#btnList").click(function() {

					var userControl = "${role_id}";

					if (userControl == 0) {
						$(location).attr("href", "/charity/foroneonone");
					} else {
						$(location).attr("href", "/charity/foroneonone");
					}
				});

				$("#btnCharity").click(
						function() {

							var animalCode = "${animal.animal_Code}";

							var animalName = "${animal.animal_Name}";
							var url = '/charity/oneonone?animalCode='
									+ animalCode + "&animalName=" + animalName;

							location.href = url;
						});
			});
</script>

<div class="container">

	<h3>1:1 후원하기</h3>
	<hr>

	<div>
		<form id="animalInfo" action="" method="POST">
			<table class="table table-bordered">
				<tr>
					<td class="info">동물번호</td>
					<td>${animal.animal_Code }</td>

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
		</form>
	</div>

	<div class="text-center">
		<button id="btnList" class="btn btn-primary">목록</button>
		<c:choose>
			<c:when test="${adoption.status == 1 }">
				<button id="btnCharity" class="btn btn-info"
					style="visibility: hidden;">후원하기</button>
			</c:when>
			<c:otherwise>
				<button id="btnCharity" class="btn btn-info"
					style="visibility: visible;">후원하기</button>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<jsp:include page="/view/layout/footer.jsp" />