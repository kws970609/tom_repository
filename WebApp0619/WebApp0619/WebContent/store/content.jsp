<%@page import="com.study.model.store.Store"%>
<%@page import="com.study.model.store.StoreDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! StoreDAO storeDAO = new StoreDAO(); %>
<%
	StringBuilder sb = new StringBuilder();

	String store_id = request.getParameter("store_id");
	
	Store store = storeDAO.select(Integer.parseInt(store_id));
	
	sb.append("{");
	sb.append("\"store_id\":\""+store.getStore_id()+"\",");
	sb.append("\"name\":\""+store.getName()+"\",");
	sb.append("\"addr\":\""+store.getAddr()+"\",");
	sb.append("\"lati\":\""+store.getLati()+"\",");
	sb.append("\"longi\":\""+store.getLongi()+"\",");
	sb.append("\"memo\":\""+store.getMemo()+"\",");
	sb.append("\"icons_id\":\""+store.getIcons().getIcons_id()+"\"");
	sb.append("}");
	
	out.print(sb.toString());
%>























