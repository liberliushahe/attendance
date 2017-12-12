package com.work.log;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LoggerBeforeAdvice implements MethodBeforeAdvice {
	//方法之前
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		// TODO Auto-generated method stub
		Logger.before(arg0.toString());
	}

}
