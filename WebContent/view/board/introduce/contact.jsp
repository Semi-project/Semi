<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=3S2LG8EGQ4J90FFn8bBy&submodules=geocoder"></script>
<style>
#mapArea {
	boder: 1px solid #ddd;
	display: block;
	width: 100%;
	height: 500px;
	clear: both;
}

div.map {
	margin-top: 10px;
}
</style>
<div class="col-lg-12">
	<div class="col-lg-6">
		<div class=map>
			<div id="mapArea"></div>
		</div>
	</div>
	<div class="col-lg-6">
		<h4>대중교통 이용시</h4>
		<ul class="list-unstyled">
			<li><strong style="color: #a46332;">6호선</strong> &nbsp; 망원역 1번
				출구, 도보 7분거리</li>
			<li><strong style="color: #30bb37;">2호선</strong> &nbsp; 홍대입구역
				2번출구에서 마을버스 마포 15번 탑승 또는 신촌역 8번출구에서 마포 8번 버스 탑승 후 희성교회 정류장 하차 (3개
				정거장), 도보 1분거리</li>
		</ul>
		<hr>
		<h4>자가용 이용시</h4>
		<p>
			<b>주소 : 서울 마포구 잔다리로 122 (서교동 457-5)</b>
		</p>
		<p>※ 센터 앞에 유료 공영주차장이 있으며, 토요일 및 공휴일은 무료로 이용가능합니다. 다만 주차공간이 매우
			제한적이어서 대중교통을 이용하시면 더 편리합니다.</p>
		<hr>
		<h4>전화번호</h4>
		<ul class="list-unstyled who">
			<li><a href="tel:02-3482-0999" class="g-color-black"><i
					class="fa fa-phone g-mr-5"></i> 02-3482-0999</a></li>
			<li><a href="javascript:void(0);" class="g-color-black"><i
					class="fa fa-fax g-mr-5"></i> 02-3482-8835 </a></li>
		</ul>
	</div>
</div>
<script>
	var map = new naver.maps.Map('mapArea', {
		center : new naver.maps.LatLng(37.49899300000001, 127.03290900000002),
		zoom : 50
	});

	var marker = new naver.maps.Marker(
			{
				position : new naver.maps.LatLng(37.49899300000001,
						127.03290900000002),
				map : map
			});
</script>
<jsp:include page="/view/layout/footer.jsp" />
