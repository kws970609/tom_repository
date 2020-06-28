package com.study.controller.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.study.commons.file.FileManager;
import com.study.model.store2.Icons;
import com.study.model.store2.Store;
import com.study.model.store2.StoreDAO;

public class MyExcelServlet extends HttpServlet{
	
	StoreDAO storeDAO = new StoreDAO(); 
	PrintWriter out = null;
	int result=0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		out=response.getWriter();
		
		//1단계 업로드
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		ServletContext context = request.getServletContext();
		String saveDir = context.getRealPath("/data/");
		File saveFilePath = new File(saveDir);
		factory.setRepository(saveFilePath);
		
		ServletFileUpload upload = new ServletFileUpload();
		
		try {
			List<FileItem> itemList = upload.parseRequest(request);
			
			for(int i=0; i<itemList.size(); i++) {
				FileItem item = itemList.get(i);
				if(item.isFormField()) {
					
				}else {
					System.out.println("업로드된 파일명 :"+item.getName());
					
					File file = new File(saveDir+FileManager.getFileRename(item.getName()));
					
					//저장된 엑셀파일해석 시작
					parseExcel(file);
					out.print(result);
					System.out.println("수행결과:"+result);
					
					
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	public int parseExcel(File file) {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
			
			HSSFWorkbook book = new HSSFWorkbook(fis);
			
			HSSFSheet sheet = book.getSheetAt(0);
			
			//sheet안 row 접근!!
			int total = sheet.getPhysicalNumberOfRows();
			System.out.println("총 row의 수는 "+total);
			
			for(int i=0; i<total; i++) {
				HSSFRow row = sheet.getRow(i);
				
				Store store = new Store();
				Icons icons = new Icons();
				
				store.setName(row.getCell(0).getStringCellValue());
				store.setAddr(row.getCell(1).getStringCellValue());
				store.setLati(row.getCell(2).getNumericCellValue());
				store.setLongi(row.getCell(3).getNumericCellValue());
				store.setMemo(row.getCell(4).getStringCellValue());
				
				icons.setIcons_id((int)row.getCell(5).getNumericCellValue());
				
				store.setIcons(icons); //vo 결합
				storeDAO.insert(store);
			}
			result=1;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}

















