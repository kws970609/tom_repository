package com.dev.model.notice;

public class Comments {
	private int comments_id;
	private int notice_id;
	private String msg;
	private String cWriter;
	private String cRegdate;
	
	public int getComments_id() {
		return comments_id;
	}
	public void setComments_id(int comments_id) {
		this.comments_id = comments_id;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getcWriter() {
		return cWriter;
	}
	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}
	public String getcRegdate() {
		return cRegdate;
	}
	public void setcRegdate(String cRegdate) {
		this.cRegdate = cRegdate;
	}

}
