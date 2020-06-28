<%@page import="com.study.async.HotPlaceDAO"%>
<%@page import="com.study.async.HotPlace"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! HotPlaceDAO hotplaceDAO= new HotPlaceDAO(); %>
<%
	//클라이언트가 전송한 파라미터 값들을 이용하여 오라클에 넣기!!
	//서버측에서는 클라이언트의 통신방식이 동기 or 비동기 중요하지않음!!
	request.setCharacterEncoding("utf-8");
	
	String hotPlace_id = request.getParameter("hotPlace_id");
 	String name =	 request.getParameter("name");
 	String tel =	 request.getParameter("tel");
 	String addr =	 request.getParameter("addr");
 	String food =	 request.getParameter("food");
	
 	System.out.print(name);
 	System.out.print(tel);
 	System.out.print(addr);
 	System.out.print(food);
 	
 	HotPlace hotPlace = new HotPlace();
 	hotPlace.setHotplace_id(Integer.parseInt(hotPlace_id)); //스트링인 id값을 int로 변환해서 넣어주기
 	hotPlace.setName(name);
 	hotPlace.setTel(tel);
 	hotPlace.setAddr(addr);
 	hotPlace.setFood(food);
 	
 	int result = hotplaceDAO.insert(hotPlace);
 	
 	if(result!=0){
		out.print("<script>");
		out.print("alert('업로드 성공');");
		out.print("</script>");		
	}
 	
%>