<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/reset.css">



<!-- ---------------------------- -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script charset="UTF-8" type="text/javascript"
	src="http://t1.daumcdn.net/postcode/api/core/180928/1538455030985/180928.js"></script>
<!--autoload=false 파라미터를 이용하여 자동으로 로딩되는 것을 막습니다.-->
<script
	src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>
<!-- DATAPICKER -->
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<!-- <script src="https://code.jquery.com/jquery.min.js"></script> -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<script>
	var checkid = -1;// 아이디 유효성 
	var checkpw; // 비밀번호 유효성 

	var mail = -1; //이메일
	var regExp1 = /^[a-zA-Z0-9]{5,13}$/;
	//id와 비밀번호의 유효성 검사
	var regExp2 = /[a-z0-9]{4,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/i;
	//e-mail의 유효성 검사
	var regname = /^[가-힝]{2,}$/;
	//이름의 유효성 검사
	function checkPassword(password, id) {

		if (!/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/
				.test(password)) {
			alert('숫자+영문자+특수문자 조합으로 8자리 이상 사용해야 합니다.');
			$('#password').val('').focus();
			return false;
		}
		var checkNumber = password.search(/[0-9]/g);
		var checkEnglish = password.search(/[a-z]/ig);
		if (checkNumber < 0 || checkEnglish < 0) {
			alert("숫자와 영문자를 혼용하여야 합니다.");
			$('#password').val('').focus();
			return false;
		}
		if (/(\w)\1\1\1/.test(password)) {
			alert('같은 문자를 4번 이상 사용하실 수 없습니다.');
			$('#password').val('').focus();
			return false;
		}

		if (password.search(id) > -1) {
			alert("비밀번호에 아이디가 포함되었습니다.");
			$('#password').val('').focus();
			return false;
		}
		return true;
	}

	$(document)
			.ready(
					function() {
						$("#userid").keyup(function() {
							if ($(this).val().length > 13) {
								alert('아이디가 너무 깁니다.');
								$(this).val($(this).val().substr(0, 13));

							}
						});
						$("#userpw").change(
								function() {
									checkPassword($('#userpw').val(), $(
											'#userid').val());
								});
						$("#userpwCheck").change(function() {

							if ($("#userpw").val() != $("#userpwCheck").val()) {
								alert("비밀번호가 다릅니다 다시 입력해주세요");
							}
						});
						$("#btn")
								.click(
										function() {
											new daum.Postcode(
													{
														oncomplete : function(
																data) {
															// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

															// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
															// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
															var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
															var extraRoadAddr = ''; // 도로명 조합형 주소 변수

															// 법정동명이 있을 경우 추가한다. (법정리는 제외)
															// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
															if (data.bname !== ''
																	&& /[동|로|가]$/g
																			.test(data.bname)) {
																extraRoadAddr += data.bname;
															}
															// 건물명이 있고, 공동주택일 경우 추가한다.
															if (data.buildingName !== ''
																	&& data.apartment === 'Y') {
																extraRoadAddr += (extraRoadAddr !== '' ? ', '
																		+ data.buildingName
																		: data.buildingName);
															}
															// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
															if (extraRoadAddr !== '') {
																extraRoadAddr = ' ('
																		+ extraRoadAddr
																		+ ')';
															}
															// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
															if (fullRoadAddr !== '') {
																fullRoadAddr += extraRoadAddr;
															}

															// 우편번호와 주소 정보를 해당 필드에 넣는다.
															document
																	.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
															document
																	.getElementById('home').value = fullRoadAddr;
															document
																	.getElementById('homeAddress').value = data.jibunAddress;

															// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
															if (data.autoRoadAddress) {
																//예상되는 도로명 주소에 조합형 주소를 추가한다.
																var expRoadAddr = data.autoRoadAddress
																		+ extraRoadAddr;
																document
																		.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
																		+ expRoadAddr
																		+ ')';

															} else if (data.autoJibunAddress) {
																var expJibunAddr = data.autoJibunAddress;
																document
																		.getElementById('guide').innerHTML = '(예상 지번 주소 : '
																		+ expJibunAddr
																		+ ')';

															} else {
																document
																		.getElementById('guide').innerHTML = '';
															}
														}
													}).open();
										});

						$("#birth").datepicker({
							dateFormat : 'yy-mm-dd'
						});

						$("#check").click(function() {
							if (!regExp1.test($("#userid").val())) {
								alert("형식에 맞춰 ID를 입력하세요");
								$("#userid").val('');
								$("#userid").focus();
								return false;

							} else {
								if ($("#userid").val().length < 5) {
									alert('아이디가 너무 짧습니다.');
								} else {
									$.ajax({
										type : "post",
										url : "/member/memberidcheck",
										data : {
											userid : $("#userid").val()

										},
										dataType : "json",
										success : function(d) {
											//console.log("성공");
											//console.log(d.two);
											checkid = d;
											if (d == 0) {
												alert("사용가능한 아이디입니다.");
											} else {
												alert("아이디가 중복됩니다.");
											}

										},
										error : function() {

											console.log("실패");
										}

									});
								}
							}//endof first if 
						});

						$("#check2").click(function() {
							var email = "";
							email += $("#email").val();
							email += $("#email2").val();
							//console.log(email);
							if (!regExp2.test(email)) {
								alert("이메일을 다시 입력해주세요");
							} else {

								$.ajax({
									type : "post",
									url : "/member/membercheckemail",
									data : {
										useremail : email

									},
									dataType : "json",
									success : function(d) {
										//console.log("성공");
										console.log(d);
										//console.log(d.two);
										mail=d;
										if (d == 0) {
											alert("사용 할 수 있는 이메일 입니다.");

										} else {
											alert("중복된 이메일입니다 다른 이메일을 입력해주세요");
										}

									},
									error : function() {

										console.log("실패");
									}

								});

							}
						});
						$("#join").click(
								
								function() {
									var username=$("#name").val();
									var userid=$("#userid").val();
									var userbirth=$("#birth").val();
									var userpw=$("#userpw").val();
									var gender=$("input[name='gender']:checked").val(); // 성별 입력
									var address=$("#home").val();
									address+=$("#homeAddress").val();// 주소  
									var phone=$("#phone1").val();
									phone+=$("#phone2").val();//전화번호
									phone+=$("#phone3").val();
									var smartPhone=$("#smartPhone").val();
									smartPhone+=$("#smartPhone1").val();// 휴대폰
									smartPhone+=$("#smartPhone2").val();
									var email = "";
									email += $("#email").val();
									email += $("#email2").val();
									var subscriptionNews=$("input[name='news']:checked").val(); //수신동의 체크 이메일 
									var subscriptionSms=$("input[name='sms']:checked").val(); // 수신동의 체크 sms 
									//console.log(checkid);
									console.log(userbirth);

									if (!regname.test($("#name").val())) {
										alert("이름을  확인해주세요");
										$("#name").focus();
										return false;
									}
									if ($("#birth").val() == "") {
										alert("생년월일을 입력해주세요");
										$("#birth").focus();
										return false;
									}
									if ($("#userid").val() == "") {
										alert("아이디를 입력해주세요");
										$("#userid").focus();
										return false;
									}
									if ($("#userpw").val() == "") {
										alert("비밀번호를 입력해주세요");
										$("#userpw").focus();
										return false;
									}
									if ($("#postcode").val() == "") {
										alert("우편번호를 입력해주세요");
										$("#postcode").focus();
										return false;
									}
									if ($("#phone1").val() == ""
											|| $("#phone2").val() == ""
											|| $("#phone3").val() == "") {
										alert("전화번호를 입력해주세요");
										$("#phone2").focus();
										return false;
									}

									if ($("#smartPhone1").val() == ""
											|| $("#smartPhone2").val() == "") {
										alert("휴대전화 번호를 입력해주세요");
										$("#smartPhone1").focus();
										return false;
									}
									if ($("#email").val() == ""
											|| $("#email2").val() == "") {
										alert("이메일을 입력해주세요");
										$("#email").focus();
										return false;
									}
									if(checkid==-1){
										alert("아이디 중복검사를 해주세요");
										$("#check").focus();
										return false;
									}
									if(mail==-1){
										alert("이메일 중복검사를 해주세요");
										$("#check2").focus();
										return false;
									}
									
									$.ajax({
										type : "post",
										url : "/member/join",
										data : {
											userid : userid,
											userbirth:userbirth,
											userpw:userpw,
											username:username,
											gender:gender,
											address:address,
											phone:phone,
											smartPhone:smartPhone,
											email : email,
											subscriptionNews:subscriptionNews,
											subscriptionSms:subscriptionSms
											

										},
										dataType : "json",
										success : function(d) {
											console.log(d);
											if(d.subscriptionCnt==1 && d.memberCnt==1){
												//window.location.href = "/main";
											
												alert("회원가입 성공");
												location.href = "/main";
											}else{
												alert("회원가입 실패");
											}
										},
										error : function() {

											console.log("실패");
										}

									}); //endof ajax

								});

					});
</script>
</head>
<body>

	<form method="POST" name="inputForm" action="/member/join">
		<table>
			<tr>

				<td><label for="name">이름 </label></td>
				<td><input type="text" name="name" id="name" size="20px">
				</td>
			</tr>
			<tr>
				<td><label for="birth">생년월일 </label></td>
				<td><input type="text" id="birth"></td>

			</tr>

			<tr>
				<td><label for="userid">아이디</label></td>
				<td><input type="text" name="userid" id="userid" size="20px">
					<input type="button" id="check" value="중복확인"></td>
			</tr>
			<tr>
				<td><label for="userpw">비밀번호 </label></td>
				<td><input type="password" name="userpw" id="userpw"
					size="20px"> *영문 대소문자/숫자/특수문자를 혼용하여 2종류10~16자 또는 3종 8~16자</td>
			</tr>
			<tr>
				<td><label for="userpwCheck">비밀번호 확인 </label></td>
				<td><input type="password" name="userpwCheck" id="userpwCheck"
					size="20px"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><input type="radio" name="gender" value="남" >남 <input
					type="radio" name="gender" value="여" checked>여</td>
			</tr>
			<tr>
				<td><label for="postcode">우편번호 </label></td>
				<td><input type="text" name="postcode" id="postcode" size="5">
					<input type="button" id="btn" value="우편번호검색"></td>
			</tr>
			<tr>
				<td><label for="home">집주소 </label></td>
				<td><input type="text" name="home" id="home" size="60">
					<span id="guide" style="color: #999"></span></td>

			</tr>
			<tr>
				<td><label for="homeAddress">상세주소 </label></td>
				<td><input type="text" name="homeAddress" id="homeAddress"
					size="60"></td>
			</tr>
			<tr>
				<td><label for="phone1">연락처 </label></td>
				<td><select id="phone1">
						<option value=""></option>
						<option value="02">02</option>
						<option value="031">031</option>
						<option value="032">032</option>
						<option value="033">033</option>
						<option value="041">041</option>
						<option value="042">042</option>
						<option value="043">043</option>
						<option value="051">051</option>
						<option value="052">052</option>
						<option value="053">053</option>
						<option value="054">054</option>
						<option value="055">055</option>
						<option value="061">061</option>
						<option value="062">062</option>
						<option value="063">063</option>
						<option value="064">064</option>
						<option value="070">070</option>
				</select> - <input type="text" name="phone2" id="phone2" size="5">- <input
					type="text" name="phone3" id="phone3" size="6"></td>
			</tr>

			<tr>
				<td>휴대폰</td>
				<td><select id="smartPhone" name="smartPhone">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="019">019</option>
				</select> -<input type="text" name="smartPhone1" id="smartPhone1" size="5">
					-<input type="text" name="smartPhone2" id="smartPhone2" size="5"></td>
			</tr>
			<tr>
				<td><label for="email">이메일</label></td>
				<td><input type="text" name="email" id="email" size="10">@
					<select id="email2">
						<option value=""></option>
						<option value="@gmail.com">gmail.com</option>
						<option value="@naver.com">naver.com</option>
						<option value="@hanmail.com">hanmail.com</option>
				</select> <input type="button" id="check2" value="중복확인"></td>
			</tr>
			<tr>
				<td><label for="news">뉴스메일 </label></td>
				<td><input type="radio" name="news" value="1" checked>받습니다. <input
					type="radio" name="news" value="0">받지 않습니다.</td>
			</tr>
			<tr>
				<td><label for="sms">SMS안내(이벤트)</label></td>
				<td><input type="radio" name="sms" value="1" checked>받습니다. <input
					type="radio" name="sms" value="0">받지 않습니다.</td>
			</tr>
		</table>
		<input type="button" id="join" value="회원가입">
	</form>
</body>
</html>
