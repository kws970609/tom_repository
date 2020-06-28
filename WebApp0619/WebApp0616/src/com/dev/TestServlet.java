package com.dev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 서블릿의 생명주기에대해 알아보기!!
 서블릿의 인스턴스가 생성되는 시점은 '최초의' 브라우저가 (클라이언트)가 접속할때 이다.(요청할때)
 
 일단 생성된 서블릿은, 클라이언트의 요청을 받아야하므로, 요청을 처리하는 메서드가 무조건 정의되어있어야한다
 이때 클라이언트의 요청방식에 따라 알맞는 doXX형 메서드를 재정의 해야한다..
 ex)클라이언트의 요청방법이 get방식인 경우 doGet()재정의
 	클라이언트 요청 방법이 post 방식인 경우 doPost()재정의
 	클라이언트가 요청시 default가 get방식이므로, 이 예제에서는 doGet() 먼저 정의해본다 
 */

public class TestServlet extends HttpServlet{
	
	
	/*
	 서블릿의 생명주기 메서드 3가지
	 init() - 태어나서 초기화 될때
	 service(요청, 응답) - 클라이언트의 요청을 처리
	 					 get -> doGet(요청,응답)호출
	 					 post -> doPost(요청,응답)호출
	destroy(); - 소멸할때 호출
				닫을 자원이 있을때 이 메서드에서 처리...
	 */
	int count=0;
	//서블릿의 실행되는 서버환경에대한 정보를 넘겨받을수 있는 기회의 영역이다!!!!
	/*init메서드 주 역할 : 서블릿이  주석 추가!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	*/
	public void init(ServletConfig config) throws ServletException {
		String data =config.getInitParameter("babo");
		//web.xml 은 서버가동시 읽혀지므로, xml이 변경될때 마다
		//서버를 재가동 해주자!!
		System.out.println("태어날때 전달받은 데이타는 "+data);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//스트림을 뽑기전에 서블릿에대한 각종 설정!!
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		//클라이언트의 웹브라우저로 텍스트 전송하기
		PrintWriter out = response.getWriter();
		System.out.println("저는"+this+"에요"+count++);
	}
	
	public void destroy() {
		System.out.println("저 갑니다..ㅜㅜ");
	}
}







































