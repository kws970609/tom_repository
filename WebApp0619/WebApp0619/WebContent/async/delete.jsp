<%@page import="com.study.async.HotPlace"%>
<%@page import="com.study.async.HotPlaceDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!HotPlaceDAO hotPlaceDAO = new HotPlaceDAO();%>
<%
	request.setCharacterEncoding("utf-8");

	int hotPlace_id = Integer.parseInt(request.getParameter("hotPlace_id"));
	HotPlace hotPlace = new HotPlace();
	hotPlace.setHotplace_id(hotPlace_id);
	
	int result = hotPlaceDAO.delete(hotPlace);
	out.print(result);
%>





