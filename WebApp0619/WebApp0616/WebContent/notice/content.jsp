<%@page import="com.dev.model.board.NoticeDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.dev.model.notice.Notice"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
NoticeDAO noticeDAO = new NoticeDAO();
%>
<%
	//게시물의 pk를 클라이언트로부터 넘겨받자!!
	
	int notice_id =0;
	
	notice_id = Integer.parseInt(request.getParameter("notice_id"));
	
	out.print(noticeDAO);
	noticeDAO.select(notice_id);
	Notice notice =noticeDAO.select(notice_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록 폼</title>
<style>
body{
	font-size:9pt;
}

div{
	margin:auto;
	width:600px;
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
	location.href="/notice/delete.jsp?notice_id="+<%=notice.getNotice_id()%>;
}
function edit(){
	//수정을 담당하는 서블릿에게 요청!!
	$("form").attr("method", "post"); //양이 많아서
	$("form").attr("action", "/notice/edit.jsp");
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
		<div>
			<table border="1px" width="100%">
				<tr>
					<td width="65%"><input type="text" placehold="댓글"/></td>
					<td width="25%"><input type="text" placehold="작성자"/></td>
					<td width="10%"><input type="button" value="댓글등록"/></td>
				</tr>
				<tr>
					<td>댓글</td>
					<td>작성자</td>
					<td>등록일</td>
				</tr>
			</table>	
		</div>
	</div>
</body>
</html>




















