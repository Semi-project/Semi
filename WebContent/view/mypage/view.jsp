<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:include page="/view/layout/header.jsp" />
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
   color: #0000ff;
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
   <div class="col-lg-2">
      <div class="mypage">마이페이지</div>
      <div class="side">
         <ul id="side-navi">
            <li class="group">
               <div class="title">회원정보</div>
               <ul class="side-navi-sub">
                  <li><a href="#">개인정보</a></li>
                  <li><a href="#">후원내역</a></li>
                  <li><a href="#">후원 및 결제정보</a></li>
               </ul>
            </li>
            <li class="group">
               <div class="title">1:1 문의</div>
            </li>

         </ul>
      </div>


   </div>
   <div class="col-lg-8">
      <div id="top" class="">
         <form method="GET" name="mypageFrm" action="/mypage/view">

            <table style="width: 80%; margin: 0 auto;">
               <tr>
                  <th><label for="name">이름 </label></th>
                  <td><input type="text" name="name" id="name" size="20px"
                     value="${name}" readonly="readonly" /></td>
               </tr>

               <tr>
                  <th><label for="userid">아이디</label></th>
                  <td><input type="text" name="userid" id="userid" size="20px"
                     value="${userid }" readonly="readonly" />
               </tr>

               <tr>
                  <th><label for="userpw">비밀번호 </label></th>
                  <td><input type="password" name="userpw" id="userpw"
                     size="20px" value="${userpw }" readonly="readonly" /></td>
               </tr>

               <tr>
                  <th><label for="userNewpw">새비밀번호 </label></th>
                  <td><input type="password" name="userNewpw" id="userNewpw"
                     size="20px" /></td>
               </tr>

               <tr>
                  <th><label for="userpwCheck">비밀번호 확인 </label></th>
                  <td><input type="password" name="userCheck" id="userpwCheck"
                     size="20px" /></td>

               </tr>

               <tr>
                  <th><label for="postcode">우편번호</label></th>
                  <td><input type="text" name="postcode" id="postcode" size="5"
                     value="${postcode }"> <input type="button" id="btn"
                     value="우편번호검색"></td>
               </tr>

               <tr>
                  <th><label for="home">집주소</label></th>
                  <td><input type="text" name="home" id="home" size="60"
                     value="${home }" /> <span id="guide" style="color: #999"></span>
                  </td>
               </tr>

               <tr>
                  <th><label for="homeAddress">상세주소 </label></th>
                  <td><input type="text" name="homeAddress" id="homeAddress"
                     size="60" value="${homeAddress }"></td>
               </tr>


               <tr>
                  <th><label for="phone1">연락처 </label></th>
                  <td><select id="phone1">
                        <option value=""></option>
                        <option value="02">02</option>
                        <option value="031">031</option>
                        <option value="032">032</option>
                        <option value="033">033</option>
                        <option value="041">041</option>
                        <option value="042">042</option>
                        <option value="043">043</option>
                        <option value="051">051</option>
                        <option value="052">052</option>
                        <option value="053">053</option>
                        <option value="054">054</option>
                        <option value="055">055</option>
                        <option value="061">061</option>
                        <option value="062">062</option>
                        <option value="063">063</option>
                        <option value="064">064</option>
                        <option value="070">070</option>
                  </select> - <input type="text" name="phone2" id="phone2" size="5"
                     value="${phone2 }">- <input type="text" name="phone3"
                     id="phone3" size="6" value="${phone3 }"></td>
               </tr>

               <tr>
                  <th><label for="smartPhone">휴대폰 </label></th>
                  <td><select id="smartPhone" name="smartPhone">
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="019">019</option>
                  </select> -<input type="text" name="smartPhone1" id="smartPhone1" size="5"
                     value="${smartPhone1 }"> -<input type="text"
                     name="smartPhone2" id="smartPhone2" size="5"
                     value="${smartPhone2 }"></td>
               </tr>
               <tr>
                  <th><label for="email">이메일 </label></th>
                  <td><input type="text" name="email" id="email" size="10"
                     value="${email }">@ <select id="email2">
                        <option value=""></option>
                        <option value="@gmail.com">gmail.com</option>
                        <option value="@naver.com">naver.com</option>
                        <option value="@hanmail.com">hanmail.com</option>
                  </select> <input type="button" id="check2" value="중복확인"></td>
               </tr>

            </table>

         </form>
      </div>

   </div>
   <div class="col-lg-2"></div>
</div>
<jsp:include page="/view/layout/footer.jsp" />