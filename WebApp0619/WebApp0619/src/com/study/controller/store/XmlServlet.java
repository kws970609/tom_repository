package com.study.controller.store;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.study.model.store2.Icons;
import com.study.model.store2.Store;
import com.study.model.store2.StoreDAO;


public class XmlServlet extends HttpServlet{
	
	StoreDAO storeDAO = new StoreDAO();
	ArrayList<Store> list = null;
	Store store;
	Icons icons;
	boolean isName;
	boolean isAddr;
	boolean isLati;
	boolean isLongi;
	boolean isMemo;
	boolean isIcons_id;
	
	PrintWriter out = null;
	int result=0;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		out=response.getWriter();
		//1단계) 업로드
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//application 내장객체의 자료형은 ServletContext 이다!!
		//ServletContext? 어플리케이션의 전역 정보를 가진 객체!!
		ServletContext context = request.getServletContext();
		String saveDir = context.getRealPath("/data/");
		File saveFilePath = new File(saveDir); //위에 모든것은 파일이 아닌  디렉토리 설정이다!!
		factory.setRepository(saveFilePath); 
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> itemList = upload.parseRequest(request); //요청해석!!
			
			for(int i=0; i<itemList.size();i++) {
				FileItem item = itemList.get(i);
				if(item.isFormField()) { //업로드 컴포넌트가 아니라면.. 즉 일반 텍스트 컴포넌트라면
					
				}else {
					System.out.println("파일 업로드 발견!! "+item.getName());
					
					//xml파일의 내용을 한줄씩 읽어보자!!
					BufferedReader buffr = null;
					buffr = new BufferedReader(new InputStreamReader(item.getInputStream()));
					StringBuilder sb = new StringBuilder();
					while(true) {
						String data=buffr.readLine();
						if(data==null) break;
						sb.append(data);
					}
					if(buffr!=null) {
						buffr.close();
					}
					parseXml(sb.toString()); //스트링으로 결과 전달
					result=1;
					out.print(result);
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public void parseXml(String xml) {
		//System.out.println(xml);
		//넘겨받은 xml String을 파싱하자!! 그후 오라클에 insert하자!!
		SAXParserFactory factory= SAXParserFactory.newInstance();
		
		//팩토리로부터 파서 생성!!
		try {
			SAXParser saxParser= factory.newSAXParser();
			
			//파일이 아닌 스트링 데이터로부터 파싱을 해야한다!!
			InputSource source = new InputSource(new StringReader(xml));
			saxParser.parse(source, new DefaultHandler() {
				
				public void startDocument() throws SAXException {
					list = new ArrayList<Store>();	
				}
				
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					System.out.println("<"+qName+">");
					if(qName.equals("store")) {
						store = new Store();
					}if(qName.equals("name")) {
						isName=true;
					}if(qName.equals("addr")) {
						isAddr=true;
					}if(qName.equals("lati")) {
						isLati=true;
					}if(qName.equals("longi")) {
						isLongi=true;
					}if(qName.equals("memo")) {
						isMemo=true;
					}if(qName.equals("icons_id")) {
						icons = new Icons();
						isIcons_id=true;
					}
				}
				
				public void characters(char[] ch, int start, int length) throws SAXException {
					String data = new String(ch, start, length);
					System.out.print(data);
					if(isName) {
						store.setName(data);
					}else if(isAddr) {
						store.setAddr(data);
					}else if(isLati) {
						store.setLati(Double.parseDouble(data));
					}else if(isLongi) {
						store.setLongi(Double.parseDouble(data));
					}else if(isMemo) {
						store.setMemo(data);
					}else if(isIcons_id) {
						icons = new Icons();
						icons.setIcons_id(Integer.parseInt(data));
						store.setIcons(icons);
					}
					
				}
				
				public void endElement(String uri, String localName, String qName) throws SAXException {
					System.out.println("</"+qName+">");
					if(qName.equals("name")) {
						isName=false;
					}else if(qName.equals("addr")) {
						isAddr=false;
					}else if(qName.equals("lati")) {
						isLati=false;
					}else if(qName.equals("longi")) {
						isLongi=false;
					}else if(qName.equals("memo")) {
						isMemo=false;
					}else if(qName.equals("icons_id")) {
						isIcons_id=false;
						list.add(store);
					}
				}
				
				public void endDocument() throws SAXException {
					System.out.println("채워진 수는 :"+list.size());
					for(int i=0; i<list.size(); i++) {
						storeDAO.insert(list.get(i));
					}
				}
			});
						
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}





































