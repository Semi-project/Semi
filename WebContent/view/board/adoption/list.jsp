<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/view/layout/header.jsp" />
<style>
.adoptimg {
	width: 480px;
	height: 300px;
}

div.btnwrite {
	float: right;
}

#container {
	width: 960px;
	margin: 0 auto;
	text-align: center;
}

.navtab {
	list-style: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
}
/* Float the list items side by side */
.navtab li {
	float: left;
}
/* Style the links inside the list items */
.navtab li a {
	display: inline-block;
	color: #000;
	text-align: center;
	text-decoration: none;
	padding: 14px 16px;
	font-size: 17px;
	transition: 0.3s;
}
/* Style the tab content */
.tabcontent {
	display: none;
	background-color: #ffedf7;
	padding: 6px 12px;
	color: black;
	height: 220px;
}

ul.navtab li.current {
	background-color: #ffedf7;
	color: #222;
}

.tabcontent.current {
	display: block;
}
</style>
<script>
	$(function() {
		$('ul.navtab li').click(function() {
			var activeTab = $(this).attr('data-tab');
			$('ul.navtab li').removeClass('current');
			$('.tabcontent').removeClass('current');
			$(this).addClass('current');
			$('#' + activeTab).addClass('current');
		})
	});

	$(document).ready(function() {
		$("#btnwrite").click(function() {
			$(location).attr("href", "/adoption/form");
		});

	});
</script>
<div class="wrapper">
	<div class="row">
		<div class="col-lg-4">여기는 왼쪽;</div>
		<div class="col-lg-4">
			<div class="col-lg-12 col-md-12">
				<div class="adoptimg">
					<img src="/img/adoption/adopt.jpg" class="img-fluid">
				</div>
			</div>
			<div class="col-lg-12 col-md-12 g-pa-20">
				<p
					class="g-font-size-48 g-line-height-1_5 g-mt-10 g-mt-60--lg g-mb-30"
					style="color: #88d2f7;">
					사지말고,<br>입양하세요
				</p>
				<p class="g-color-white">
					누구든지 돈을 주고 동물을 산다면<br>그 이면에는 죽을때까지 고통 받아야 하는<br> 또 다른
					생명들이 존재해야 하는 것을<br> 기억해 주세요.
				</p>
			</div>
			<div class="col-lg-12">
				<div id="container">
					<ul class="navtab">
						<li class="current" data-tab="tab1"><a href="#">입양신청</a></li>
						<li data-tab="tab2"><a href="#">입양절차</a></li>
						<li data-tab="tab3"><a href="#">임시보호</a></li>
						<li data-tab="tab4"><a href="#">개인구조</a></li>
					</ul>
					<div id="tab1" class="tabcontent current">
						<h3>입양신청</h3>
						<ul>
							<li>입양신청 시 카라 입양원칙에 동의 하셔야 합니다.</li>
							<li>[입양신청하기]게시판의 입양신청서를 제출해주셔야 등록이 됩니다.</li>
							<li>입양동의서를 꼼꼼히 확인해주시고 모든 항목들에 동의하신다면 입양신청서를 작성해주세요.</li>
							<li>맞음비는 유기동물 치료비, 동물보호소 후원금으로 전액 사용됩니다.</li>
						</ul>

					</div>

					<div id="tab2" class="tabcontent">
						<h3>입양절차</h3>
						<p>새로운 가족을 입양하는 일입니다. 소요기간이 길어지더라도 양해부탁드립니다.</p>
						<li>댕냥이 입양동의서 동의 후, 입양신청 양식을 빠짐없이 기입해주세요.</li>
						<li>미성년자는 보호자의 동의 및 직접적인 보호자와의 인터뷰 절차를 거치게 됩니다.</li>
						<li>입양비는 7만원이며 전액 유기동물의 치료비와 사설보호소 후원금으로 사용됩니다.</li>
						<li>입양 시에는 신분증 사본과 도장을 지참하셔야 합니다.</li>
						<li>입양이 결정되면 담당자가 직접 신청자의 댁을 방문하게 됩니다.(서울, 경기지역 거주자 분들께 입양의
							우선권이 있습니다.)</li>
						<li>입양된 이후에 입양인은 반드시 단체의 모니터링에 응해주셔야 합니다.</li>
					</div>

					<div id="tab3" class="tabcontent">
						<h3>임시보호</h3>
						<p>
							아직 입양이 망설여지시거나 기존에 있던 동물과의 관계가 걱정이 되신다면 임시보호를 신청해보세요.<br> 입양
							담당자와의 지속적인 상담 후 입양을 결정하셔도 좋습니다.<br> 또한 사람을 경계하거나 사설보호소에서 구조된
							동물은 임시보호가정에서의 보살핌이 추후 입양에 긍정적인 영향을 가져올 수 있습니다.<br> 몸과 마음에 상처
							입은 동물들이 가정에서 따뜻한 보살핌을 받고 상처가 치유되기도 합니다.
						</p>
					</div>

					<div id="tab4" class="tabcontent">
						<h3>개인구조</h3>
						<p>
							단체에서 구조한 동물들 외에도 개인이 구조하여 보호하고 있는 유기동물들도 가족을 기다리고 있습니다.<br>

							단체에 신청서를 제출하신 분 중 선별하여 개인구조 공간에 정보를 게시하고 있으며, 개인구조의 입양절차와 신청은 단체의
							기준과 다릅니다.<br> 게시글 내의 구조자와 직접 상담하여 입양을 결정하시면 됩니다. 유기동물 입양홍보가
							필요하신 분은 ‘입양홍보신청서’를 작성해주세요.
						</p>
					</div>
				</div>
				<!-- endof navtab -->
			</div>
			<div class="col-lg-12">
				<div class="btnwrite">
					<c:if test="${member.role_id ne 1}">
						<button id="btnwrite">글쓰기</button>
					</c:if>
				</div>
			</div>
		</div>
		<div class="col-lg-4">-------------${meber.userid };------</div>
	</div>
</div>

<jsp:include page="/view/layout/footer.jsp" />