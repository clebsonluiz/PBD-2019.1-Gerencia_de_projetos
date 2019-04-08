package br.com.pbd2019_1.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class DateUtil {

	public static LocalDateTime getDateTime(LocalDate localDate, LocalTime localTime) {
		return LocalDateTime.of(localDate, localTime);
	}
	
	public static java.util.Date parceToDate(LocalDateTime localDateTime){
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static LocalDateTime parceToLocalDateTime(java.util.Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public static LocalDate parseToLocalDate(java.util.Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static java.util.Date parseToDate(LocalDate localDate){
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	
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
	
	public static String getDateString(String format,Date date) {
		return new SimpleDateFormat(format).format(date);
	}
	
	
}
