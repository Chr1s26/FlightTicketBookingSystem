package util;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss.SSSSSS");
	
	private static DateTimeFormatter formatterWithoutFraction = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
	
	private static DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	public static LocalDateTime toDateTimeObj(String dateString) {
		try {
			LocalDateTime dateObj = LocalDateTime.parse(dateString,isoFormatter);
			  return dateObj;
		} catch (DateTimeParseException e1) {
			try {
			LocalDateTime dateObj = LocalDateTime.parse(dateString,formatterWithoutFraction);
			  return dateObj;}
			catch (DateTimeParseException e2) {
				try {
					 LocalDateTime dateObj = LocalDateTime.parse(dateString,formatter);
					 return dateObj;
				} catch (DateTimeParseException e3) {
					e3.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static Timestamp toTimestampObj(LocalDateTime dateObj) {
		return Timestamp.valueOf(dateObj);
	}
	
}
