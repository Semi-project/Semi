<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />
<div class="wrapper">
	<div class="row ">
		<div class="col-lg-4">col-lg-4</div>
		<div class="col-lg-4">
			<div class="container ">
				<form id="frm" action="" method="post">
					<fieldset>

						<section>
							<div class="alert">
								<p>다음의 경우에는 입양이 어렵습니다.</p>
								<ul class="" style="padding-left: 15px;">
									<li>가정으로 입양이 아닌 경우 (ex)공장, 회사, 군부대, 농장, 식당 등)</li>
									<li>5마리 이상의 동물을 한꺼번에 키우고 있는 경우.(상담후 입양이 가능 할 수 있습니다.)</li>
									<li>미취학 아동의 자녀가 3명 이상이거나 5세 이하의 아이가 2명 이상인 경우.</li>
									<li>가족 구성원 전체의 동의를 얻지 않은 경우나 미성년자의 입양신청</li>
									<li>외국으로 입양을 원하거나 한국에 거주하는 외국인.(상담후 입양이 가능 할 수 있습니다.)</li>
									<li>가족구성원 중 동물에 대한 알레르기나 우울증 등의 질환으로 치료를 받고있는 경우.(상담후 입양이
										가능 할 수 있습니다.)</li>
									<li>70세 이상 혼자 거주하고 있는 경우</li>
								</ul>
							</div>



						</section>
						<h5 class="font-bold">입양 질문</h5>
						<section>
							<label class="control-label">1. 입양을 원하시는 이유를 적어주세요.</label> <label
								class="textarea textarea-resizable"><textarea id="q1"
									name="q1" rows="2"></textarea></label>
						</section>
						<section>
							<label class="control-label">2. 입양해서 기르신 반려동물이 있나요? 있다면
								소개해주세요.</label> <label class="textarea textarea-resizable"><textarea
									id="q2" name="q2" rows="2"></textarea></label>
						</section>
						<section>
							<label class="control-label">3. 키우고 계신 반려동물이 있나요? 있다면
								소개해주세요.</label> <label class="textarea textarea-resizable"><textarea
									id="q3" name="q3" rows="2"></textarea></label>
						</section>
						<section>
							<label class="control-label">4. 현재 주거 형태가 어떻게 되나요?</label> <label
								class="textarea textarea-resizable"><textarea id="q4"
									name="q4" rows="2"></textarea></label>
						</section>
						<section>
							<label class="control-label">5. 입양 후 모니터링을 위한 전화연락이나 방문이
								가능한 시간대를 선택해주세요</label>
							<div class="inline-group">
								<label class="radio"><input name="q5" type="radio"
									value="9시~10시"><i class="rounded-x"></i>9시~10시</label> <label
									class="radio"><input name="q5" type="radio"
									value="10시~11시"><i class="rounded-x"></i>10시~11시</label> <label
									class="radio"><input name="q5" type="radio"
									value="11시~12시"><i class="rounded-x"></i>11시~12시</label> <label
									class="radio"><input name="q5" type="radio"
									value="12시~13시"><i class="rounded-x"></i>12시~13시</label> <label
									class="radio"><input name="q5" type="radio"
									value="13시~14시"><i class="rounded-x"></i>13시~14시</label> <label
									class="radio"><input name="q5" type="radio"
									value="14시~15시"><i class="rounded-x"></i>14시~15시</label> <label
									class="radio"><input name="q5" type="radio"
									value="15시~16시"><i class="rounded-x"></i>15시~16시</label> <label
									class="radio"><input name="q5" type="radio"
									value="16시~17시"><i class="rounded-x"></i>16시~17시</label> <label
									class="radio"><input name="q5" type="radio"
									value="17시~18시"><i class="rounded-x"></i>17시~18시</label> <label
									class="radio"><input name="q5" type="radio"
									value="전부가능"><i class="rounded-x"></i>전부가능</label>
							</div>
							<div class="clearfix"></div>
						</section>
						<section>
							<label class="control-label">5. 입양동의</label>
							<div class="alert alert-info fade in" style="margin-bottom: 5px;">
								<ul class=""
									style="list-style-type: number; padding-left: 20px;">
									<li>본인(이하 '입양인'이라 합니다.)은 동물권행동 댕냥이 (이하 '단체'라 합니다.)를 통하여
										반려동물(이하 '입양동물'이라 합니다.)을 입양함에 있어 입양동물이 자연사하는 시점까지 책임 있는 보호자로서
										입양동물에게 최적의 환경과 보살핌을 제공할 것이며 최선을 다하여 입양동물을 보호할 의무가 있습니다.</li>
									<li>입양인은 입양인이나 혹은 가족을 포함한 제 3자의 고의 또는 과실로 인하여 입양동물에 대한
										학대행위가 발생하거나 입양동물이 양도, 매매, 유기, 유실된 경우에는 단체로부터의 어떠한 민, 형사상의 처벌에도
										이의를 제기하지 않고 따르는데 동의합니다. 입양인은 입양동물이 양도, 매매, 유기, 유실된 경우에 그 즉시로
										단체에 통지할 의무를 지니며 입양동물을 되찾기 위한 노력에 최선을 다하여 협조할 의무가 있습니다.</li>
									<li>입양인은 입양동물에게 양질의 사료와 깨끗한 물을 공급하고, 적합하고 적절한 운동, 질병에 대한
										예방접종, 정기 건강검진을 시행하는 등, 꼭 필요한 복지를 제공할 의무가 있습니다.</li>
									<li>만일 입양동물이 질병에 걸렸을 때 입양인은 신속하게 필요한 수의학적 치료를 받게 하고 성실하게 그
										치료에 임할 것이며 만일 입양동물의 완치가 불가능하고 그 고통이 극심한 경우에는 이를 단체에 통지하고 단체와의
										협의 하에 대안을 강구하여야 하며 임의로 처리해서는 안 됩니다. 입양인은 입양동물이 자연사한 경우에도 즉시 이
										사실을 단체에 통지할 의무가 있습니다.</li>
									<li>입양인은 상시 입양동물에게 입양인의 연락처가 기재된 이름표를 반드시 착용시켜야 하며 만일 입양인의
										연락처가 변경되거나 주거지의 변동사항이 있을 시에는 즉시 단체에 이에 대한 정보를 통지할 의무가 있습니다.</li>
									<li>입양인은 입양 후 단체에서 시행하는 입양동물의 모니터링을 위한 전화 또는 방문에 응할 것이며
										단체가 입양동물의 면회나 사진을 요구할 시 언제든지 이에 협조할 의무가 있습니다.</li>
									<li>입양인은 단체의 입양동물 중성화수술 방침에 동의하며, 입양 전에 미리 중성화수술이 시행될 수 없는
										경우에는 입양동물의 건강상태가 양호하고 그 연령이 수술하기에 적합한 조건이 갖추어지는 즉시로 중성화수술을
										시행하고 이에 대한 사실을 즉시 단체에 통지하며 중성화수술 전이라 할지라도 그 어떠한 경우에도 입양동물을
										교배하지 않을 의무가 있습니다.</li>
									<li>입양인은 개인적인 사유로 파양을 하는 경우에는, 반드시 단체에게 통보해야 하며 임의대로 재입양할
										수 없습니다. 또한, 단체에게 입양비 반환을 청구할 수 없습니다.</li>
									<li>입양인은 입양 후에도, 만일 단체에서 입양인이 입양동물을 돌볼 여건과 환경이 적합하지 않다고
										판단하여 입양동물의 반환을 요구하는 경우에는 이에 이의를 제기하지 않고 적극 협조할 의무가 있습니다.</li>
								</ul>
							</div>
							<p style="margin-bottom: 10px;">입양인은 이하에 서명함으로써 상기의 조항에 동의하며,
								입양인이 상기의 조항을 위반 시에는 단체의 어떠한 처벌조치에도 이의를 제기함이 없이 따를 것을 서약합니다.</p>
							<label class="checkbox"> <input type="checkbox" name="c2"><i></i>
								<span>위의 유의사항을 확인하였으며, 이에 동의합니다.</span>
							</label>
						</section>

					</fieldset>
					<footer>
						<div class="row text-center">
							<div class="col-md-4 col-md-push-4">
								<button type="submit" class="btn-u btn-u-dark btn-block"
									style="margin-top: 10px;">등록</button>
							</div>
						</div>
					</footer>
				</form>
			</div>
		</div>
		<div class="col-lg-4">col-lg-4</div>
	</div>
</div>
</body>
</html>