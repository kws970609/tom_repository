package com.dev.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.model.board.NoticeDAO;
import com.dev.model.notice.Notice;

public class RegistServlet extends HttpServlet {
	// 글 등록을 처리하는 서블릿!!
	// 클라이언트가 post방식으로 요청을 할 것임!!
	// 글 등록 파라미터 중 상세보기의 경우 데이터량잉 많기 때문

	// service()에 의해 doXXX형 메서드가 호출된다!!
	// 따라서 개발자는 service()가 아니라, doXXX형에 집중해야한다!!
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeDAO noticeDAO= new NoticeDAO();
		// 파라미터 추출하기
		request.setCharacterEncoding("utf-8");

		// 아래의 두 코드는 jsp에서의 Page 지시영역에 해당함!!
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();//empty 상태의 VO
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);
		if(result!=0) {
			out.print("<script>");
			out.print("alert('성공');");
			out.print("location.href='/board/list.jsp';");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('실패');");
			out.print("history.back();");
			out.print("</script>");
		}
		
	}

}













