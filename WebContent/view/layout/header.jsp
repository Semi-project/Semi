<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
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
		$("#btnwrite").click(function() {
			$(location).attr("href", "/Board/board/write/")
		});
		$("#btnback").click(function() {
			$(location).attr("href", "/Board/board/list")
		});

		$("#c4 li").click(function() {
			$(location).attr("href", "/Board/main")
		});
		$(".main_logo").click(function() {
			$(location).attr("href", "/Board/main")
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
	.main_logo {
		
	}
}

/* 중간 기기들 (데스크탑, 992px 이상) */
@media ( min-width : @screen-md-min) {
	...
}

/* 큰 기기들 (큰 데스크탑, 1200px 이상) */
@media ( min-width : @screen-lg-min) {
	...
}
/*테스트*/
body, div, ul, li, table, tr, td, th {
	margin: 0px;
	padding: 0px;
}

.main_logo {
	margin-left: -57px;
}

/*헤더 부분*/
ul, li {
	list-style: none;
}

/*네비게이션 전체 영역 제어*/
.nav {
	height: 35px;
	list-style: none;
	width: 100%;
	margin-top: 30px;
	background: #F3969A;
	font-size: 12px;
	color: #505050;
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
	z-index: 100;
}
</style>
</head>
<body>
	<header>
		<div class="header">
			<div class="container">
				<div class="row">
					<div class="col-lg-2">
						<div class="main_logo">
							<img src="/Board/image/logo.jpg">
						</div>
					</div>
					<div class="col-lg-10 text-center" id="main_nav">
						<div class="nav">
							<ul>
								<li class="navi_set">
									<div class="topnav" id="topnav_1">소개</div>
									<ul class="subnav">
										<li>오시는길</li>
										<li>공지사항</li>
									</ul>
								</li>
								<li class="navi_set">
									<div class="topnav" id="topnav_2">참여하기</div>
									<ul class="subnav">
										<li><a href="/adoption/list">입양하기</a></li>
										<li>입양보내기</li>
										<li>입양후기</li>
									</ul>
								</li>
								<li class="navi_set"><div class="topnav" id="topnav_3">후원하기</div>

									<ul class="subnav">
										<li>정기후원</li>
									</ul></li>
								<li class="navi_set"><div class="topnav" id="topnav_4">고객센터</div>
									<ul class="subnav">
										<li>제보게시판</li>
										<li>자유게시판</li>
									</ul></li>
							</ul>

						</div>
					</div>
					<div class="col-lg-4">dddd</div>
				</div>
			</div>
		</div>
	</header>
	<div></div>
	<div></div>
	<div></div>