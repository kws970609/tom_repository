<%@page import="java.sql.DriverManager"%>
<%@page import="com.dev.model.notice.Notice"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
//선언부 == 서블릿의 멤버 영역
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String id = "c##java";
    String pw = "android";
    
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
%>
<%
	//게시물의 pk를 클라이언트로부터 넘겨받자!!

	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection(url, id, pw);
	int notice_id=0;
	notice_id = Integer.parseInt(request.getParameter("notice_id"));
	
	//조회수 업데이트
	String sql="update notice set hit= hit+1 where notice_id="+ notice_id;
	pstmt = con.prepareStatement(sql);
	pstmt.executeUpdate();
	
	sql="select * from notice where notice_id = "+notice_id;
	pstmt = con.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	Notice notice = new Notice();
	if(rs.next()){ //레코드가 있다면??
		notice.setNotice_id(rs.getInt("notice_id"));
		notice.setTitle(rs.getString("title"));
		notice.setWriter(rs.getString("writer"));
		notice.setContent(rs.getString("content"));
		notice.setRegdate(rs.getString("regdate"));
		notice.setHit(rs.getInt("hit"));
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록 폼</title>
<style>

div{
	margin:auto;
	width:500px;
	height:500px;
	border: 2px solid blue;
	text-align:center;
}
div input,textarea{
	width:98%;
}
textarea{
	height:350px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$("#bt_list").click(function(){
		//history.back();//이전페이지 돌아가기 (캐쉬를 보여주는거기 때문에, 누군가가 글을썼을때, 갱신된것을 보여주지 않는다)
		$(location).attr("href","/board/list.jsp");
	});
	
	$("#bt_del").click(function(){
		if(confirm("삭제하시겠습니까?")){
		  del();
		}
	});
	$("#bt_edit").click(function(){
		if(confirm("수정하시겠습니까?")){
		  edit();
		}
	});
});
function del(){
	//alert("삭제요청 시도");
	//get or post?
	location.href="/board/delete.jsp?notice_id="+<%=notice.getNotice_id()%>;
}
function edit(){
	//수정을 담당하는 서블릿에게 요청!!
	$("form").attr("method", "post"); //양이 많아서
	$("form").attr("action", "/board/edit.jsp");
	$("form").submit();
	

}
</script>
</head>
<body bgcolor="yellow">
	<div>
		<form>
			<input type="hidden" name="notice_id" value="<%=notice.getNotice_id()%>"/>
			<input type="text" name="title" value="<%=notice.getTitle() %>"/>
			<input type="text" name="writer" value="<%=notice.getWriter() %>"/>
			<textarea name="content"><%=notice.getContent() %></textarea>
		</form>
		<button id="bt_list" >리스트</button>
		<button id ="bt_edit">수정</button>
		<button id ="bt_del">삭제</button>
	</div>
</body>
</html>
<%
	if(rs!=null){rs.close();}
	if(pstmt!=null){pstmt.close();}
	if(con!=null){con.close();}
%>




















