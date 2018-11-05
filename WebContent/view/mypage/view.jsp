<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	var result = '${result}';
	var successMsg = '${successMsg}';
	var failMsg = '${failMsg}';
	
	if(result == 'success'){
		alert(successMsg);
	}
	else if(result == 'fali'){
		alert(failMsg);
	}
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

ul.side-navi-sub li:hover {
   background: #cf0;
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
      <div id="top" class="">
         <form method="POST" name="mypage" id="mypage" action="/mypage/view">

            <table style="width: 80%; margin: 0 auto;">
               <tr>
                  <td id="title">아이디</td>
                  <td>${memberView.userid }</td>
               </tr>

<!--                <tr> -->
<!--                   <td id="title">비밀번호</td> -->
<%--                   <td><input type="password" value="${memberView.userpw }"  readonly="readonly"/></td> --%>
<!--                </tr> -->

               <tr>
                  <td id="title">이름</td>
                  <td>${memberView.name }</td>
               </tr>

               <tr>
                  <td id="title">성별</td>
                  <td>${memberView.gender }</td>
               </tr>

               <tr>
                  <td id="title">생일</td>
                  <td>${memberView.userbirth }</td>
               </tr>

               <tr>
                  <td id="title">이메일</td>
                  <td>${memberView.email }
                  </td>
               </tr>

               <tr>
                  <td id="title">휴대전화</td>
                  <%-- <td>${memberView.phone }</td> --%>
                  <td>${memberView.phone} </td>     
               </tr>
               <tr>
                  <td id="title">주소</td>
                  <td>${memberView.address }</td>
               </tr>


            </table>

         </form>
      </div>

   </div>
   <div class="col-lg-2"></div>
</div>
<jsp:include page="/view/layout/footer.jsp" />