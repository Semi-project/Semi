<%@page import="dto.board.Free_Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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


</style>
   
<%
List<Free_Board> blist = (List) request.getAttribute("boardlist");
%>


<jsp:include page="/view/layout/header.jsp" />
<div>
&nbsp;
</div>
<div class="container">

	<h3>자유 게시판</h3>
	<hr>



<form id="_formTable" >

	<table class="board">
<!-- <table  style="width: 1300px" class="ui single line table" > -->
<col width="250px"><col width="350px"><col width="200px"><col width="200px"><col width="300px">
		<tr>
			<th style="width: 70px; text-align: center;">번호</th>
			<th style="width: 45%; text-align: center;">제목</th>
			<th style="width: 20%; text-align: center;">작성자</th>
			<th style="width: 10%; text-align: center;">조회수</th>
			<th style="width: 10%; text-align: center;">추천수</th>
			<th style="width: 20%; text-align: center;">날짜</th>
			
			<!-- <th>비밀글 or 공개글  <c:if test="${i.lock ==1}">비밀글</c:if> 0일경우에는 공개글 </th> -->
		</tr>
	
<%-- 		<c:forEach var="i" items="<%=blist%>" step="1"> --%>
		<c:forEach var="i" items="${boardlist }">
		<tr>
			<td style="padding-left:15px">${i.boardno}</td>
			<td style="padding-left:15px">
				<!-- 비밀글일 경우 제목을 출력안함 -->
				<%-- 
				<c:if test="${i.secret==1 }">
					글제목을 보여줌
				</c:if>
				<c:if test="${i.secret==0 || i.userid == session.id || session.id =='관리자id'}">
					비밀글
				</c:if>
				 --%>
				<a href="/freeboard/view?boardno=${i.boardno }">${i.title}</a>
			</td>
			<td style="padding-left:15px">${i.userid}</td>
			<td style="padding-left:15px">${i.hit}</td>
			<td style="padding-left:15px">${i.recommend }</td>
			<td style="padding-left:15px">${i.insert_Dat}</td>
		</tr>
		</c:forEach>
	</table>
	
	<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber}">

	
	<c:if test="${login ne null }">
	<br>
<!-- 	<button class="ui active button" onclick="goWriteBtn()"> -->
<!--   	<i class="write icon"></i> -->
<!--   	글쓰기 -->
<!-- 	</button> -->
	
 	<input type="button" class="ui active button" value="글쓰기" onclick="goWriteBtn()">  
 		<script> 
 		function goWriteBtn() {
 			location.href = "/freeboard/write";
 		}
 		</script> 
	</c:if>

	
	


<!-- <br>
   <a href="/freeboard/list?pageNum=0">1 </a>
   <a href="/freeboard/list?pageNum=1">2 </a>
   <a href="/freeboard/list?pageNum=2">3 </a>
   <a href="/freeboard/list?pageNum=3">4 </a>
   <a href="/freeboard/list?pageNum=4">5 </a>
</div> -->
   
<br><br>
<jsp:include page="/view/board/Free/freeboard_paging.jsp" flush="false">
	<jsp:param value="${fbpp.pageNumber}" name="pageNumber"/>
	<jsp:param value="${totalRecordCount}" name="totalRecordCount"/>
	<jsp:param value="${pageCountPerScreen}" name="pageCountPerScreen"/>
	<jsp:param value="${recordCountPerPage}" name="recordCountPerPage"/>
</jsp:include>	

<br><br>

<div style="width: 700px" align="center">
<table class="basic ui table" style="width: 100%">
<col width="150px"><col width="200px"><col width="150px"><col width="200px">
<tr>	
	<td colspan="4">상세검색</td>
</tr>
<tr height="55px">
	<td>
	작성자
	</td>
	<td>
		<div class="ui input">
			<input type="text" style="width: 200px; height: 50px" name="namesearch" value="${fbpp.namesearch}">
		</div>
	</td>
	
	<td>
	제목
	</td>
	<td>
		<div class="ui input">
		<input type="text" style="width:200px; height: 50px" name="contentsearch" value="${fbpp.contentsearch }">
		</div>
	</td>
</tr>
</table>

	<button class="ui pink basic button" onclick="submitSearch()">검색</button>
<!-- 	<button class="ui black basic button" >초기화</button> -->
</div>
</form>
<script>
function submitSearch(){
	$('#_formTable').attr({"target":"_self","action":"/freeboard/list"}).submit();
}
function goPage(pageNumber) {
	   $("#_pageNumber").val(pageNumber);
	   

	   $("#_formTable").attr({"target":"_self","action":"/freeboard/list"}).submit();
	   
	    
	}

</script>

<jsp:include page="/view/layout/footer.jsp" />