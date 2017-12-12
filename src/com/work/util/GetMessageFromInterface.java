package com.work.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;

public class GetMessageFromInterface {
	
	//图灵接口方法
	public static String getMessageTuLing(String message){
		Properties prop=new Properties();
		InputStream in=GetMessageFromInterface.class.getClassLoader().getResourceAsStream("rebot.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url=prop.getProperty("tuling_interface_url");
		String key=prop.getProperty("tuling_interface_key");
		StringBuffer json=new StringBuffer();
		String inputline=null;
		String info=null;
		try {
			 message = URLEncoder.encode(message,"utf-8");
			URL u=new URL(url+"?key="+key+"&info="+message);
	
			HttpURLConnection con=(HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
			InputStream input=con.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(input));
			while((inputline=reader.readLine())!=null){
			   json.append(new String(inputline.getBytes(),"utf-8"));
		   }
		   input.close();
		   info=ParseStringToJson(json.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
   //天气查询接口
	public static String getMessageFromWeather(String city){
		Properties prop=new Properties();
		InputStream in=GetMessageFromInterface.class.getClassLoader().getResourceAsStream("rebot.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer json=new StringBuffer();
		String info=null;
		String url=prop.getProperty("baidu_interface_url");
		 try {
			//city = URLEncoder.encode(city,"utf-8");
			URL u=new URL(url+"&bk_key="+city);
			
			HttpURLConnection con=(HttpURLConnection) u.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
			InputStream input=con.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(input));
		    while((info=reader.readLine())!=null){
		    	json.append(new String(info.getBytes(),"utf-8"));
		    }
		 System.out.println(json.toString());
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json.toString();
		
	}
	//获取新闻接口
	public static String getNews(String type){
		Properties prop=new Properties();
		InputStream in=GetMessageFromInterface.class.getClassLoader().getResourceAsStream("rebot.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer json=new StringBuffer();
		String info=null;
		String url=prop.getProperty("news_interface_url");
		String key=prop.getProperty("news_interface_key");
		 try {
			//city = URLEncoder.encode(city,"utf-8");
			URL u=new URL(url+"?key="+key+"&type="+type);
			
			HttpURLConnection con=(HttpURLConnection) u.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
			InputStream input=con.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(input));
		    while((info=reader.readLine())!=null){
		    	json.append(new String(info.getBytes(),"utf-8"));
		    }
		 System.out.println(json.toString());
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json.toString();
		
	}
	//转换和解析json字符串
	@SuppressWarnings("unused")
	public static String ParseStringToJson(String json){
		System.out.println(json);
		JSONObject object=JSONObject.parseObject(json);
		String code=object.getString("code");
		String text=object.getString("text");
		return text;
	}
}
