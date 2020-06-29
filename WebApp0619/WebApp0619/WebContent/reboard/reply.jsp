<%@page import="java.io.PrintWriter"%>
<%@page import="com.study.model.reboard.ReBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.study.model.reboard.ReBoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!ReBoardDAO reboardDAO = new ReBoardDAO(); %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="reboard" class="com.study.model.reboard.ReBoard"></jsp:useBean>
<jsp:setProperty property="*" name="reboard"/>
<%
	reboardDAO.updateRank(reboard);

	int result = reboardDAO.reply(reboard);
	System.out.println(result);
	if(result==1){
		out.print("등록성공");
		List<ReBoard> list= reboardDAO.selectAll();
		
	}
%>