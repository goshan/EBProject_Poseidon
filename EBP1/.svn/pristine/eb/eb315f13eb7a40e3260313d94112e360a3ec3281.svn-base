package com.ibm.p1.tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String dateToDateTime(Date date){
		final String[] month={
				"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"
		};
		StringBuffer ret=new StringBuffer();
		String dateToString=date.toString();
		ret.append(dateToString.substring(24,24+4));
		String sMonth=dateToString.substring(4,4+3);
		
		for(int i =0;i<12;i++){
			if(sMonth.equalsIgnoreCase(month[i])){
				if((i+1) <10){
					ret.append("-0");
				}
				else{
					ret.append("-");
				}
				ret.append(i+1);
				break;
			}
		}
		ret.append("-");
		ret.append(dateToString.substring(8,8+2));
		ret.append(" ");
		ret.append(dateToString.substring(11,11+8));
		return ret.toString();
	}
	
	public static String timestampToDateTime(Timestamp timeStamp){

		String formatStr ="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		try {
			Date d = formatter.parse(timeStamp.toString());
			
			return dateToDateTime(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date timestampToDate(Timestamp timeStamp){
		String formatStr ="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		try {
			Date d = formatter.parse(timeStamp.toString());
			
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
