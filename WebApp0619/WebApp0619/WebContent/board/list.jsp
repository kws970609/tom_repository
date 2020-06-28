<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//데이터를 나누어서 보여주는 기법을 페이징 처리라 하며,
	//이 처리에 필요한 로직!!
	
	int currentPage=1; //현재 페이지
	
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));	
	}
	
	int totalRecord = 26; //총 레코드 수!!
	int pageSize = 10; //한 페이지당 보여줄 레코드 수!!
	int totalPage = (int)Math.ceil((double)totalRecord/pageSize); //총 페이지 수!!
	int blockSize=10; //한 블럭당 보여질 페이지 수
	int firstPage=currentPage-(currentPage-1)%blockSize; //각 블럭의 시작 페이지
	int lastPage=firstPage+(blockSize-1); //각 블럭의 마지막 페이지
	int num= totalRecord-(currentPage-1)*10; //페이지당 시작번호
%>
<%="totalPage :"+totalPage+"<br>" %>
<%
	out.print("현재 "+currentPage+" 페이지입니다");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8"></meta>
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
a{
	text-decoration: none;
}
.pageNum{
	font-size: 20pt;
	font-weight: bold;
	color: red;
}
</style>
</head>
<body>

<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  <%for(int i=1; i<=pageSize; i++) { %>
  <% if(num<1)break;%>
  <tr>
    <td><%=num-- %></td>
    <td>제목</td>
    <td>홍길동</td>
    <td>2020-06-26</td>
    <td>457</td>
  </tr>
  <%} %>
  <tr>
  	<td colspan="5" align="center" style="text-align: center">
  		<%if(firstPage-1>1) {%>
  			<a href="/board/list.jsp?currentPage=<%=firstPage-1%>">◀</a>
  		<%}else { %>
  			<a href="javascript:alert('이전페이지가 없습니다');">◀</a>
  		<%} %>
  		
  		<%for(int i=firstPage; i<=lastPage; i++) { %> 
  		<%if(i>totalPage) break; %>
  		<a <%if(currentPage==i){%> class ="pageNum" <%}%> href="/board/list.jsp?currentPage=<%=i %>">[<%=i%>]</a>
  		<%}%>
  		
  		<%if(lastPage+1>totalPage) { %>
  			<a href="javascript:alert('마지막 페이지입니다');">▶</a>
  		<%}else{ %>
  			<a href="/board/list.jsp?currentPage=<%=lastPage+1%>">▶</a>
  		<%} %>
  	</td>
  </tr>

</table>

</body>
</html>
