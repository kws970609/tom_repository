<%@page import="com.study.model.store2.Store"%>
<%@page import="java.util.List"%>
<%@page import="com.study.model.store2.StoreDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! StoreDAO storeDAO = new StoreDAO(); %>
<% 
	List<Store> storeList = storeDAO.selectAll();
	
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append("\"storeList\":[");
	for(int i=0; i<storeList.size();i++){
		Store store = storeList.get(i);
		
		sb.append("{");
		sb.append("\"store_id\":"+store.getStore_id()+",");
		sb.append("\"name\":\""+store.getName()+"\",");
		sb.append("\"addr\":\""+store.getAddr()+"\",");
		sb.append("\"lati\":\""+store.getLati()+"\",");
		sb.append("\"longi\":\""+store.getLongi()+"\",");
		sb.append("\"memo\":\""+store.getMemo()+"\",");
		sb.append("\"icons_id\":"+store.getIcons().getIcons_id()+",");
		sb.append("\"title\":\""+store.getIcons().getTitle()+"\",");
		sb.append("\"filename\":\""+store.getIcons().getFilename()+"\"");
		
		if(i<storeList.size()-1){
			sb.append("},");
		}else{
			sb.append("}");
		}
	}
	sb.append("]");
	sb.append("}");
	
	out.print(sb.toString());
	
%>












































