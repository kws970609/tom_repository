<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dev.model.notice.Notice"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String id = "c##java";
	String pw = "android";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

%>
<%
	//오라클 연동하기 select 문 수행~~
	con=DriverManager.getConnection(url,id,pw);
	String sql ="select * from notice order by notice_id desc";
	pstmt = con.prepareStatement(sql);
	rs=pstmt.executeQuery();
	
	//rs의 모든 데이터를 list에 담자!!
	ArrayList<Notice> list = new ArrayList<Notice>();
	
	while(rs.next()){
		Notice notice = new Notice();
		notice.setNotice_id(rs.getInt("notice_id"));
		notice.setTitle(rs.getString("title"));
		notice.setWriter(rs.getString("writer"));
		notice.setContent(rs.getString("content"));
		notice.setRegdate(rs.getString("regdate"));
		notice.setHit(rs.getInt("hit"));
		
		list.add(notice);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
button{
	background:yellow;
	color:red;
}
a{text-decoration:none}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	//'누구를 어떻게 할지의' 문법구조를 갖는다
	//누구를 표현하기 위한 선택자는 css의 선택자를 그대로 지원!!
	$("button").click(function(){
		$(location).attr("href", "/notice/registForm.jsp");
	});
});
</script>
</head>
<body>
	<table width="100%" border="1px">
		<tr>
			<td width="5%">No</td>
			<td width="65%">제목</td>
			<td width="10%">작성자</td>
			<td width="10%">등록일</td>
			<td width="10%">조회수</td>
		</tr>
		<%int total = list.size(); %>
		<%for(int i=0; i<list.size(); i++){%>
		<%Notice notice = list.get(i);%> 
		<tr onMouseOver="this.style.background='cyan'" onMouseOut="this.style.background=''">
			<td><%=total--%></td>
			<td><a href="/notice/content.jsp?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle()%></a></td>
			<td><%=notice.getWriter()%></td>
			<td><%=notice.getRegdate().substring(0,10)%></td>
			<td><%=notice.getHit()%></td>
		</tr>
		<%}%>
		<tr>
			<td colspan="5">
				<button>등록</button>
			</td>
		</tr>
	</table>
</body>
</html>





































