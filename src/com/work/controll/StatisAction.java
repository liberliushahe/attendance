package com.work.controll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.work.entity.Employee;
import com.work.entity.Report;
import com.work.service.EmployeeService;
import com.work.service.OwnReportService;
import com.work.service.TimeTableService;

@Controller
public class StatisAction {
    @Autowired TimeTableService timetableservice;
    @Autowired EmployeeService employeeservice;
    @Autowired OwnReportService ownReportService;
   //跳转用户界面
	@RequestMapping("statis.do")
	public String online(HttpServletRequest request){
		        //获取确认人员和没有确认人员
				List<String> sure=employeeservice.queryEmployeeSure();
				List<Employee> unsure=employeeservice.queryEmployeeUnSure();
				List<Employee>  work=ownReportService.getAllUserWork();
				List<Employee>  unwork=ownReportService.getAllUserUnWork();
				request.setAttribute("sure", sure);
				request.setAttribute("unsure", unsure);
				request.setAttribute("work", work);
				request.setAttribute("unwork", unwork);
		        return "WEB-INF/admin/statis"; 
	}
	//ajax获取用户实时数据
	@RequestMapping("getUserReport.do")
	public void getUserreport(HttpServletResponse response){
		List<Report> list=timetableservice.getAllUserReport();
		List<Object> list_array=new ArrayList<Object>();
		
		for(Report item :list){
			list_array.add(item);
		}
		JSONArray array=new JSONArray(list_array);
		try {
			response.getWriter().print(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
