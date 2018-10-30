<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script>
	$(document).ready(function() {
		$("#findpw").click(function() {
			//console.log("눌렸다");
			if ($("#username").val() == '') {
				alert('이름을 입력하세요');
				$("#username").focus();
				return;
			}
			if ($("#userid").val() == '') {
				alert('아이디를 입력하세요');
				$("#userid").focus();
				return;
			}
			if ($("#email").val() == '') {
				alert('이메일을 입력하세요');
				$("#email").focus();
				return;

			}
			var email = "";
			email += $("#email").val();
			email += $("#email2").val();

			$.ajax({
				type : "post",
				url : "/send/mail",
				data : {
					username : $("#username").val(),
					useremail : email,
					userid : $("#userid").val()
				},
				dataType : "text",
				success : function(d) {
					//console.log("--"+d+"--");
					//console.log(d=="성공");
					if(d=="성공"){
						
						alert("메일로 발송해드렸습니다 메일을 확인하세요");
					}else{
						alert("일치하는 정보가 없습니다 다시 확인해주세요");
					}

				},
				error : function() {

					console.log("실패");
				}

			});
		});
	});
	function myClose() {
		self.close();
	}
</script>
</head>
<body>
	<div align="center">
		<form name="find">
			<font class="title">비밀번호 찾기<br></font>
			<table
				style="width: 500px; height: 100px; border: 0; border-color: #A3C2F6;"
				cellspacing="0" cellpadding="0">
				<tr>
					<td width="15%" align="right"><font color="#FF0000" size=1></font><font
						class="style1">아이디&nbsp;&nbsp;</font></td>
					<td><input type="text" id="userid" name="userid" size="10"
						maxlength="10" class="input_style1"></td>
				</tr>
				<tr>
					<td width="15%" align="right"><font color="#FF0000" size=1></font><font
						class="style1">이름&nbsp;&nbsp;</font></td>
					<td><input type="text" id="username" name="username" size="10"
						maxlength="10" class="input_style1"></td>
				</tr>
				<tr>
					<td width="15%" align="right"><font color="#FF0000" size=1></font><font
						class="style1">이메일&nbsp;&nbsp;</font></td>
					<td><input type="text" id="email" name="email1" size="20"
						maxlength="50" class="input_style1"> @ <select id="email2"
						name="email2">
							<option value="@naver.com">naver.com</option>
							<option value="@daum.net">hanmail.net</option>
							<option value="@gmail.com">gmail.com</option>
					</select></td>
				</tr>


			</table>
			<input type="button" value="비밀번호찾기" id="findpw">&nbsp;&nbsp;
			<input type="button" value="닫기" onClick="myClose()">
		</form>
	</div>
</body>
</html>
