<%@ page contentType="text/html; charset=UTF-8"%>
<%!

%>

<%
	String dan = request.getParameter("dan");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>단 전송</title>

<script>
function send(){
	
	form1.action="/board/receive.jsp"
	form1.submit();
}
</script>
</head>
<body>
	<form name="form1" method="get">
		<input type="text" name = dan placehold="원하는 단입력">
	</form>
	<button onClick ="send()">단 전송 </button>
</body>
</html>









