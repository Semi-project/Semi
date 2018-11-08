<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Controll", "no-cache");
%>

<jsp:include page="/view/layout/header.jsp" />



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js">
	
</script>

<script type="text/javascript">
	$(document).ready(function() {
		// 	$("#btnUpdate").click(function(){
		// 		location.href="/mypage/update";
		// 	})

		$("#btnCancel").click(function() {
			history.go(-1);
		})

		$("#btnUpdate").click(function() {

			$("form").submit();

		})
	});
</script>
<style>
#top {
	margin-top: 100px;
}

#join_frm {
	
}

#join_frm .bar {
	width: 30px;
	margin: 25px 0 20px 0;
	height: 2px;
	background: #283444;
}

#join_frm .join_title {
	font-size: 20px;
	color: #283444;
}

#join_frm .join_table {
	width: 100%;
	border-top: 1px solid #e3e3e3;
	margin: 20px 0 30px 0;
}

#join_frm .join_table th {
	width: 196px;
	background: #f7f7f7;
	color: #6d6e72;
	height: 40px;
	border-bottom: 1px solid #e3e3e3;
	font-weight: normal;
}

#join_frm .join_table td {
	padding: 7px 0 7px 30px;
	color: #6d6e72;
	border-bottom: 1px solid #e3e3e3;
}

#join_frm .join_table td input[type=radio] {
	vertical-align: -2px;
}

#join_frm .join_table td input[type=text], #join_frm .join_table td input[type=password]
	{
	height: 23px;
	border: 1px solid #ececec;
	line-height: 23px;
	color: #6d6e72;
	padding-left: 10px;
	width: 225px;
	margin-right: 10px;
}

#join_frm .join_table td a {
	height: 25px;
	width: 75px;
	background: #283444;
	line-height: 25px;
	text-align: center;
	display: inline-block;
	color: #fff;
	font-size: 12px;
}

#join_frm .join_table td span {
	line-height: 25px;
	font-size: 11px;
}

#join_frm .join_table td select {
	height: 25px;
	line-height: 25px;
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

div.text-center {
	display: inline-block;
	margin-left: 300px;
}

div.row {
	display: inline-block;
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




		<h1>회원정보 수정</h1>
		<hr>


		<form action="update" method="post" id="join_frm">
			<table class="join_table">
				<tr>
					<th>아이디</th>
					<td><input name="userid" value="${memberView.userid }"
						readonly="readonly"  /></td>
				</tr>
				<tr>
					<th>성별</th>
					<td><input type="text" name="gender"
						value="${memberView.gender }" readonly="readonly" /></td>
				</tr>

				<tr>

					<th>생일</th>
					<td><input type="text" name="userbirth"
						value="${memberView.userbirth }" readonly="readonly" /></td>
				</tr>

				<tr>
					<th>이메일</th>
					<td><input type="text" name="email"
						value="${memberView.email }" /></td>
				</tr>
				<tr>
					<th>휴대전화</th>
					<td><input type="text" name="smartPhone"
						value="${memberView.phone }" /></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input id="address" type="text" name="address"
						value="${memberView.address }"  rows=10 cols=10  /></td>
				</tr>
			</table>
			<button type="button" id="btnUpdate" class="btn btn-info">수정
				적용</button>
			<button type="button" id="btnCancel" class="btn btn-danger">취소</button>

		</form>
	</div>

	<div></div>


</body>
</html>


<jsp:include page="/view/layout/footer.jsp" />