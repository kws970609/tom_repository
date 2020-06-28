package com.dev.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("클라이언트의 GET 요청을 받았어요!");
		//request객체에는 클라이언트가요청시 전송한 파라미터 정보들이 들어있따.. 그래서 꺼내먹자!!
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		System.out.println("id값은"+id);
		System.out.println("pass값은"+pass);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("클라이언트의 POST 요청을 받았어요!!");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		System.out.println("id값은"+id);
		System.out.println("pass값은"+pass);
	}
}
















