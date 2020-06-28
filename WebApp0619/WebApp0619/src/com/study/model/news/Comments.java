package com.study.model.news;

public class Comments {
	private int comments_id;
	private News news; // comments는 부모인 news를 가지고 있다 has a 관계!!!
	private String msg;
	private String cwriter;
	private String cregdate;

	public int getComments_id() {
		return comments_id;
	}

	public void setComments_id(int comments_id) {
		this.comments_id = comments_id;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCwriter() {
		return cwriter;
	}

	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}

	public String getCregdate() {
		return cregdate;
	}

	public void setCregdate(String cregdate) {
		this.cregdate = cregdate;
	}

}
