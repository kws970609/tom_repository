<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
</script>
</head>
<body>
<%	
	//아래의 코드에서 request변수는 우리가 선언한적이 없지만 에러가 나징낳고, 사용가능한 이유는
	//sun에서 이미 jsp에서 필요한 필수 객체들의이름을 미리 정해놓았고, 이러한 10여개의 객체를가리켜 jsp내장객체(built-in)이라 한다
	//request, response, session,pageConxtex, out, application, config, exception, page
	//클라이언트가 전송한 파라미터를 이용하여 해당 단을 출력 out.print();
	//HTTP get/post 로 전송되는 모든 파라미터는 String 이다
	int dan = Integer.parseInt(request.getParameter("dan"));
	for(int i=1; i<=9; i++){
		out.print(dan+"*"+i+"="+(dan*i)+"<br>");
	}
%>

</body>
</html>











