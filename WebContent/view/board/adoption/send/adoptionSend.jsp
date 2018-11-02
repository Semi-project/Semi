<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/view/layout/header.jsp" />
<style>
div.title {
	margin-top: 80px;
}
</style>
<script type="text/javascript"
	src="/resource/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function() {

	$("#btnCancel").click(function() {
		history.go(-1);
	});

	function checkAll(){
		
	}
	
	$("#btnSave").click(function() {
		submitContents();
	});

	$("input:radio[name='animal']").click(function(){
		var selected = $('input[name="animal"]:checked').val();
				
		var show1 = document.getElementById("select1");
		var show2 = document.getElementById("select2");
		var show3 = document.getElementById("select3");
		
		if (selected == "dog") {
			
			show1.style.display = "block";
			show2.style.display = "none";
			show3.style.display = "none";
			
		} else if (selected == "cat") {
			
			show1.style.display = "none";
			show2.style.display = "block";
			show3.style.display = "none";
			
		} else if (selected == "etc") {

			show1.style.display = "none";
			show2.style.display = "none";
			show3.style.display = "block";

		}
	
	});
	
});
</script>

<div>
	<h3>입양 보내실 동물</h3>
	<hr>
	<div style="width:70%">
		<form id="frm" action="/adoption/send/insert" method="post"
			enctype="multipart/form-data">
			<table class="table table-bordered">
				<tr>
					<td>유저 아이디</td>
					<td><input type="text" id="userid" name="userid" value="${userid }" readonly /></td>
				</tr>
				<tr>
					<td>동물 이름</td>
					<td><input type="text" id="name" name="name" /></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" id="age" name="age" min="0" /></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><input type="radio" ID="male" name="gender" value="남"
						checked /><label for="male">남</label> <input type="radio"
						ID="female" name="gender" value="여" /><label for="female">여</label>
					</td>
				</tr>
				<tr>
					<td>몸무게</td>
					<td><input type="number" id="weight" name="weight" min="0" /></td>
				</tr>
				<tr>
					<td>중성화 여부</td>
					<td>
						<input type="radio" ID="neuter1" name="neuter" value="Y" checked /><label for="neuter1">Yes</label>
						<input type="radio" ID="neuter2" name="neuter" value="N" /><label for="neuter2">No</label>
					</td>
				</tr>
				<tr>
					<td rowspan="2">품종</td>
					<td>
						<input type="radio" ID="animal1" name="animal" value="dog" checked /><label for="animal1">멍멍이</label>
						<input type="radio" ID="animal2" name="animal" value="cat" /><label for="animal2">냥냥이</label>
						<input type="radio" ID="animal3" name="animal" value="etc" /><label for="animel3">기타</label>
					</td>
				</tr>
				<tr>
             	<td id="select1">
					<select id="species" name="species1">
            			<c:forEach var="species" items="${speciesList[0] }">
							<option value="${species.species_Code }">${species.species_Name }</option>
						</c:forEach>
            		</select>
            	</td>
            	<td id="select2" style="display:none;">
            		<select id="species" name="species2">
            			<c:forEach var="species" items="${speciesList[1] }">
							<option value="${species.species_Code }">${species.species_Name }</option>
						</c:forEach>
            		</select>
            	</td>
            	<td id="select3" style="display:none;">
            		<select id="species" name="species3">
            			<c:forEach var="species" items="${speciesList[2] }">
							<option value="${species.species_Code }">${species.species_Name }</option>
						</c:forEach>
            		</select>
            	</td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="10" cols="30" id="inputText"
							name="content" style="width: 766px; height: 412px;"></textarea></td>
				</tr>
			</table>
			<div style="">
				<button id="btnSave" class="btn btn-info">저장</button>
				<button id="btnCancel" class="btn btn-danger">취소</button>
			</div>
		</form>
	</div>


</div>

<script type="text/javascript">
	// 스마트에디터 스킨 적용
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "inputText", //<textarea>의 id 입력
		sSkinURI : "/resource/smarteditor/SmartEditor2Skin.html",
		fCreator : "createSEditor2",
		htParams : {
			bUseToolbar : true, //툴바 사용여부
			bUseVerticalResizer : false, //입력창 크기 조절 바
			bUseModeChanger : true
		//모드 탭
		}
	});

	//<form>의 submit에 맞춰 스마트에디터 내용 적용
	function submitContents(elClickedObj) {
		oEditors.getById["inputText"].exec("UPDATE_CONTENTS_FIELD", []);
		try {
			elClickedObj.form.submit();
		} catch (e) {
		}
	}
</script>

<jsp:include page="/view/layout/footer.jsp" />