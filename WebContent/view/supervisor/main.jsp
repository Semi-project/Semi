<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#menu_box>div {
   float: left;
   width: 150px;
   height: 150px;
   background-color: red;
   margin: 10px 10px;
}

#menu_box {
   height: 1%;
   display: inline-block;
   text-align: center;
}

#menu_box:after {
   content: ".";
   display: block;
   clear: both;
   visibility: hidden;
   height: 0;
}
</style>
</head>
<body>
   <div id="menu_box">
      <div id="menu1">입양 신청 리스트</div>
      <div id="menu2">입양 보내기 리스트</div>
      <div id="menu3">text_box3</div>
      <br>
      <div id="menu4">text_box4</div>
      <div id="menu5">text_box5</div>
      <div id="menu6">text_box6</div>


   </div>

</body>
</html>