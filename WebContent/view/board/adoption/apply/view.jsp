<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />
<style>
.question {
	width: 100%;
	border-top: 2px solid #bdbdbd;
	border-bottom: 2px solid #bdbdbd;
	font-family: "NanumGothic", sans-serif;
}

.question .qtit {
	text-align: left;
	margin: 50px 0 10px 0;
}

.question tr {
	border-top: 1px solid #cdcdcd;
}

.question tr:first-child {
	border-top: 0;
}

.question th {
	background: #f6f6f6;
	width: 20%;
	text-align: left;
}

.question td {
	text-align: left;
	background: #fff;
	padding-top: 1% !important;
	padding-bottom: 1% !important;
	color: #5c5c5c;
	font-size: 15px;
	line-height: 26px;
	padding-left: 3%
}

.question input[type="text"], .question select {
	margin-right: 1%;
	color: #5c5c5c;
	line-height: 12px;
	font-size: 14px;
	font-family: "NanumGothic", sans-serif;
	background: #f6f6f6;
	vertical-align: middle;
	border: 1px solid #cdcdcd;
	padding: 1%;
}

.question select {
	padding: 0.9%;
}

.question textarea {
	vertical-align: middle;
	border: 1px solid #cdcdcd;
	width: 94%;
	height: 100px;
	background: #f6f6f6;
	padding: 1%
}

.question label {
	margin-right: 1%;
}




</style>
<div class="wrapper">
	<div class="row ">


		<div class="container ">
			<form id="frm" action="" method="post">
				<h5 class="font-bold">입양 질문</h5>
				<hr>

				<table class="question">
					<tr>
						<td></td>

						<td><label>다음의 경우에는 입양이 어렵습니다.</label>
							<ul class="" style="padding-left: 15px;">

								<li>가정으로 입양이 아닌 경우 (ex)공장, 회사, 군부대, 농장, 식당 등)</li>
								<li>5마리 이상의 동물을 한꺼번에 키우고 있는 경우.(상담후 입양이 가능 할 수 있습니다.)</li>
								<li>미취학 아동의 자녀가 3명 이상이거나 5세 이하의 아이가 2명 이상인 경우.</li>
								<li>가족 구성원 전체의 동의를 얻지 않은 경우나 미성년자의 입양신청</li>
								<li>외국으로 입양을 원하거나 한국에 거주하는 외국인.(상담후 입양이 가능 할 수 있습니다.)</li>
								<li>가족구성원 중 동물에 대한 알레르기나 우울증 등의 질환으로 치료를 받고있는 경우.(상담후 입양이
									가능 할 수 있습니다.)</li>
								<li>70세 이상 혼자 거주하고 있는 경우</li>
							</ul></td>
					</tr>
					<tr>
						<th><label>1. 입양을 원하시는 아이의 이름을 적어주세요.</label><br></th>
						<td><label> ${adoptionView.animalName }
						</label></td>
					</tr>
					<tr>
						<th><label>2. 입양을 원하시는 이유를 적어주세요.</label><br></th>
						<td><label>${adoptionView.adoptionReason }
						</label></td>
					</tr>
					<tr>
						<th><label>3. 입양해서 기르신 반려동물이 있나요?<br> 있다면
								소개해주세요.
						</label><br></th>
						<td><label>${adoptionView.adoptionExp }
						</label></td>
					</tr>
					<tr>
						<th><label>4. 키우고 계신 반려동물이 있나요?<br> 있다면 소개해주세요.
						</label></th>
						<td><label> ${adoptionView.adoptionCuranimal }
						</label></td>
					</tr>
					<tr>
						<th><label class="control-label">5. 현재 주거 형태가 어떻게
								되나요?</label></th>
						<td><label>${adoptionView.adoptionHousing }</label></td>
					</tr>
					<tr>
						<th><label>6. 입양 후 모니터링을 위한 전화연락이나 방문이 가능한 시간대를
								선택해주세요</label></th>
						<td style="padding-left: 30px;">
							${adoptionView.adoptionCalltime }
						</td>
					</tr>


				</table>

				<button type="submit" style="margin-top: 10px;">입양수락</button>

			</form>
		</div>


	</div>
</div>
<jsp:include page="/view/layout/footer.jsp" />