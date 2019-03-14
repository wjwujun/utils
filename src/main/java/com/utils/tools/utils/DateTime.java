package com.utils.tools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期、时间转换处理工具
 * <pre>
 * 本类提供时间日期、处理转化处理的静态方法，主要方法有：
 * 1、转化时间成指定格式的字符串 convertDateTime()
 * 2、将指定格式的字符串日期时间转化为Date类型的日期时间 parseDateTime()
 * 3、日期 加 年，月，天，时，分，秒 数 addDateTime()
 *
 * 4、获取当前时间，格式：yyyy-MM-dd getCurrentDate_YYYYMMDD()
 * 5、获取当前时间，格式：yyyy-MM-dd HH:mm getCurrentDate_YYYYMMDDHHMM()
 * 6、获取当前时间，格式：yyyy-MM-dd HH:mm:ss getCurrentDate_YYYYMMDDHHMMSS()
 * 7、获取当前时间，格式：yyyyMMddHHmmss getCurrentDate_YYYYMMDDHHMMSS_EX () 
 * 8、获取当前时间，格式：yyyy年MM月dd日 getCurrentDate_YYYYMMDD_CN()
 * 9、获取当前时间，格式：yyyy年MM月dd日HH时mm分 getCurrentDate_YYYYMMDDHHMM_CN()
 * 10、获取当前时间，格式：yyyy年MM月dd日HH时mm分ss秒 getCurrentDate_YYYYMMDDHHMMSS_CN()
 *
 * 11、转换时间，格式：yyyy-MM-dd convertDateTime_YYYYMMDD()
 * 12、转换时间，格式：yyyy-MM-dd HH:mm convertDateTime_YYYYMMDDHHMM()
 * 13、转换时间，格式：yyyy-MM-dd HH:mm:ss convertDateTime_YYYYMMDDHHMMSS()
 * 14、转换时间，格式：dd日HH:mm convertDateTime_DDHHMM()
 * 15、转换时间，格式：HH:mm:ss convertDateTime_HHMMSS()
 * 16、转换时间，格式：HH:mm convertDateTime_HHMM()
 * 17、转换时间，格式：yyyy年MM月dd日 convertDateTime_YYYYMMDD_CN()
 * 18、转换时间，格式：yyyy年MM月dd日HH时mm分 convertDateTime_YYYYMMDDHHMM_CN()
 * 19、转换时间，格式：yyyy年MM月dd日HH时mm分ss秒 convertDateTime_YYYYMMDDHHMMSS_CN()
 *
 * 20、解析时间，格式：yyyy-MM-dd parseDateTime_YYYYMMDD()
 * 21、解析时间，格式：yyyy-MM-dd HH:mm parseDateTime_YYYYMMDDHHMM()
 * 22、解析时间，格式：yyyy-MM-dd HH:mm:ss parseDateTime_YYYYMMDDHHMMSS()
 * 23、解析时间，格式：yyyy年MM月dd parseDateTime_YYYYMMDD_CN()
 * 24、解析时间，格式：yyyy年MM月dd日HH时mm分 parseDateTime_YYYYMMDDHHMM_CN()
 * 25、解析时间，格式：yyyy年MM月dd日HH时mm分ss秒 parseDateTime_YYYYMMDDHHMMSS_CN()
 * 
 * 26、日期 加 年 数 addYears()
 * 27、日期 加 月 数 addMonths()
 * 28、日期 加 天 数 addDays()
 * 29、日期 加 小时 数 addHours()
 * 30、日期 加 分 数 addMinutes()
 * 31、日期 加 秒 数 addSeconds()
 * 32、生成 MS SQL SERVER 的日期字段的日期值 nowForMSSQLDateField()
 * 33、取星期几 getWeekByDate()
 * 34、获取两个 Date 相差的 天数 getDays()
 * 35、获取两个 Date 相差的 分钟数 getMinutes()
 * 36、获取两个 Date 相差的 分钟数 getMinutes_No_abs()
 * 37、生成日期列表 listDays()
 */
public class DateTime {
	public static final String STTIME = "sttime";
	public static final String ENDTIME = "endtime";
	/**
	 * 转化时间成指定格式的字符串
	 * @param currentDate 待转化给定日期时间
	 * @param datetimeFormat 为日期、时间指定的格式
	 * @return 按指定格式转后的日期、时间字符串
	 * @author Allen Zhang
	 */
	public static synchronized String convertDateTime(Date currentDate, String datetimeFormat) {
		if (currentDate == null || "".equals(currentDate)) {
			return "";
		}
		else if (datetimeFormat == null || "".equals(datetimeFormat)) {
			return "";
		}
		else {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(datetimeFormat);
				return formatter.format(currentDate);
			} catch (Exception e) {
				return "";
			}
		}
	}
	
	/**
	 * 将指定格式的字符串日期时间转化为Date类型的日期时间
	 * @param datetimeStr 指定格式的日期日期字符串
	 * @param datetimeFormat 为日期、时间指定的格式
	 * @return 转换成的Date类型的日期时间
	 * @author Allen Zhang
	 */
	public static synchronized Date parseDateTime(String datetimeStr, String datetimeFormat) {  
		Date parsedDate = null;  
		if (datetimeStr == null || "".equals(datetimeStr)) {
			return parsedDate;
		}
		if (datetimeFormat == null || "".equals(datetimeFormat)) {
			return parsedDate;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(datetimeFormat);  
			parsedDate = (Date)formatter.parse(datetimeStr);  
		}
		catch  (Exception  e)  {  
		}  
		return  parsedDate;  
	}  	
	
    /**
     * 日期 加 年，月，天，时，分，秒 数
     * @param currentDate 当前时间
     * @param AddType 添加类型  年，月，天，时，分，秒
     * @param dateTimeCount 数量
     * @return 加后的日期
     * @author Allen Zhang
     */
    public static synchronized Date addDateTime(Date currentDate, int AddType, int dateTimeCount) {
    	Date addedDate = currentDate;
    	if (currentDate != null && dateTimeCount != 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);    			
			calendar.add(AddType, dateTimeCount);
			addedDate = calendar.getTime();
    	}
    	return addedDate;
    }

	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获取当前时间
	 * 格式：yyyy-MM-dd
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String getCurrentDate_YYYYMMDD() {
		return convertDateTime(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取当前时间
	 * 格式：yyyy-MM-dd HH:mm
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String getCurrentDate_YYYYMMDDHHMM() {
		return convertDateTime(new Date(), "yyyy-MM-dd HH:mm");
	}
	
	/**
	 * 获取当前时间
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String getCurrentDate_YYYYMMDDHHMMSS() {
		return convertDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取当前时间
	 * 格式：yyyy-MM-dd HH:mm:ss:SS 包含毫秒
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String getCurrentDate_YYYYMMDDHHMMSS_millisecond() {
		return convertDateTime(new Date(), "yyyy-MM-dd HH:mm:ss:SS");
	}

	/**
	 * 获取当前时间
	 * 格式：HH:mm:ss
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String getCurrentDate_HHMMSS() {
		return convertDateTime(new Date(), "HH:mm:ss");
	}
	
	/**
	 * 获取当前时间
	 * 格式：yyyyMMddHHmmss
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String getCurrentDate_YYYYMMDDHHMMSSWithOutSeparator() {
		return convertDateTime(new Date(), "yyyyMMddHHmmss");
	}
    /////////////////////////////////////////////////////////////////////////////////////

	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获取当前时间
	 * 格式：yyyy年MM月dd日
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String getCurrentDate_YYYYMMDD_CN() {
		return convertDateTime(new Date(), "yyyy年MM月dd日");
	}

	/**
	 * 获取当前时间
	 * 格式：yyyy年MM月dd日HH时mm分
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String getCurrentDate_YYYYMMDDHHMM_CN() {
		return convertDateTime(new Date(), "yyyy年MM月dd日HH时mm分");
	}
	
	/**
	 * 获取当前时间
	 * 格式：yyyy年MM月dd日HH时mm分ss秒
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String getCurrentDate_YYYYMMDDHHMMSS_CN() {
		return convertDateTime(new Date(), "yyyy年MM月dd日HH时mm分ss秒");
	}
	/////////////////////////////////////////////////////////////////////////////////////
   
	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 转换时间
	 * 格式：yyyy-MM-dd
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_YYYYMMDD(Date currentDate) {
		return convertDateTime(currentDate, "yyyy-MM-dd");
	}
	/**
	 * 转换时间
	 * 格式：MM-dd
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 */
	public static String convertDateTime_MMDD(Date currentDate) {
		return convertDateTime(currentDate, "MM-dd");
	}
	
	/**
	 * 转换时间
	 * 格式：yyyy-MM-dd HH:mm
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_YYYYMMDDHHMM(Date currentDate) {
		return convertDateTime(currentDate, "yyyy-MM-dd HH:mm");
	}
	
	
	/**
	 * 转换时间
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_YYYYMMDDHHMMSS(Date currentDate) {
		return convertDateTime(currentDate, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 转换时间
	 * 格式：yyyy-MM-dd HH:mm:ss:SS
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_YYYYMMDDHHMMSS_SS(Date currentDate) {
		return convertDateTime(currentDate, "yyyy-MM-dd HH:mm:ss:SS");
	}
	
	/**
	 * 转换时间
	 * 格式：dd日HH:mm
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_DDHHMM(Date currentDate) {
		return convertDateTime(currentDate, "dd日HH:mm");
	}

	/**
	 * 转换时间
	 * 格式：HH:mm:ss
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_HHMMSS(Date currentDate) {
		return convertDateTime(currentDate, "HH:mm:ss");
	}

	/**
	 * 转换时间
	 * 格式：HH:mm
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_HHMM(Date currentDate) {
		return convertDateTime(currentDate, "HH:mm");
	}
	/////////////////////////////////////////////////////////////////////////////////////

	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 转换时间
	 * 格式：yyyy年MM月dd日
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_YYYYMMDD_CN(Date currentDate) {
		return convertDateTime(currentDate, "yyyy年MM月dd日");
	}
	
	/**
	 * 转换时间
	 * 格式：yyyy年MM月dd日
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_YYYYMM_CN(Date currentDate) {
		return convertDateTime(currentDate, "yyyy年MM月");
	}
	
	
	/**
	 * 转换时间
	 * 格式：yyyy年MM月dd日HH时mm分
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_YYYYMMDDHHMM_CN(Date currentDate) {
		return convertDateTime(currentDate, "yyyy年MM月dd日HH时mm分");
	}
	
	
	/**
	 * 转换时间
	 * 格式：yyyy年MM月dd日HH时mm分ss秒
	 * @param currentDate 等转换日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static String convertDateTime_YYYYMMDDHHMMSS_CN(Date currentDate) {
		return convertDateTime(currentDate, "yyyy年MM月dd日HH时mm分ss秒");
	}
	/////////////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 解析时间
	 * 格式：yyyy-MM-dd
	 * @param currentDate 等解析日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static Date parseDateTime_YYYYMMDD(String currentDate) {
		return parseDateTime(currentDate, "yyyy-MM-dd");
	}
	
	/**
	 * 解析时间
	 * 格式：yyyy-MM-dd HH:mm
	 * @param currentDate 等解析日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static Date parseDateTime_YYYYMMDDHHMM(String currentDate) {
		return parseDateTime(currentDate, "yyyy-MM-dd HH:mm");
	}
	
	/**
	 * 解析时间
	 * 格式：yyyy-MM-dd HH:mm
	 * @param currentDate 等解析日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static Date parseDateTime_YYYYMMDDHHMM_(String currentDate) {
		return parseDateTime(DateTime.getCurrentDate_YYYYMMDD().split("-")[0] + "-" + currentDate, "yyyy-MM-dd HH:mm");
	}
	
	
	/**
	 * 解析时间
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * @param currentDate 等解析日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static Date parseDateTime_YYYYMMDDHHMMSS(String currentDate) {
		return parseDateTime(currentDate, "yyyy-MM-dd HH:mm:ss");
	}
	/////////////////////////////////////////////////////////////////////////////////////
	

	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 解析时间
	 * 格式：yyyy年MM月dd
	 * @param currentDate 等解析日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static Date parseDateTime_YYYYMMDD_CN(String currentDate) {
		return parseDateTime(currentDate, "yyyy年MM月dd");
	}
	
	/**
	 * 解析时间
	 * 格式：yyyy年MM月dd日HH时mm分
	 * @param currentDate 等解析日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static Date parseDateTime_YYYYMMDDHHMM_CN(String currentDate) {
		return parseDateTime(currentDate, "yyyy年MM月dd日HH时mm分");
	}
	
	
	/**
	 * 解析时间
	 * 格式：yyyy年MM月dd日HH时mm分ss秒
	 * @param currentDate 等解析日期
	 * @return 当前时间的字符串形式
	 * @author Allen Zhang
	 */
	public static Date parseDateTime_YYYYMMDDHHMMSS_CN(String currentDate) {
		return parseDateTime(currentDate, "yyyy年MM月dd日HH时mm分ss秒");
	}
	/////////////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////////////////////////////////
    /**
     * 日期 加 年 数
     * @param currentDate 当前时间
     * @param years 年数量
     * @return 加后的日期
     * @author Allen Zhang
     */
    public static Date addYears(Date currentDate, int years) {
    	return addDateTime(currentDate, Calendar.YEAR, years);
    }
    
    /**
     * 日期 加 月 数
     * @param currentDate 当前时间
     * @param months 月数量
     * @return 加后的日期
     * @author Allen Zhang
     */
    public static Date addMonths(Date currentDate, int months) {
    	return addDateTime(currentDate, Calendar.MONTH, months);
    }

    
    /**
     * 日期 加 天 数
     * @param currentDate 当前时间
     * @param days 日数量
     * @return 加后的日期
     * @author Allen Zhang
     */
    public static Date addDays(Date currentDate, int days) {
    	return addDateTime(currentDate, Calendar.DATE, days);
    }

    /**
     * 日期 加 小时 数
     * @param currentDate 当前时间
     * @param hours 小时数量
     * @return 加后的日期
     * @author Allen Zhang
     */
    public static Date addHours(Date currentDate, int hours) {
    	return addDateTime(currentDate, Calendar.HOUR, hours);
    }

    /**
     * 日期 加 分 数
     * @param currentDate 当前时间
     * @param minutes 分钟数量
     * @return 加后的日期
     * @author Allen Zhang
     */
    public static Date addMinutes(Date currentDate, int minutes) {
    	return addDateTime(currentDate, Calendar.MINUTE, minutes);
    }

    /**
     * 日期 加 秒 数
     * @param currentDate 当前时间
     * @param seconds 秒数量
     * @return 加后的日期
     * @author Allen Zhang
     */
    public static Date addSeconds(Date currentDate, int seconds) {
    	return addDateTime(currentDate, Calendar.SECOND, seconds);
    }
	/////////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////////
    /**
     * 生成 MS SQL SERVER 的日期字段的日期值
     * @author Allen Zhang
     */
    public static Date nowForMSSQLDateField() {
    	return new Date();
    }
	/////////////////////////////////////////////////////////////////////////////////////
    
   
	/////////////////////////////////////////////////////////////////////////////////////
    /**
     * 取 星期几
     * @param currentDate 当前日期
     * @return 星期几
     * @author Allen Zhang
     */
    public static synchronized String getWeekByDate(Date currentDate) {
        String week = "";
 	   if (currentDate != null) {
 		   Calendar calendar = Calendar.getInstance();
 		   calendar.setTime(currentDate);
 		   week = getWeek(calendar, true);
 	   }
 	   return week;
    }
    
    /**
     * 取 星期几
     * @param currentDate 当前日期
     * @return 星期几
     * @author Allen Zhang
     */
    public static synchronized String getWeekByDateStr(String currentDate) {
        String week = "";
 	   if (currentDate != null) {
 		   Calendar calendar = Calendar.getInstance();
 		   calendar.setTime(parseDateTime_YYYYMMDD(currentDate));
 		   week = getWeek(calendar, true);
 	   }
 	   return week;
    }
   
   /**
    * 取 星期几 ，具体取值
    * @param currentDate 当前日期
    * @param toChinese 是否输出中文
    * @return 星期几
    * @author Allen Zhang
    */
    @SuppressWarnings("static-access")
    public static synchronized String getWeek(Calendar calendar, boolean toChinese) {
       String strRet = "";   
       int intWeek = 0;
       
       /**
        * 获取本周的第几天
        */
       intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1;   
       if (!toChinese) {   
          return (String.valueOf(intWeek));   
       }   
       switch (intWeek) {
       		case 1: {
       			strRet = "周一";
       			break;   
       		}
       		case 2: {
       			strRet = "周二";
       			break;   
       		}
       		case 3: {
       			strRet = "周三";
       			break;   
       		}
       		case 4: {
       			strRet = "周四";
       			break;   
       		}
       		case 5: {
       			strRet = "周五";
       			break;   
       		}
       		case 6: {
       			strRet = "周六";
       			break;   
       		}
       		case 0: {
       			strRet = "周日";   
       			break;   
       		}
       		default: {
       			strRet = "不详";   
           		break;   
       		}
       }   
       return  strRet;
	}     
   
    /**
     * 获取两个 Date 相差的 天数
     * @param dateBeg 开始日期
     * @param dateEnd 结束日期
     * @return 两个 Date 相差的 天数
     * @author Allen Zhang
     */
    public static long getDays(Date dateBeg, Date dateEnd) {
    	if (dateEnd != null && dateEnd != null) {
        	return (Math.abs(dateEnd.getTime() - dateBeg.getTime()) / 24 / 3600 / 1000 );
    	}
    	else {
    		return 0;
    	}
    }

    /**
     * 获取两个 Date 相差的 分钟数
     * @param dateBeg 开始日期
     * @param dateEnd 结束日期
     * @return 两个 Date 相差的 分钟数
     * @author Allen Zhang
     */
    public static long getMinutes(Date dateBeg, Date dateEnd) {
    	if (dateEnd != null && dateEnd != null) {
        	return (Math.abs(dateEnd.getTime() - dateBeg.getTime()) / 60 / 1000 );
    	}
    	else {
    		return 0;
    	}
    }

    /**
     * 获取两个 Date 相差的 秒数
     * @param dateBeg 开始日期
     * @param dateEnd 结束日期
     * @return 两个 Date 相差的 秒数
     * @author Allen Zhang
     */
    public static long getSecond(Date dateBeg, Date dateEnd) {
    	if (dateEnd != null && dateEnd != null) {
        	return (Math.abs(dateEnd.getTime() - dateBeg.getTime()) / 1000 );
    	}
    	else {
    		return 0;
    	}
    }
    
    /**
     * 获取两个 Date 相差的 毫秒数
     * @param dateBeg 开始日期
     * @param dateEnd 结束日期
     * @return 两个 Date 相差的 毫秒数
     * @author Allen Zhang
     */
    public static long getMilliSecond(Date dateBeg, Date dateEnd) {
    	if (dateEnd != null && dateEnd != null) {
    	    return Math.abs(dateEnd.getTime() - dateBeg.getTime());
    	}else {
    	    return 0;
    	}
    }
    
    /**
     * 毫秒转日期
     * @param msec 毫秒
     * @return
     * @author jh
     */
    public static String getDateTime_MilliSecond(long msec) {
		DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(msec);
		return formatter.format(calendar.getTime());
    }

    /**
     * 获取两个 Date 相差的 分钟数
     * @param dateBeg 开始日期
     * @param dateEnd 结束日期
     * @return 两个 Date 相差的 分钟数
     * @author Allen Zhang
     */
    public static long getMinutes_No_abs(Date dateBeg, Date dateEnd) {
    	if (dateEnd != null && dateEnd != null) {
        	return ((dateEnd.getTime() - dateBeg.getTime()) / 60 / 1000 );
    	}
    	else {
    		return 0;
    	}
    }
    
    /**
     * 生成日期列表
     * @param dateBeg 起 日期
     * @param dateEnd 止 日期
     * @return 日期列表
     * @author AllenZhang
     */
    public static synchronized ArrayList<String> listDays(Date dateBeg, Date dateEnd) {
    	ArrayList<String> listday = new ArrayList<String>();
    	if (dateBeg != null && dateEnd != null) {
        	long days = getDays(dateBeg, dateEnd);
        	if (days > 0) {
        		String dateBegStr = "";
        		if (dateBeg.before(dateEnd)) {
            		dateBegStr = convertDateTime_YYYYMMDD(dateBeg);
        		}
        		else {
            		dateBegStr = convertDateTime_YYYYMMDD(dateEnd);
        		}
        		for (int i = 0 ; i < days ; i ++ ) {
        			Date date = addDays(parseDateTime_YYYYMMDD(dateBegStr + " 00:00:00"), i);
        			String dateBegNextDayStr = convertDateTime_YYYYMMDD(date);
    				if (!listday.contains(dateBegNextDayStr)) {
    					listday.add(dateBegNextDayStr);
    				}
    			}    		
        	}
    	}
    	return listday;
    }
    
    /**
     * 生成日期列表，样式为 8.1
     * @param dateBeg 起 日期
     * @param dateEnd 止 日期
     * @return 日期列表
     * @author AllenZhang
     */
    public static synchronized ArrayList<String> listDays2(Date dateBeg, Date dateEnd) {
    	ArrayList<String> listday = new ArrayList<String>();
    	if (dateBeg != null && dateEnd != null) {
        	long days = getDays(dateBeg, dateEnd);
        	if (days > 0) {
        		days += 1;
        		String dateBegStr = "";
        		if (dateBeg.before(dateEnd)) {
            		dateBegStr = convertDateTime_YYYYMMDD(dateBeg);
        		}
        		else {
            		dateBegStr = convertDateTime_YYYYMMDD(dateEnd);
        		}
        		for (int i = 0 ; i < days ; i ++ ) {
        			Date date = addDays(parseDateTime_YYYYMMDD(dateBegStr + " 00:00:00"), i);
        			String dateBegNextDayStr = convertDateTime(date,"M.d");
    				if (!listday.contains(dateBegNextDayStr)) {
    					listday.add(dateBegNextDayStr);
    				}
    			}    		
        	}else{
        		listday.add(convertDateTime(dateBeg,"M.d"));
        	}
    	}
    	return listday;
    }
    
    /**
     * listMonths:获取当年所有的月份列表
     * @author ozd
     * @return
     */
    public static synchronized ArrayList<String> listYearMonths(){
    	ArrayList<String> listMonth = new ArrayList<String>();
    	int month = Integer.valueOf(DateTime.getMonth());
    	String year = DateTime.convertDateTime(new Date(), "yyyy-");
    	for (int i = 1; i <= 12; i++) {
    		if(i > month){
    			break;
    		}
    		if(i < 10){
    			listMonth.add(year + "0" + i);
    		}else{
    			listMonth.add(year + i);
    		}
		}
    	return listMonth;
    }
    
    /**
     * listMonths:获取指定日期的月份列表【若跨年这存在返回0，不推荐使用】
     * @author ozd
     * @return
     */
    public static synchronized ArrayList<String> listMonths(String startDay, String endDay){
    	ArrayList<String> listMonth = new ArrayList<String>();
    	try{
    		// 获取两个日期相差的月份数
        	int result = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(sdf.parse(startDay));
            c2.setTime(sdf.parse(endDay));
            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
            if(result < 0){
            	return listMonth;
            }
            
            result = Math.abs(result);
            
            if(result == 0){
            	listMonth.add(startDay.substring(0, 7));
            	return listMonth;
            }
            
            for (int i = 0; i <= result; i++) {
        		listMonth.add(DateTime.convertDateTime(DateTime.addMonths(DateTime.parseDateTime_YYYYMMDD(startDay), i), "yyyy-MM"));
            }
    	}catch (Exception e) {
    		
    	}
    	return listMonth;
    }
	/**
	 * listMonthsAcrossY:获取指定日期的月份列表【跨年】
	 * @author ozd
	 * @return
	 */
	public static synchronized ArrayList<String> listMonthsAcrossY(String startDay, String endDay){
		ArrayList<String> listMonth = new ArrayList<String>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

			Calendar min = Calendar.getInstance();
			Calendar max = Calendar.getInstance();

			min.setTime(sdf.parse(startDay));
			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

			max.setTime(sdf.parse(endDay));
			max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

			Calendar curr = min;
			while (curr.before(max)) {
				listMonth.add(sdf.format(curr.getTime()));
				curr.add(Calendar.MONTH, 1);
			}

		}catch (Exception e) {

		}
		return listMonth;
	}
    
    /**
     * 获取当前日期的第二天
     * @param day
     * @return
     * 创建日期:Apr 2, 2010-9:36:07 AM
     * @author jh
     */
    public static String getTomorrow_YYYYMMDD(String day){
		Date date=DateTime.parseDateTime_YYYYMMDD(day);
		Date tomorrowDate=DateTime.addDays(date, 1);
		return convertDateTime_YYYYMMDD(tomorrowDate);
    }
    
    /**
     * 取 月份 ，具体取值1月 ， 2月
     * @param currentDate 当前日期
     * @param toChinese 是否输出中文
     * @return 星期几
     * @author Allen Zhang
     */
     public static synchronized String getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;//月
        String strRet = "";   
        switch (month) {
        		case 1: {
        			strRet = "1";
        			break;   
        		}
        		case 2: {
        			strRet = "2";
        			break;   
        		}
        		case 3: {
        			strRet = "3";
        			break;   
        		}
        		case 4: {
        			strRet = "4";
        			break;   
        		}
        		case 5: {
        			strRet = "5";
        			break;   
        		}
        		case 6: {
        			strRet = "6";
        			break;   
        		}
        		case 7: {
        			strRet = "7";   
        			break;   
        		}
        		case 8: {
        			strRet = "8";   
        			break;   
        		}
        		case 9: {
        			strRet = "9";   
        			break;   
        		}
        		case 10: {
        			strRet = "10";   
        			break;   
        		}
        		case 11: {
        			strRet = "11";   
        			break;   
        		}
        		case 12: {
        			strRet = "12";   
        			break;   
        		}
        		default: {
        			strRet = "不详";   
            		break;   
        		}
        }   
        return  strRet;
 	} 
     
     /**
      * 取 季度，具体取值一季度 二季度
      * @param toChinese 是否输出中文
      * @return 季度
      * @author Allen Zhang
      */
      public static synchronized String getSeason() {
 		Calendar cal = Calendar.getInstance();
 		int month = cal.get(Calendar.MONTH) + 1;//月
         String strRet = "";   
         switch (month) {
         		case 1: {
         			strRet = "一季度";
         			break;   
         		}
         		case 2: {
         			strRet = "一季度";
         			break;   
         		}
         		case 3: {
         			strRet = "一季度";
         			break;   
         		}
         		case 4: {
         			strRet = "二季度";
         			break;   
         		}
         		case 5: {
         			strRet = "二季度";
         			break;   
         		}
         		case 6: {
         			strRet = "二季度";
         			break;   
         		}
         		case 7: {
         			strRet = "三季度";   
         			break;   
         		}
         		case 8: {
         			strRet = "三季度";   
         			break;   
         		}
         		case 9: {
         			strRet = "三季度";   
         			break;   
         		}
         		case 10: {
         			strRet = "四季度";   
         			break;   
         		}
         		case 11: {
         			strRet = "四季度";   
         			break;   
         		}
         		case 12: {
         			strRet = "四季度";   
         			break;   
         		}
         		default: {
         			strRet = "不详";   
             		break;   
         		}
         }   
         return  strRet;
  	}

	/**
	 * 获取距离明天零点的剩余时间
	 *
	 * @return
	 * @throws Exception
	 */
	public static Long nextDayZeroDate() throws Exception {
		Calendar cal = Calendar.getInstance();
		Date now = new Date();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Long secsOfNextDay = cal.getTime().getTime();
		Long secsOfCurrentTime = now.getTime();
		Long distance = (secsOfNextDay - secsOfCurrentTime) / 1000;
		if (distance > 0 && distance != null) {
			return distance;
		}
		return new Long(0);
	}
	public static String getDistanceTime(String str1, String str2, String pattern) {
		String res = "";
		LocalDateTime localDate = DateTime.parseDateTime_YYYYMMDDHHMMSS(str2).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		if(localDate.isBefore(LocalDateTime.now())){
			res = "已超时-";
		}
		DateFormat df = new SimpleDateFormat(pattern);
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		long ms = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff ;
			if(time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res+day + "天" + hour + "小时" + min + "分";
	}
}