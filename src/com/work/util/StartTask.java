package com.work.util;

import java.util.Timer;

public class StartTask {
	@SuppressWarnings("deprecation")
	public static void start(){
		Timer timer = new Timer();  		
	    timer.schedule(new TimerTask(), 1000, 1000); 	 
	}
}
