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

<table class="board">
<thead>
<tr>
<th style="width: 45%; text-align: center;">동물 이름</th>
<th style="width: 20%; text-align: center;">성별</th>
<th style="width: 10%; text-align: center;">품종</th>
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
			<td class="board_subject"><a href="/adoption/send/view?animal_code=${animal.animal_Code }">${animal.animal_Name } [ 입양 완료 ]</a></td>
		</c:if>
		<c:if test="${AllSet ne true }">
			<td class="board_subject"><a href="/adoption/send/view?animal_code=${animal.animal_Code }">${animal.animal_Name }</a></td>
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