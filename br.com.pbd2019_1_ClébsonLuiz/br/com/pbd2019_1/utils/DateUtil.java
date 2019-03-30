package br.com.pbd2019_1.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

	public static Date getDataAtual(){
		return getDateSQL(new java.util.Date());
	}
	
	public static java.util.Date getDate(String yyyy_MM_dd){
		java.util.Date date = null;
		try {
			date =  new SimpleDateFormat("yyyy-MM-dd").parse(yyyy_MM_dd);
		} catch (ParseException e) {}
		
		return date;
	}
	
	public static java.util.Date getDate(Date date){
		return new java.util.Date(date.getTime());
	}
	
	public static Date getDateSQL(String date) {
		return Date.valueOf(date);
	}
	
	public static Date getDateSQL(java.util.Date date) {
		return Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date));
	}
	
}
