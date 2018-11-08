<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
   $("#btnDelete").click(function(){
      var deactSugg = $(":input:radio[name=DEACT_SUGG]:checked").val();
//       location.href = '/member/logout';
			$("form").submit();
         
   });
   
	$("#btnCancel").click(function() {
		history.go(-1);
	})  
      
   
});
</script>



<style>
#top {
   margin-top: 100px;
}

th {
   background: #ffeff4;
   fonti-size: 12px;
   text-align: left;
   border-top: 1px solid #ccc;
   padding: 5px 10px;
}

td {
   font-size: 12px;
   border-top: 1px solid #ccc;
   padding: 5px 10px;
   color: #000000;
}

table {
   border-bottom: 1px solid #ccc;
}

input {
   vertical-align: middle;
   background: #f2f2f2;
   margin-right: 3px;
}

select {
   width: 70px;
}

div.contentsTop {
   overflow: hidden;
   position: relative;
   height: 55px;
   margin-bottom: 40px;
}

ul.location {
   float: right;
   list-style: none;
}

div.mypage {
   position: relative;
   color: #fff;
   height: 115px;
   background-color: #F3969A;
   height: 115px;
   display: block;
   padding-left: 20px;
   margin-bottom: 2px;
   overflow: hidden;
   width: 180px;
   z-index: 4;
   top: 0;
   left: 0;
   margin-left: 10px;
   text-align: center;
}

div.side {
   margin-left: 10px;
   margin-top: 30px;
}

ul#side-navi {
   width: 200px;
   text-indent: 10px;
   margin-left: 10px;
}

ul#side-navi, ul#side-navi ul {
   margin: 0;
   padding: 0;
   list-style: none;
}

li.group {
   margin-bottom: 3px;
}

li.group div.title {
   height: 35px;
   line-height: 35px;
   background: #F3969A;
   cursor: pointer;
   text-align: center;
}

ul.side-navi-sub li {
   margin-bottom: 2px;
   height: 35px;
   line-height: 35px;
   background: #ffeff0;
   cursor: pointer;
}

ul.side-navi-sub li a {
   display: block;
   width: 100%;
   height: 100%;
   text-decoration: none;
   color: #000;
}
div.text-center {
	display: inline-block;
	margin-left: 300px;
}

#btnDelete{

	border-radius:10px;

    width:75px;

    background-color: #f8585b;

    border: none;

    color:#fff;

    padding: 12px 0;

    text-align: center;

    text-decoration: none;

    display: inline-block;

    font-size: 15px;

    margin: 4px;

    cursor: pointer;

}

#btnCancel{

	border-radius:10px;

    width:75px;

    background-color: #32E4E4;

    border: none;

    color:#fff;

    padding: 12px 0;

    text-align: center;

    text-decoration: none;

    display: inline-block;

    font-size: 15px;

    margin: 4px;

    cursor: pointer;

}


</style>

<div class="row ">
   <div class="col-lg-2" style="margin-left: 20px; margin-top:10px;" >
      <div class="mypage">마이페이지</div>
      <div class="side">
         <ul id="side-navi">
            <li class="group">
               <div class="title">회원정보</div>
               <ul class="side-navi-sub">
                  <li><a href="/mypage/check">개인정보수정</a></li>
                  <li><a href="/mypage/adoption">입양신청내역</a>
                  <li><a href="/mypage/deletcheck">회원탈퇴하기</a>
                  <li><a href="/mypage/updatepasswordcheck">비밀번호변경</a>
                  <li><a href="/mypage/charity">후원내역</a>
               </ul>
            </li>
            <li class="group">
               <div class="title"><a href="/qnaboard/list">1:1 문의</a></div>
            </li>

         </ul>
      </div>


   </div>
   <div class="col-lg-8" >
   <div>
  
      <div id="top" class="">
      <div class="text-center">
  	 <h1>회원탈퇴하기</h1>
  	 <hr>
   	</div>
         <form method="POST" name="delete" id="delete" action="/mypage/delete">

            <table style="width:50%; margin: 0 auto;">
        
               <tr>
                  <td id="title"><input type="radio" name="deactSugg" value="1.사이트에 다시 가입하기위해"/>1.사이트에 다시 가입하기위해</td>
                  
               </tr>

               <tr>
                 <td id="title"><input type="radio" name="deactSugg" value="2.더이상 활동하지않기위해"/>2.더이상 활동하지않기위해</td>
               </tr>

               <tr>
                   <td id="title"><input type="radio" name="deactSugg" value="3.다른사이트로 이전하기위해"/>3.다른사이트로 이전하기위해</td>
               </tr>

               <tr>
                   <td id="title"><input type="radio" name='deactSugg' value="4.기타"/>4.기타</td>
               </tr>


            </table>
	
         </form>
         <div class="text-center">
         <button id="btnDelete" type="submit"  >회원탈퇴</button>
         <button type="button" id="btnCancel">취소</button>
     	</div>    

      </div>

   </div>
   </div>
   <div class="col-lg-2"></div>
</div>
<jsp:include page="/view/layout/footer.jsp" />