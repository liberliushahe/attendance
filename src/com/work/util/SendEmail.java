package com.work.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.work.dao.TimeTableDao;
import com.work.entity.TimeTable;

/*
 * Author:jijiuxue
 * Time:2017-6-19
 * 发送邮件工具类
 * 查询所有用户邮箱，定期发送相关记录
 * 定时提醒功能
 */
public class SendEmail {
	public static  String MAILUSERNAME=null;
	public static  String MAILPASSWORD=null;

    public static void sendMail(String email,String username){
    	TimeTableDao timedao=new TimeTableDao();
    	List<TimeTable> list=timedao.queryTimeTableByUserName(username);
    	int count=timedao.findCurrentAllCountByUsername(username);
    	 Properties props = new Properties();  
    	 Properties prop=new Properties();
    		try {
    			InputStream in = SendEmail.class.getClassLoader().getResourceAsStream("mail.properties");
    			prop.load(in);
    			MAILUSERNAME=prop.getProperty("mail-username");
    			MAILPASSWORD=prop.getProperty("mail-password");

    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
         // 开启debug调试  
         //props.setProperty("mail.debug", "true");  
         // 发送服务器需要身份验证  
         props.setProperty("mail.smtp.auth", "true");  
         // 设置邮件服务器主机名  
         props.setProperty("mail.host", "smtp.qq.com");  
         // 发送邮件协议名称  
         props.setProperty("mail.transport.protocol", "smtp"); 
         //开启ssl验证，qq邮箱需要使用
         final String smtpPort = "465";
         props.setProperty("mail.smtp.port", smtpPort);
         props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.setProperty("mail.smtp.socketFactory.fallback", "false");
         props.setProperty("mail.smtp.socketFactory.port", smtpPort); 
         // 设置环境信息  
         Session session = Session.getInstance(props);  
           
         // 创建邮件对象  
         Message msg = new MimeMessage(session);  
         try {
			msg.setSubject("月考勤记录详情信息");
			// 设置邮件内容  
			
	        StringBuffer stringbuffer = new StringBuffer();  
	      
	        stringbuffer.append("<html>")  
	        .append("<head>")  
	        .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
	        .append("<title></title>")  
	        .append("<style type=\"text/css\">")  
	        .append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}") 
	    	.append("table {border；1px; width: 600px; min-height: 25px; line-height: 25px; text-align: center;  border-collapse: collapse;}") 
            .append(" .flag1{font-size: 15px;color: red;}")
            .append(" .foot{margin:auto 0}")
            .append(" .info{margin:right}")
	        .append("</style>")  
	        .append("</head>")  
	        .append("<body>")  
	        .append("<span>尊敬的在线考勤系统用户:"+username+"</span><br>")
	        .append("<span class=\"test\">请尽快查看自己的打卡记录，如有问题，请登录</span><br/>")  
	        .append("<span>http://10.52.64.38:8989/attendance/login.jsp 查看和编辑</span><br/>")
	        .append("<span class=\"test\">注意：当月考勤系统在每月1号会进行邮件提醒,2号自动发送至公司邮箱,请及时查看！！</span><br/>")  

	        .append("</h4>本月记录共有<span class=\"test\">"+count+"</span>条</h4>")
	        .append("<table border=1 cellspacing=0 cellpadding=0 width=90%>")
	        .append("<tr class=\"flag1\"><td width=6%>姓名</td><td width=10%>日期</td><td width=10%>上班打卡时间</td><td width=10%>下班打卡时间</td><td width=10%>周末加班</td><td width=10%>假日加班</td><td width=10%>说明</td></tr>");
	        for(int i=0;i<list.size();i++){
	        	String week="";
	        	String holiday="";
	        	String explain="";
	        	if(!(list.get(i).getWeekend()==null)){
	        		week=list.get(i).getWeekend();
	        	}else{
	        	
	        		 week="";
	        	}
	        	if(!(list.get(i).getHoliday()==null)){
	        		holiday=list.get(i).getHoliday();
	        	}else{
	        		
	        		holiday="";
	        	}
	        	if(!(list.get(i).getExplain()==null)){
	        		explain=list.get(i).getExplain();
	        	}else{
	        		explain="";
	        	}
	        	
		        	stringbuffer.append("<tr><td width=6%>"+list.get(i).getUsername()+"</td><td width=10%>"+list.get(i).getDate()+"</td><td width=10%>"+list.get(i).getStarttime()+"</td><td width=10%>"+list.get(i).getEndtime()+"</td><td width=10%>"+week+"</td><td width=10%>"+holiday+"</td><td width=10%>"+explain+"</td></tr>");
	        }

	        stringbuffer.append("</table>")
	        .append("<span class=\"foot\">此为系统邮件，请勿回复 ©2017 attendance.com版权所有</span>")
	        .append("</body>")  
	        .append("</html>");
	        msg.setContent(stringbuffer.toString(), "text/html;charset =utf-8"); 
	        //msg.setText(stringbuffer.toString());
	         try {
				msg.setFrom(new InternetAddress(MAILUSERNAME,"在线考勤管理系统","utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	           
	         Transport transport = session.getTransport();  
	        
	         // 连接邮件服务器  
	         transport.connect(MAILUSERNAME, MAILPASSWORD);  
	         // 发送邮件  
	         transport.sendMessage(msg, new Address[] {new InternetAddress(email)}); 
	        
	         // 关闭连接  
	         transport.close();  
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         
     }  
   
    
    //发送验证码模板
    public static void sendCode(String email,String code) throws Exception{
    Properties props = new Properties(); 
	Properties prop=new Properties();
	try {
		InputStream in = SendEmail.class.getClassLoader().getResourceAsStream("mail.properties");
		prop.load(in);
		MAILUSERNAME=prop.getProperty("mail-username");
		MAILPASSWORD=prop.getProperty("mail-password");

	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    
    
    // 发送服务器需要身份验证  
    props.setProperty("mail.smtp.auth", "true");  
    // 设置邮件服务器主机名  
    props.setProperty("mail.host", "smtp.qq.com");  
    // 发送邮件协议名称  
    props.setProperty("mail.transport.protocol", "smtp"); 
    //开启ssl验证，qq邮箱需要使用
    final String smtpPort = "465";
    props.setProperty("mail.smtp.port", smtpPort);
    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.socketFactory.port", smtpPort); 
    // 设置环境信息  
    Session session = Session.getInstance(props);  
      
    // 创建邮件对象  
    Message msg = new MimeMessage(session);  
    try {
		msg.setSubject("考勤管理系统密码修改");
		// 设置邮件内容  
		
       StringBuffer stringbuffer = new StringBuffer();  
     
       stringbuffer.append("<html>")  
       .append("<head>")  
       .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
       .append("<title></title>")  
       .append("<style type=\"text/css\">")  
       .append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}") 
   	.append("table {border；1px; width: 600px; min-height: 25px; line-height: 25px; text-align: center;  border-collapse: collapse;}") 
       .append(" .flag1{font-size: 15px;color: red;}")
       .append(" .foot{margin:auto 0}")
        .append(" .info{margin:right}")
       .append("</style>")  
       .append("</head>")  
       .append("<body>")  
      .append("<span>尊敬的在线考勤系统用户:"+email+"您好!</span><br>")
       .append("<span class=\"test\"></span><br/>")  
       .append("<span>您此次申请的账号信息变更需要的验证码如下,请在10分钟内输入验证码进行下一步操作</span><br/>")
       .append("<span>如非您本人操作,请忽略此邮件。</span><br/>")  

       .append("</h2><span class=\"test\">验证码:"+code+"</span></h2>")
      
       .append("<span class=\"foot\">此为系统邮件，请勿直接回复 ©2017 attendance.com版权所有</span>")
       .append("</body>")  
       .append("</html>");
       msg.setContent(stringbuffer.toString(), "text/html;charset =utf-8"); 
       //msg.setText(stringbuffer.toString());
        try {
			msg.setFrom(new InternetAddress(MAILUSERNAME,"在线考勤管理系统","utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
          
        Transport transport = session.getTransport();  
       
        // 连接邮件服务器  
        transport.connect(MAILUSERNAME, MAILPASSWORD);  
        // 发送邮件  
        transport.sendMessage(msg, new Address[] {new InternetAddress(email)}); 
       
        // 关闭连接  
        transport.close();  
		
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
    }  
    //发送提醒邮件
    public static void sendRemind(String email,String username) throws Exception{
    Properties props = new Properties();  
    Properties prop=new Properties();
	try {
		InputStream in = SendEmail.class.getClassLoader().getResourceAsStream("mail.properties");
		prop.load(in);
		MAILUSERNAME=prop.getProperty("mail-username");
		MAILPASSWORD=prop.getProperty("mail-password");

	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    // 发送服务器需要身份验证  
    props.setProperty("mail.smtp.auth", "true");  
    // 设置邮件服务器主机名  
    props.setProperty("mail.host", "smtp.qq.com");  
    // 发送邮件协议名称  
    props.setProperty("mail.transport.protocol", "smtp"); 
    //开启ssl验证，qq邮箱需要使用
    final String smtpPort = "465";
    props.setProperty("mail.smtp.port", smtpPort);
    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.socketFactory.port", smtpPort); 
    // 设置环境信息  
    Session session = Session.getInstance(props);  
      
    // 创建邮件对象  
    Message msg = new MimeMessage(session);  
    try {
		msg.setSubject("考勤管理系统日报提醒");
		// 设置邮件内容  
		
       StringBuffer stringbuffer = new StringBuffer();  
     
       stringbuffer.append("<html>")  
       .append("<head>")  
       .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
       .append("<title></title>")  
       .append("<style type=\"text/css\">")  
       .append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}") 
   	.append("table {border；1px; width: 600px; min-height: 25px; line-height: 25px; text-align: center;  border-collapse: collapse;}") 
       .append(" .flag1{font-size: 15px;color: red;}")
       .append(" .foot{margin:auto 0}")
        .append(" .info{margin:right}")
       .append("</style>")  
       .append("</head>")  
       .append("<body>")  
      .append("<span>尊敬的在线考勤系统用户:"+username+"您好!</span><br>")
       .append("<span class=\"test\"></span><br/>")  
       .append("<span>您有两天日报未写,请尽快填写日报!!!!</span><br/>")
       .append("<span>如非您本人操作,请忽略此邮件。</span><br/>")            
       .append("<span class=\"foot\">此为系统邮件，请勿直接回复 ©2017 attendance.com版权所有</span>")
       .append("</body>")  
       .append("</html>");
       msg.setContent(stringbuffer.toString(), "text/html;charset =utf-8"); 
       //msg.setText(stringbuffer.toString());
        try {
			msg.setFrom(new InternetAddress(MAILUSERNAME,"在线考勤管理系统","utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
          
        Transport transport = session.getTransport();  
       
        // 连接邮件服务器  
        transport.connect(MAILUSERNAME, MAILPASSWORD);  
        // 发送邮件  
        transport.sendMessage(msg, new Address[] {new InternetAddress(email)});       
        // 关闭连接  
        transport.close();  
		
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
    }  
    /**
     * 发送邮件给没有确定的人员
     */
  //发送提醒邮件
    public static void sendRemindToUnsure(String email,String username) throws Exception{
    Properties props = new Properties();  
    Properties prop=new Properties();
	try {
		InputStream in = SendEmail.class.getClassLoader().getResourceAsStream("mail.properties");
		prop.load(in);
		MAILUSERNAME=prop.getProperty("mail-username");
		MAILPASSWORD=prop.getProperty("mail-password");

	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    // 发送服务器需要身份验证  
    props.setProperty("mail.smtp.auth", "true");  
    // 设置邮件服务器主机名  
    props.setProperty("mail.host", "smtp.qq.com");  
    // 发送邮件协议名称  
    props.setProperty("mail.transport.protocol", "smtp"); 
    //开启ssl验证，qq邮箱需要使用
    final String smtpPort = "465";
    props.setProperty("mail.smtp.port", smtpPort);
    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.socketFactory.port", smtpPort); 
    // 设置环境信息  
    Session session = Session.getInstance(props);  
      
    // 创建邮件对象  
    Message msg = new MimeMessage(session);  
    try {
		msg.setSubject("考勤管理系统数据确认提醒");
		// 设置邮件内容  
		
       StringBuffer stringbuffer = new StringBuffer();  
     
       stringbuffer.append("<html>")  
       .append("<head>")  
       .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
       .append("<title></title>")  
       .append("<style type=\"text/css\">")  
       .append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}") 
   	.append("table {border；1px; width: 600px; min-height: 25px; line-height: 25px; text-align: center;  border-collapse: collapse;}") 
       .append(" .flag1{font-size: 15px;color: red;}")
       .append(" .foot{margin:auto 0}")
        .append(" .info{margin:right}")
       .append("</style>")  
       .append("</head>")  
       .append("<body>")  
      .append("<span>尊敬的在线考勤系统用户:"+username+"您好!</span><br>")
       .append("<span class=\"test\"></span><br/>")  
       .append("<span>您还没有确认考勤，请及时确认!!!!</span><br/>")
       .append("<span>如非您本人操作,请忽略此邮件。</span><br/>")            
       .append("<span class=\"foot\">此为系统邮件，请勿直接回复 ©2017 attendance.com版权所有</span>")
       .append("</body>")  
       .append("</html>");
       msg.setContent(stringbuffer.toString(), "text/html;charset =utf-8"); 
       //msg.setText(stringbuffer.toString());
        try {
			msg.setFrom(new InternetAddress(MAILUSERNAME,"在线考勤管理系统","utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
          
        Transport transport = session.getTransport();  
       
        // 连接邮件服务器  
        transport.connect(MAILUSERNAME, MAILPASSWORD);   
        // 发送邮件  
        transport.sendMessage(msg, new Address[] {new InternetAddress(email)});       
        // 关闭连接  
        transport.close();  
		
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
    }  
    /**
     * 发送附件邮件
     */
    public static void sendEmailFile(String email,String filename) throws Exception{
    	Properties props = new Properties();  
    	Properties prop=new Properties();
    	try {
    		InputStream in = SendEmail.class.getClassLoader().getResourceAsStream("mail.properties");
    		prop.load(in);
    		MAILUSERNAME=prop.getProperty("mail-username");
    		MAILPASSWORD=prop.getProperty("mail-password");

    	} catch (Exception e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
        // 发送服务器需要身份验证  
        props.setProperty("mail.smtp.auth", "true");  
        // 设置邮件服务器主机名  
        props.setProperty("mail.host", "smtp.qq.com");  
        // 发送邮件协议名称  
        props.setProperty("mail.transport.protocol", "smtp"); 
        //开启ssl验证，qq邮箱需要使用
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort); 
        // 设置环境信息  
        Session session = Session.getInstance(props);            
        //读取本地邮件
        Message msg = new MimeMessage(session); 
        String affix="c:\\"+filename+".xls";
        String decodeFile=filename+".xls";
        System.out.println(decodeFile);
        try {
    		msg.setSubject(filename);
    		// 设置邮件内容  
    	    // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
    	    Multipart multipart = new MimeMultipart();             	     	  
    	    //   设置邮件的文本内容
    	    BodyPart contentPart = new MimeBodyPart();
    	    contentPart.setText("甘肃项目组考勤表");
    	    multipart.addBodyPart(contentPart);
    	    //添加附件
    	    BodyPart messageBodyPart= new MimeBodyPart();
    	    DataSource source = new FileDataSource(affix);
    	    //添加附件的内容
    	    messageBodyPart.setDataHandler(new DataHandler(source));
    	    //添加附件的标题
    	    //这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
    	    sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
    	    messageBodyPart.setFileName("=?GBK?B?"+enc.encode(decodeFile.getBytes())+"?=");
    	    multipart.addBodyPart(messageBodyPart);
    	  
    	  
    	    //将multipart对象放到message中
    	    msg.setContent(multipart);
    	    //保存邮件
    	    msg.saveChanges();
           //msg.setText(stringbuffer.toString());
            try {
    			msg.setFrom(new InternetAddress(MAILUSERNAME,"甘肃考勤","utf-8"));
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}  

            Transport transport = session.getTransport();  
           
            // 连接邮件服务器  
            transport.connect(MAILUSERNAME, MAILPASSWORD);    
            // 发送邮件  
            transport.sendMessage(msg, new Address[] {new InternetAddress(email)});       
            // 关闭连接  
            transport.close();  
    		
    	} catch (MessagingException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}  
    }
}

