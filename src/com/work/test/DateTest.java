package com.work.test;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.work.dao.OwnReportDaoImpl;
import com.work.dao.WebSiteDaoImpl;
import com.work.entity.OwnReport;
import com.work.entity.WebSite;
import com.work.util.DetectDesignData;
import com.work.util.SendEmail;

/**
 * @author: jijiuxue
 * @date:2017-12-4 下午1:13:30
 * @version :1.0.0
 * 
 */
public class DateTest {
	public static void main(String[] args){
		OwnReportDaoImpl dao=new OwnReportDaoImpl();
		WebSiteDaoImpl sitedao=new WebSiteDaoImpl();
    	List<OwnReport> list=dao.getAllOwnReportByAutoReport();      
    	WebSite website=new WebSite();
	    website= sitedao.getWebSiteById(1);
    	Iterator<OwnReport> it=list.iterator();       	
//    	while(it.hasNext()){
//    	OwnReport ownReport=new OwnReport(); 
//    	ownReport=it.next();
//    	//登录网站
//    	DetectDesignData.imitateLogin(ownReport, website);
//    	}
       //获取当前最新日期
    	//String date1=DetectDesignData.parseCurrentDate(DetectDesignData.getPageData(website));
    	//ownReport.setWdate1(date1);
    	//设置表单数据    	
		//DetectDesignData.designData(ownReport, website);
		
		//System.out.println("yemiafdfdfdf:"+DetectDesignData.getPageData(website));
		//System.out.println("****************************************");
    	//获取未操作日报数量
     	//int i=DetectDesignData.parseHtmlData(DetectDesignData.getPageData(website));
     	//if(i==2){
        //	try {
		//		SendEmail.sendRemind(ownReport.getEmail(),ownReport.getUsername());
		//		System.out.println(ownReport.getUsername()+"邮件发送成功");
		//	} catch (Exception e) {
				// TODO Auto-generated catch block
		//		e.printStackTrace();
		//	}
    //   	}
    //	}
    	//同步数据
	    website= sitedao.getWebSiteById(1);
    	while(it.hasNext()){
        	OwnReport ownReport=new OwnReport(); 
        	ownReport=it.next();
        	List<String> list1=DetectDesignData.getUserMonthReport(ownReport, website, "17-09-01");
        	System.out.println(ownReport.getUsername());
        	for(String val : list1){
        		System.out.println(val);
        	}

            
             //String value="2017/12/1";
             //int month=Integer.parseInt(date1.substring(5,date1.lastIndexOf("/")));
			//int day=Integer.parseInt(date1.substring(date1.lastIndexOf("/")+1));
			//	System.out.println(month+" "+day);
				
				
    	}
    	
    	
	}	

}
