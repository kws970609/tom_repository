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

public class RegistServlet extends HttpServlet {
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String id = "c##java";
	String pw = "android";

	Connection con;
	PreparedStatement pstmt;
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		out.print("제목은" + title + "<br>");
		out.print("작성자" + writer + "<br>");
		out.print("내용은" + content + "<br>");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("성공");
			String sql = "insert into notice(notice_id, title, writer, content)";
			sql += " values(seq_notice.nextval,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);

			int result = pstmt.executeUpdate();// 쿼리 수행
			if (result != 0) {
				System.out.println("등록 성공");

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("등록 실패");
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
