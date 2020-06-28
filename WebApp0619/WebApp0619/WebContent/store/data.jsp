<%@page import="com.study.model.store.StoreDAO"%>
<%@page import="com.study.model.store.Store"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! StoreDAO storeDAO = new StoreDAO(); %>
<%
StringBuilder sb = new StringBuilder();

List<Store> list = storeDAO.selectAll();
sb.append("{");
sb.append("\"storeList\":[");

	for(int i=0; i<list.size(); i++){
		Store store = list.get(i);
		
		sb.append("{");
		sb.append("\"store_id\":\""+store.getStore_id()+"\",");
		sb.append("\"name\":\""+store.getName()+"\",");
		sb.append("\"addr\":\""+store.getAddr()+"\",");
		sb.append("\"lati\":\""+store.getLati()+"\",");
		sb.append("\"longi\":\""+store.getLongi()+"\",");
		sb.append("\"memo\":\""+store.getMemo()+"\",");
		sb.append("\"filename\":\""+store.getIcons().getFilename()+"\"");
		
		if(list.size()-1!=i){
			sb.append("},");
		}else{
			sb.append("}");
		}
	}
	sb.append("]");
	sb.append("}");

	out.print(sb.toString());

%>















