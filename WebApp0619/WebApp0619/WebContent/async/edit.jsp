<%@page import="com.study.async.HotPlace"%>
<%@page import="com.study.async.HotPlaceDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! HotPlaceDAO hotPlaceDAO = new HotPlaceDAO();%>
<%

	request.setCharacterEncoding("utf-8");
	
	String hotPlace_id = request.getParameter("hotPlace_id");
	String name = request.getParameter("name");
	String tel = request.getParameter("tel");
	String addr = request.getParameter("addr");
	String food = request.getParameter("food");
	
	HotPlace hotPlace = new HotPlace();
	hotPlace.setHotplace_id(Integer.parseInt(hotPlace_id));
	hotPlace.setName(name);
	hotPlace.setTel(tel);
	hotPlace.setAddr(addr);
	hotPlace.setFood(food);
	
	int result = hotPlaceDAO.edit(hotPlace);
	out.print(result);
%>


























