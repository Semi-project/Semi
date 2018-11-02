<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>댕냥이</title>
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/main.css">
<!-- 자바 스크립트 -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<script>
	$(document).ready(function() {

		$(".main_logo").click(function() {
			$(location).attr("href", "/main")
		});
		$(".topnav").hover(function() { //마우스를 topnav에 오버시
			$(this).parent().find(".subnav").slideDown('normal').show(); //subnav가 내려옴.
			$(this).parent().hover(function() {
			}, function() {
				$(this).parent().find(".subnav").slideUp('fast'); //subnav에서 마우스 벗어났을 시 원위치시킴  
			});
		});

	});
</script>
<style>
/*테스트 */

/* Custom, iPhone Retina */
/* 작은 기기들 (태블릿, 768px 이상) */
@media ( min-width : @screen-sm-min) {
}

/* 중간 기기들 (데스크탑, 992px 이상) */
@media ( min-width : @screen-md-min) {
}

/* 큰 기기들 (큰 데스크탑, 1200px 이상) */
@media ( min-width : @screen-lg-min) {

	
}

ul.top_menu a {
	text-decoration: none;
	color: #999;
}

ul.top_menu {
	position: absolute;
	right: 10px;
	top: 20px;
}

ul.top_menu li {
	display: inline;
	padding-right: 30px;
}

.main_logo {
	maring: 0 auto;
	font-size: 100%;
	padding: 26px 0 17px;
	height: 64px;
	font-size: 100%;
	display: inline-bl
}

/*네비게이션 전체 영역 제어*/
.nav {
	position: relative;
	height: 35px;
	width: 100%;
	margin-top: 100px;
	font-size: 12px;
	color: #505050;
	background: #F3969A;
}

/*주메뉴 영역 제어*/
.topnav {
	text-align: center;
	width: 100px;
	height: 15px;
	cursor: pointer;
	font-weight: bold;
}

/*주메뉴 마우스 오버시*/
.topnav:hover {
	color: #d3d3d3;
}

/*주메뉴,서브메뉴 한묶음 제어*/
.navi_set {
	float: left;
	padding: 10px;
	position: relative;
	left: 250px;
}

/*서브메뉴 전체영역 제어*/
.subnav {
	position: absolute;
	left: 0;
	top: 35px;
	background: #FFF0F0;
	display: none;
	z-index: 50;
	top: 35px;
}

/*서브메뉴 한칸 제어*/
.subnav li {
	padding: 10px;
	width: 100px;
	text-align: center;
	border-bottom: 1px solid #dcdcdc;
	border-top: 1px solid #dcdcdc;
	cursor: pointer;
}

/*서브메뉴 마우스 오버시*/
.subnav li:hover {
	background: white;
}

#main_nav {
	/*    z-index: 40; */
	display: inline-block;
}

.dropdown>ul>li {
	display: inline-block;
	position: relative;
}

.dropdown>ul>li>ul {
	position: absolute;
	list-style-type: none;
	padding-left: 0px;
	display: none;
}

.dropdown>ul>li:hover>ul {
	display: block;
	z-index: 70;
	
}
</style>
</head>
<body>
	<div class="col-lg-12">
		<div class="col-lg-4">
			<header>
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					<div class="main_logo">
						<h1>
							<a href="/main"><img src="/img/main/logo.png" /></a>
						</h1>
					</div>
				</div>

			</header>
		</div>
		<div class="col-lg-4">
			<div class="col-lg-12"></div>

		</div>
		<div class="col-lg-4">
			<div class="dropdown">
				<ul class="top_menu">
					<!-- 비로그인상태 -->
					<c:if test="${not login }">
						<li><a href="/member/login">로그인</a></li>
						<li><a href="/member/join">회원가입</a></li>
					</c:if>
					<c:if test="${login }">
						<c:if test="${role_id ne 1}">
							<li>관리자</li>
							<li><a href="#">관리자페이지</a>
								<ul>
									<li><a href="/adoption/application/list">입양신청서</a></li>
									<li><a href="/adoption/send/list">입양보내기</a></li>
									<li><a href="/member/list">회원목록</a></li>
								</ul></li>
						</c:if>
						<c:if test="${role_id ne 0}">
							<li>일반회원</li>
						</c:if>
						<li><strong>${nick } 님, 환영합니다</strong></li>
						<li><a href="/member/logout">로그아웃</a></li>
						<li><a href="/mypage/view">마이페이지</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div></div>
		<div class="col-lg-12">
			<div class="nav">
				<ul>
					<li class="navi_set">
						<div class="topnav" id="topnav_1">소개</div>
						<ul class="subnav">
							<li><a href="/introduce/contact">오시는길</a></li>
							<li><a href="/noticeboard/list">공지사항</a></li>
						</ul>
					</li>
					<li class="navi_set">
						<div class="topnav" id="topnav_2">참여하기</div>
						<ul class="subnav">
							<li><a href="/adoption/application/insert">입양하기</a></li>
							<c:choose>
								<c:when test="${role_id eq 1}">
									<li><a href="/adoption/send/mlist"> 입양보내기</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/adoption/send/mlist"> 입양보내기</a></li>
								</c:otherwise>
							</c:choose>
							<li><a href="/review/list">입양후기</a></li>
						</ul>
					</li>
					<li class="navi_set">
						<div class="topnav" id="topnav_1">후원하기</div>
						<ul class="subnav">
							<li><a href="/charity/main">후원</a></li>
							<li><a href="/charity/foroneonone">1:1 후원</a></li>
						</ul>
					</li>
					<li class="navi_set"><div class="topnav" id="topnav_4">정보마당</div>
						<ul class="subnav">
							<li><a href="/qnaboard/paginglist">QnA게시판</a></li>
							<li><a href="/freeboard/list">자유게시판</a></li>
						</ul></li>
				</ul>

			</div>
		</div>
	</div>