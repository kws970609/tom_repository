package com.dev;

import javax.servlet.http.HttpServlet;
/*
서블릿의 생명주기에대해 알아보기!!
서블릿의 인스턴스가 생성되는 시점은 최초의 브라우저가 (클라이언트)가 접속할때 이다.(요청할때)

일단 생성된 서블릿은, 클라이언트의 요청을 받아야하므로, 요청을 처리하는 메서드가 무조건 정의되어있어야한다
이때 클라이언트의 요청방식에 따라 알맞는 doXX형 메서드를 재정의 해야한다..
ex)클라이언트의 요청방법이 get방식인 경우 doGet()재정의
	클라이언트 요청 방법이 post 방식인 경우 doPost()재정의
	클라이언트가 요청시 default가 get방식이므로, 이 예제에서는 doGet() 먼저 정의해본다 
*/
public class MyServlet extends HttpServlet{

}
