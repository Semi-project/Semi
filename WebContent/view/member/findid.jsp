<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>아이디 찾기</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script>
	$(document).ready(function() {
		$("#findid").click(function() {
			console.log("눌렸다");
			if ($("#username").val()=='') {
				alert('이름을 입력하세요');
				$("#username").focus();
			}
			if($("#email").val()==''){
				alert('이메일을 입력하세요');
				$("#email").focus();
				
			}
			var email = "";
			email += $("#email").val();
			email += $("#email2").val();
			
			$.ajax({
				type : "post",
				url : "/member/findid",
				data : {
					username : $("#username").val(),
					useremail : email
				},
				dataType : "json",
				success : function(d) {
					console.log(d);
					alert("찾으시는 아이디는 : "+d.idList+"입니다.");
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
			<font class="title">이메일로 ID찾기<br></font>
			<table
				style="width: 500px; height: 100px; border: 0; border-color: #A3C2F6;"
				cellspacing="0" cellpadding="0">
				<tr>
					<td width="15%" align="right"><font color="#FF0000" size=1></font><font
						class="style1">이름&nbsp;&nbsp;</font></td>
					<td><input type="text" id="username" name="username" size="10"
						maxlength="10" class="input_style1"></td>
				</tr>
				<tr>
					<td width="15%" align="right"><font color="#FF0000" size=1></font><font
						class="style1">이메일&nbsp;&nbsp;</font></td>
					<td><input type="text" id="email" name="email1" size="20" maxlength="50"
						class="input_style1"> @ <select id="email2" name="email2">
							<option value="@naver.com">naver.com</option>
							<option value="@daum.net">hanmail.net</option>
							<option value="@gmail.com">gmail.com</option>
					</select></td>
				</tr>


			</table>
			<input type="button" value="아이디찾기" id="findid">&nbsp;&nbsp;
			<input type="button" value="닫기" onClick="myClose()">
		</form>
	</div>
</body>
</html>
