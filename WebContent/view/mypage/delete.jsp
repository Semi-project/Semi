<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/view/layout/header.jsp" />    
<!DOCTYPE html>
<html>
<head>
    <title>탈퇴 화면</title>
    
    <style type="text/css">
        table{
            margin-left:auto; 
            margin-right:auto;
            border:3px solid skyblue;
        }
        
        td{
            border:1px solid skyblue
        }
        
        #title{
            background-color:skyblue
        }
        
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
    
       
 <script type="text/javascript">
        // 비밀번호 미입력시 경고창
        function checkValue(){
            if(!document.deleteform.userpw.value){
                alert("비밀번호를 입력하지 않았습니다.");
                return false;
            }
        }
 </script>
 
 
</head>
<body>

<div class="row ">
   <div class="col-lg-2">
      <div class="mypage">마이페이지</div>
      <div class="side">
         <ul id="side-navi">
            <li class="group">
               <div class="title">회원정보</div>
               <ul class="side-navi-sub">
                  <li><a href="/mypage/update">개인정보수정</a></li>
                  <li><a href="/mypage/adoption">입양신청내역</a>
                  <li><a href="/mypage/delete">회원탈퇴하기</a>
               </ul>
            </li>
            <li class="group">
               <div class="title"><a href="/qnaboard/list">1:1 문의</a></div>
            </li>

         </ul>
      </div>
      
      
</div>
<div style="text-align: center;">
<br><br>
    <b><font size="6" color="gray">내 정보</font></b>
    <br><br><br>
 
    <form name="deleteform" method="post" action="/mypage/delete"
        onsubmit="return checkValue()">
 
        <table>
            <tr>
                <td bgcolor="skyblue">비밀번호</td>
                <td><input type="password" name="userpw" maxlength="50"></td>
            </tr>
        </table>
        
        <br> 
        <input type="button" value="취소" />
        <input type="submit" value="확인" /> 
    </form>
   </div>
   </div>
</body>
</html>

<jsp:include page="/view/layout/footer.jsp" />
