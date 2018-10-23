<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="/view/layout/header.jsp" />

<div style="text-align: center;">
	<!-- 비로그인상태 -->
	<c:if test="${not login }">
		<strong>로그인이 필요합니다</strong>
		<br>
		<button onclick='location.href="/member/login";'>로그인</button>
		<button onclick='location.href="/member/join";'>회원가입</button>
	</c:if>

	<!-- 로그인상태 -->
	<c:if test="${login }">
		<strong>${nick } 님, 환영합니다</strong>
		<br>
		<button onclick='location.href="/board/paginglist";'>게시판 가기</button>
		<button onclick='location.href="/member/logout";'>로그아웃</button>
	</c:if>
</div>

<jsp:include page="/view/layout/footer.jsp" />