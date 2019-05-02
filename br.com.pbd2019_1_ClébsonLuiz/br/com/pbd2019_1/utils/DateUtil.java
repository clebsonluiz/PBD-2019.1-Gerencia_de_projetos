package br.com.pbd2019_1.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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
	
	public static Date getDateTime(java.util.Date date) {
		return Date.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	}
	
	public static java.util.Date getDateString(String format, String dateString) throws ParseException {
		return new SimpleDateFormat(format).parse(dateString);
	}
	
	public static interface TimeUtil{
		
		public static int[] horario(String horario) 
		{
			List<String> list = new ArrayList<>();
			
			for(int i = 0; i < 60; i++) 
			{
				if( i < 10)
					list.add("0"+i);
				else
					list.add(""+i);
			}
			
			int hora = 0;
			int minuto = 0;
			int segundo = 0;
			
			String[] s = horario.split(":"); 
			
			for(int i = 0; i < 60; i++) 
				if(list.get(i).equals(s[0])) 
				{
					hora = i;
					break;
				}
			
			for(int i = 0; i < 60; i++) 
				if(list.get(i).equals(s[1])) 
				{
					minuto = i;
					break;
				}
			
			for(int i = 0; i < 60; i++) 
				if(list.get(i).equals(s[2])) 
				{
					segundo = i;
					break;
				}
			
			return new int[] {hora, minuto, segundo};
		} 
		
		/**
		 * Calcula se um determinado horario antecede o outro <br>
		 * horario1 antecede horario2 ? <br>
		 * @param horario1 Horario1
		 * @param horario2 Horario2
		 * */
		public static boolean isAntes(String horario1, String horario2) 
		{
			
			int[] hora1 = horario(horario1);
			int[] hora2 = horario(horario2);
			
			LocalTime horario_1 = LocalTime.of(hora1[0], hora1[1], hora1[2]); 
			LocalTime horario_2 = LocalTime.of(hora2[0], hora2[1], hora2[2]); 
			
			return horario_1.isBefore(horario_2);
			
		} 
	}
	
	
}
