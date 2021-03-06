package com.work.controll;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.work.dao.EmployeeDao;
import com.work.dao.OwnReportDaoImpl;
import com.work.dao.TimeTableDao;
import com.work.dao.WebSiteDaoImpl;
import com.work.entity.Employee;
import com.work.entity.OwnReport;
import com.work.entity.TimeTable;
import com.work.entity.WebSite;
import com.work.service.EmployeeService;
import com.work.service.TimeTableService;
import com.work.util.DetectDesignData;
import com.work.util.Page;
import com.work.util.PageUtil;
import com.work.util.SendEmail;

@Controller
public class getListAction {
	//登录系统控制器
	@Autowired TimeTableService timetableservice;
	@Autowired EmployeeService employeeservice;
	@RequestMapping("getList.do")
	public String login(HttpServletRequest request, Map<Object,String> model) {

		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}

		int totalCount=timetableservice.findAllCount();
		Page page=PageUtil.createPage(10, currentpage, totalCount);
		List<TimeTable> list=timetableservice.queryTimeTable(page);
		request.setAttribute("List", list);
		request.setAttribute("page", page);

		return "WEB-INF/admin/manage"; 
	}
	//管理员主页显示
	@RequestMapping("main.do")
	public String main(HttpServletRequest request, Map<Object,String> model) {
		int admincount=timetableservice.findAllCount();
		request.setAttribute("admincount", admincount);
		return "WEB-INF/admin/main"; 	
	}
	//用户主页
	@RequestMapping("usermain.do")
	public String usermain(HttpServletRequest request, Map<Object,String> model) {
		String username=(String) request.getSession().getAttribute("username");
		int usercount=timetableservice.findCurrentAllCountByUser(username);
		request.setAttribute("usercount", usercount);
		
		return "WEB-INF/user/usermain"; 	
	}
	//用户信息
	@RequestMapping("getUserMassage.do")
	public String userMassage(HttpServletRequest request, Map<Object,String> model){
		return "WEB-INF/user/userMassage";
	}
	//邮件提醒
	@RequestMapping("sendMail.do")
	public String sendmail(HttpServletRequest request, Map<Object,String> model) {
		List<Employee> list=employeeservice.queryEmail();
		for(int i=0;i<list.size();i++){
			String username=list.get(i).getUsername();
			String email=list.get(i).getEmail();
			SendEmail.sendMail(email, username);
			System.out.println("第"+(i+1)+"封邮件发送成功！发送给用户"+username);
		}
		return "redirect:getList.do";	
	}
	//获取用户当月记录
	@RequestMapping("getUserList.do")
	public String getUserList(HttpServletRequest request, Map<Object,String> model) {
		String currentPage=request.getParameter("currentPage");
		String username=(String) request.getSession().getAttribute("username");
        
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}
		int totalCount=timetableservice.findCurrentAllCountByUser(username);
		Page page=PageUtil.createPage(10, currentpage, totalCount);
		List<TimeTable> list=timetableservice.queryUserTimeTable(username, page);
		//获取确认人员和没有确认人员
		List<String> sure=employeeservice.queryEmployeeSure();
		List<Employee> unsure=employeeservice.queryEmployeeUnSure();
		request.setAttribute("List", list);
		request.setAttribute("page", page);
		request.setAttribute("sure", sure);
		request.setAttribute("unsure", unsure);
		return "WEB-INF/user/user"; 	
	}
	
	//条件查询用户名，开始时间，结束时间
//	@RequestMapping("query.do")
//	public String query(HttpServletRequest request){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String currentPage=request.getParameter("currentPage");
//		String username=request.getParameter("username");
//		String start_time=request.getParameter("starttime");
//		String end_time=request.getParameter("endtime");
//		int currentpage=0;
//		if(currentPage==null ||"".equals(currentPage)){
//			currentpage=1;
//		}else{
//			currentpage=Integer.parseInt(currentPage);
//		}
//		
//		int totalCount=timetableservice.findAllCountByUser(username, start_time, end_time);
//		Page page=PageUtil.createPage(10, currentpage, totalCount);
//		List<TimeTable> list=timetableservice.queryTimeTable(username, start_time, end_time, page);
//		request.setAttribute("List", list);
//		request.setAttribute("page", page);
//
//		return "WEB-INF/admin/query"; 
//		
//		
//	}
	//增加考勤
	@RequestMapping("add.do")
	public String add(HttpServletRequest request, Map<Object,String> model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name=request.getParameter("username");
		String date=request.getParameter("date");
		String start=request.getParameter("start-time");
		String end=request.getParameter("end-time");
		String week=request.getParameter("week");
		String holiday=request.getParameter("holi");
		String explain=request.getParameter("explain");
		TimeTable table = new TimeTable();
		table.setUsername(name);
		table.setDate(date);
		table.setStarttime(start);
		table.setEndtime(end);
		if(week.equals("1")){
        	table.setWeekend("是");
        }else{
        	table.setWeekend("");
        }
		if(holiday.equals("1")){
			table.setHoliday("是");
		}else{
			table.setHoliday("");
		}

		table.setExplain(explain);
		timetableservice.addTimeTable(table);
	 String username=(String) request.getSession().getAttribute("username");
		if(employeeservice.role(username).equals("1")){
			return "redirect:getList.do"; 
		}else{
			return "redirect:getUserList.do"; 
		}
			
	}
	//编辑考勤
	@RequestMapping("update.do")
	public String edit(HttpServletRequest request, Map<Object,String> model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}
		String ID=request.getParameter("id");
		int id=Integer.parseInt(ID);
		String name=request.getParameter("username");
		String date=request.getParameter("date");
		String start=request.getParameter("starttime");
		String end=request.getParameter("endtime");
		String week=request.getParameter("week");
		String holiday=request.getParameter("holi");
		String explain=request.getParameter("explain");
        
		TimeTable table = new TimeTable();
		table.setId(id);
		table.setUsername(name);
		table.setDate(date);
		table.setStarttime(start);
		table.setEndtime(end);
        if(week.equals("1")){
        	table.setWeekend("是");
        }else{
        	table.setWeekend("");
        }
		if(holiday.equals("1")){
			table.setHoliday("是");
		}else{
			table.setHoliday("");
		}
		
		table.setExplain(explain);
		timetableservice.updateTimeTable(table);
		String username=(String) request.getSession().getAttribute("username");
		if(employeeservice.role(username).equals("1")){
			return "redirect:getList.do?currentPage="+currentpage; 
		}else{
			return "redirect:getUserList.do?currentPage="+currentpage; 
		} 	
	}
	//批量删除考勤记录
	@RequestMapping("batchdelete.do")
	public String batchDelete(HttpServletRequest request, Map<Object,String> model) {
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}
        String allid=request.getParameter("allid");
        String[] id=allid.split(",");
		timetableservice.batchDeleteTimeTable(id);
		String username=(String) request.getSession().getAttribute("username");
		if(employeeservice.role(username).equals("1")){
			return "redirect:getList.do?currentPage="+currentpage; 
		}else{
			return "redirect:getUserList.do?currentPage="+currentpage; 
		} 	
	}	
	//删除考勤
	@RequestMapping("delete.do")
	public String delete(HttpServletRequest request, Map<Object,String> model) {
		String currentPage=request.getParameter("currentPage");
		int currentpage=0;
		if(currentPage==null ||"".equals(currentPage)){
			currentpage=1;
		}else{
			currentpage=Integer.parseInt(currentPage);
		}
        String ID=request.getParameter("id");
        int id=Integer.parseInt(ID);
		timetableservice.deleteTimeTable(id);
		String username=(String) request.getSession().getAttribute("username");
		if(employeeservice.role(username).equals("1")){
			return "redirect:getList.do?currentPage="+currentpage; 
		}else{
			return "redirect:getUserList.do?currentPage="+currentpage; 
		} 	
	}
	//一键生成用户记录
	@RequestMapping("createUser.do")
	public String createUser(HttpServletRequest request){
		String createusername=request.getParameter("username");
		String date=request.getParameter("date");
		timetableservice.createUser(createusername, date);
		String username=(String) request.getSession().getAttribute("username");
		request.setAttribute("info", "创建成功");
		if(employeeservice.role(username).equals("1")){
			return "redirect:getList.do"; 
		}else{
			return "redirect:getUserList.do"; 
		} 	
		
	}
	//一键生成批量用户记录
	@RequestMapping("createAllUser.do")
	public String createAllUser(HttpServletRequest request){
		String date=request.getParameter("date");
		String username=(String) request.getSession().getAttribute("username");
	List<Employee> userlist=employeeservice.queryIsAutoCreate();
	if(userlist.size()!=0){
		for(int i=0;i<userlist.size();i++){
			System.out.println(userlist.get(i).getUsername());
			timetableservice.createUser(userlist.get(i).getUsername(), date);
		}
		request.setAttribute("info", "创建成功");
		
	}else{
		request.setAttribute("info", "无用户需要自动生成");
	}
		if(employeeservice.role(username).equals("1")){
			return "redirect:getList.do"; 
		}else{
			return "redirect:getUserList.do"; 
		} 	
	}
	//清空数据
	@RequestMapping("deleteData.do")
	public String deleteData(){
		employeeservice.modifyUserSureToZero();
		return timetableservice.deleteData();
	}
	//同步数据
	@RequestMapping("asyncData.do")
	public String asyncData(){
		GetDataListByThread t=new GetDataListByThread();
		GetDataListByThread1 t1=new GetDataListByThread1();
		Thread t2=new Thread(t);
		Thread t3=new Thread(t1);
		t2.start();
		t3.start();
    	return "1";
	}
}
class GetDataListByThread implements Runnable {
	public List<OwnReport> getList(){
		OwnReportDaoImpl dao=new OwnReportDaoImpl();
    	return dao.getAllOwnReportByAutoAsyn();
    	
	}
	
	public void run() {
		// TODO Auto-generated method stub
		Date date=new Date();	
		WebSiteDaoImpl sitedao=new WebSiteDaoImpl();	
		OwnReportDaoImpl dao=new OwnReportDaoImpl();
		TimeTableDao timetabledao=new TimeTableDao();
		WebSite website=new WebSite();
	    website= sitedao.getWebSiteById(1);
       	SimpleDateFormat format1=new SimpleDateFormat("yy-MM-01");
    	//同步数据
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
         //设置如果当月的上一月是12月份则当年减1
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        Date upmonth = calendar.getTime();
		String startMonth=format1.format(upmonth);
		List<OwnReport> list=getList();
		List<OwnReport> listnew=new ArrayList<OwnReport>();
		int length=list.size();
		if(length%2==0){
			for(int t=0;t<length/2;t++){
			listnew.add(list.get(t));	
			}
		}else{
		for(int t=0;t<length/2;t++){
			listnew.add(list.get(t));	
			}
		}		
    	Iterator<OwnReport> it=listnew.iterator();   
	    website= sitedao.getWebSiteById(1);
	    System.out.println("****************************更新数据开始。。。。。。。。");
    	while(it.hasNext()){
        	OwnReport ownReport=new OwnReport(); 
        	ownReport=it.next();
        	
        	List<String> dates=null;
        	//获取清洗的数据
        try{
        	 dates=DetectDesignData.getUserMonthReport(ownReport, website,startMonth);
        }catch(Exception e){
        	System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
        	System.out.println("》》》》》》》》》》》》》》用户登录异常》》》》》》》》》》》》》》》");
        	System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
        	System.out.println(ownReport.getUsername()+"密码登录出现异常");
        	continue;
        }
        	//自动生成数据
        	if(dao.getLinkManByReportId(ownReport.getUsername())!=null){
				timetabledao.createUserReportBySynchro(dao.getLinkManByReportId(ownReport.getUsername()).getUsername(), dates);
	        	System.out.println(ownReport.getUsername()+" 数据更新完毕-------------》》》》》》》》》");
        	}	
		
    	}
	}

}
 class GetDataListByThread1 implements Runnable {
	public List<OwnReport> getList(){
		OwnReportDaoImpl dao=new OwnReportDaoImpl();
    	return dao.getAllOwnReportByAutoAsyn();
    	
	}
	public void run() {
		Date date=new Date();	
		WebSiteDaoImpl sitedao=new WebSiteDaoImpl();	
		OwnReportDaoImpl dao=new OwnReportDaoImpl();
		TimeTableDao timetabledao=new TimeTableDao();
		WebSite website=new WebSite();
	    website= sitedao.getWebSiteById(1);
       	SimpleDateFormat format1=new SimpleDateFormat("yy-MM-01");
    	//同步数据
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
         //设置如果当月的上一月是12月份则当年减1
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        Date upmonth = calendar.getTime();
		String startMonth=format1.format(upmonth);
		List<OwnReport> list=getList();
		List<OwnReport> listnew=new ArrayList<OwnReport>();
		int length=list.size();
		if(length%2==0){
			for(int t=length/2;t<length;t++){
			listnew.add(list.get(t));	
			}
		}else{
		for(int t=length/2;t<length;t++){
			listnew.add(list.get(t));	
			}
		}
    	Iterator<OwnReport> it=listnew.iterator();   
	    website= sitedao.getWebSiteById(1);
	    System.out.println("****************************更新数据开始。。。。。。。。");
    	while(it.hasNext()){
        	OwnReport ownReport=new OwnReport(); 
        	ownReport=it.next();
        	List<String> dates=null;
        	//获取清洗的数据
        try{
        	 dates=DetectDesignData.getUserMonthReport(ownReport, website,startMonth);
        }catch(Exception e){
        	System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
        	System.out.println("》》》》》》》》》》》》》》用户登录异常》》》》》》》》》》》》》》》");
        	System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
        	System.out.println(ownReport.getUsername()+"密码登录出现异常");
        	continue;
        }
        	//自动生成数据
        	if(dao.getLinkManByReportId(ownReport.getUsername())!=null){
				timetabledao.createUserReportBySynchro(dao.getLinkManByReportId(ownReport.getUsername()).getUsername(), dates);
	        	System.out.println(ownReport.getUsername()+" 数据更新完毕-------------》》》》》》》》》");
        	}	
		
    	}
	}

}