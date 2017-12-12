package com.work.entity;

public class Notice {
	private int id;
	private String title;
	private String content;
	private String author;
	private String publishdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content
				+ ", author=" + author + ", publishdate=" + publishdate + "]";
	}
	

}
