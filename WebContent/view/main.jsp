<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="/view/layout/header.jsp" />
<script>
	var scope = {
		w : window,
		jQ : jQuery
	};

	/**
	 * ----------------------------
	 * @Plug-in Tab Menu Type
	 * ----------------------------
	 **/
	(function(param) {

		var global = param.w, $ = param.jQ;

		var pluginName = 'uiTab';

		function TabPlugin($selector, options) {
			this.$selector = $selector;
			this.detect = {};
			this.config = $.extend({}, this.defaults, options || {});

			if (!this.$selector.length)
				return;

			this._init();
		}

		TabPlugin.prototype = {
			constructor : TabPlugin.prototype,
			defaults : {
				menuItemClass : '.tabItem',
				contentClass : '.panel',
				isSelected : 'is-selected',
				visibleContent : 1
			},
			_init : function() {
				this.setting();
				this._setEvent();
				this._defaultShowing();
			},
			setting : function() {
				this.detect.$selector = this.$selector;
				this.detect.$items = this.detect.$selector
						.find(this.config.menuItemClass + ' a');
			},
			_setEvent : function() {

				var self = this;
				$(document)
						.on(
								'click.ui.tab',
								this.config.menuItemClass + ' a',
								function(e) {
									e.preventDefault();
									var target = this, $showTabContent = $(target.hash);

									self
											._hidePanels($(target),
													$showTabContent);
									self
											._showPanels($(target),
													$showTabContent);
								})
			},
			_showPanels : function($this, $showContent) {
				this.$targetItem.addClass(this.config.isSelected);
				$showContent.addClass(this.config.isSelected);
			},
			_hidePanels : function($this, $showContent) {
				this.$targetItem = $this.closest('li');
				var isActived = this.$targetItem.siblings('.'
						+ this.config.isSelected);

				if (!!isActived) {
					isActived.removeClass(this.config.isSelected);
				}

				$showContent.siblings(this.config.contentClass).removeClass(
						this.config.isSelected);
			},
			_defaultShowing : function() {
				this.detect.$items.eq(this.config.visibleContent - 1).trigger(
						'click');
			}

		};

		$.fn[pluginName] = $.fn[pluginName]
				|| function(options) {
					var $this = this;
					return $.each($this, function(idx, el) {
						var $selector = $this.eq(idx);
						if (!$.data(this, 'plugin_' + pluginName)) {
							$.data(this, 'plugin_' + pluginName, new TabPlugin(
									$selector, options))
						}
						return $selector;
					})
				};

		$(function() {
			/**
			 * [data-*] 로 플러그인 호출 권장
			 * @param @type {} : 플러그인 옵션값
			 * 기본값 { menuItemClass : '.tabItem', isSelected : 'is-selected', visibleContent : 1}
			 */
			/* // 탭 메뉴 플러그인 사용법
			 $('[data-tab="***"]').koicaTabs({
			 menuItemClass : ".tabItem", // 탭 메뉴의 li 클래스 사용자 정의
			 isSelected : "is-selected", // 탭 메뉴 활성화 클래스
			 contentClass : '.panel', // 탭 콘텐츠 클래스
			 visibleContent: 1 // 처음에 보여줄 탭 메뉴 및 콘텐츠
			 });
			 */
			$('[data-tab="tabs"]').uiTab();
		});

	})(scope);
</script>
<div style="text-align: center;">
	<div class="col-lg-12"></div>

	<div class="col-lg-12">
		<div class="col-lg-6">
			<div class="top">
				<section class="container">
					<!-- 사진 -->
					<div id="carousel-example-generic" class="carousel slide"
						data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item active">
								<img src="/img/main/137.png" alt="...">
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="/img/main/143.png" alt="...">
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="/img/main/145.png" alt="...">
								<div class="carousel-caption">...</div>
							</div>
						</div>

						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic"
							role="button" data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>

				</section>
			</div>
		</div>
		<div class=col-lg-6>
		
		</div>
	</div>
	<!-- endof first div 12  -->
	<div class="col-lg-12">
		<div id="section1">
			<div class="col-lg-6 center">
				<div class="main-tab">
					<h4 class="a11y">게시판에 관한 탭 목록입니다.</h4>
					<ul class="tabList-v1" data-tab="tabs">
						<li class="tabItem is-selected"><a href="#tab01-1">공지사항</a></li>
						<li class="tabItem"><a href="#tab01-2">입양 동물</a></li>
						<li class="tabItem"><a href="#tab01-3">입양게시판</a></li>

					</ul>
					<div class="tabPanel">
						<h4 class="a11y">모집에 대한 내용입니다.</h4>
						<ul id="tab01-1" class="panel is-selected">
							<li><a href="#none">2017년 1차 글로벌새마을청년봉사단(제3기) 국내교육을
									시행합니다.</a></li>
							<li><a href="#none">(수정)2017년 3차 월드프렌즈 코이카봉사단(115기) 모집을
									합니다.</a></li>
							<li><a href="#none">2017년 2차 월드프렌즈 코디네이터 모집,선발을 실시합니다.</a></li>
							<li><a href="#none">제 2기 KOICA-UNV 대학봉사단 활동국가 미리보기 강연을
									실시합니다.</a></li>
							<li><a href="#none">(수정)114기 월드프렌즈 코이카봉사단 모집(~12.26)을
									실시합니다.</a></li>
						</ul>
						<h4 class="a11y">합격자 발표에 대한 내용입니다.</h4>
						<ul id="tab01-2" class="panel">
							<li><a href="#none">2017년 1차 글로벌새마을청년봉사단(제3기) 국내교육을
									시행합니다.</a></li>
							<li><a href="#none">2017년 2차 월드프렌즈 코디네이터 모집,선발을 실시합니다.</a></li>
							<li><a href="#none">(수정)2017년 3차 월드프렌즈 코이카봉사단(115기) 모집을
									합니다.</a></li>
							<li><a href="#none">(수정)114기 월드프렌즈 코이카봉사단 모집(~12.26)을
									실시합니다.</a></li>
							<li><a href="#none">제 2기 KOICA-UNV 대학봉사단 활동국가 미리보기 강연을
									실시합니다.</a></li>
						</ul>
						<h4 class="a11y">국내교육에 대한 내용입니다.</h4>
						<ul id="tab01-3" class="panel">
							<li><a href="#none">2017년 2차 월드프렌즈 코디네이터 모집,선발을 실시합니다.</a></li>
							<li><a href="#none">2017년 1차 글로벌새마을청년봉사단(제3기) 국내교육을
									시행합니다.</a></li>
							<li><a href="#none">(수정)2017년 3차 월드프렌즈 코이카봉사단(115기) 모집을
									합니다.</a></li>
							<li><a href="#none">제 2기 KOICA-UNV 대학봉사단 활동국가 미리보기 강연을
									실시합니다.</a></li>
							<li><a href="#none">(수정)114기 월드프렌즈 코이카봉사단 모집(~12.26)을
									실시합니다.</a></li>
						</ul>

					</div>
				</div>

				<br /> <br />
				<div class="main-tab v1">
					<h3 class="a11y">기수별 게시판, 국가별 게시판 탭 목록입니다.</h3>
					<ul class="tabList" data-tab="tabs">
						<li class="tabItem is-selected"><a href="#tab03-1">기수별
								게시판</a></li>
						<li class="tabItem"><a href="#tab03-2">국가별 게시판</a></li>
					</ul>
					<div class="tabPanel">
						<h4 class="a11y">기수별 게시판에 대한 내용입니다.</h4>
						<ul id="tab03-1" class="panel is-selected">
							<li class="panel-item"><a href="#none">동티모르 커뮤니티 방 신설을
									요청합니다.</a> <span class="date">2016-09-07</span></li>
							<li class="panel-item"><a href="#none">안녕하세요. 도미니카 Las
									Terrenas 에서 당신을..</a> <span class="date">2016-09-07</span></li>
							<li class="panel-item"><a href="#none"><strong>[콜롬비아]</strong>[문의
									및 요청] 파견증명서 출력을 요청</a> <span class="date">2016-09-07</span></li>
							<li class="panel-item"><a href="#none"><strong>[도미니카공화국]</strong>
									활동기관명 재변경 요청합니다.</a> <span class="date">2016-09-07</span></li>
							<li class="panel-item"><a href="#none"><strong>[콜롬비아]</strong>[문의
									및 요청] 파견증명서 출력을 요청</a> <span class="date">2016-09-07</span></li>
						</ul>
						<h4 class="a11y">국가별 게시판에 대한 내용입니다.</h4>
						<ul id="tab03-2" class="panel">
							<li class="panel-item"><a href="#none"><strong>[콜롬비아]</strong>[문의
									및 요청] 파견증명서 출력을 요청</a> <span class="date">2016-09-07</span></li>
							<li class="panel-item"><a href="#none">동티모르 커뉴니티 방 신설을
									요청합니다.</a> <span class="date">2016-09-07</span></li>
							<li class="panel-item"><a href="#none"><strong>[도미니카공화국]</strong>
									활동기관명 재변경 요청합니다.</a> <span class="date">2016-09-07</span></li>
							<li class="panel-item"><a href="#none">안녕하세요. 도미니카 Las
									Terrenas 에서 당신을..</a> <span class="date">2016-09-07</span></li>
							<li class="panel-item"><a href="#none"><strong>[콜롬비아]</strong>[문의
									및 요청] 파견증명서 출력을 요청</a> <span class="date">2016-09-07</span></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<!-- endof col-lg-6 first -->
		<div class="col-lg-6"></div>
	</div>

</div>

<jsp:include page="/view/layout/footer.jsp" />