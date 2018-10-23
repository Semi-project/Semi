<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	$("#btnList").click(function() {
		$(location).attr("href", "/adoption/send/list");
	});
// 	$("#btnUpdate").click(function() {
// 		$(location).attr("href", "/board/update?boardno=${boardView.boardno }");
// 	});
	$("#btnDelete").click(function() {
		$(location).attr("href", "/adoption/send/delete?animal_code=${animal.animal_Code }");
	});
});
</script>

<div class="container">

<h3>입양 보내기 신청</h3>
<hr>

<div>
<table class="table table-bordered">
<tr>
	<td class="info">동물번호</td><td>${animal.animal_Code }</td>
</tr>
<tr>
	<td class="info">이름</td><td colspan="2">${animal.animal_Name }</td>
</tr>
<tr>
	<td class="info">나이</td><td colspan="2">${animal.animal_Age }</td>
</tr>
<tr>
	<td class="info">성별</td><td colspan="2">${animal.animal_Gender_Code }</td>
</tr>
<tr>
	<td class="info">체중</td><td colspan="2">${animal.animal_Gr }</td>
</tr>
<tr>
	<td class="info">중성화여부</td><td colspan="2">${animal.animal_Neuters }</td>
</tr>
<tr>
	<td class="info">품종</td><td colspan="2">${animal.species_Name }</td>
</tr>
<tr>
	<td class="info">특징</td><td colspan="2">${animal.animal_Feature }</td>
</tr>
</table>
</div>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>
</div>

<jsp:include page="/view/layout/footer.jsp" />