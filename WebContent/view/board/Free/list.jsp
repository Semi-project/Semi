<%@page import="dto.board.Free_Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Semantic UI -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/css/SemanticUI/semantic.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/semantic.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/SemanticUI/semantic.css" /> 
   
<%
List<Free_Board> blist = (List) request.getAttribute("boardlist");
%>


<jsp:include page="/view/layout/header.jsp" />
<div>
&nbsp;
</div>
<div align="center">


<form id="_formTable">

<table  style="width: 1300px" class="ui single line table" >
<col width="250px"><col width="350px"><col width="200px"><col width="200px"><col width="300px">
		<tr>
			<th style="font-weight: bold; padding-left:15px">게시글번호</th>
			<th style="font-weight: bold; padding-left:15px">제목</th>
			<th style="font-weight: bold; padding-left:15px">작성자</th>
			<th style="font-weight: bold; padding-left:15px">조회수</th>
			<th style="font-weight: bold; padding-left:15px">날짜</th>
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
			<td style="padding-left:15px">${i.insert_Dat}</td>
		</tr>
		</c:forEach>
	</table>
	
	<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber}">
</form>
	
	<c:if test="${login ne null }">
	<br>
	<button class="ui active button" onclick="goWriteBtn()">
  	<i class="write icon"></i>
  	글쓰기
	</button>
	
		<!-- <input type="button" class="primary ui button" value="글쓰기" onclick="goWriteBtn()"> -->
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
			<input type="text" style="width: 200px; height: 50px" id ="search" name="userid">
		</div>
	</td>
	<td>작성일</td>
	<td>
		<div class="ui input">
			<input type="date" style="width: 200px; height: 50px">
		</div>
	</td>
	<td>
	제목
	</td>
	<td>
		<div class="ui input">
		<input type="text" style="width:200px; height: 50px">
		</div>
	</td>
</tr>
</table>

	<button class="ui pink basic button" id=search name="userid" value='submit' >검색</button>
	<button class="ui black basic button" >초기화</button>
</div>
<script>
function goPage(pageNumber) {
	   $("#_pageNumber").val(pageNumber);
	   

	   $("#_formTable").attr({"target":"_self","action":"/freeboard/list"}).submit();
	   
	    
	}

</script>

<jsp:include page="/view/layout/footer.jsp" />