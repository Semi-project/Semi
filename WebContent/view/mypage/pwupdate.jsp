<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js">
	
</script>

<script type="text/javascript">
	function tocheckpw2() {
		var pw = document.getElementById("userpw").value;
		var pwck = document.getElementById("PwCheck").value;

		if (pw != pwck) {
			document.getElementById('pwsame').innerHTML = '비밀번호가 틀렸습니다. 다시 입력해 주세요';
			return false;
		}
	}
</script>
<style>
#top {
	margin-top: 100px;
}

th {
	background: #ffeff4;
	fonti-size: 12px;
	text-align: left;
	border-top: 1px solid #ccc;
	padding: 5px 10px;
}

td {
	font-size: 12px;
	border-top: 1px solid #ccc;
	padding: 5px 10px;
	color: #000000;
}

table {
	border-bottom: 1px solid #ccc;
}

input {
	vertical-align: middle;
	background: #f2f2f2;
	margin-right: 3px;
}

select {
	width: 70px;
}

div.contentsTop {
	overflow: hidden;
	position: relative;
	height: 55px;
	margin-bottom: 40px;
}

ul.location {
	float: right;
	list-style: none;
}

div.mypage {
	position: relative;
	color: #fff;
	height: 115px;
	background-color: #F3969A;
	height: 115px;
	display: block;
	padding-left: 20px;
	margin-bottom: 2px;
	overflow: hidden;
	width: 180px;
	z-index: 4;
	top: 0;
	left: 0;
	margin-left: 10px;
	text-align: center;
}

div.side {
	margin-left: 10px;
	margin-top: 30px;
}

ul#side-navi {
	width: 200px;
	text-indent: 10px;
	margin-left: 10px;
}

ul#side-navi, ul#side-navi ul {
	margin: 0;
	padding: 0;
	list-style: none;
}

li.group {
	margin-bottom: 3px;
}

li.group div.title {
	height: 35px;
	line-height: 35px;
	background: #F3969A;
	cursor: pointer;
	text-align: center;
}

ul.side-navi-sub li {
	margin-bottom: 2px;
	height: 35px;
	line-height: 35px;
	background: #ffeff0;
	cursor: pointer;
}

ul.side-navi-sub li a {
	display: block;
	width: 100%;
	height: 100%;
	text-decoration: none;
	color: #000;
}

ul.side-navi-sub li:hover {
	background: #cf0;
}

div.row {
	display: inline-block;
}

div.text-center {
	display: inline-block;
	margin-left: 300px;
}
</style>




</head>
<body>

	<div class="row ">
		<div class="col-lg-2" style="margin-left: 20px; margin-top: 10px;">
			<div class="mypage">마이페이지</div>
			<div class="side">
				<ul id="side-navi">
					<li class="group">
						<div class="title">회원정보</div>
						<ul class="side-navi-sub">
							<li><a href="/mypage/check">개인정보수정</a></li>
							<li><a href="/mypage/adoption">입양신청내역</a>
							<li><a href="/mypage/deletcheck">회원탈퇴하기</a>
							<li><a href="/mypage/updatepasswordcheck">비밀번호변경</a>
							<li><a href="/mypage/charity">후원내역</a>
						</ul>
					</li>
					<li class="group">
						<div class="title">
							<a href="/qnaboard/list">1:1 문의</a>
						</div>
					</li>

				</ul>
			</div>
		</div>
	</div>

	<div class="text-center">

		<h1>비밀번호 변경</h1>
		<hr>

		<form action="/mypage/userpwupdate" onsubmit="return tocheckpw2()"
			data-ajax="false" method="post">

			<input type="hidden" id="userid" value="${memberView.userid }" /> <label
				for="userpw">비밀번호 </label> <input type="password" id="userpw"
				name="userpw" required><br> <label for="pck">비밀번호
				확인 </label> <input type="password" id="PwCheck" required><br>
			<p id="pwsame" style="color: red;"></p>

			<br>
			<br> <input type="submit" class="btn btn-info" value="변경하기">
			<input type="reset" class="btn btn-danger" value="취소">
		</form>


	</div>
</body>

</html>
<jsp:include page="/view/layout/footer.jsp" />
