<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />


<style>
.charity {
	margin: 0;
	width: 150px;
	height: 100px;
	padding: 0;
	display: inline-flex;
	margin-top: 50px;
}

#step1 {
	background-color: #c6c6c6;
}

#step2 {
	background-color: #c6c6c6;
}

#step3 {
	background-color: #F3969A;
}

.home {
	border: 1px solid #ed646a;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	font-size: 12px;
	font-family: arial, helvetica, sans-serif;
	padding: 10px 10px 10px 10px;
	text-decoration: none;
	display: inline-block;
	text-shadow: -1px -1px 0 rgba(0, 0, 0, 0.3);
	font-weight: bold;
	color: #FFFFFF;
	background-color: #F3969A;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#F3969A),
		to(# #F3969A));
	background-image: -webkit-linear-gradient(top, #F3969A, # #F3969A);
	background-image: -moz-linear-gradient(top, #F3969A, # #F3969A);
	background-image: -ms-linear-gradient(top, #F3969A, # #F3969A);
	background-image: -o-linear-gradient(top, #F3969A, # #F3969A);
	background-image: linear-gradient(to bottom, #F3969A, # #F3969A);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,
		startColorstr=#F3969A, endColorstr=##F3969A);
}

.home:hover {
	border: 1px solid #e93a42;
	background-color: #ee686e;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ee686e),
		to(#000000));
	background-image: -webkit-linear-gradient(top, #ee686e, #000000);
	background-image: -moz-linear-gradient(top, #ee686e, #000000);
	background-image: -ms-linear-gradient(top, #ee686e, #000000);
	background-image: -o-linear-gradient(top, #ee686e, #000000);
	background-image: linear-gradient(to bottom, #ee686e, #000000);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,
		startColorstr=#ee686e, endColorstr=#000000);
}
</style>
</head>
<body>
	<div class="row ">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="col-lg-12">
				<div class="charity" id="step1">후원종류 선택</div>
				<div class="charity" id="step2">후원자정보 입력 및 결제</div>
				<div class="charity" id="step3">후원 완료</div>
			</div>
			<div class="col-lg-12">
				<img src="/img/charity/finish.PNG" />
			</div>
			<div class="col-lg-12 text-center">
			<button class="home">홈으로</button>
			</div>
		</div>
	</div>

	<jsp:include page="/view/layout/footer.jsp" />