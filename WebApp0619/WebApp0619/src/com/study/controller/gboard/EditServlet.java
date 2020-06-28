package com.study.controller.gboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.commons.db.DBManager;

public class EditServlet extends HttpServlet{
	
	DBManager manager = DBManager.getInstance();
	Connection con = null;
	PreparedStatement pstmt=null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //인코딩 처리
		PrintWriter out = response.getWriter();
		
		String title= request.getParameter("title");
		String writer= request.getParameter("writer");
		String content= request.getParameter("content");
		String filename=request.getParameter("filename");
		int gboard_id= Integer.parseInt(request.getParameter("gboard_id"));
		
		String sql ="update gboard set title=?, writer=?, content=?, filename=?";
		sql+= " where gboard_id=?";
		
		con=manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,title);
			pstmt.setString(2,writer);
			pstmt.setString(3,content);
			pstmt.setString(4, filename);
			pstmt.setInt(5,gboard_id); //숫자 바인드 변수
			
			int result = pstmt.executeUpdate();
			
			if(result!=0) {
				out.print("수정성공!");
			}else {
				out.print("수정실패~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
	}
}






















