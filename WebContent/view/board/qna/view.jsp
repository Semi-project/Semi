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
	$("#btnCommInsert").click(function(){
		$form = $("<form>").attr({
			action: "/qnacomment/write",
			method: "post"
		}).append(
				$("<input>").attr({
					type:"hidden",
					name:"boardno",
					value:"${qnaView.boardno}"
				})
			).append(
				$("<textarea>")
				.attr("name","content")
				.css("display","none")
				.text($(qna_commentContent).val())
			);
		$(document.body).append($form);
		$form.submit();
	
});

	function deleteComment( commentNo ) {
		$.ajax({
			type: "post"
			, url: "/qnacomment/delete"
			, dataType: "json"
			, data: {
				commentNo: commentNo
			}
		,	success: function(data){
			if(data.success){
			
				$("[data-commentno='"+commentNo+"']").remove();
			}else{
				alert("댓글 삭제 실패")
			}
		}
		, error: function(){
			console.log("error");
		}
	});
  }

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
<!-- <td class="info">닉네임</td><td colspan="2">[추후 추가]</td> -->
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


<div>

<hr>

<div class="form-inline text-center">
	<input type="text" size="10" class="form-control"
			id="qna_commentWriter"
			value="${sessionScope.userid }" readonly="readonly"/>
	<textarea rows="2" cols="60"
			class="form-control" id="qna_commentContent"></textarea>
	<button id="btnCommInsert" class="btn">입력</button>				
</div>

<table class="table table-striped table-hover table-condensed">
<thead>
<tr>
	<th style="width: 5%;">번호</th>
	<th style="width: 10%;">작성자</th>
	<th style="width: 50%;">댓글</th>
	<th style="width: 20%;">작성일</th>
	<th style="width: 5%;"></th>
</tr>
</thead>
<tbody	id="commentBody">
<c:forEach items="${qna_commentList }" var="qna_comment">
<tr data-commentNo="${qna_comment.commentNo }">
		<td>${qna_comment.rnum }</td>
		<td>${qna_comment.userid }</td>	
		<td>${qna_comment.content }</td>
		<td><fmt:formatDate value="${qna_comment.insertdate}" pattern="yy-MM-dd hh:mm:ss" /></td>
		<td>
		<c:if test="${sessionScope.userid eq qna_comment.userid }">
		<button class="btn btn-default btn-xs"
			onclick="deleteComment(${qna_comment.commentNo });">삭제</button>
		</c:if>
	</td>	 
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>


<jsp:include page="/view/layout/footer.jsp" />
















