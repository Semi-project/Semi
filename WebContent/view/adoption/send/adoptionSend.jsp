<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
      elPlaceHolder : "ir1",
      //SmartEditor2Skin.html 파일이 존재하는 경로 
      sSkinURI : "/smarteditor/SmartEditor2Skin.html",
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
	$("#contents").submit();
});

</script>



<h3>네이버 스마트 에디터 2.0</h3>
<hr>

<form id="frm" action="send.jsp" method="post">
      <table width="100%">
         <tr>
            <td>제목</td>
            <td><input type="text" id="title" name="title" /></td>
         </tr>
         <tr>
            <td>내용</td>
            <td><textarea rows="10" cols="30" id="ir1" name="content"
                  style="width: 766px; height: 412px;"></textarea></td>
         </tr>
         <tr>
            <td colspan="2"><input type="button" id="save" value="저장" /> <input
               type="button" value="취소" /></td>
         </tr>
      </table>
   </form>


<jsp:include page="/view/layout/footer.jsp" />