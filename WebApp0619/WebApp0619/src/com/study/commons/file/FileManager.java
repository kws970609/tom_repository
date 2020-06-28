package com.study.commons.file;

public class FileManager {
	// 파일명 오늘 날짜로 변경하기!!
	public static String getFileRename(String ori) {
		long time= System.currentTimeMillis(); // 20200622153152121.png
		
		String rename=time+"."+getExt(ori); //파일명 제작!!
		return rename;
	}
	
	//파일 확장자만 추출하는 메서드
	public static String getExt(String filename) {
		int index = filename.lastIndexOf(".");
		String ext = filename.substring(index + 1, filename.length());
		return ext;
	}
//	public static void main(String[] args) {
//		System.out.println(getFileRename("아아아아앙.jpg"));
//	}
}










