<%@page import="java.util.List"%>
<%@page import="com.study.async.HotPlace"%>
<%@page import="com.study.async.HotPlaceDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! HotPlaceDAO hotPlaceDAO = new HotPlaceDAO(); %>
<%
StringBuilder sb = new StringBuilder();
/*
sb.append("{");
sb.append("\"storeList\":[");
sb.append("{");
sb.append("\"name\":\"종로김밥\",");
sb.append("\"tel\":\"02-730-9888\",");
sb.append("\"addr\":\"서울시 종로구\",");
sb.append("\"food\":\"삼각김밥\"");
sb.append("},");
sb.append("{");
sb.append("\"name\":\"춘천닭갈비\",");
sb.append("\"tel\":\"061-332-5555\",");
sb.append("\"addr\":\"춘천\",");
sb.append("\"food\":\"매운닭갈비\"");
sb.append("}");
sb.append("]");
sb.append("}");
*/
List<HotPlace> list = hotPlaceDAO.selectAll();
sb.append("{");
sb.append("\"storeList\":[");
	
	for(int i=0; i<list.size();i++){
		HotPlace hotPlace = list.get(i);
		
		sb.append("{");
		sb.append("\"hotPlace_id\":\""+hotPlace.getHotplace_id()+"\",");
		sb.append("\"name\":\""+hotPlace.getName()+"\",");
		sb.append("\"tel\":\""+hotPlace.getTel()+"\",");
		sb.append("\"addr\":\""+hotPlace.getAddr()+"\",");
		sb.append("\"food\":\""+hotPlace.getFood()+"\"");
		
		if(list.size()-1!=i){
			sb.append("},");
		}
		else{
			sb.append("}");
		}
	}
	sb.append("]");
	sb.append("}");
	
	out.print(sb.toString());

%>