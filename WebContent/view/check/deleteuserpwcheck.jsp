<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/view/layout/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>
	$(document).ready(function() {

		var result = '${result}';
		var msg = '${msg}'

		if (result == 'fail') {
			alert(msg);
		}

		$("#deletcheck").click(function() {
			$("form").submit();
		});
	});
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
	<div class="col-lg-8" align="center">
		<div id="top" class="">
			<form name="ckeckform" method="post" action="/mypage/deletcheck">
				<input type="hidden" name="userid" value="${userid}">
				<table>
					<tr>
						<td bgcolor="skyblue">비밀번호</td>
						<td><input type="password" name="userpw" maxlength="10"></td>
					</tr>
				</table>

				<br>
			</form>
			<button id="deletcheck">확인</button>
		</div>
	</div>

	<div class="col-lg-2"></div>



	<jsp:include page="/view/layout/footer.jsp" />