<%@page import="com.study.async.HotPlace"%>
<%@page import="com.study.async.HotPlaceDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! HotPlaceDAO hotPlaceDAO = new HotPlaceDAO(); %>
<%
	StringBuilder sb = new StringBuilder();
		
	String hotPlace_id = request.getParameter("hotPlace_id");
	
	HotPlace hotPlace = hotPlaceDAO.select(Integer.parseInt(hotPlace_id));
	
	sb.append("{");
	sb.append("\"hotPlace_id\":\""+hotPlace.getHotplace_id()+"\",");
	sb.append("\"name\":\""+hotPlace.getName()+"\",");
	sb.append("\"tel\":\""+hotPlace.getTel()+"\",");
	sb.append("\"addr\":\""+hotPlace.getAddr()+"\",");
	sb.append("\"food\":\""+hotPlace.getFood()+"\"");		
	sb.append("}");


	out.print(sb.toString());

%>