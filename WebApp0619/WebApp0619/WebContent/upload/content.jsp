<%@page import="com.study.model.gboard.GBoard"%>
<%@page import="com.study.model.gboard.GBoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!GBoardDAO gboardDAO = new GBoardDAO();%>
<%
	int gboard_id = 0;
	gboard_id = Integer.parseInt(request.getParameter("gboard_id"));
	GBoard gboard = gboardDAO.select(gboard_id);

%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>

<script>
$(function(){
	CKEDITOR.replace('content');	
});

//파일을 포함한 대량의 데이터를 웹서버 요청시 가져가려면?
// http 전송방식중 post를 이용해야 한다!!
//전송하는 데이터중 파일이 포함 되어잇을경우 (바이너리 파일) 
//form 속성에  !!! multipart/form-data 지정 되어 있어야함!!
function edit(){
	$("form").attr({
		"method":"post",
		"enctype":"multipart/form-data",
		"action":"/upload/edit"
	});
	$("form").submit();
}
</script>

</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form>
			<input type="text" name="title" value=<%=gboard.getTitle()%>"> <input
				type="text" name="writer" value=<%=gboard.getWriter()%>"> <input
				type="file" name="imgFile" value=<%=gboard.getFilename()%>/>
			<textarea id="content" name="content" "style="height: 200px"><%=gboard.getContent()%></textarea>
			<input type="button" value="수정" onClick="edit()"> 
			<input type="button" value="삭제" onClick="delete()"> 
			<input type="button" value="리스트">
		</form>
	</div>

</body>
</html>