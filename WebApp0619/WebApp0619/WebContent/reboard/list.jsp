<%@page import="com.study.model.reboard.ReBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.study.model.reboard.ReBoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!ReBoardDAO reboardDAO = new ReBoardDAO(); %>
<%
	List<ReBoard> boardList = reboardDAO.selectAll();
	//페이징 처리에 필요한 변수 선언 및 페이지 분할 계산
	int currentPage =1; //현재 페이지
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int totalRecord=boardList.size(); //총 레코드 수
	int pageSize=10; //페이지당 보여질 레코드 수
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);
	int blockSize =10; //블럭당 보여질 페이지 수
	int firstPage=currentPage-(currentPage-1)%blockSize; //블럭당 첫 페이지
	int lastPage=firstPage+(blockSize-1);
	int num=totalRecord-((currentPage-1)*pageSize); //페이지당 시작 번호
%>
<%="현재 페이지는 "+currentPage %>
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
	left:0px;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
.pageStyle{
	font-size: 15pt;
	font-weight: bold;
	color: blue;	
}
a{text-decoration: none}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
//문서가 로드되어야 생성된 DOM에 접근할 수 있으므로...
$(function(){
	$("button").click(function(){
		location.href="/reboard/registForm.jsp";
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
		<%for(int i=1; i<=pageSize; i++){%>
		<%if(num<1)break; %>
		<tr>
			<td><%=num-- %></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5">
				<button onClick="location.href='/comments/registForm.jsp';">글 등록</button>
			</td>
		</tr>
		<tr>
			<td colspan="5" style="text-align: center;">
				<a href="/reboard/list.jsp">◀</a>
				<%for(int i=firstPage; i<=lastPage; i++){ %>
				<%if(i>totalPage) break; %>
				<a <%if(i==currentPage){ %> class="pageStyle"<%} %> href="/reboard/list.jsp?currentPage=<%=i %>">[<%=i %>]</a>
				<%} %>
				<a href="/reboard/list.jsp">▶</a>
			</td>
		</tr>
	</table>
</body>
</html>
