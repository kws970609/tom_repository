package com.study.controller.store;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import com.study.model.store2.Store;
import com.study.model.store2.StoreDAO;

public class MyExcelServlet extends HttpServlet{
	
	StoreDAO storeDAO = new StoreDAO(); 
	PrintWriter out = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		out=response.getWriter();
		
		//1단계 업로드
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
	
	}
}

















