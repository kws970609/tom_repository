<%@ page contentType="text/html; charset=UTF-8"%>
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
	$("#bt_regist").click(function(){
		regist();
	});
});
function regist(){
	$("form").attr("method","post");
	$("form").attr("action","/board/regist");
	$("form").submit();
}
</script>
</head>
<body>
	<div>
		<form>
			<input type="hidden" name="notice_id"/>
			<input type="text" name="title" placeholder="제목 입력"/>
			<input type="text" name="writer" placeholder="작성자 입력"/>
			<textarea placeholder="내용 작성..." name="content"></textarea>
		</form>
		<button id="bt_list" >리스트</button>
		<button id ="bt_regist">등록</button>
	</div>
</body>
</html>
