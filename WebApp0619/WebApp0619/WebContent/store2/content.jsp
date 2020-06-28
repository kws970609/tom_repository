<%@page import="com.study.model.store2.Store"%>
<%@page import="java.util.List"%>
<%@page import="com.study.model.store2.StoreDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! StoreDAO storeDAO = new StoreDAO(); %>
<% 
	String store_id = request.getParameter("store_id");
	
	Store store = storeDAO.select(Integer.parseInt(store_id));
	
	StringBuilder sb = new StringBuilder();
		
	sb.append("{");
	sb.append("\"store_id\":"+store.getStore_id()+",");
	sb.append("\"name\":\""+store.getName()+"\",");
	sb.append("\"addr\":\""+store.getAddr()+"\",");
	sb.append("\"lati\":\""+store.getLati()+"\",");
	sb.append("\"longi\":\""+store.getLongi()+"\",");
	sb.append("\"memo\":\""+store.getMemo()+"\",");
	sb.append("\"icons\":{");
	sb.append("\"icons_id\":\""+store.getIcons().getIcons_id()+"\",");
	sb.append("\"title\":\""+store.getIcons().getTitle()+"\",");
	sb.append("\"filename\":\""+store.getIcons().getFilename()+"\"");
	sb.append("}");
	sb.append("}");

	out.print(sb.toString());
	
%>












































