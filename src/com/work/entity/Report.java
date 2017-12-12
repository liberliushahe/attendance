package com.work.entity;

import java.util.List;

public class Report {
	private String name;
	private String count;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	private List<String> date;
	private List<String> start;
	private List<String> end;
	public List<String> getDate() {
		return date;
	}
	public void setDate(List<String> date) {
		this.date = date;
	}
	public List<String> getStart() {
		return start;
	}
	public void setStart(List<String> start) {
		this.start = start;
	}
	public List<String> getEnd() {
		return end;
	}
	public void setEnd(List<String> end) {
		this.end = end;
	}
	

}
