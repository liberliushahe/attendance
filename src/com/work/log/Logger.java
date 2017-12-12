package com.work.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 
 * @author jijiuxue
 * @version v0.0.1
 * @desc 该类为日志管理基础类 具体是采用spring sop实现
 */
public class Logger {
	//设置日志
	public static void log(String msg){
		File file=new File("attendance.log");
		try {
			PrintWriter out=new PrintWriter(new FileWriter(file,true));
			out.println(new Date()+":"+msg);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 方法执行前增加日志
	public static void before(String msg) {
		log(msg);
	}

}
