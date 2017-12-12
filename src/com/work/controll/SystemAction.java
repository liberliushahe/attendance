package com.work.controll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.work.entity.Employee;
import com.work.service.EmployeeService;

@Controller
public class SystemAction {
	@Autowired EmployeeService employeeservice;

	@RequestMapping("system.do")
	public String online(){
		return "WEB-INF/admin/system"; 
	}
	@RequestMapping("chmodStat.do")
	public void chmod(@RequestParam("userid") String userid,@RequestParam("online") Integer online){
		employeeservice.modifyOnline(userid, online);
	}
	
	@RequestMapping("getTimeLog.do")
	public String timelog(){
		return "WEB-INF/user/timelog"; 
	}
	@RequestMapping("getMessage.do")
	public String getMessage(HttpServletRequest request){
		//执行方法之前修改状态
		String userid=(String) request.getSession().getAttribute("username");
		employeeservice.modifyOnline(userid, 1);		
		//退出之后修改状态		
		List<Employee> list=employeeservice.queryEmployeeOnline();
		request.setAttribute("List", list);
		return "WEB-INF/user/message"; 
	}
	//获取用户在线离线状态 ajax获取
	@RequestMapping("getUserStat.do")
	public void getUserStat(HttpServletRequest request,HttpServletResponse response){
	
		List<Employee> list=employeeservice.queryEmployeeOnline();
		List<Object> listarray=new ArrayList<Object>();
		for(Employee item :list){
			listarray.add(item);
		}
		
		JSONArray array=new JSONArray(listarray);
		try {
			response.getWriter().print(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
