package com.work.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.work.dao.EmployeeDao;
import com.work.dao.OwnReportDaoDetailImpl;
import com.work.dao.OwnReportDaoImpl;
import com.work.dao.TimeTableDao;
import com.work.dao.WebSiteDaoImpl;
import com.work.entity.Employee;
import com.work.entity.OwnReport;
import com.work.entity.OwnReportDetail;
import com.work.entity.WebSite;

/**
 * @author jijiuxue
 * @return 
 * @desc 定时任务
 * @desc 定期生成考勤记录并且发送邮件提醒 
 * @deprecated 最后修正邮件提醒
 * @desc 本次优化致力于自动化运维 减少运维时间
 * 
 */

public class TimerTask extends java.util.TimerTask {
    private static String MAILRECEIVE=null;
    private static String CREATEDAY=null;
    private static String CREATETIME=null;
    private static String DELETEDAY=null;
    private static String REPORTTIME=null;
    private static String FLAGZERO=null;
    private static String ASYDAY=null;
    private static String ASYTIME=null;
    private static String FINDTIME1=null;
    private static String FINDTIME2=null;
    private static String REMINDDAY=null;
    private static String REMINDTIME1=null;
    private static String REMINDTIME2=null;
    private static String REMINDTIME3=null;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Properties prop=new Properties();
		try {
			InputStream in = TimerTask.class.getClassLoader().getResourceAsStream("mail.properties");
			prop.load(in);
			MAILRECEIVE=prop.getProperty("mail-receive");
			CREATEDAY=prop.getProperty("createday");
			CREATETIME=prop.getProperty("createtime");
			DELETEDAY=prop.getProperty("deleteday");
			REPORTTIME=prop.getProperty("createreporttime");
			FLAGZERO=prop.getProperty("flagzero");
			ASYDAY=prop.getProperty("asyday");
			ASYTIME=prop.getProperty("asytime");
			FINDTIME1=prop.getProperty("findhost1");
			FINDTIME2=prop.getProperty("findhost2");
			REMINDDAY=prop.getProperty("remindday");
			REMINDTIME1=prop.getProperty("remindtime1");
			REMINDTIME2=prop.getProperty("remindtime2");
			REMINDTIME3=prop.getProperty("remindtime3");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date date=new Date();
		EmployeeDao employeedao=new EmployeeDao() ;
		OwnReportDaoImpl dao=new OwnReportDaoImpl();
		WebSiteDaoImpl sitedao=new WebSiteDaoImpl();
		TimeTableDao timetabledao=new TimeTableDao();
		
		WebSite website=new WebSite();
	     website= sitedao.getWebSiteById(1);
		SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
       	SimpleDateFormat format1=new SimpleDateFormat("yy-MM-01");

		String da=format.format(date);
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int day=c.get(Calendar.DAY_OF_MONTH);
		int month=c.get(Calendar.MONTH);
		String currentdate=String.valueOf(year)+"/"+String.valueOf(month);
		if(day==Integer.parseInt(CREATEDAY) && da.equals(CREATETIME)){
			/**
			 * 创建用户记录 符合一定条件
			 * @desc 创建字段为自动生成的用户
			 */
			//获取上月日期
			
		    List<Employee> userlist=employeedao.queryIsAutoCreate();
		    if(userlist.size()!=0){		
		    	for(int i=0;i<userlist.size();i++){
	
				timetabledao.createUser(userlist.get(i).getUsername(), currentdate);
			}	
		}
		    try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    /**
			 * 数据创建完成后 进行邮件自动提醒
			 */
		    List<Employee> list=employeedao.queryEmail();
			for(int i=0;i<list.size();i++){
				String username=list.get(i).getUsername();
				String email=list.get(i).getEmail();
				SendEmail.sendMail(email, username);
				System.out.println("第"+(i+1)+"封邮件发送成功！发送给用户"+username);
			}
			 try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else if(day==Integer.parseInt(DELETEDAY) && da.equals(CREATETIME)){
			timetabledao.deleteData();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("-----------------考勤表数据清除成功-----------------");
			System.out.println("+++++++++++++++++^------------^+++++++++++++++++");
			System.out.println("++++++++++++++++考勤系统版权所有2017+++++++++++++++++");
		}else if(da.equals(REPORTTIME)){
			/**
			 * 每天7点自动填写，不会默认覆盖原有数据
			 * 首先判断当日数据是否已经生成，如果已经存在 则不会自动填写
			 */
	    	List<OwnReport> list=dao.getAllOwnReportByAutoReport();    	
	    	Iterator<OwnReport> it=list.iterator();       	
	    	while(it.hasNext()){
	    	OwnReport ownReport=new OwnReport(); 
	    	ownReport=it.next();	    		    	
	    	//登录网站
	    	DetectDesignData.imitateLogin(ownReport, website);
	    	}
	    	
		}else if(da.equals(FLAGZERO)){
			//每天23：点归零
		    dao.modifyWflagToZero();
		
		}else if(day==Integer.parseInt(ASYDAY)&&da.equals(ASYTIME)){
			//每月1号与用户数据同步
	    	//同步数据
			Calendar calendar = Calendar.getInstance();
            calendar.setTime(date); // 设置为当前时间
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
            Date upmonth = calendar.getTime();
			String startMonth=format1.format(upmonth);
	    	List<OwnReport> list=dao.getAllOwnReportByAutoAsyn();     	
	    	Iterator<OwnReport> it=list.iterator();   
		    website= sitedao.getWebSiteById(1);
		    System.out.println("****************************更新数据开始。。。。。。。。");
	    	while(it.hasNext()){
	        	OwnReport ownReport=new OwnReport(); 
	        	ownReport=it.next();
	        	//获取清洗的数据
	        	List<String> dates=DetectDesignData.getUserMonthReport(ownReport, website,startMonth);
	        	//自动生成数据
	        	if(dao.getLinkManByReportId(ownReport.getUsername())!=null){
					timetabledao.createUserReportBySynchro(dao.getLinkManByReportId(ownReport.getUsername()).getUsername(), dates);
	        	}	
			
	    	}
			
		}else if(da.equals(FINDTIME1)||da.equals(FINDTIME2)){
			//每天9点扫描在线主机和接收主机数据
	    	List<OwnReport> list=dao.getAllOwnReportByModifyFlag();    	
	    	Iterator<OwnReport> it=list.iterator();       	
	    	while(it.hasNext()){
	    	OwnReport ownReport=new OwnReport(); 
	    	ownReport=it.next();
	    	//登录网站
	    	String ip=employeedao.getUserOwnIp(ownReport.getUsername());
	    	//开始判断用户是否在线
	    	if(ip!=null){
	    	if(hostIsReachable(ip)==true){
	    		dao.modifyWflag(ownReport.getUsername());
	    	}
	    	}
	    	}
		}else if(day==Integer.parseInt(REMINDDAY)){
			//邮件通知没有确认的用户
			List<Employee> list=employeedao.queryEmployeeUnSure();
			/**
			 * 如果查询的没有确定的用户为空则自动导出数据 自动发送邮件
			 * 反之邮件提醒
			 */
			if(da.equals(REMINDTIME1) || da.equals(REMINDTIME2)|| da.equals(REMINDTIME3)){
			if(list.size()==0){
				String month1=String.valueOf(date.getMonth());
				String path="c:\\";               
				FileUpLoad.writeExcelByAuto(path, month1);
				String filename="甘肃考勤"+month1+"月份";	
			    try {
			    	//发送给指定用户
					SendEmail.sendEmailFile(MAILRECEIVE,filename);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}else{
				for(Employee e :list){
					try {
						SendEmail.sendRemindToUnsure(e.getEmail(), e.getUsername());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		}
		}
	}
	  //判断用户ip是否可达
	public boolean hostIsReachable(String ip){
		Runtime run=Runtime.getRuntime();
		try {
			Process p=run.exec("ping "+ip);
			 InputStream input= p.getInputStream();
			 byte[] b=new byte[1024];
			while( input.read(b)!=-1){
			String str=	new String(b).toString();
			if(str.indexOf("100% 丢失")!=-1){				
				return false;
			}
			}
			//System.out.println(buffer.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
