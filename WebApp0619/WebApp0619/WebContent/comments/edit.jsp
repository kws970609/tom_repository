<%@page import="com.study.model.news.News"%>
<%@page import="com.study.model.news.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!NewsDAO newsDAO = new NewsDAO();%>
<%
	request.setCharacterEncoding("utf-8"); //파라미터 인코딩
	
	String title=request.getParameter("title");
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	int news_id = Integer.parseInt(request.getParameter("news_id"));
	
	News news = new News(); 
	news.setTitle(title);
	news.setWriter(writer);
	news.setContent(content);
	news.setNews_id(news_id);
	
	int result = newsDAO.edit(news);
	if(result!=0){
		out.print("<script>");
		out.print("alert('수정성공');");
		out.print("location.href='/comments/content.jsp?news_id="+news_id+"';");
		out.print("</script>");
	}else{	
		out.print("<script>");
		out.print("alert('수정실패');");
		out.print("history.back();");
		out.print("</script>");
	}
%>
