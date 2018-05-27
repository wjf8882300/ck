/** 
 * @(#)DateUtils.java 1.0.0 2015年4月21日 下午2:32:29  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */ 

package com.tonggu.util;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;

/**   
 * 日期工具
 *  
 * @author  HuangXiaodong
 * @version $Revision:1.0.0, $Date: 2015年4月21日 下午2:32:29 $ 
 */
public class DateUtils {
	// 一天的毫秒数 60*60*1000*24
	public final static long DAY_MILLIS = 86400000;

	// 一小时的毫秒数 60*60*1000
	private final static long HOUR_MILLIS = 3600000;

	// 一分钟的毫秒数 60*1000
	private final static long MINUTE_MILLIS = 60000;
	
	/**默认时区名称**/
	private final static String DEFAULT_TIME_ZONE = "Asia/Shanghai";
	
	/**
	 * 定自义格式当前日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		return formatDate(Calendar.getInstance().getTime(), format);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param format 可以为yyyy-MM-dd，yyyy-MM-dd HH:mm:ss等
	 * @return
	 */
	public static String formatDate(Date date, String format){		
		return DateFormatUtils.format(date, format, TimeZone.getTimeZone(DEFAULT_TIME_ZONE), Locale.getDefault(Locale.Category.FORMAT));
	}
	
	/**
	 * 日期转换
	 * 
	 * @param format 可以为yyyy-MM-dd，yyyy-MM-dd HH:mm:ss等
	 * @return
	 */
	public static Date parseDate(String dateStr, String format) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		java.text.ParsePosition pos = new java.text.ParsePosition(0);
		return formatter.parse(dateStr, pos);
	}
	
	/**
	 * 日期和时间截取到日期 
	 * 如dt:yyyy-MM-dd HH:mm:ss 返回yyyy-MM-dd
	 *
	 * @param dt
	 * @return
	 */
	public static Date truncateDate(Date dt) {
		return org.apache.commons.lang3.time.DateUtils.truncate(dt, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 计算N天后的日期
	 * 
	 * @param date
	 * @param par
	 * @return
	 */
	public static Date getAfterDay(Date date, int par) {
		return org.apache.commons.lang3.time.DateUtils.truncate(org.apache.commons.lang3.time.DateUtils.addDays(date, par), Calendar.DAY_OF_MONTH);
	}

	/**
	 * 计算N个月后的日期
	 * 
	 * @param date
	 * @param par
	 * @return
	 */
	public static Date getAfterMonth(Date date, int par) {
		return org.apache.commons.lang3.time.DateUtils.truncate(org.apache.commons.lang3.time.DateUtils.addMonths(date, par), Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 计算N个月后的日期（带顺延），如1月31号加1个月为2月28号，则顺延一天变为3月1号
	 *
	 * @param date
	 * @param par
	 * @return
	 */
	public static Date getAfterMonthNext(Date date, int par) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		int oldDate = c.get(Calendar.DAY_OF_MONTH);
		c.add(Calendar.MONTH, par);
		int newDate = c.get(Calendar.DAY_OF_MONTH);
		// 判断新月天数是否小于旧月天数，若小于则表明出现了28 < 30, 30 < 31之类的，需顺延
		if(newDate < oldDate) {
			c.add(Calendar.DATE, 1); // 加1天
		}		
		return org.apache.commons.lang3.time.DateUtils.truncate(c.getTime(), Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取指定日期 指定分钟后的日期
	 * 
	 * @param monthNum
	 * @return
	 */
	public static Date getDateAfterByMinute(Date date, Integer minute) {		
		return org.apache.commons.lang3.time.DateUtils.addMinutes(date, minute);
	}
	
	/**
	 * 获取某个月的第一天
	 * @param dt
	 * @param formatStr
	 * @return
	 */
	public static String getFirstDay(Date dt,String formatStr) {	
		return formatDate(org.apache.commons.lang3.time.DateUtils.truncate(dt, Calendar.MONTH), formatStr);
	}
   
	/**
	 * 获取某个月的最后一天
	 * 算法如下：1)取本月第一天 2)当月加1 3)日期减一
	 * @param dt
	 * @param formatStr
	 * @return
	 */
	public static String getLastDay(Date dt,String formatStr) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(dt);
		ca.set(Calendar.DAY_OF_MONTH, 1); 
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DATE, -1);
		return formatDate(ca.getTime(), formatStr);
	}
	
	/**
	 * 获取某月最后一天 日
	 * @param dt
	 * @param formatStr
	 * @return
	 */
	public static int getLastDateDay(Date dt, String formatStr) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(dt);
		ca.set(Calendar.DAY_OF_MONTH, 1); 
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DATE, -1);
		
		return ca.get(Calendar.DATE);
	}
	
	/**
 	 * 
 	 * 计算两个日期之间相差几天
 	 * 
 	 * @param beginDate
 	 *            开始日期 
 	 * @param endDate
 	 *            截止日期 
 	 * @return 相差天数
 	 * @author zhoudl
 	 */
	public static int datePhaseDiffer(Date beginDate, Date endDate) {
 		try {
 			return (int) ((endDate.getTime() - beginDate.getTime()) / DAY_MILLIS);
 		} catch (Exception e) {
 			throw new RuntimeException(e);
 		}
 	}
	
 	/**
 	 * 计算两个日期之间相差几小时
 	 *
 	 * @author  wangjf
 	 * @date    2015年7月2日 下午3:37:23
 	 * @param beginDate
 	 * @param endDate
 	 * @return
 	 */
 	public static int hourPhaseDiffer(Date beginDate, Date endDate) {
 		try {
 			return (int) ((endDate.getTime() - beginDate.getTime()) / HOUR_MILLIS);
 		} catch (Exception e) {
 			throw new RuntimeException(e);
 		}
 	}
 	
 	/**
 	 * 计算两个日期之间相差几分钟
 	 *
 	 * @author  wangjf
 	 * @date    2015年7月2日 下午3:37:26
 	 * @param beginDate
 	 * @param endDate
 	 * @return
 	 */
 	public static int minutePhaseDiffer(Date beginDate, Date endDate) {
 		try {
 			return (int) ((endDate.getTime() - beginDate.getTime()) / MINUTE_MILLIS);
 		} catch (Exception e) {
 			throw new RuntimeException(e);
 		}
 	}
 	
 	/**
 	 * 计算两个日期之间相差几秒
 	 *
 	 * @author  wangjf
 	 * @date    2015年7月2日 下午3:37:53
 	 * @param beginDate
 	 * @param endDate
 	 * @return
 	 */
 	public static int secondPhaseDiffer(Date beginDate, Date endDate) {
 		try {
 			return (int) ((endDate.getTime() - beginDate.getTime()) / 1000);
 		} catch (Exception e) {
 			throw new RuntimeException(e);
 		}
 	}
		
	/**
	 * 判断两个日期之间相差多少月
	 * 
	 * @param begindate
	 * @param enddate
	 * @return
	 * @author HuangXiaodong 2014-10-08, 20:28:47
	 * @modified
	 */
	public static int monthPhaseDiffer(Date begindate, Date enddate) {
		try {
			int year1 = getYear(begindate);
			int month1 = getMonth(begindate);
			int day1 = getDay(begindate);
			int year2 = getYear(enddate);
			int month2 = getMonth(enddate);
			int day2 = getDay(enddate);
			int month = (year2 - year1) * 12 + month2 - month1;
			if (day2 - day1 > 0)
				return month + 1;
			else
				return month;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 计算两个日期相差的天、小时、分钟
	 * 
	 * @param start
	 * @param end
	 */
	public static String show(Date start, Date end) {
		long temp = end.getTime() - start.getTime();
		String leavingTime = temp / DAY_MILLIS + "天" + (temp % DAY_MILLIS) / HOUR_MILLIS + "小时" + ((temp % DAY_MILLIS) % HOUR_MILLIS) / MINUTE_MILLIS + "分";
		return leavingTime;
	}

	/**
	 * 
	 * 获取日期中的年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date dt) {	
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取日期中的月份
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getMonth(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.get(Calendar.MONTH);
	}
	
	/**
	 * 获取日期中的天
	 *
	 * @param dt
	 * @return
	 */
	public static int getDay(Date dt)  {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.get(Calendar.DATE);
	}

	/**
	 * 比较两个日期
	 * date1 >= date2 返回true，否则返回false
	 * @param date1
	 * @param date2
	 * @param day
	 * @return
	 */
	public static boolean compare_date(Date date1, Date date2) {
		boolean flag = false;
		if (date1.getTime() >= date2.getTime()) {
			return true;
		}
		return flag;
	}

	/**
	 * 取下个还款日期
	 * 如  date:2015-11-16 day:10 则返回2015-12-10
	 *   date:2015-12-01 day:10 则返回2016-01-10
	 * @param date
	 * @param day
	 * @return
	 */
	static public Date nextPayDate(Date date, String day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int dateDD = cal.get(Calendar.DAY_OF_MONTH);
		int dd = Integer.parseInt(day);
		cal.set(Calendar.DAY_OF_MONTH, dd);
		if (dateDD >= dd) {
			cal.add(Calendar.MONTH, 1);
		}
		return cal.getTime();
	}

	/**
	 * 下一还款日包含还款日当天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	static public Date nextPayDateIncludeToday(Calendar date, String day) {
		Objects.requireNonNull(date);
		int dateDD = date.get(Calendar.DAY_OF_MONTH);
		int dd = Integer.parseInt(day);
		date.set(Calendar.DAY_OF_MONTH, dd);
		if (dateDD > dd) {
			date.add(Calendar.MONTH, 1);
		}
		return date.getTime();
	}
	
	/**
	 * 获取上一个还款日
	 * 
	 * @param day
	 * @return
	 */
	public static Calendar lastPayDateWithPattern(Date date, String day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dd = Integer.parseInt(day);
		int date_dd = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, dd);
		if (date_dd < dd) {
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		}
		return cal;
	}
	
	 /**
 	 * 页面显示的日期型 显示的样式是yyyyMMdd
 	 * @param date
 	 * @return 
 	 * @author zhoudl
 	 */
 	public static String showDateString(Date date){
 		if (null == date ){
 			throw new RuntimeException("日期不能为空");
 		}
 		return formatDate(date, "yyyyMMdd");
 	}

	/**
	 * 获取某天起始时间
	 *
	 * @author  wangjf
	 * @date    2015年4月30日 下午2:26:36
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date){
		return truncateDate(date);
	}
	
	/**
	 * 获取某天结束时间
	 *
	 * @author  wangjf
	 * @date    2015年4月30日 下午2:26:48
	 * @param date
	 * @return
	 */
	public static Date getEndDate(Date date){
		return parseDate(DateUtils.formatDate(date, "yyyy-MM-dd") + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 获取第二天日期
	 *
	 * @author  wangjf
	 * @date    2015年11月19日 下午8:29:37
	 * @param date
	 * @return
	 */
	public static Date getNextDate(Date date) {
		return getAfterDay(getStartDate(date), 1);
	}
	
	/**
	 * 获取当月第一天起始时间
	 *
	 * @author  wangjf
	 * @date    2015年4月30日 下午3:45:24
	 * @param date
	 * @return
	 */
	public static Date getMonthStartDate(Date date){	
		return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.MONTH);
	}
	
	/**
	 * 将yyyy-MM-dd格式的字符串转为日期格式
	 *
	 * @author  wangjf
	 * @date    2015年5月1日 下午3:40:42
	 * @param dateStr
	 * @return
	 */
	public static Date parseStandardDate(String dateStr){
		return DateUtils.parseDate(dateStr, "yyyy-MM-dd");
	}
		
	/**
	 * 日期Date转换为Timestamp
	 * **/
	public static Timestamp getDateToTimeStamp(Date paramDate){	
		if(paramDate==null){
			return null;
		}
		return new Timestamp(paramDate.getTime());
	}
		
	/**
	 * 指定日期之前的某个固定时间
	 * **/
	public static Timestamp getTimeStamp(Date currDate,int num){
		if(currDate==null){
			throw new RuntimeException("日期对象不能为空");
		}		
		return new Timestamp(getAfterMonth(currDate, -num).getTime());
	}
	
//	public static void main(String[] args) {
//		System.out.println(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.getFirstDay(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.getLastDay(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.datePhaseDiffer(parseDate("20151212", "yyyyMMdd"), parseDate("20151112", "yyyyMMdd")));
//		System.out.println(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.getAfterDay(new Date(), 7));
//		System.out.println(DateUtils.getAfterMonth(new Date(), 1));
//		System.out.println(DateUtils.getAfterMonthNext(parseDate("2015-10-31", "yyyy-MM-dd"), 1));
//		System.out.println(DateUtils.getDateAfterByMinute(new Date(), 10));
//		//System.out.println(DateUtils.addStringDa(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"), 10, "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.nextPayDate(new Date(), "10"));
//		System.out.println(DateUtils.getStartDate(new Date()));
//		System.out.println(DateUtils.getEndDate(new Date()));
//		System.out.println(DateUtils.getMonthStartDate(new Date()));
//		//System.out.println(getFormatedDateString(null));
//		System.out.println(DateUtils.getDateToTimeStamp(new Date()));
//		System.out.println(DateUtils.getTimeStamp(new Date(), 10));
//		//System.out.println(DateUtils.getSpaceMonths(new Timestamp(parseDate("20151012", "yyyyMMdd").getTime()), new Timestamp(parseDate("20181012", "yyyyMMdd").getTime())));
//		System.out.println(DateUtils.monthPhaseDiffer(parseDate("20151112", "yyyyMMdd"), parseDate("20151212", "yyyyMMdd")));
//		System.out.println(DateUtils.truncateDate(DateUtils.getDateToTimeStamp(new Date())));
//	}
}
