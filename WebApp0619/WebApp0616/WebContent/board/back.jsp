<%@ page contentType="text/html; charset=UTF-8"%>
<%! 
	int x=5;
	public int getX(){
		return x;
	}
%>
<%
	//스크립틀릿 영역
	//서블릿의 서비스 메서드 영역!!
	String color = request.getParameter("color");
	out.print("전송된 색상은 "+color);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배경색 바꿔보기</title>
<script>
function setBg(){
	//기존에는 자바스크립트로 DOM을 이용하여 처리했다면,
	//오늘은 jsp로 제어해보자!!
	alert(getMsg());
	
	//서버에 입력양식 전송하기!!
	//서버측에다 재요청을 시도하는것이다!!!
	form1.action="/board/back.jsp"; //서버의 url	
	form1.submit();// 전송
}
</script>
</head>
<body bgcolor="<%=color%>">
	<form name= "form1" method="get">
		<select name="color">
			<option value="red">Red</option>
			<option value="yellow">Yellow</option>
			<option value="blue">Blue</option>
		</select>
	<form>
<button onClick="setBg()">배경색 변경</button>
</body>
</html>
























