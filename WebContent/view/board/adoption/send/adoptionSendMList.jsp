<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	$("table").on("click", "tr", function() {
		//클릭이벤트가 발생한 <tr>의 첫번째 <td>자식의 텍스트
		var animal_code = $(this).children("td").eq(0).text();
		
		$(location).attr("href","/adoption/send/view?animal_code="+animal_code);
	});
	
	$("#btnWrite").click(function(){
		$(location).attr("href", "/adoption/send/insert");
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

#btnDeleteBox {
	float:left;
}

#btnOkBox {
	display: inline-block;
}

#btnWriteBox {
	float:right;
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
	<c:if test="${animal.status eq 1 }">
		<c:set var="doneLoop" value="false" />
		<c:set var="AllSet" value="false" />

		<c:if test="${not doneLoop }">
			<c:forEach items="${adoptList }" var="adopt">
				<c:if test="${adopt.status eq 1 }">
					<c:if test="${animal.animal_Code eq adopt.animalCode}">
						<%-- break; --%>
						<c:set var="doneLoop" value="true" />
						<c:set var="AllSet" value="true" />
					</c:if>
				</c:if>
				<c:if test="${adopt.status ne 1 }">
					<c:if test="${animal.animal_Code ne adopt.animalCode}">
						<%-- break; --%>
						<c:set var="doneLoop" value="true" />
					</c:if>
				</c:if>
			</c:forEach>
		</c:if>
		
		<c:if test="${AllSet eq true }">
			<td><a href="/adoption/send/view?animal_code=${animal.animal_Code }">${animal.animal_Name } [ 입양 완료 ]</a></td>
		</c:if>
		<c:if test="${AllSet ne true }">
			<td><a href="/adoption/send/view?animal_code=${animal.animal_Code }">${animal.animal_Name }</a></td>
		</c:if>
		
		</c:if>
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
		<li>
			<a href="/adoption/send/mlist" aria-label="First">
				<span aria-hidden="true">&larr;처음</span>
			</a>
		</li>
		</c:if>
		
		<!-- 이전페이지 -->
		<!-- 첫 페이지라면 버튼동작 안되게 만들기 -->
		<c:if test="${paging.curPage eq 1 }">
		<li class="disabled">
			<span aria-hidden="true">&laquo;</span>
		</li>
		</c:if>
		
		<c:if test="${paging.curPage ne 1 }">
		<li>
			<a href="/adoption/send/mlist?curPage=${paging.curPage-1 }" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		<li>
		</c:if>
		
		<!-- 페이징 리스트 -->
		<c:forEach
			begin="${paging.startPage }"
			end="${paging.endPage }"
			var="i">
			
			<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
			<c:if test="${paging.curPage eq i }">
				<li class="active"><a href="/adoption/send/mlist?curPage=${i }">${i }</a></li>
			</c:if>
			<c:if test="${paging.curPage ne i }">
				<li><a href="/adoption/send/mlist?curPage=${i }">${i }</a></li>
			</c:if>
		</c:forEach>
		
		<!-- 다음 페이지 -->
		<c:if test="${paging.curPage eq paging.totalPage }">
			<li class="disabled">
				<span aria-hidden="true">&raquo;</span>
			</li>
		</c:if>
		
		<c:if test="${paging.curPage ne pagin.totalPage }">
			<li>
				<a href="/adoption/send/mlist?curPage=${paging.curPage+1 }" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</c:if>
		
	</ul>
	<div id="btnWriteBox">
		<button id="btnWrite">글쓰기</button>
	</div>
	</div>
</div>

<jsp:include page="/view/layout/footer.jsp" />