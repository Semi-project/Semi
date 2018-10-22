<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	$("table").on("click", "tr", function() {
		//클릭이벤트가 발생한 <tr>의 첫번째 <td>자식의 텍스트
		var boardno = $(this).children("td").eq(0).text();
		
		$(location).attr("href","/adoption/send/view?animal_code="+animal_code);
	});
});
</script>

<style type="text/css">
th, td:not(:nth-child(2)) {
	text-align: center;
}
td {
	border-left: 1px solid white;
	border-right: 1px solid white;
}
</style>

<div class="container">

<h3>게시글 목록</h3>
<hr>

<table class="table table-hover table-striped table-condensed">
<thead>
<tr>
<th style="width: 45%">동물 이름</th>
<th style="width: 20%">성별</th>
<th style="width: 10%">품종</th>
</tr>
</thead>

<tbody>
<c:forEach items="${animalList }" var="animal">

<tr>
<td><a href="/adoption/send/view?animal_code=${animal.animal_Code }">${animal.animal_Name }</a></td>
<td>${animal.animal_Gender_Code }</td>
<td>${animal.species_Name }</td>
</tr>

</c:forEach>
</tbody>

</table>
</div>


<jsp:include page="/view/layout/footer.jsp" />