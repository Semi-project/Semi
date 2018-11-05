<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>







<%
// init
	int totalRecordCount; // 총 글의 갯수
	int pageNumber; // 현재 페이지번호 
	int pageCountPerScreen; // 스크린당 페이지수[블록수]
	int recordCountPerPage; // 페이지당 글의 수 

// 데이터 받기

	// 총글의 갯수
		String st1 = request.getParameter("totalRecordCount");
		if(st1 ==null) totalRecordCount = 0;
		else totalRecordCount = Integer.parseInt(st1);
	
	// 현재 페이지번호
		String st2 = request.getParameter("pageNumber");
		if(st2 ==null) pageNumber = 0;
		else pageNumber = Integer.parseInt(st2);
	
	// 스크린당 페이지수[블록수]
		String st3 = request.getParameter("pageCountPerScreen");
		if(st3 ==null) pageCountPerScreen = 0;
		else pageCountPerScreen = Integer.parseInt(st3);
	
	// 페이지당 글의 수 
		String st4 = request.getParameter("recordCountPerPage");
		if(st4 ==null) recordCountPerPage = 0;
		else recordCountPerPage = Integer.parseInt(st4);

		
// 총 페이지 갯수 구하기
	int totalPageCount = totalRecordCount / recordCountPerPage ;  //6
	
	// 만약 총 글의 갯수가 12개면 , 총 페이지의 갯수는 2 개  [ recordCountPerPage는 10이기때문 ] 12/10 = 1
	if(totalRecordCount%recordCountPerPage !=0){
		totalPageCount++;
	}

	
// 시작페이지
	int screenStartPageIndex = ((pageNumber+1)/pageCountPerScreen)*pageCountPerScreen;
	// 예를들어 pageNumber(=0) 에 +1 을 한후 10으로 나누게되면 0이나온다 0 *10 = 0으로 나오게됨
	// 만약 pageNumber가 9면(즉 10페이지) 1*10 = 10으로 나오게됨
	
	
// 마지막페이지
	int screenEndPageIndex = ((pageNumber+1)/pageCountPerScreen)*pageCountPerScreen+pageCountPerScreen;
	
	//[1][2][3][4][5][6][7][8][9][10]
	//초기에는 블록이 10개로 잡힘 
	if(screenEndPageIndex > totalPageCount){
		screenEndPageIndex =  totalPageCount; //즉 마지막페이지가 전체 페이지보다 클경우, 마지막페이지를 전체페이지의갯수로정한다
		//[1][2]
	}
	
	
// [1][2] ~ [10] 에서 이전 페이지로 이동할 시에 계산하는 소스
// pageNumber:10이면 -> 11블록	그러면 다음 조건은 거짓임 ->	pageNumber:9 면.. 다음 조건은 참으로    
if (((pageNumber+1) % pageCountPerScreen) == 0)
{//		9					10								
    screenStartPageIndex = (((pageNumber+1) / pageCountPerScreen) * pageCountPerScreen) - pageCountPerScreen;
    //		0					9			/		10			*		10						10
    screenEndPageIndex = pageNumber+1;
    //		10				9
}

%>

<div style="float: left; width: 96%; text-align: center;">
	<!-- 처음페이지로 이동링크 -->
	<a href="#none" title="처음페이지" onclick="goPage('0')">
		<img alt="처음페이지" src="/img/freeboard/leftDouble.gif" style="width: 9px; height: 9px;">
	</a>
	&nbsp;&nbsp;
	<!-- 이전페이지로 이동 -->
	<%
	if(screenStartPageIndex>1){
		//2페이지 이상이어야함
		%>
		<a href="#none" title="이전페이지" onclick="goPage('<%=screenStartPageIndex-1%>')">
			<img alt="이전페이지" src="/img/freeboard/left.gif" style="width: 9px; height: 9px;">
		</a>
		&nbsp;&nbsp;
		<%
	}
	%>
	<!-- 페이지 블록[1][2][3]... 만드는부분 -->
	<%
	for(int i=screenStartPageIndex; i<screenEndPageIndex; i++){
		//현재 페이지넘버 같을때 현재페이지가1이면  1 [2] [3]이런식으로 만들기 
		if(i == pageNumber){
			%>
			<span style="font-size: 13pt; color: #F3969A; font-weight: bold;">
				<!-- 0페이지부터 시작하기때문에 +1을 해주도록한다. -->
				<%=i+1 %> 
			</span>
			&nbsp;&nbsp;
			<%
		}
		//다를때 [2] [3] .. 들을 생성 (anchor추가)
		else{
			
			%>
			<a href="#none" title="<%=i+1%>페이지" onclick="goPage('<%=i%>')"
			style="font: 8pt; color: #000000; font-weight: normal;">
				<%=i+1%>
			</a>
			&nbsp;&nbsp;
			<%
		}
	}
	%>
	<!-- 다음페이지 -->
	<%
	System.out.println("screenEndPageIndex="+screenEndPageIndex);
	System.out.println("totalPageCount="+totalPageCount);
	if(screenEndPageIndex < totalPageCount){
		
		%>
		<a href="#none" title="다음페이지" onclick="goPage('<%=pageNumber+1%>')">
			<img alt="다음페이지" src="/img/freeboard/right.gif" style="width: 9px; height: 9px">
		</a>
		&nbsp;
		<%
	}
	//마지막페이지계산
	int end_page = 0;
	if(totalPageCount>0){
		end_page = totalPageCount-1;
		
	}
	%>
	<a href="#none" title="마지막페이지" onclick="goPage('<%=end_page%>')">
		<img alt="마지막페이지" src="/img/freeboard/rightdouble.gif" style="width: 9px; height: 9px">
	</a>   
</div>    
  
<!-- 단순글자 (우측하단) 현재페이지 표시-->
<%-- <div style="float: left; width: 4%; text-align: center;">
	<span style="font-size: 1em; color: #000000">

	 <%=pageNumber+1%> p 
	</span>

</div> --%>