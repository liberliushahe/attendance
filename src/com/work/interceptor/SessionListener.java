package com.work.interceptor;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	 private int userCounts=0;
   //创建会话
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		userCounts++;
		event.getSession().getServletContext().setAttribute("userCounts", userCounts);  
	
	}
   //会话销毁
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		userCounts--;

	}

}
