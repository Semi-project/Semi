<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style>
#wrap {
	text-align: center;
}

.box {
	margin: 0 auto;
	width: 300px;
	height: 300px;
	background-color: #c6c6c6;
	display: inline-block;
	margin-top: 100px;
}

#site {
	background-image: url('/img/charity/web.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	transform: scale(1);
	-webkit-transform: scale(1);
	-moz-transform: scale(1);
	-ms-transform: scale(1);
	-o-transform: scale(1);
	transition: all 0.3s ease-in-out;
}

#animal {
	background-image: url('/img/charity/dogcat.jpg');
	background-size: cover;
	background-repeat: no-repeat;
	transform: scale(1);
	-webkit-transform: scale(1);
	-moz-transform: scale(1);
	-ms-transform: scale(1);
	-o-transform: scale(1);
	transition: all 0.3s ease-in-out;
}

#animal:hover {
	transform: scale(1.2);
	-webkit-transform: scale(1.2);
	-moz-transform: scale(1.2);
	-ms-transform: scale(1.2);
	-o-transform: scale(1.2);
}

#site:hover {
	transform: scale(1.2);
	-webkit-transform: scale(1.2);
	-moz-transform: scale(1.2);
	-ms-transform: scale(1.2);
	-o-transform: scale(1.2);
}

.charity {
	margin: 0;
	width: 150px;
	height: 100px;
	padding: 0;
	display: inline-flex;
	margin-top: 50px;
	width: 150px;
}

#step1 {
	background-color: #F3969A;
}

#step2 {
	background-color: #c6c6c6;
}

#step3 {
	background-color: #c6c6c6;
}
</style>
<script>
	$(document).ready(function() {
		$("#site").click(function() {
			$.ajax({
				type : "GET",
				url : "/charity/forsite",
				dataType : "text",
				error : function() {
					alert('통신실패!!');
				},
				success : function(data) {
					$('#wrap').html(data);
				}

			});

		});
		$("#animal").click(function() {

			$.ajax({
				type : "GET",
				url : "/charity/animal",
				dataType : "text",
				error : function() {
					alert('통신실패!!');
				},
				success : function(data) {
					$('#wrap').html(data);
				}

			});
		});
	});
</script>
</head>
<body>
	<div class="row ">
		<div class="col-lg-2"></div>
		<div class="col-lg-8 center">




			<div id="wrap">
				<div class="col-lg-12 center">
					<div class="charity" id="step1">후원종류 선택</div>
					<div class="charity" id="step2">후원자정보 입력 및 결제</div>
					<div class="charity" id="step3">후원 완료</div>
				</div>

				<div class="box" id="site">사이트 후원</div>
				<div class="box" id="animal">동물 후원</div>

			</div>
		</div>
		<div class="col-lg-2"></div>

	</div>

	<jsp:include page="/view/layout/footer.jsp" />