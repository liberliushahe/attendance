package com.work.entity;

public class WebSite {

	private int id;
	private String loginurl;
	private String formurl;
	private String pageurl;     
	private String referer;
	private String agent;
	private String contenttype;
	private String language;
	private String encoding;
	private String connection;
	private String host;
	private String listurl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getListurl() {
		return listurl;
	}
	public void setListurl(String listurl) {
		this.listurl = listurl;
	}
	public String getLoginurl() {
		return loginurl;
	}
	public void setLoginurl(String loginurl) {
		this.loginurl = loginurl;
	}
	public String getFormurl() {
		return formurl;
	}
	public void setFormurl(String formurl) {
		this.formurl = formurl;
	}
	public String getPageurl() {
		return pageurl;
	}
	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getConnection() {
		return connection;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
}
