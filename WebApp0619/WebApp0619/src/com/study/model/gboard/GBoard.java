package com.study.model.gboard;

public class GBoard {
	private int gboard_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private String filename;
	
	public int getGboard_id() {
		return gboard_id;
	}
	public void setGboard_id(int gboard_id) {
		this.gboard_id = gboard_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
