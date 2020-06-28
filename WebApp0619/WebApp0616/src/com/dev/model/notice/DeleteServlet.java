package com.dev.model.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet{
	
	Connection con;
	PreparedStatement pstmt;
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String id="c##java";
	String pass="android";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	//post or get 모든 요청이 아래의 메서드를 가쳐가게 하자!!
	public void doRequest(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		//파라미터 받기!!
		
		response.setContentType("text/html;charset=utf-8");
		
		String notice_id = request.getParameter("notice_id");
		PrintWriter out = response.getWriter();
		
		String sql="delete from notice where notice_id=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url,id, pass);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(notice_id));
			
			int result = pstmt.executeUpdate();
			
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}













