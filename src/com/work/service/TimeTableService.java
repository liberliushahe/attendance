package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.dao.TimeTableDao;
import com.work.entity.Report;
import com.work.entity.TimeTable;
import com.work.util.Page;
@Service
public class TimeTableService {
	
	@Autowired private TimeTableDao timetabledao;
	public List<TimeTable> queryTimeTable(String username){
		 return null;
	 }
	public List<TimeTable> queryTimeTable(Page page){
		return timetabledao.queryTimeTable(page);
	}
	//查询用户当月记录总数
	public int findCurrentAllCountByUser(String username){
		return timetabledao.findCurrentAllCountByUser(username);
	}
	public int findCurrentAllCountByUsername(String username) {
		return timetabledao.findCurrentAllCountByUsername(username);
	}
	//查询用户当月记录
		public List<TimeTable> queryUserTimeTable(String username,Page page){
			return timetabledao.queryUserTimeTable(username, page);
		}
	//根据条件用户名查询
	public List<TimeTable> queryTimeTable(String username,String start,Page page){
		return timetabledao.queryTimeTable(username, start, page);
	}
	//查询用户所属记录总数
	public int findAllCountByUser(String username,String start) {
		return timetabledao.findAllCountByUser(username,start);
	}
	public int findAllCount() {
		return timetabledao.findAllCount();
	}
	//判断打卡是否完成
	public String isOverTimeTable(String ip){
		return timetabledao.isOverTimeTable(ip);
	}
	public String isHasTimeTable(String ip){
		
		String flag= timetabledao.isHasTimeTable(ip);
		
		return flag;
	}
	public void addTimeTable(TimeTable timetable){
		timetabledao.addTimeTable(timetable);
	 }
	 public void startwork(String ip){
		 timetabledao.startwork(ip);
	 }
	 public void endwork(String ip) {
		 timetabledao.endwork(ip);
	 }
	 public void updateTimeTable(TimeTable table){
		 timetabledao.updateTimeTable(table);
	 }
	 public void deleteTimeTable(int id){
		 timetabledao.deleteTimeTable(id);
	 }
	 public int findAllCountByLate(String username,String late) {
		return  timetabledao.findAllCountByLate(username, late);
	 }
	 public int findAllCountByEarly(String username,String late) {
	    return  timetabledao.findAllCountByEarly(username, late);

	 }
	 public void createUser(String username,String date){
		 timetabledao.createUser(username, date);
	 }
	 public String deleteData(){
	    return	 timetabledao.deleteData();
	 }
	 //批量删除
	 public void batchDeleteTimeTable(String[] id) {
		 timetabledao.batchDeleteTimeTable(id);
	 }
	//返回用户当月每一天的上班打卡时间和下班打卡时间 线性图表显示
		public Report getReport(String username){
			return timetabledao.getReport(username);
		}
		/**
		 * 报表展示方法展示每个用户当月上班次数
		 * @param null
		 * @return
		 */
		//返回用户当月所有打卡记录 线性图表显示
		public List<Report> getAllUserReport(){
			return timetabledao.getAllUserReport();
		}
}
