package com.work.controll;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONArray;
import com.work.entity.Employee;
import com.work.entity.Notice;
import com.work.entity.OperationLog;
import com.work.entity.Report;
import com.work.service.EmployeeService;
import com.work.service.NoticeService;
import com.work.service.OperationService;
import com.work.service.TimeTableService;

@Controller
public class LoginAction {
	//登录系统控制器
	@Autowired EmployeeService employeeservice;
	@Autowired TimeTableService timetableservice;
	@Autowired NoticeService noticeservice;
	@Autowired OperationService operationservice;
	@RequestMapping("login.do")
	public String login(HttpServletRequest request,HttpServletResponse response,String username, String password, Map<Object,String> model) {
   
		if (employeeservice.login(username, password).equals("YES")) {
			//System.out.println(username+" :"+"login success");
			String ip=request.getRemoteAddr();			
			//管理员记录总数
			int admincount=timetableservice.findAllCount();
			//管理员迟到总数
			//int admincountlate=timetableservice.findAllCountByLate(username, "2");
			//管理员早退总数
			//int admincountearly=timetableservice.findAllCountByEarly(username, "2");
			//用户总数
			int usercount=timetableservice.findCurrentAllCountByUser(username);
			//用户迟到总数
			//int usercountlate=timetableservice.findAllCountByLate(username, "1");
			//用户早退总数
			//int usercountearly=timetableservice.findAllCountByEarly(username, "1");
			//增加登录日志
			OperationLog log=new OperationLog();
			log.setType(1);//设置1为登录成功  2为登录失败 3为账号未激活
			log.setContent("账号登录成功");
			log.setIp(ip);
			log.setUsername(username);
			String time=formatdate();
			log.setTime(time);
			operationservice.addLog(log);

			Employee employee=employeeservice.queryInfo(username);
			//登录网站修改用户ip实时更新
			employeeservice.modifyUserIp(ip, username);
			List<Notice> list=noticeservice.queryNotice();
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("employee", employee);
			request.getSession().setAttribute("imagepath", employee.getImage());
			request.getSession().setAttribute("ip", ip);
			if(employeeservice.role(username).equals("1")){
				//request.getSession().setAttribute("admincount", admincount);
				request.setAttribute("admincount", admincount);
				//request.getSession().setAttribute("admincountlate", admincountlate);
				//request.getSession().setAttribute("admincountearly", admincountearly);
				request.getSession().setAttribute("list", list);
				return "forward:main.do";
				
			}else{
				//request.getSession().setAttribute("usercount", usercount);
				request.setAttribute("usercount", usercount);
				request.getSession().setAttribute("list", list);
				//request.getSession().setAttribute("usercountlate", usercountlate);
				//request.getSession().setAttribute("usercountearly", usercountearly);
				return "forward:usermain.do";
			}
			

		} else if(employeeservice.login(username, password).equals("ineffect")){
			String ip=request.getRemoteAddr();
			OperationLog log=new OperationLog();
			log.setType(2);//设置1为登录成功  2为登录失败
			log.setContent("账号激活失败");
			log.setIp(ip);
			log.setUsername(username);
			String time=formatdate();
			log.setTime(time);
			operationservice.addLog(log);	
			request.setAttribute("errorinfo", "账号未激活 请联系管理员");
			return "forward:login.jsp";
		}
		
		else {
			String ip=request.getRemoteAddr();
			OperationLog log=new OperationLog();
			log.setType(2);//设置1为登录成功  2为登录失败
			log.setContent("用户名或者密码错误");
			log.setIp(ip);
			log.setUsername(username);
			String time=formatdate();
			log.setTime(time);
			operationservice.addLog(log);		
			request.setAttribute("errorinfo", "用户名或密码错误");
			return "forward:login.jsp";

		}
	}
	//退出登录
	@RequestMapping("loginout.do")
		public String loginout(HttpServletRequest request,HttpServletResponse response){
		//清除session
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Cache-Control","no-store"); 
    	response.setDateHeader("Expires", 0); 
    	response.setHeader("Pragma","no-cache");
    	session.removeAttribute("username");
    	session.removeAttribute("employee");
		session.invalidate();
		return "redirect:login.jsp";
	}
	public String formatdate(){
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String currentdate=format.format(date);
		return currentdate;
	}
	//获取报表数据
	
	@RequestMapping("getReport.do")
	public  void getreport(HttpServletRequest request,HttpServletResponse response){
		String username=(String)request.getSession().getAttribute("username");
		Report report=timetableservice.getReport(username);
		List<Object> list=new ArrayList<Object>();
		list.add(report.getDate());
		list.add(report.getStart());
		list.add(report.getEnd());		
		//list转json
		JSONArray array=new JSONArray(list);
		try {
			response.getWriter().println(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//账号注册
	@RequestMapping("register.do")
	public  String registerCount(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username=request.getParameter("username");
		String userid=request.getParameter("userid");
		String tel=request.getParameter("tel");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Employee employee=new Employee();
		employee.setUsername(username);
		employee.setPhone(tel);
		employee.setPassword(password);
		employee.setEmail(email);
		employee.setImage("headimage.jpg");//设置默认头像
		employee.setRole(0); //角色为用户
		employee.setIsswitch(1);
		employee.setIsflag(0);
		employee.setIp("0.0.0.0");
		employee.setUserid(userid);
		employee.setStatus(1);//状态可用
		String flag=employeeservice.AddEmployee(employee);
		if(flag.equals("1")){
			request.setAttribute("errorinfo", "注册成功");
			return "redirect:login.jsp";
			
		}else{
			request.setAttribute("errorinfo", "注册失败");
			return "redirect:register.jsp";
		}
	}
}
