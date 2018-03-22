package util;

import java.util.Calendar;
import java.util.Date;

public class NowDateUtil {
	private static long longDate = System.currentTimeMillis();
	private static Date date = new Date(longDate);
	private static Calendar calDate = Calendar.getInstance();

	public static String getFullDate() {
		return calDate.get(Calendar.YEAR) + "" + (date.getMonth() + 1) + ""
				+ date.getDate() + "" + date.getHours() + ""
				+ date.getMinutes() + "" + date.getSeconds();
	}

	public static String getLongDate() {
		return longDate + "";
	}

	public static String getCalendarDate() {
		return calDate.get(Calendar.YEAR) + ""
				+ (calDate.get(Calendar.MONTH) + 1) + ""
				+ calDate.get(Calendar.DATE) + "" + calDate.get(Calendar.HOUR)
				+ "" + calDate.get(Calendar.MINUTE) + ""
				+ calDate.get(Calendar.SECOND);
	}

	public static String getDateDate() {
		return date.getYear() + "" + (date.getMonth() + 1) + ""
				+ date.getDate() + "" + date.getHours() + ""
				+ date.getMinutes() + "" + date.getSeconds();
	}
	
	public static String getY_M_D() {
		return calDate.get(Calendar.YEAR) + "-" + (date.getMonth() + 1) + "-"
				+ date.getDate();
	}

	public static void main(String[] args) {
		System.out.println(NowDateUtil.getLongDate());
		System.out.println(NowDateUtil.getCalendarDate());
		System.out.println(NowDateUtil.getDateDate());
		System.out.println(NowDateUtil.getFullDate());
		System.out.println(NowDateUtil.getY_M_D());
	}
}
