<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
//선언부 == 서블릿의 멤버 영역
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String id = "c##java";
    String pw = "android";
    
    Connection con;
    PreparedStatement pstmt;
%>
<%
	//삭제후 리스트를 클라이언트에게 보여줘야 하므로
	//이 페이지는 디자인적인 기능이 필요없다!! 즉 html 필요없음
	int notice_id=0;
	notice_id = Integer.parseInt(request.getParameter("notice_id"));
	String sql="delete from notice where notice_id="+notice_id;
	
	out.print(sql);
	//삭제 쿼리수행
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection(url, id, pw);
	
	pstmt = con.prepareStatement(sql);
	int result= pstmt.executeUpdate();
	
	
%>
<script>
<%if(result==1){%>
	alert("삭제완료");
	location.href="/notice/list.jsp";
<%}else{%>
	alert("삭제실패");
	history.back();
<%}%>
</script>
<%
	if(pstmt!=null){pstmt.close();}
	if(con!=null){con.close();}
%>













