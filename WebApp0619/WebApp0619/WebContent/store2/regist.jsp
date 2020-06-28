<%@page import="com.study.model.store.Icons"%>
<%@page import="com.study.model.store.Store"%>
<%@page import="com.study.model.store.StoreDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! StoreDAO storeDAO = new StoreDAO(); %>
<%
	//맛집 정보 파라미터들을 넘겨받아 오라클에 넣기!!
	request.setCharacterEncoding("utf-8");

	String name =request.getParameter("name");
	String addr =request.getParameter("addr");
	String lati =request.getParameter("lati");
	String longi =request.getParameter("longi");
	String icons_id =request.getParameter("icons_id");
	String memo =request.getParameter("memo");

	System.out.println("name "+name+"<br>");
	System.out.println("addr "+addr+"<br>");
	System.out.println("lati "+lati+"<br>");
	System.out.println("longi "+longi+"<br>");
	System.out.println("icons_id "+icons_id+"<br>");
	System.out.println("memo "+memo+"<br>");
	
	Store store = new Store(); //empty 상태
	store.setName(name);
	store.setAddr(addr);
	store.setLati(Double.parseDouble(lati));
	store.setLongi(Double.parseDouble(longi));
	Icons icons = new Icons(); //empty형태
	icons.setIcons_id(Integer.parseInt(icons_id));
	store.setIcons(icons); //vo 결합
	store.setMemo(memo);
	
	int result = storeDAO.insert(store);
	System.out.println("수행 결과:"+ result);//개발자를 위한 디버깅
	out.print(result);
%>






















