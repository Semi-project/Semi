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
<style>
table th {
	width: 35%;
}
</style>
<div class="container">

	<h3>1:1 후원하기</h3>
	<hr>

	<div>
		<form id="animalInfo" action="" method="POST">
			<table class="table table-bordered">
				<tr>
					<th>동물번호</th>
					<td>${animal.animal_Code }</td>

				</tr>
				<tr>
					<th>이름</th>
					<td colspan="2">${animal.animal_Name }</td>
				</tr>
				<tr>
					<th>나이</th>
					<td colspan="2">${animal.animal_Age }</td>
				</tr>
				<tr>
					<th>성별</th>
					<td colspan="2">${animal.animal_Gender_Code }</td>
				</tr>
				<tr>
					<th>체중</th>
					<td colspan="2">${animal.animal_Gr }</td>
				</tr>
				<tr>
					<th>중성화여부</th>
					<td colspan="2">${animal.animal_Neuters }</td>
				</tr>
				<tr>
					<th>품종</th>
					<td colspan="2">${animal.species_Name }</td>
				</tr>
				<tr>
					<th>특징</th>
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