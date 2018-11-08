<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
	$(document).ready(function() {
		$("#btnList").click(function() {
			var userControl = "${role_id}";

			if (userControl == 0) {
				$(location).attr("href", "/adoption/send/list");
			} else {
				$(location).attr("href","/adoption/send/mlist");
			}
		});

		$("#btnDelete").click(function() {
			$(location).attr("href","/adoption/send/delete?animal_code=${animal.animal_Code }");
		});
						
		$("#btnadoption").click(function() {
			$(location).attr("href","/adoption/application/insert?animal_Name=${animal.animal_Name }");
		});
	});
</script>
<style>
#board_view {
	padding-top: 30px;
}

.board_view_subject {
	overflow: hidden;
	border-top: 2px solid #283444;
	border-bottom: 1px solid #283444;
	height: 42px;
	padding: 0 20px;
	line-height: 42px;
}

.board_view_subject_left {
	float: left;
	color: #283444;
}

.board_view_subject_right {
	float: right;
	color: #6d6e72;
}

.board_view_content {
	padding: 40px;
	color: #6d6e72;
	line-height: 1.5;
}

.tutorial_content {
	padding: 40px;
	color: #6d6e72;
	line-height: 1.5;
}

.photo_content {
	padding: 40px 0;
}

.industrialEdu_content {
	padding: 40px 0;
}

.board_view_prev {
	border-top: 1px solid #283444;
	border-bottom: 1px solid #ececec;
	background: url('/resources/images/common/point_view_prev.jpg');
	background-repeat: no-repeat;
	padding-left: 42px;
	height: 40px;
	line-height: 38px;
}

.board_view_next {
	border-bottom: 1px solid #283444;
	background: url('/resources/images/common/point_view_next.jpg');
	background-repeat: no-repeat;
	padding-left: 42px;
	height: 40px;
	line-height: 38px;
}

.board_view_prev span, .board_view_next span {
	margin-right: 38px;
	color: #283444;
}

.board_view_prev a, .board_view_next a {
	color: #6d6e72;
}

.board_view_prev a:hover, .board_view_next a:hover {
	color: #283444;
}

.board_view_botton {
	text-align: right;
	padding: 10px 0 50px 0;
	height: 34px;
	overflow: hidden;
}

.board_view_botton a {
	width: 69px;
	height: 34px;
	font-size: 12px;
	line-height: 34px;
	background: #283444;
	text-align: center;
	color: #fff;
	display: block;
	margin-left: 10px;
	float: right;
}

table th {
	width: 35%;
	background-color: #F3969A;
}
</style>
<div class="container">

	<h3>입양 보내기 신청</h3>
	<hr>

	<div>
		<div id="board_view">
			<div class="board_view_subject">
				<p class="board_view_subject_left">동물번호 : ${animal.animal_Code } |
					동물이름 : ${animal.animal_Name }</p>
				<p class="board_view_subject_right">글쓴이 : ${animal.userid }</p>

			</div>
			<div class="board_view_content">
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