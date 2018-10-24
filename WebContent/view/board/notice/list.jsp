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
		
		$(location).attr("href","/noticeboard/view?boardno="+boardno);
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

<h3>공지사항</h3>
<hr>

<table class="table table-hover table-striped table-condensed">
<thead>
<tr>
<th style="width: 10%">번호</th>
<th style="width: 45%">제목</th>
<th style="width: 20%">작성자</th>
<th style="width: 10%">조회수</th>
<th style="width: 20%">작성일</th>
</tr>
</thead>

<tbody>
<c:forEach items="${notice_boardList }" var="board">
<tr>
<td>${board.boardno }</td>
<td><a href="/noticeboard/view?boardno=${board.boardno }">${board.title }</a></td>
<td>${board.userid }</td>
<td>${board.hit }</td>
<td><fmt:formatDate value="${board.insert_dat }" pattern="yyyy-MM-dd"/></td>
</tr>
</c:forEach>
</tbody>

</table>
<div id="btnWriteBox">
	<button id="btnWrite" onclick='location.href="/noticeboard/write";'>글쓰기</button>
</div>

<div class="text-center">
  <ul class="pagination pagination-sm">
  	<!-- 처음으로 가기 -->
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/board/paginglist" aria-label="First">
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
      <a href="/noticeboard/list?curPage=${paging.curPage-1 }" aria-label="Previous">
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
    	  <li class="active"><a href="/board/paginglist?curPage=${i }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/board/paginglist?curPage=${i }">${i }</a></li>
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
      <a href="/board/paginglist?curPage=${paging.curPage+1 }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
</div>

</div>


<jsp:include page="/view/layout/footer.jsp" />











