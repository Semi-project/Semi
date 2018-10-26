<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />
<style>
/* 폰트안보이게(배경깔때) */
.blind {
	visibility: hidden;
	overflow: hidden;
	position: absolute;
	top: 0;
	left: 0;
	width: 1px;
	height: 1px;
	font-size: 0;
	line-height: 0
}

/* 메인 타이틀 */
.title_area {
	float: none;
	position: relative;
	*height: 34px;
	*zoom: 1
}

.title_area:after {
	display: block;
	clear: both;
	content: ''
}

.obj_section .title_area h4 {
	float: left;
	height: 21px;
	margin: 5px 10px 0 0;
	background: url(htp://제목이배경으로 깔리는 이미지 주소) no-repeat -9999px -9999px
}

.obj_section .title_area h4.hh_photo {
	width: 54px;
	background-position: 0 -88px
}

/* 갤러리 */
.sub_photo li {
	float: left;
	position: relative;
	width: 160px;
	margin-right: 8px;
	margin-bottom: 30px;
	display: block;
}

.sub_photo li.last {
	margin-right: 0
}

.sub_photo li a strong {
	display: block;
	overflow: hidden;
	padding-right: 8px;
	white-space: nowrap;
	text-overflow: ellipsis;
	font-size: 14px;
}

.sub_photo li img {
	width: 160px;
	height: 120px;
}

.sub_photo p.thmb {
	margin-bottom: 3px
}

.sub_photo .tx_brief {
	margin-top: 2px;
	padding-right: 13px
}

.sub_photo .tx_brief a {
	color: #666
}
/* //갤러리 */

/* 각게시판 페이징 */
.paginate {
	padding: 16px 0 0;
	text-align: center;
}

.paginate a, .paginate strong {
	display: inline-block;
	position: relative;
	_width /**/: 17px;
	margin-right: 1px;
	padding: 2px 4px 3px;
	border: 1px solid #f5f2ed;
	color: #666;
	font-family: Verdana;
	font-size: 11px;
	font-weight: bold;
	line-height: normal;
	text-decoration: none
}

.paginate strong {
	border: 1px solid #928070;
	color: #f52d2d !important
}

.paginate .pre {
	margin-right: 6px;
	padding: 5px 6px 2px 14px;
	_padding-bottom: 1px;
	background:
		url(http://static.naver.com/common/paginate/bu_pg3_l_off.gif)
		no-repeat 6px 5px !important;
	letter-spacing: -1px
}

.paginate .next {
	margin-left: 4px;
	padding: 5px 14px 2px 6px;
	_padding-bottom: 1px;
	background:
		url(http://static.naver.com/common/paginate/bu_pg3_r_off.gif)
		no-repeat 62px 5px !important;
	letter-spacing: -1px
}

.paginate a.pre {
	background: url(http://static.naver.com/common/paginate/bu_pg3_l_on.gif)
		no-repeat 6px 6px !important
}

.paginate a.next {
	background: url(http://static.naver.com/common/paginate/bu_pg3_r_on.gif)
		no-repeat 33px 6px !important
}

.paginate .pre, .paginate .next {
	display: inline-block;
	position: relative;
	top: 0;
	_top: -2px;
	_width: 75px;
	border: 1px solid #b4b4b4;
	color: #ccc;
	font-family: '돋움', Dotum;
	font-size: 11px;
	line-height: normal
}

.paginate a.pre, .paginate a.next {
	color: #565656
}

.paginate a:hover {
	border: 1px solid #e9e9e9;
	color: #FFF;
	background-color: #939393 !important
}
/* //각게시판 페이징 */
#scroll {
	position: absolute;
	left: 50%;
	float: left;
	width: 80px;
	margin-left: 550px;
	margin-top: 8px;
}

#scroll .btn_f01 {
	margin-left: 10px
}

#scroll .btn_f02 {
	margin: 12px 0 0 17px
}

#scroll .btn_f03 {
	margin: 7px 0 0 17px
}

#scroll img {
	border: none;
	vertical-align: top
}
</style>

<!--jQuery에 animate로 따라다니는 움직힘 구현-->

<script type="text/javascript">
	$(window).scroll(function() {
		$('#scroll').animate({
			top : $(window).scrollTop() + "px"
		}, {
			queue : false,
			duration : 350
		});

	});

	//배너 클릭시 이벤트처리
	$('#scroll').click(function() {

		//배너 움직임 설정
		$('#scroll').animate({
			top : "+=15px",
			opacity : 0
		}, "slow");
	});

	//body에 배너 삽입 - 다른 내용 넣을때는 이부분에 넣어서 처리

	$(document)
			.ready(
					function() {
						$(
								'<div id="scroll"><ul><li><img src="https://www.ekara.org/front/img/parttake/adopt.jpg"></li><ul></div>')
								.prependTo(document.body)
					});
</script>

<p></p>
<div class="col-lg-2"></div>
<!-- 갤러리시작 -->
<div class="col-lg-8">
	<div class="sub_photo">
		<div class="title_area">
			<h4 class="hh_photo">
				<strong class="blind">갤러리</strong>
			</h4>
		</div>
		<ul>
			<li>
				<p class="thmb">
					<a href="#"> <img
						src="https://www.ekara.org/front/img/parttake/adopt.jpg" alt="">
					</a>
				</p> <a href="#"><strong>제목부분</strong></a>
				<p class="tx_brief">2014-01-28</p>
			</li>
			<li>
				<p class="thmb">
					<a href="#"> <img
						src="https://www.ekara.org/front/img/parttake/adopt.jpg" alt="">
					</a>
				</p> <a href="#"><strong>제목부분</strong></a>
				<p class="tx_brief">2014-01-28</p>
			</li>
			<li>
				<p class="thmb">
					<a href="#"> <img
						src="https://www.ekara.org/front/img/parttake/adopt.jpg" alt="">
					</a>
				</p> <a href="#"><strong>제목부분</strong></a>
				<p class="tx_brief">2014-01-28</p>
			</li>
			<li>
				<p class="thmb">
					<a href="#"> <img
						src="https://www.ekara.org/front/img/parttake/adopt.jpg" alt="">
					</a>
				</p> <a href="#"><strong>제목부분</strong></a>
				<p class="tx_brief">2014-01-28</p>
			</li>
			<li>
				<p class="thmb">
					<a href="#"> <img
						src="https://www.ekara.org/front/img/parttake/adopt.jpg" alt="">
					</a>
				</p> <a href="#"><strong>제목부분</strong></a>
				<p class="tx_brief">2014-01-28</p>
			</li>
			<li>
				<p class="thmb">
					<a href="#"> <img
						src="https://www.ekara.org/front/img/parttake/adopt.jpg" alt="">
					</a>
				</p> <a href="#"><strong>제목부분</strong></a>
				<p class="tx_brief">2014-01-28</p>
			</li>
			<li>
				<p class="thmb">
					<a href="#"> <img
						src="https://www.ekara.org/front/img/parttake/adopt.jpg" alt="">
					</a>
				</p> <a href="#"><strong>제목부분</strong></a>
				<p class="tx_brief">2014-01-28</p>
			</li>
			<li>
				<p class="thmb">
					<a href="#"> <img
						src="https://www.ekara.org/front/img/parttake/adopt.jpg" alt="">
					</a>
				</p> <a href="#"><strong>제목부분</strong></a>
				<p class="tx_brief">2014-01-28</p>
			</li>
			<li>
				<p class="thmb">
					<a href="#"> <img
						src="https://www.ekara.org/front/img/parttake/adopt.jpg" alt="">
					</a>
				</p> <a href="#"><strong>제목부분</strong></a>
				<p class="tx_brief">2014-01-28</p>
			</li>

			<!-- li가 게시판 1개글입니다 보일 갤러리 갯수만큼 li반복합니다.-->
		</ul>
	</div>
	<!-- 갤러리끝 -->
</div>
<div class="col-lg-2"></div>

<div class="col-lg-12">
	<!-- 페이징시작 -->
	<div class="paginate">
		<a class="pre" href="#">이전</a> <a href="#">1</a> <a href="#">2</a> <a
			href="#">3</a> <a href="#">4</a> <strong>5</strong>
		<!-- 선택된페이지 -->
		<a href="#">6</a> <a href="#">7</a> <a href="#">8</a> <a href="#">9</a>
		<a href="#">10</a> <a class="next" href="#">다음</a>
	</div>
</div>
<!-- 페이징끝 -->