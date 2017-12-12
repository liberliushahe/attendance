package com.work.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityInterceptor implements HandlerInterceptor {
	private static final String LOGIN_URL = "/attendance/timeout.jsp";  
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String url = request.getRequestURI();
		HttpSession session=request.getSession(true);
		//允许通过的控制器
		//String[] pass=new String[]{"login.do","online.do","work.do","getJson.do"};
		Object name = session.getAttribute("username");
		
		String[] access=new String[]{"checkEmail.do","modify.do","sendCode.do","register.do","login.do","online.do","work.do","getJson.do","getClient","modifyflag.do"};
		for(int i=0;i<access.length;i++){
			if(url.indexOf(access[i])!=-1){
				return true;
				
			}
			
		}
		
//		if(url.indexOf("modify.do")!=-1 ||url.indexOf("sendCode.do")!=-1 ||url.indexOf("register.do")!=-1 ||url.indexOf("login.do")!=-1 || url.indexOf("online.do")!=-1 || url.indexOf("work.do")!=-1 ||  url.indexOf("getJson.do")!=-1){
//			
//			
//			return true;
//		}
		
		if (name==null || name.equals("")) {
				response.sendRedirect(LOGIN_URL); 
                 return false;
				  } 
		return true;
			    						
		}
		
	

}
