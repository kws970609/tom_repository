<%@page import="com.study.model.news.News"%>
<%@page import="com.study.model.news.Comments"%>
<%@page import="com.study.model.news.CommentsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! CommentsDAO commentsDAO = new CommentsDAO();%>
<%
	//댓글 파라미터를 받아 , DAO에게 등록 처리!!
	request.setCharacterEncoding("utf-8");
	
	String news_id=request.getParameter("news_id");
	String msg = request.getParameter("msg");
	String cwriter = request.getParameter("cwriter");
	
	out.print("news_id 는"+ news_id+"<br>");
	out.print("msg 는"+ msg+"<br>");
	out.print("cwriter 는"+ cwriter+"<br>");
	
	//VO채우기!!
	News news = new News();
	news.setNews_id(Integer.parseInt(news_id));
	
	Comments comments = new Comments();
	comments.setMsg(msg);
	comments.setCwriter(cwriter);
	
	
	//Vo간 합체
	comments.setNews(news);
	
	int result=commentsDAO.insert(comments);
	
	if(result!=0){
		out.print("<script>");
		out.print("alert('댓글 등록 성공');");
		out.print("location.href='/comments/content.jsp?news_id="+news_id+"';");
		out.print("</script>");
	}else{
		out.print("<script>");
		out.print("alert('댓글 등록실패');");
		out.print("history.back();");
		out.print("</script>");
	
	}
	
%>



























