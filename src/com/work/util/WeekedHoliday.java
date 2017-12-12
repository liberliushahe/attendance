package com.work.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WeekedHoliday {

	public static boolean isWeeked(Date date){
	    Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        if(ca.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || ca.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
        return true;
        }
		return false;
	}

	public static boolean isHoliday(Date date){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=format.format(date);
		boolean flag=false;
		List<String> holiday=new ArrayList<String>();
		holiday.add("2018-01-01");
		holiday.add("2018-02-15");
		holiday.add("2018-02-16");
		holiday.add("2018-02-17");
		holiday.add("2018-02-18");
		holiday.add("2018-02-19");
		holiday.add("2018-02-20");
		holiday.add("2018-02-21");
		holiday.add("2018-04-05");
		holiday.add("2018-04-06");
		holiday.add("2018-04-07");
		holiday.add("2018-04-29");
		holiday.add("2018-04-30");
		holiday.add("2018-05-01");
		holiday.add("2018-06-16");
		holiday.add("2018-06-17");
		holiday.add("2018-06-18");
		holiday.add("2018-09-22");
		holiday.add("2018-09-23");
		holiday.add("2018-09-24");
		holiday.add("2018-10-01");
		holiday.add("2018-10-02");
		holiday.add("2018-10-03");
		holiday.add("2018-10-04");
		holiday.add("2018-10-05");
		holiday.add("2018-10-06");
		holiday.add("2018-10-07");
		Iterator<String> it=holiday.iterator();
		while (it.hasNext()) {
			String dateString=it.next();
			if(currentDate.equals(dateString)){
				flag=true;
				break;	
			}
			try {
				format.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

}
