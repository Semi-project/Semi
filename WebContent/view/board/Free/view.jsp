<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>


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
<td class="info">닉네임</td><td colspan="2">[추후 추가]</td>
</tr>

<tr><td class="info">본문</td><td colspan="4">${freeboardService.content }</td></tr>

<tr>
<td class="info">조회수</td><td>${freeboardService.hit }</td>
<td class="info">추천수</td><td>[추후 추가]</td>
</tr>

<tr>
<td class="info">작성일</td><td colspan="4">${freeboardService.insert_Dat }</td>
<td class="info"><a href="/freeboardfile/download?fileno=${fileno.fileno }"/>첨부파일</td><td colspan="4">
</tr>

</table>
</div>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary"><a href="list">목록</a></button>
	<button id="btnUpdate" class="btn btn-info"><a href="update">수정</a></button>
	<button id="btnDelete" class="btn btn-danger"><a href=delete">삭제</a></button>
</div>
</div>

</body>
</html>