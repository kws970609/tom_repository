<%@ page contentType="text/html;charset=UTF-8"%>

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
<script src="//cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
<script>
	$(function() {
		CKEDITOR.replace("content");
		
		//등록버튼
		$($("input[type='button']")[0]).click(function(){
			//글 등록을 서버에 요청한다!! (jsp or servlet)
			$("form").attr({
				"action":"/reboard/regist.jsp",
				"method":"post"
			});
			$("form").submit();
		});
		
		//목록버튼
		$($("input[type='button']")[1]).click(function(){
			location.href="/reboard/list.jsp";
		});
		
	});
</script>
</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form>
			<input type="text" id="title" name="title"placeholder="title"> 
				<input type="text" id="writer" name="writer" placeholder="writer">
			<textarea id="content" name="content" placeholder="Write content"
				style="height: 200px"></textarea>
			<input type="button" value="등록">
			<input type="button" value="목록">
		</form>
	</div>

</body>
</html>
























