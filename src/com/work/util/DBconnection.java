package com.work.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

//import com.sun.org.apache.xalan.internal.utils.XMLSecurityPropertyManager.Property;
public class DBconnection {

	public static  String DBDRIVER=null;
	public static  String DBURL=null;
	public static  String DBUSER=null;
	public static  String DBPASS=null;
	public static Connection con=null;
	public static PreparedStatement prep=null;
	public static ResultSet rs=null;
	//获取连接对象
	public static Connection getConnection(){
		Properties prop=new Properties();
		try {
			InputStream in = DBconnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
			prop.load(in);
			DBDRIVER=prop.getProperty("driverClass");
			DBURL=prop.getProperty("url");
			DBUSER=prop.getProperty("username");
			DBPASS=prop.getProperty("password");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			Class.forName(DBDRIVER).newInstance();			
			con=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接异常！");
		}
		return con;
	}
    public static void close(Connection con){
    	if(con!=null){                      //如果连接对象不为空
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("数据库关闭异常！");
			}
    	}
    	
    }
    public static void close(PreparedStatement prep){
    	if(prep!=null){                      //如果连接对象不为空
    		try {
				prep.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("数据库关闭异常！");
			}
    	}
    	
    }
    public static void close(ResultSet rs){
    	if(rs!=null){                      //如果连接对象不为空
    		try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("数据库关闭异常！");
			}
    	}
    	
    }
}
