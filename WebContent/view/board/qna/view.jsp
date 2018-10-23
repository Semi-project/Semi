<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Controll", "no-cache"); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	var result = '${result}';
	var successMsg = '${successMsg}';
	var failMsg = '${failMsg}';
	
	if(result == 'success'){
		alert(successMsg);
	}
	else if(result == 'fali'){
		alert(failMsg);
	}
	
	$("#btnList").click(function() {
		$(location).attr("href", "/qnaboard/paginglist");
	});
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/qnaboard/update?boardno=${qnaView.boardno }");
	});
	$("#btnDelete").click(function() {
		
		var userid = '${qnaView.userid}'; // 글을 작성한 사용자 
		var loginId = '${loginId}'; // 실제로 로그인 한 사용자 
		
		if(userid == loginId){
			$(location).attr("href", "/qnaboard/delete?boardno=${qnaView.boardno}&userid=${qnaView.userid}");
		}
		else{
			alert("글을 삭제할수 없습니다.");
			return false;
		}
		
	});
});
</script>

<div class="container">

<h3>QnA 상세보기</h3>
<hr>

<form id="qnaForm" name="qnaForm" method="POST">
<input type="hidden" id="loginId" name="loginId" value="${loginId}">
<div>
<table class="table table-bordered">
<tr>
<td class="info">글번호</td><td>${qnaView.boardno }</td>
<td class="info">제목</td><td colspan="2">${qnaView.title }</td>
</tr>

<tr>
<td class="info" id="userid" name="userid">아이디</td><td>${qnaView.userid }</td>
<td class="info">닉네임</td><td colspan="2">[추후 추가]</td>
</tr>

<tr><td class="info">본문</td><td colspan="4">${qnaView.content }</td></tr>


<tr>
<td class="info">작성일</td><td colspan="4">${qnaView.insert_Dat }</td>
</tr>
</table>
</div>
 </form>
<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>
	<button id="btnUpdate"  class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>
</div>

<jsp:include page="/view/layout/footer.jsp" />
















