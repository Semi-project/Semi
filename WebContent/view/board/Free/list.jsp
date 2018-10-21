<%@page import="dto.board.Free_Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
    <%
	List<Free_Board> blist = (List) request.getAttribute("boardlist");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
		<tr>
			<th>게시글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>날짜</th>
		</tr>
	
<%-- 		<c:forEach var="i" items="<%=blist%>" step="1"> --%>
		<c:forEach var="i" items="${boardlist }">
		<tr>
			<td>${i.boardno}</td>
			<td><a href="/freeboard/view?boardno=${i.boardno }">${i.title}</a></td>
			<td>${i.userid}</td>
			<td>${i.hit}</td>
			<td>${i.insert_Dat}</td>
		</tr>
		</c:forEach>
	</table>
	
	
	<c:if test="${login ne null }">
	<button>글쓰기</button>
	</c:if>
	
	<input type="file" value="파일첨부">
</body>
</html>