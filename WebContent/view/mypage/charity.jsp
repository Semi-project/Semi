<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% response.setHeader("Cache-Controll", "no-cache"); %>   

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
   <div class="col-lg-2" style="margin-left: 20px; margin-top:10px;">
      <div class="mypage">마이페이지</div>
      <div class="side">
         <ul id="side-navi">
            <li class="group">
               <div class="title">회원정보</div>
               <ul class="side-navi-sub">
                  <li><a href="/mypage/check">개인정보수정</a></li>
                  <li><a href="/mypage/adoption">입양신청내역</a>
                  <li><a href="/mypage/delete">회원탈퇴하기</a>
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
   <div class="col-lg-8">
      <div id="top" class="">
         <form method="GET" name="adoption" id="adoption" action="/mypage/adoption">
<h3>후원내역</h3>
<hr>
<table class="table table-hover table-striped table-condensed">
<thead>
<tr>
<th style="width: 10%">결제 종류</th>
<th style="width: 20%">동물번호</th>
<th style="width: 20%">결제금액</th>
<th style="width: 20%">결제자</th>
<th style="width: 30%">결제날짜</th>
</tr>
</thead>

<tbody>

<c:forEach items="${charityList }" var="charity">
<tr>
<td>${charity.pay_method }</td>
<td>${charity.name }</td>
<td>${charity.paid_amount }</td>
<td>${charity.userid }</td>
<td><fmt:formatDate value="${charity.charity_date }" pattern="yyyy-MM-dd"/></td>
</tr>
</c:forEach>

</tbody>



</table>
</form>
           
</div>

</div>
<div class="col-lg-2"></div>
</div>

<div class="text-center">
  <ul class="pagination pagination-sm">
  	<!-- 처음으로 가기 -->
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/mypage/charity?search=${paging.search }" aria-label="First">
        <span aria-hidden="true">&larr;처음</span>
      </a>
    </li>
	</c:if>
	

	
	  
  
  	<!-- 이전 페이지 -->
  	<!-- 첫 페이지라면 버튼 동작 안 되게 만들기 -->
  	<c:if test="${paging.curPage eq 1 }">
    <li class="disabled">
        <span aria-hidden="true">&laquo;</span>
    </li>
    </c:if>
    
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/mypage/charity?curPage=${paging.curPage-1 }&search=${paging.search }" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    
    
    
    
    
    <!-- 페이징 리스트 -->
    <c:forEach
     begin="${paging.startPage }"
     end="${paging.endPage }"
     var="i">

		<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
		<c:if test="${paging.curPage eq i}">          
    	  <li class="active"><a href="/mypage/charity?curPage=${i }&search=${paging.search }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/mypage/charity?curPage=${i }&search=${paging.search }">${i }</a></li>
    	</c:if>
    </c:forEach>




    
    <!-- 다음 페이지 -->
  	<c:if test="${paging.curPage eq paging.totalPage }">
    <li class="disabled">
        <span aria-hidden="true">&raquo;</span>
    </li>
	</c:if>
	
  	<c:if test="${paging.curPage ne paging.totalPage }">
    <li>
      <a href="/mypage/charity?curPage=${paging.curPage+1 }&search=${paging.search }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
</div>
</div>

<jsp:include page="/view/layout/footer.jsp" />