<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Semantic UI -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/css/SemanticUI/semantic.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/semantic.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/SemanticUI/semantic.css" /> 

<!-- Semantic UI -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/css/SemanticUI/semantic.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/semantic.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/SemanticUI/semantic.css" />


<jsp:include page="/view/layout/header.jsp" />

<div class="container">


	<script type="text/javascript">
$(document).ready(function() {
	$("#btnCommInsert").click(function(){

		$form = $("<form>").attr({
			action: "/freeboardcomment/insert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"boardno",
				value:"${freeboardService.boardno }"
			})
		).append(
			$("<textarea>")
				.attr("name", "content")
				.css("display", "none")
				.text($("#commentContent").val())
		);
		$(document.body).append($form);
		$form.submit();
	});

});
function deleteComment( comment_no ) {
	$.ajax({
		type: "post"
		, url:"/freeboardcomment/delete"
		, dataType: "json"
		, data: {
			comment_no: comment_no
		}
		, success: function(data){
			if(data.success) {
				
				$("[data-comment_no='"+comment_no+"']").remove();
				
			} else {
				alert("댓글 삭제 실패");
			}
		}
		, error: function() {
			console.log("error");
		}
	});
	
	if(${recommend }) { //추천상태
		$("#btnRecommend")
			.addClass("btn-danger")
			.removeClass("btn-primary")
			.text("추천 취소");
	} else {	//추천 안 한상태
		$("#btnRecommend")
			.addClass("btn-primary")
			.removeClass("btn-danger")
			.text("추천");
	}
	// 추천 버튼 클릭 이벤트 처리
	$("#btnRecommend").click(function() {
		$.ajax({
			type: "get"
			, url: "/freeboard/recommend"
			, dataType: "json"
			, data: {
				boardno: '${freeboardService.boardno }'
			}
			, success: function(data) {
// 				console.log("success");
				console.log(data);
				
				//추천 버튼 색상 변경
				$("#btnRecommend")
					.toggleClass("btn-primary")
					.toggleClass("btn-danger");

				//추천수 갱신
				$("#recommend").text(data.recommend);
				
				//추천 버튼 텍스트 변경
				if(data.result) {
					$("#btnRecommend").text("추천 취소");
				} else {
					$("#btnRecommend").text("추천");
				}
			}
			, error: function(e) {
// 				console.log("fail");
				
				console.log(e.responseText);
			}
		});
	});
}


</script>
<c:if test="${login }">
<button id="btnRecommend" class="btn pull-right" style="margin-top: 30px;"></button>
</c:if>
	<h3>게시글 상세보기</h3>
	<hr>

	<div>
		<table class="table table-bordered" border="1">
			<tr>
				<td class="info">글번호</td>
				<td>${freeboardService.boardno }</td>
				<td class="info">제목</td>
				<td colspan="2">${freeboardService.title }</td>
			</tr>

			<tr>
				<td class="info">아이디</td>
				<td>${freeboardService.userid }</td>
				<td class="info"><a
					href="/freeboardfile/download?fileno=${fileno.fileno }" />첨부파일</td>
				<td>${fileno.file_SaveName }
				<!--  
					정렬 : 부모번호, 단계, 깊이
					부모번호 정렬 
					
					댓글(1,0,0)
						댓댓글(1,1,1)
							댓댓댓글(1,2,2)
						댓댓글(1,3,1)
					댓글(2,0,0)
					댓글(3,0,0)
					댓글(4,0,0)
					
					1) 본문에다가 댓글쓸 때, (x,y,z) 	(5,0,0)
					2) 부모번호가 2번인 댓글에 댓글을 쓸때 (x,y,z) -> (2,1,1)
					3) 1번댓글에 댓글을 쓸 때 (x,y,z,) -> (1,3,1)
					
					
					
					step, depth 
					
						
				
				-->
				
				</td>
			</tr>

			<tr>
				<td class="info">본문</td>
				<td colspan="4">${freeboardService.content }</td>
			</tr>

			<tr>
				<td class="info">조회수</td>
				<td>${freeboardService.hit }</td>
				<td class="info">추천수</td><td id="recommend">${freeboardService.recommend }</td>
				
			</tr>

			<tr>
				<td class="info">작성일</td>
				<td colspan="4">${freeboardService.insert_Dat }</td>

			</tr>

		</table>
	</div>

	<div class="text-center">
		<button class="ui blue basic button">
			<a href="list">목록</a>
		</button>
		<c:if test="${userid eq freeboardService.userid}">
		<button class="ui green basic button"
			onclick='location.href="/freeboard/update?boardno=${freeboardService.boardno }";'>
			수정</a>
		</button>
		<button class="ui red basic button"
			onclick='location.href="/freeboard/delete?boardno=${freeboardService.boardno }";'>삭제</button>
			</c:if>
	</div>
</div>
<div>


	<!-- 댓글 처리 시작 -->
	<div>

		<hr>
		<!-- 댓글 입력 -->
		<div class="form-inline text-center">
			<input type="text" size="10" class="form-control" id="commentWriter"
				value="${sessionScope.userid }" readonly="readonly" />
			<textarea rows="2" cols="60" class="form-control" id="commentContent"></textarea>
			<button id="btnCommInsert" class="btn">입력</button>
		</div>
		<!-- 댓글 입력 end -->

		<!-- 댓글 리스트 -->
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
			<tbody id="commentBody">
				<c:forEach items="${commentList }" var="ii">
					<tr data-commentno="${ii.comment_no }">
					<td>${ii.rnum }</td>
						<td>${ii.userid }</td>
						<td>${ii.content }</td>
						<td><fmt:formatDate value="${ii.insert_dat }"
								pattern="yy-MM-dd" /></td> 
						<td><c:if test="${sessionScope.userid eq ii.userid }"> 
								<button class=" ui negative basic button btn btn-default btn-xs"
 								onclick="deleteComment(${ii.comment_no });">삭제</button>
		</c:if>
	</td>
					</tr> 
				</c:forEach>
			</tbody> 
		</table>
		<!-- 댓글 리스트 end -->

	</div>
	<!-- 댓글 처리 끝 -->

</div>

<jsp:include page="/view/layout/footer.jsp" />