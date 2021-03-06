<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/login.css">
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="UTF-8"> -->
<!-- <title>Insert title here</title> -->
<jsp:include page="/view/layout/header.jsp" />


<script>
	$(document)
			.ready(
					function() {
						$("#findid") //팝업창 
								.click(
										function() {
										//	console.log("눌렸다");
											window
													.open(
															'/member/findid',
															'popup',
															'width=300, height=200, left=0, top=0, toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=no, scrollbars=no, copyhistory=no');

										});
						$("#findpw") //팝업창 
								.click(
										function() {
											window
													.open(
															'/member/findpw',
															'popup',
															'width=300, height=200, left=0, top=0, toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=no, scrollbars=no, copyhistory=no');
										});
						////////////////////////////////////////////
						$("#userpw").keyup(function() { // 일정 길이 넘어가면 길이를 잘라줌 
							if ($(this).val().length > 13) {
								$(this).val($(this).val().substr(0, 13));
							}
						});
						$("#userid").keyup(function() { 
							if ($(this).val().length > 13) {
								$(this).val($(this).val().substr(0, 13));
							}
						});
						////////////////////////////////////////////
						// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
						var userInputId = getCookie("userInputId");
						$("input[name='userid']").val(userInputId);

						if ($("input[name='userid']").val() != "") { // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
							$("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
						}

						$("#idSaveCheck")
								.change(
										function() { // 체크박스에 변화가 있다면,
											if ($("#idSaveCheck")
													.is(":checked")) { // ID 저장하기 체크했을 때,
												var userInputId = $(
														"input[name='userid']")
														.val();
												setCookie("userInputId",
														userInputId, 7); // 7일 동안 쿠키 보관
											} else { // ID 저장하기 체크 해제 시,
												deleteCookie("userInputId");
											}
										});

						// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
						$("input[name='userid']")
								.keyup(
										function() { // ID 입력 칸에 ID를 입력할 때,
											if ($("#idSaveCheck")
													.is(":checked")) { // ID 저장하기를 체크한 상태라면,
												var userInputId = $(
														"input[name='userid']")
														.val();
												setCookie("userInputId",
														userInputId, 7); // 7일 동안 쿠키 보관
											}
										});
						////////////////////////////////////////////
						$("#loginBtn").click(function() {

							if ($("#userid").val() == "") {
								alert("아이디를 입력해주세요");
								$("#userid").focus();
								return;
							}
							if ($("#userpw").val() == "") {
								alert("비밀번호를 입력해주세요");
								$("#userpw").focus();
								return;
							}
							var exp = /[a-z0-9]$/;
							//영문자와 숫자 //정규표현식. test(입력값) 규칙에 맞으면 true 
							if (!exp.test($("#userid").val())) {
								alert("영문자와 숫자만 입력가능합니다.");
								$("#userid").focus();
								return;
							}
							$.ajax({
								type : "post",
								url : "/member/login/login.do",
								data : {
									userid : $("#userid").val(), //아이디 값 
									userpw : $("#userpw").val() //비밀번호
								},
								dataType : "text",
								success : function(d) {
									//console.log(d);
									if (d == 0) { //로그인 성공했을경우
										alert($("#userid").val() + "님 환영합니다.");
										location.href = "/main";
									} else if (d == 1) { //아이디가 없을 경우
										alert("존재하지 않는 아이디입니다.");
										//message = ""
									} else { //비밀번호가 틀렸을 경우 
										alert("비밀번호가 틀렸습니다.");
									}
									//$("#result").html(d);
								},
								error : function() {

									console.log("실패");
								}

							});

							//$(location).attr("href", "/member/login");
						});
					});
	//쿠키이름,쿠키값,만료일 
	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	//쿠키를 지우는 함수 
	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}

	//쿠키를 가져오는 함수 
	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
</script>


	<div class="wrapper" style="clear: both;">
		<form class="form-signin" method="post">
			<h2 class="form-signin-heading">Please login</h2>
			<input type="text" class="form-control" name="userid" id="userid"
				placeholder="UserId" required="" autofocus="" /> <input
				type="password" class="form-control" name="userpw" id="userpw"
				placeholder="Password" required="" /> <label class="checkbox">
				<input type="checkbox" value="remember-me" id="idSaveCheck"
				name="rememberMe"> Remember me
			</label>
			<button class="btn btn-lg btn-custom  btn-block" type="button"
				id="loginBtn">Login</button>
			<div id="findid">
				<label>아이디찾기</label>
			</div>
			<div id="findpw">
				<label>비밀번호찾기</label>
			</div>
		</form>
	</div>

	<!-- 	<div id="result" style="text-align: center">로그인 결과 출력 영역</div> -->

	<jsp:include page="/view/layout/footer.jsp" />