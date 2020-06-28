<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
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
	//클라이언트가 전송한 파라미터들을 받아서 오라클에 넣을것임..
	//따라서 디자인 코드는 필요없음..
	//out.print("나 regist.jsp입니다");
	//클라이언트가 전송한 파라미터는 요청객체에 들어있고, 이 요청객체는 이미
	//jsp 에서는 재자객체라는 이름으로 지원 된다...
	//request 객체의 자료형은 HttpServletRequest이다!!!
	
	//파라미터를 받기전에, 인코딩 처리를 먼저해놓자!!
	request.setCharacterEncoding("utf-8");
	
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	
	out.print("제목은"+title+"<br>");
	out.print("작성자"+writer+"<br>");
	out.print("내용은"+content+"<br>");
	
	/*오라클에 넣기!!
		1단계)드라이버 로드
		2) 접속
		3) 쿼리문 수행
		4) 연결끊기 및 자원 반납
	*/
	
	
	
    Class.forName("oracle.jdbc.driver.OracleDriver");
    con = DriverManager.getConnection(url, id, pw);
    
    if(con!=null){
    	out.print("성공");
    }else{
    	out.print("실패");
    }
    
    String sql ="insert into notice(notice_id, title, writer, content)";
    		sql+=" values(seq_notice.nextval,?,?,?)";
    
    pstmt=con.prepareStatement(sql);
    pstmt.setString(1, title);
    pstmt.setString(2, writer);
    pstmt.setString(3, content);
    
    int result = pstmt.executeUpdate();//쿼리 수행
    if(result!=0){
    	out.print("등록 성공");
    }else{
    	out.print("등록 실패");
    }
    if(pstmt!=null){
    	pstmt.close();
    }
    if(con!=null){
    	con.close();
    }
	
	
%>
<script>
alert("등록 성공");
location.href="/notice/list.jsp";
</script>


























