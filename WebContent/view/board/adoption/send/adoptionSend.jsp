<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- header jsp 적용 -->
	<jsp:include page="/view/layout/header.jsp" />
<!-- jquery 2.2.4 -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- jQuery Form Plugin -->
	<script type="text/javascript" src="http://malsup.github.com/min/jquery.form.min.js"></script>

<!-- 네이버 스마트에디터 2.0 -->
	<script type="text/javascript" src="/view/adoption/send/se2/js/HuskyEZCreator.js" charset="UTF-8"></script>

<script type="text/javascript">
   $(document).ready(function() {
      //jquery.form.js 플러그인 사용
      //   http://malsup.com/jquery/form/

      $("#frm").submit(function() {
         submitContents();
         console.log("ㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
         $("#frm").ajaxForm({
            
            dataType : "json",
            success : function(res) {
               console.log("성공");
            },
            error : function() {
               console.log("실패");
            }
   
         });
      });
   });
   
   $("#btnCancel").click(function() {
		history.go(-1);
	});
</script>

<div>
<h3>입양 보내실 동물</h3>
<hr>
<div>
	<form id="frm" action="/adoption/send/insert" method="post"
			enctype="multipart/form-data">
		<table width="100%" class="tableSet">
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name" /></td>
			</tr>
			<tr>
           		<td>나이</td>
            	<td><input type="number" id="age" name="age" min="0" /></td>
         	</tr>
         	<tr>
            	<td>성별</td>
            	<td>
            		<select id="gender" name="gender">
            			<option value="남">남</option>
            			<option value="여">여</option>
            		</select>
				</td>
         	</tr>
         	<tr>
            	<td>몸무게</td>
            	<td><input type="number" id="weight" name="weight" min="0" /></td>
         	</tr>
         	<tr>
            	<td>중성화 여부</td>
            	<td>
            		<select id="neuter" name="neuter">
            			<option value="Y">Y</option>
            			<option value="N">N</option>
            		</select>
				</td>
         	</tr>
         	<tr>
            	<td rowspan="2">품종</td>
            	<td>
            		<input type="radio" name="animal" value="dog">멍멍이
					<input type="radio" name="animal" value="cat" checked="checked">냥냥이
					<input type="radio" name="animal" value="guitaR">기타
            	</td>
            </tr>
            <tr>
            	<td>
            		<select id="species" name="species">
            			<c:forEach var="species" items="${speciesList }">
            				<option value="${species.species_Code }">${species.species_Name }</option>
            			</c:forEach>
            		</select>
            	</td>
         	</tr>
         	<tr>
            	<td>내용</td>
            	<td><textarea rows="10" cols="30" id="inputText" name="content"
                  style="width: 766px; height: 412px; rowspan=2"></textarea></td>
         	</tr>
         	<tr>
            	<td colspan="2">
            		<input type="button" id="btnSave" value="저장" /> 
            		
               </td>
			</tr>
		</table>
	</form>
</div>

<div>
	<input type="button" id="btnCancel" value="취소" />
</div>

</div>

<script type="text/javascript">

// 스마트에디터
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
      	oAppRef : oEditors,
      	elPlaceHolder : "inputText",
      	//SmartEditor2Skin.html 파일이 존재하는 경로 
      	sSkinURI : "/resource/smarteditor/SmartEditor2Skin.html",
	    fCreator : "createSEditor2",
      	htParams : { // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
         	bUseToolbar : true,
         	bUseVerticalResizer : false,	// 입력창 크기 조절바 
         	bUseModeChanger : true,		// 모드 탭
      }      
   });
	
	//<form>의 submit에 맞춰 스마트에디터 내용 적용
	function submitContents(elClickedObj){
		oEditors.getById["inputText"].exec("UPDATE_CONTENTS_FIELD", []);
 		try {
 			elClickedObj.form.submit();
 		} catch (e) {
	 	}
	}

</script>

<jsp:include page="/view/layout/footer.jsp" />