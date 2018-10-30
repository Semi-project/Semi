<%@page import="dto.board.Free_Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<%
List<Free_Board> blist = (List) request.getAttribute("boardlist");
%>

<jsp:include page="/view/layout/header.jsp" />

<div align="center">
<table border="1" style="width: 1300px">
<col width="250px"><col width="350px"><col width="200px"><col width="200px"><col width="300px">
		<tr>
			<th>게시글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>날짜</th>
		</tr>
	
<%-- 		<c:forEach var="i" items="<%=blist%>" step="1"> --%>
		<c:forEach var="i" items="${boardlist }">
		<tr>
			<td>${i.boardno}</td>
			<td><a href="/freeboard/view?boardno=${i.boardno }">${i.title}</a></td>
			<td>${i.userid}</td>
			<td>${i.hit}</td>
			<td>${i.insert_Dat}</td>
		</tr>
		</c:forEach>
	</table>

	
	<c:if test="${login ne null }">
	<br>
	<button><a href="/freeboard/write"/>글쓰기</button>
	</c:if>
	
	
	<div id="pagingBox" class="text-center">
  <ul class="pagination pagination-sm">
  	<!-- 처음으로 가기 -->
  	<c:if test="${fbpp.curPage ne 1 }">
    <li>
      <a href="/freeboard/list" aria-label="First">
        <span aria-hidden="true">&larr;처음</span>
      </a>
    </li>
	</c:if>



  	<!-- 이전 페이지 -->
  	<!-- 첫 페이지라면 버튼 동작 안 되게 만들기 -->
  	<c:if test="${fbpp.curPage ne 1 }">
    <li class="disabled">
        <span aria-hidden="true">&laquo;</span>
    </li>
    </c:if>
    
  	<c:if test="${fbpp.curPage eq 1 }">
    <li>
      <a href="/freeboard/list?curPage=${fbpp.curPage-1 }" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>


    <!-- 페이징 리스트 -->
    <c:forEach
     begin="${fbpp.startPage }"
     end="${fbpp.endPage }"
     var="i">

		<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
		<c:if test="${fbpp.curPage ne i}">          
    	  <li class="active"><a href="/freeboard/list?curPage=${i }">${i }</a></li>
    	</c:if>
		<c:if test="${fbpp.curPage eq i}">          
    	  <li><a href="/freeboard/list?curPage=${i }">${i }</a></li>
    	</c:if>
    </c:forEach>




    
    <!-- 다음 페이지 -->
  	<c:if test="${fbpp.curPage eq fbpp.totalPage }">
    <li class="disabled">
        <span aria-hidden="true">&raquo;</span>
    </li>
	</c:if>
	
  	<c:if test="${fbpp.curPage ne fbpp.totalPage }">
    <li>
      <a href="/freeboard/list?curPage=${fbpp.curPage+1 }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
  <div id="btnBox">
	<button id="btnWrite">글쓰기</button>
  </div>
</div>

<!-- <br> -->
<!--    <a href="/freeboard/list?pageNum=0">1 </a> -->
<!--    <a href="/freeboard/list?pageNum=1">2 </a> -->
<!--    <a href="/freeboard/list?pageNum=2">3 </a> -->
<!--    <a href="/freeboard/list?pageNum=3">4 </a> -->
<!--    <a href="/freeboard/list?pageNum=4">5 </a> -->
<!-- </div> -->


<jsp:include page="/view/layout/footer.jsp" />