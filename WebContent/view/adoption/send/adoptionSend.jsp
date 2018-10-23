<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- header jsp 적용 -->
<jsp:include page="/view/layout/header.jsp" />
<!-- jquery -->
<script src="https://code.jquery.com/jquery.min.js"></script>
<!-- 네이버 스마트에디터 2.0 -->
<script type="text/javascript" src="/view/adoption/send/se2/js/HuskyEZCreator.js" charset="UTF-8"></script>

<script type="text/javascript">

// 스마트에디터
var oEditors = [];
$(function() {
   nhn.husky.EZCreator.createInIFrame({
      oAppRef : oEditors,
      elPlaceHolder : "inputText",
      //SmartEditor2Skin.html 파일이 존재하는 경로 
      sSkinURI : "/resource/smarteditor/SmartEditor2Skin.html",
      htParams : { // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
         bUseToolbar : true,
         // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
         bUseVerticalResizer : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
         bUseModeChanger : true,
         fOnBeforeUnload : function() {
         }
      },
      fOnAppLoad : function() { //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용 
         oEditors.getById["ir1"].exec("PASTE_HTML",
               [ "----" ]);
      },
      fCreator : "createSEditor2"
   });
});
	
// 저장버튼
$("#save").click(function(){
	oEditors.getById["inputText"].exec("UPDATE_CONTENTS_FIELD", []);
 	$("#frm").submit();
});

</script>


<div>
<h3>입양 보내실 동물</h3>
<hr>
<div>
	<form id="frm" action="/adoption/send/insert" method="post">
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
            	<td>품종</td>
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
            		<input type="button" id="save" value="저장" /> 
            		<input type="button" value="취소" />
               </td>
			</tr>
		</table>
	</form>
</div>
</div>
<jsp:include page="/view/layout/footer.jsp" />