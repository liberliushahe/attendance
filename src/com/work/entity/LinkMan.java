package com.work.entity;

public class LinkMan {
	private int id;
	private String name;
	private String phone;
	private String qq;
	private String matter;
	private String address;
	private String email;
	private String job;
	private int departid;
	private String departname;
	public int getDepartid() {
		return departid;
	}
	public void setDepartid(int departid) {
		this.departid = departid;
	}
	
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	private Department depart;

	public Department getDepart() {
		return depart;
	}
	public void setDepart(Department depart) {
		this.depart = depart;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}
	@Override
	public String toString() {
		return "LinkMan [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", qq=" + qq + ", matter=" + matter + ", address=" + address
				+ ", email=" + email + ", job=" + job + ", depart=" + depart
				+ "]";
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
		
}
