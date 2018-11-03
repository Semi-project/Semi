<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
// 	$("table").on("click", "tr", function() {
// 		//클릭이벤트가 발생한 <tr>의 첫번째 <td>자식의 텍스트
// 		var boardno = $(this).children("td").eq(0).text();
		
// 		$(location).attr("href","/board/view?boardno="+boardno);
// 	});
	
// 	$("#btnUpdate").click(function() {
// 		location.href="/board/write";
// 	});
	
	$("#btnSearch").click(function() {
		$(location).attr("href", "/adoption/application/list?search="+$("#search").val());
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
			.attr("action", "/adoption/application/deletelist")
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
	
	$("#btnUpdate").click(function() {
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
			.attr("action", "/adoption/application/updatelist")
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
	var $checkboxes=$("input:checkbox[name='checkRow']:enabled");

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
th, td:not(:nth-child(2)) {
	text-align: center;
}
td {
	border-left: 1px solid white;
	border-right: 1px solid white;
}

#pagingBox {
	position: relative;
}


/* 버튼 좌우정렬 및 상하정렬 */
#btnUpdateBox, #btnDeleteBox {
	position: absolute;
	top: 0;
	bottom: 0;
	height: 30px;
	margin: auto;
}
#btnUpdateBox {
	right: 10px;
}
#btnDeleteBox {
	left: 10px;
}
#btnDelete, #btnUpdate {
	height: 25px;
}

</style>

<div class="container">

<h3>게시글 목록</h3>
<hr>

<table class="table table-hover table-striped table-condensed">
<thead>
<tr>
<th>
	<input type="checkbox" id="checkAll" onclick="checkAll();" />
</th>
<th style="width: 10%">입양코드</th>
<th style="width: 35%">동물이름</th>
<th style="width: 20%">작성자</th>
<th style="width: 10%">동물코드</th>
<th style="width: 10%">상태</th>
</tr>
</thead>

<tbody>
<c:forEach items="${boardList }" var="board">
<tr>
<c:if test="${board.status eq 0 }">
	<td><input type="checkbox" name="checkRow" value="${board.adoptionCode }" /></td>
</c:if>
<c:if test="${board.status eq 1 }">
	<td><input type="checkbox" name="checkRow" value="${board.adoptionCode }" disabled /></td>
</c:if>
<td>${board.adoptionCode }</td>
<td><a href="/adoption/application/view?adoption_code=${board.adoptionCode }">${board.animalName }</a></td>
<td>${board.userid }</td>
<td>${board.animalCode }</td>
<td>${board.status }</td>
</tr>
</c:forEach>
</tbody>

</table>

<div id="pagingBox" class="text-center">
  <ul class="pagination pagination-sm">
  	<!-- 처음으로 가기 -->
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/adoption/application/list?search=${paging.search }" aria-label="First">
        <span aria-hidden="true">&larr;처음</span>
      </a>
    </li>
	</c:if>
	
	
	
	  
  
  	<!-- 이전 페이지 -->
  	<!-- 첫 페이지라면 버튼 동작 안 되게 만들기 -->
  	<c:if test="${paging.curPage eq 1 }">
    <li class="disabled">
        <span aria-hidden="true">&laquo;</span>
    </li>
    </c:if>
    
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/adoption/application/list?curPage=${paging.curPage-1 }&search=${paging.search }" aria-label="Previous">
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
    	  <li class="active"><a href="/adoption/application/list?curPage=${i }&search=${paging.search }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/adoption/application/list?curPage=${i }&search=${paging.search }">${i }</a></li>
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
      <a href="/adoption/application/list?curPage=${paging.curPage+1 }&search=${paging.search }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
  <div id="btnDeleteBox">
	<button id="btnDelete">삭제</button>
  </div>
  <div id="btnUpdateBox">
	<button id="btnUpdate">입양수락</button>
  </div>
</div>
<div id="searchBox" class="text-center">
	<input type="text" id="search" />
	<button id="btnSearch">검색</button>
</div>

</div>

<jsp:include page="/view/layout/footer.jsp" />