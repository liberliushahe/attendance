package com.work.entity;
//相册管理实体类
public class Photo {
	private int id;        //编号
	private String title;  //标签头
	private String content;//内容
	private String path;   //路径
	private String wisdom; //名言
	private String author; //作者
	private String publishdate;//发布日期
	
	public String getWisdom() {
		return wisdom;
	}
	public void setWisdom(String wisdom) {
		this.wisdom = wisdom;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "PhotoDao [id=" + id + ", title=" + title + ", content="
				+ content + ", path=" + path + "]";
	}

}
