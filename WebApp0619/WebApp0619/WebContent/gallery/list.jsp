<%@page import="com.study.model.gboard.GBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.study.model.gboard.GBoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	GBoardDAO gboardDAO = new GBoardDAO();
%>
<%
	List<GBoard>list = gboardDAO.selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
</head>
<body>

	<h2>갤러리 목록</h2>
	
	<table>
		<tr>
			<th>No</th>
			<th>이미지</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%int total=list.size();%>
		<%for(int i=0; i<list.size(); i++){ %>
		<%GBoard gboard=list.get(i); %>
		<tr>
			<td><%=total--%></td>
			<td><img src="/data/<%=gboard.getFilename()%>" width="50px"></td>
			<td><a href="/upload/content.jsp?title_id=<%=gboard.getGboard_id()%>"><%=gboard.getTitle()%></td>
			<td><%=gboard.getWriter()%></td>
			<td><%=gboard.getRegdate().substring(0,10) %></td>
			<td><%=gboard.getHit()%></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="6">
				<button onClick="location.href='/gallery/registForm.jsp'">등록</button>
			</td>
		</tr>
	</table>

</body>
</html>









