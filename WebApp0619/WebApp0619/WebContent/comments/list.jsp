<%@page import="com.study.model.news.News"%>
<%@page import="java.util.List"%>
<%@page import="com.study.model.news.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! NewsDAO newsDAO = new NewsDAO(); %>
<%! News news = new News(); %>
<%
	//목록 가져오기
	List<News> newsList = newsDAO.selectAll();
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
	position:relative;
	left:-1500px;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
//문서가 로드되어야 생성된 DOM에 접근할 수 있으므로...
$(function(){
	$("table").animate({
		left:'0px'
	});
	
});
</script>
</head>
<body>

	<table>
		<tr>
			<th width="5%">No</th>
			<th width="60%">제목</th>
			<th width="10%">작성자</th>
			<th width="20%">등록일</th>
			<th width="5%">조회수</th>
		</tr>
		<%int total=newsList.size(); %>
		<%for(int i=0; i<newsList.size(); i++) {%>
		<%News news= newsList.get(i); %>
		<tr>
			<td><%=total-- %></td>
			<td><a href="/comments/content.jsp?news_id=<%=news.getNews_id()%>"><%= news.getTitle()%>[<%=news.getCnt()%>]</a></td>
			<td><%= news.getWriter() %></td>
			<td><%=news.getRegdate().substring(0,10) %></td>
			<td><%=news.getHit() %></td>
		</tr>
		<%}%>>
		<tr>
			<td colspan="5">
				<button onClick="location.href='/comments/registForm.jsp';">글 등록</button>
			</td>
		</tr>
	</table>

</body>
</html>
















