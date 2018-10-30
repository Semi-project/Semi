<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />

<div class="container">

<h3>게시글 상세보기</h3>
<hr>

<div>
<table class="table table-bordered" border="1">
<tr>
<td class="info">글번호</td><td>${freeboardService.boardno }</td>
<td class="info">제목</td><td colspan="2">${freeboardService.title }</td>
</tr>

<tr>
<td class="info">아이디</td><td>${freeboardService.userid }</td>
<td class="info"><a href="/freeboardfile/download?fileno=${fileno.fileno }"/>첨부파일</td><td colspan="4">
</tr>

<tr><td class="info">본문</td><td colspan="4">${freeboardService.content }</td></tr>

<tr>
<td class="info">조회수</td><td>${freeboardService.hit }</td>
<td class="info">추천수</td><td>[추후 추가]</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="4">${freeboardService.insert_Dat }</td>

</tr>

</table>
</div>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary"><a href="list">목록</a></button>
	<button onclick='location.href="/freeboard/update?boardno=${freeboardService.boardno }";'>수정</a></button>
	<button onclick='location.href="/freeboard/delete?boardno=${freeboardService.boardno }";'>삭제</button>
</div>
</div>

<jsp:include page="/view/layout/footer.jsp" />