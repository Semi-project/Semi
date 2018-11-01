<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 자바 스크립트 -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<style>
#menu_box>div {
	float: left;
	width: 150px;
	height: 150px;
	background-color: red;
	margin: 10px 10px;
}

#menu_box {
	height: 1%;
	display: inline-block;
	text-align: center;
}

#menu_box:after {
	content: ".";
	display: block;
	clear: both;
	visibility: hidden;
	height: 0;
}
</style>
<script>
$(document).ready(function() {
	$("#back").click(function(){
		history.go(-1);
	});
});
</script>
</head>
<body>
	<div id="menu_box">
		<div id="menu1">
			<a href="/adoption/application/list">입양 신청 리스트</a>
		</div>
		<div id="menu2">입양 보내기 리스트</div>

		<br>
		<button id="back">돌아가기</button>


	</div>

</body>
</html>