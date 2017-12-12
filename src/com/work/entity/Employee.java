package com.work.entity;

public class Employee {
	private int id;           //员工编号
	private String ip;        //ip地址
	private String username;  //姓名
	private String password;  //密码
	private String userid;    //用户ID
	private int role;         //角色
	private int status;       //状态
	private String phone;     //手机号
	private String email;     //邮箱
	private int isswitch;     //发送邮件开关 1发送
	private int isflag;       //生成工单开关 1生成
	private String image;     //用户头像名称
	private int online;      //判断是否在线
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getIsswitch() {
		return isswitch;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setIsswitch(int isswitch) {
		this.isswitch = isswitch;
	}
	public int getIsflag() {
		return isflag;
	}
	public void setIsflag(int isflag) {
		this.isflag = isflag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
