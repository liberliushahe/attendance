package com.work.interceptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.h2.tools.Server;

import com.work.util.StartTask;

public class H2DBServerStartListener implements ServletContextListener {
	private Server server;
	@SuppressWarnings("unused")
	private boolean flag=true;//判断表是否已经创建
    //web项目启动，h2数据库随即启动
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
         if(this.server!=null){
        	 //停止数据库
        	 this.server.stop();
        	 this.server=null;
         }
	}

	@SuppressWarnings("static-access")
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
       System.out.println("start h2 database........");
       
       StartTask.start();
       
       try {
		server.createTcpServer().start();
		
		autoCreateTable();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("h2 start exception");
		e.printStackTrace();
	}
       System.out.println("h2 start success");
	}
	//数据库启动自动建表
	//部署项目方便
	public void autoCreateTable(){
		try{
			Class.forName("org.h2.Driver");
			Connection con=DriverManager.getConnection("jdbc:h2:tcp:localhost/workattendance", "root", "123456");
			String sql1=
					"CREATE TABLE `operlog` (\n" +
					"  `id` int(10) NOT NULL AUTO_INCREMENT,\n" +
					"  `type` int(1) DEFAULT NULL,\n" +
					"  `content` varchar(50) DEFAULT NULL,\n" +
					"  `username` varchar(20) DEFAULT NULL,\n" +
					"  `ip` varchar(20) DEFAULT NULL,\n" +
					"  `time` varchar(20) DEFAULT NULL,\n" +
					"  PRIMARY KEY (`id`)\n" +
					")";
			String sql2=
					"CREATE TABLE `notice` (\n" +
					"  `id` int(10) NOT NULL AUTO_INCREMENT,\n" +
					"  `title` varchar(50) DEFAULT NULL,\n" +
					"  `content` varchar(500) DEFAULT NULL,\n" +
					"  `author` varchar(10) DEFAULT NULL,\n" +
					"  `publishdate` varchar(20) DEFAULT NULL,\n" +
					"  PRIMARY KEY (`id`)\n" +
					")";
			String sql3=
					"CREATE TABLE `employee` (\n" +
					"  `id` int(10) NOT NULL AUTO_INCREMENT,\n" +
					"  `ip` varchar(20) DEFAULT NULL,\n" +
					"  `username` varchar(20) DEFAULT NULL,\n" +
					"  `password` varchar(50) DEFAULT NULL,\n" +
					"  `userid` varchar(20) DEFAULT NULL,\n" +
					"  `role` int(1) DEFAULT NULL,\n" +
					"  `status` int(1) DEFAULT NULL,\n" +
					"  `phone` varchar(12) DEFAULT NULL,\n" +
					"  `email` varchar(20) DEFAULT NULL,\n" +
					"  `isswitch` int(1) DEFAULT NULL,\n" +
					"  `isflag` int(1) DEFAULT NULL,\n" +
					"  image varchar(20) DEFAULT NULL,\n" +
					"  PRIMARY KEY (`id`)\n" +
					")";
			String sql4=
					"CREATE TABLE `timetable` (\n" +
					"  `id` int(10) NOT NULL AUTO_INCREMENT,\n" +
					"  `username` varchar(15) DEFAULT NULL,\n" +
					"  `date` varchar(10) DEFAULT NULL,\n" +
					"  `starttime` varchar(10) DEFAULT NULL,\n" +
					"  `endtime` varchar(10) DEFAULT NULL,\n" +
					"  `weekend` varchar(1) DEFAULT NULL,\n" +
					"  `holiday` varchar(1) DEFAULT NULL,\n" +
					"  `explain1` varchar(50) DEFAULT NULL,\n" +
					"  PRIMARY KEY (`id`)\n" +
					")";
			String sql5="CREATE TABLE `photo` (\n" +
					"  `id` int(10) NOT NULL AUTO_INCREMENT,\n" +
					"  `title` varchar(`100) DEFAULT NULL,\n" +
					"  `content` varchar(200) DEFAULT NULL,\n" +
					"  `path` varchar(30) DEFAULT NULL,\n" +
					"   PRIMARY KEY (`id`)\n" +
					")";
			String sql6="CREATE TABLE `department` (\n" +
					"  `departid` int(10) NOT NULL AUTO_INCREMENT,\n" +
					"  `departname` varchar(50) DEFAULT NULL,\n" +
					"  `content` varchar(50) DEFAULT NULL,\n" +
					"  PRIMARY KEY (`departid`)\n" +
					")";
			String sql7="CREATE TABLE `linkman` (\n" +
					"  `id` int(10) NOT NULL AUTO_INCREMENT,\n" +
					"  `name` varchar(20) DEFAULT NULL,\n" +
					"  `phone` varchar(15) DEFAULT NULL,\n" +
					"  `qq` varchar(15) DEFAULT NULL,\n" +
					"  `matter` varchar(100) DEFAULT NULL,\n" +
					"  `address` varchar(100) DEFAULT NULL,\n" +
					"  `email` varchar(30) DEFAULT NULL,\n" +
					"  `job` varchar(90) DEFAULT NULL,\n" +
					"   departid int(10) NOT NULL,\n" +
					"   PRIMARY KEY (`id`)\n" +
					")";
					
					
					
			String insert="INSERT INTO `employee` VALUES ('1', '0.0.0.0', '超级管理员', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '1', '1', '110110110', '1540077031@qq.com', 0,0, 'headimage.jpg',0)";
			PreparedStatement prep1=con.prepareStatement(sql1);
			PreparedStatement prep2=con.prepareStatement(sql2);
			PreparedStatement prep3=con.prepareStatement(sql3);
			PreparedStatement prep4=con.prepareStatement(sql4);
			PreparedStatement prep5=con.prepareStatement(sql5);
			PreparedStatement prep6=con.prepareStatement(sql6);
			PreparedStatement prep7=con.prepareStatement(sql7);
			PreparedStatement prep8=con.prepareStatement(insert);
			prep1.execute();
			prep2.execute();
			prep3.execute();
			prep4.execute();
			prep5.execute();
			prep6.execute();
			prep7.execute();
			prep8.execute();
			prep1.close();
			prep2.close();
			prep3.close();
			prep4.close();
			prep5.close();
			prep6.close();
			prep7.close();
			prep8.close();
			con.close();
			System.out.println("create table success");
			flag=false;
		}catch(Exception e){
			System.out.println("the table is exists");
			
		}
	}

}
