<%@page import="com.study.model.gboard.GBoard"%>
<%@page import="com.study.model.gboard.GBoardDAO"%>
<%@page import="java.io.File"%>
<%@page import="com.study.commons.file.FileManager"%>
<%@page import="java.io.IOException"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!GBoardDAO gboardDAO = new GBoardDAO();%>
<%
	//파라미터들을 넘겨받아 파일의 경우엔 서버에 저장하고 -1번경우
//텍스트 데이터의 경우엔 오라클에 넣자!! -2번 경우

//application내장객체의 getRealPath()를 이용하면
//현재의 웹사이트가 어떠한 환경에서 실행되고 있는지 상관없이 프로그래밍적으로 물리적 하드경로를 반환해준다
//ex)윈도우의 경우 d:/~~~~
//  리눅스의 경우 사용자/~~/
String saveDir = application.getRealPath("/data");
int maxSize = 3 * 1024 * 1024; //계산하지 말자!!
String encoding = "utf-8";

MultipartRequest multi = null;

//생성자에서 업로드가 발생!!
try {
	multi = new MultipartRequest(request, saveDir, maxSize, encoding);

	//파일명 교체
	String ori = multi.getOriginalFileName("imgFile");

	//새로운 이름 생성!!
	String dest = FileManager.getFileRename(ori);

	//업로드된 바로 그 파일을 반환받음!!
	File file = multi.getFile("imgFile");
	File destFile = new File(saveDir + "/" + dest);

	if (file.renameTo(destFile)) { //파일명을 제대로 바꿨다면 true
		
		//오라클에 넣기!!
		GBoard gboard = new GBoard();
		gboard.setTitle(multi.getParameter("title"));
		gboard.setWriter(multi.getParameter("writer"));
		gboard.setContent(multi.getParameter("content"));
		gboard.setFilename(dest); //1234561.jpg

		int result = gboardDAO.insert(gboard);
		
		if(result==0){
			//이미지를 처음부터 없었던것처럼 싹 지우자!!
			destFile.delete();
		}else{
			out.print("<script>");
			out.print("alert('업로드 성공');");
			out.print("location.href='/gallery/list.jsp'");
			out.print("</script>");
		}
		
	} else {
		System.out.print("변경실패");
	}

} catch (IOException e) {
	e.printStackTrace(); //개발자들을 위한 로그 기록
	out.print("<script>");
	out.print("alert('이미지 용량을 확인해주세요');");
	out.print("history.back();");
	out.print("</script>");
}
%>








































