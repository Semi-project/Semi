<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

//	 	$("table").on("click", "tr", function() {
// 		//클릭이벤트가 발생한 <tr>의 첫번째 <td>자식의 텍스트
// 		var boardno = $(this).children("td").eq(0).text();
		
// 		$(location).attr("href","/board/view?boardno="+boardno);
// 	});
	
	$("#btnWrite").click(function() {
		location.href="/noticeboard/write";
	});
	
	$("#btnSearch").click(function() {
		$(location).attr("href", "/noticeboard/list?search="+$("#search").val());
	});

	// 선택체크 삭제
	$("#btnDelete").click(function() {
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
		var map = $checkboxes.map(function() {
			return $(this).val();
		});
		var names = map.get().join(",");
// 		console.log("names : " + names);
		
// 		console.log( "map:" + map );	// 맵
// 		console.log( "map->array : " + map.get() );	// 맵->배열
// 		console.log( "array tostring : " + map.get().join(",") ); // toString
		
		// 전송 폼
		var $form = $("<form>")
			.attr("action", "/noticeboard/deleteList")
			.attr("method", "post")
			.append(
				$("<input>")
					.attr("type", "hidden")
					.attr("name", "names")
					.attr("value", names)
			);
		$(document.body).append($form);
		$form.submit();
	
	});
});


//전체 체크/해제
function checkAll() {
	// checkbox들
	var $checkboxes=$("input:checkbox[name='checkRow']");

	// checkAll 체크상태 (true:전체선택, false:전체해제)
	var check_status = $("#checkAll").is(":checked");
	
	if( check_status ) {
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

.board #no_result {
	height: 70px;
	line-height: 70px;
	text-align: center;
	border-bottom: 1px solid #283444;
}

.board_num {
	text-align: center;
	padding-bottom: 85px;
}

.board_num a {
	display: inline-block;
	width: 26px;
	height: 26px;
	border: 1px solid #e7e7e7;
	color: #8b8c90;
	line-height: 26px;
	text-align: center;
	background: #fff;
}

.board_num .board_num_on {
	border: 1px solid #293243;
	background: #293243;
	color: #fff;
}

.board_num .board_num_first {
	background: url('/resources/images/common/point_first.jpg');
}

.board_num .board_num_prev {
	background: url('/resources/images/common/point_prev.jpg');
	margin-right: 10px;
}

.board_num .board_num_next {
	background: url('/resources/images/common/point_next.jpg');
	margin-left: 10px;
}

.board_num .board_num_last {
	background: url('/resources/images/common/point_last.jpg');
}

</style>

<div class="container">

	<h3>공지사항</h3>
	<hr>

	<table class="board">
		<tbody>
			<tr>
				<th style="width: 70px; text-align: center;">번호</th>
				<th style="width: 45%; text-align: center;">제목</th>
				<th style="width: 20%; text-align: center;">작성자</th>
				<th style="width: 10%; text-align: center;">조회수</th>
				<th style="width: 20%; text-align: center;">작성일</th>
			</tr>



			<c:forEach items="${notice_boardList }" var="board">
				<tr>
					<td>${board.boardno }</td>
					<td class="board_subject" style="padding-left: 50px;"><a
						href="/noticeboard/view?boardno=${board.boardno }">${board.title }</a></td>
					<td>${board.userid }</td>
					<td>${board.hit }</td>
					<td><fmt:formatDate value="${board.insert_dat }"
							pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	
	<c:if test="${(login==true && role_id==0)}">
	<div id="btnWriteBox">
		<button id="btnWrite" onclick='location.href="/noticeboard/write";'>글쓰기</button>
	</div>
	</c:if>
	
<div id="pagingBox" class="text-center">
  <ul class="pagination pagination-sm">
  	<!-- 처음으로 가기 -->
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/noticeboard/list" aria-label="First">
        <span aria-hidden="true">&larr;처음</span>
      </a>
    </li>
	</c:if>
	
  	<!-- 이전 페이지 --
  	<!-- 첫 페이지라면 버튼 동작 안 되게 만들기 -->
  	<c:if test="${paging.curPage eq 1 }">
    <li class="disabled">
        <span aria-hidden="true">&laquo;</span>
    </li>
    </c:if>
    
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/noticeboard/list?curPage=${paging.curPage-1 }&search=${paging.search }" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    
    <!-- 페이징 리스트 -->
    <c:forEach
     begin="${paging.startPage }"
     end="${paging.endPage }"
     var="i">

		<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
		<c:if test="${paging.curPage eq i}">          
    	  <li class="active"><a href="/noticeboard/list?curPage=${i }&search=${paging.search }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/noticeboard/list?curPage=${i }&search=${paging.search }">${i }</a></li>
    	</c:if>
    </c:forEach>

    <!-- 다음 페이지 -->
  	<c:if test="${paging.curPage eq paging.totalPage }">
    <li class="disabled">
        <span aria-hidden="true">&raquo;</span>
    </li>
	</c:if>
	
  	<c:if test="${paging.curPage ne paging.totalPage }">
    <li>
      <a href="/noticeboard/list?curPage=${paging.curPage+1 }&search=${paging.search }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>

</div>
<div id="searchBox" class="text-center">
	<input type="text" id="search" />
	<button id="btnSearch">제목 검색</button>
</div>


</div>


<jsp:include page="/view/layout/footer.jsp" />

