<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />
<style>
.adoption_title {
	padding-top: 50px;
	line-height: 20px;
	color: #737373;
	padding-bottom: 25px;
}

.adoption_title span {
	display: block;
	color: #283444;
	font-size: 20px;
	margin-bottom: 5px;
}

#adoption {
	border-top: 2px solid #283444;
	width: 100%;
}

#adoption th {
	border-bottom: 1px solid #ececec;
	background: #f8f8f8;
	color: #6e6e6e;
	width: 196px;
	height: 40px;
	font-weight: normal;
}

#adoption td {
	border-bottom: 1px solid #ececec;
	color: #6e6e6e;
	padding: 7px 0 7px 15px;
}

#adoption .last th, #adoption .last td {
	border-bottom: 1px solid #283444;
}

#adoption td select {
	height: 25px;
	line-height: 25px;
	border: 1px solid #ececec;
	padding-left: 5px;
}

#adoption td input[type=text] {
	width: 95px;
	line-height: 23px;
	height: 23px;
	border: 1px solid #ececec;
	padding-left: 10px;
}

div.button {
	margin: 0 auto;
	width: 10%;
}

#btn {
	width: 100px;
	background-color: #f8585b;
	border: none;
	color: #fff;
	padding: 15px 0;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	margin: 4px;
	cursor: pointer;
}
</style>
<script>
	$(document).ready(function() {
		$("#q1").val("${param.animal_Name}");
		$("#btn").click(function() {
			if ($("#q1").val() == "") {
				alert("입양하실 동물 이름을 적어주세요.");
				$("#q1").focus();
				return false;
			}
			if ($("#q2").val() == "") {
				alert("입양하시는 이유를 적어주세요.");
				$("#q2").focus();
				return false;
			}
			if ($("#q3").val() == "") {
				alert("현재 반려동물에 대해 적어주세요.");
				$("#q3").focus();
				return false;
			}
			if ($("#q4").val() == "") {
				alert("키우시는 동물에 대해 적어주세요.");
				$("#q4").focus();
				return false;
			}
			if ($("#q5").val() == "") {
				alert("주거 형태에 대해 적어주세요.");
				$("#q5").focus();
				return false;
			}
			if ($("#q6").val() == "") {
				alert("연락가능하신 시간대를 체크해주세요.");
				$("#q6").focus();
				return false;
			}

		});

	});
</script>
<div class="wrapper">
	<div class="row ">
		<div class="container ">
			<form id="frm" action="" method="post">
				<p class="adoption_title">
					<span><label style="margin-top: 20px;">다음의 경우에는 입양이
							어렵습니다.</label>
						<ul class="" style="padding-left: 15px;">

							<li>가정으로 입양이 아닌 경우 (ex)공장, 회사, 군부대, 농장, 식당 등)</li>
							<li>5마리 이상의 동물을 한꺼번에 키우고 있는 경우.(상담후 입양이 가능 할 수 있습니다.)</li>
							<li>미취학 아동의 자녀가 3명 이상이거나 5세 이하의 아이가 2명 이상인 경우.</li>
							<li>가족 구성원 전체의 동의를 얻지 않은 경우나 미성년자의 입양신청</li>
							<li>외국으로 입양을 원하거나 한국에 거주하는 외국인.(상담후 입양이 가능 할 수 있습니다.)</li>
							<li>가족구성원 중 동물에 대한 알레르기나 우울증 등의 질환으로 치료를 받고있는 경우.(상담후 입양이 가능
								할 수 있습니다.)</li>
							<li>70세 이상 혼자 거주하고 있는 경우</li>
						</ul></span>
				</p>
				<hr>

				<table id="adoption">
					<tr>
						<th><label>1. 입양을 원하시는 아이의 이름을 적어주세요.</label><br></th>
						<td><label> <textarea style="resize: none;" id="q1"
									name="q1" cols=100 rows=2></textarea>
						</label></td>
					</tr>
					<tr>
						<th><label>2. 입양을 원하시는 이유를 적어주세요.</label><br></th>
						<td><label> <textarea style="resize: none;" id="q2"
									name="q2" cols=100 rows=2></textarea>
						</label></td>
					</tr>
					<tr>
						<th><label>3. 입양해서 기르신 반려동물이 있나요?<br> 있다면
								소개해주세요.( 없으면 없다라고 적어주시면 됩니다.)
						</label><br></th>
						<td><label> <textarea style="resize: none;" id="q3"
									name="q3" cols=100 rows=2></textarea>
						</label></td>
					</tr>
					<tr>
						<th><label>4. 키우고 계신 반려동물이 있나요?<br> 있다면 소개해주세요.
						</label></th>
						<td><label> <textarea style="resize: none;" id="q4"
									name="q4" cols=100 rows=2></textarea>
						</label></td>
					</tr>
					<tr>
						<th><label class="control-label">5. 현재 주거 형태가 어떻게
								되나요?</label></th>
						<td><label><textarea style="resize: none;" id="q5"
									name="q5" cols=100 rows=2></textarea></label></td>
					</tr>
					<tr>
						<th><label>6. 입양 후 모니터링을 위한 전화연락이나 방문이 가능한 시간대를
								선택해주세요</label></th>
						<td style="padding-left: 30px;">
							<div class="inline-group">
								<label class="radio"><input id="q6" name="q6"
									type="radio" value="9시~10시" checked="checked"><i
									class="rounded-x"></i>9시~10시</label> <label class="radio"><input
									id="q6" name="q6" type="radio" value="10시~11시"><i
									class="rounded-x"></i>10시~11시</label> <label class="radio"><input
									id="q6" name="q6" type="radio" value="11시~12시"><i
									class="rounded-x"></i>11시~12시</label> <label class="radio"><input
									id="q6" name="q6" type="radio" value="12시~13시"><i
									class="rounded-x"></i>12시~13시</label> <label class="radio"><input
									id="q6" name="q6" type="radio" value="13시~14시"><i
									class="rounded-x"></i>13시~14시</label> <label class="radio"><input
									id="q6" name="q6" type="radio" value="14시~15시"><i
									class="rounded-x"></i>14시~15시</label> <label class="radio"><input
									id="q6" name="q6" type="radio" value="15시~16시"><i
									class="rounded-x"></i>15시~16시</label> <label class="radio"><input
									name="q6" type="radio" value="16시~17시"><i
									class="rounded-x"></i>16시~17시</label> <label class="radio"><input
									id="q6" name="q6" type="radio" value="17시~18시"><i
									class="rounded-x"></i>17시~18시</label> <label class="radio"><input
									id="q6" name="q6" type="radio" value="전부가능"><i
									class="rounded-x"></i>전부가능</label>
							</div>
						</td>
					</tr>


				</table>
				<div class="button">
					<button id="btn" type="submit" style="margin-top: 10px;">등록</button>
				</div>
			</form>
		</div>


	</div>
</div>
<jsp:include page="/view/layout/footer.jsp" />
