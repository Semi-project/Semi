<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Controll", "no-cache"); %> 

<jsp:include page="/view/layout/header.jsp" />



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js">
</script>

<script type="text/javascript">
$(document).ready(function(){
// 	$("#btnUpdate").click(function(){
// 		location.href="/mypage/update";
// 	})
	
	$("#btnCancel").click(function() {
		history.go(-1);
	})
	
	$("#btnUpdate").click(function(){
	
		$("form").submit();
	
	})
});


	

</script>

<script type="text/javascript">


</script>



</head>
<body>
<div style="text-align: center;">

<h1>회원정보 수정</h1>
<hr>

<form action="update" method="post"> 


<label>아이디 <input type="text" name="userid" value="${memberView.userid }" readonly="readonly"/></label><br>
<label>성별 <input type="text" name="gender" value="${memberView.gender }" readonly="readonly"/></label><br>
<label>생일 <input type="text" name="userbirth" value="${memberView.userbirth }" readonly="readonly"/></label><br>
<label>이메일<input type="text" name="email" value="${memberView.email }" /></label><br>
<label>휴대전화 <input type="text" name="smartPhone" value="${memberView.phone }" /></label><br>
<label>주소 <input id="address" type="text" name="address" value="${memberView.address }" /></label><br>

<button type="button" id="btnUpdate" class="btn btn-info">수정 적용</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>

</form>
</div>

<div>



</div>
</body>
</html>


<jsp:include page="/view/layout/footer.jsp" />