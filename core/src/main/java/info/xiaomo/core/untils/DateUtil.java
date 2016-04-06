package info.xiaomo.core.untils;

/**
 * <p>Title: 时间和日期的工具类</p>
 * <p>Description: DateUtil类包含了标准的时间和日期格式，以及这些格式在字符串及日期之间转换的方法</p>
 * <p>Copyright: Copyright (c) 2007 advance,Inc. All Rights Reserved</p>
 * <p>Company: advance,Inc.</p>
 *
 * @author advance
 * @version 1.0
 */

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    private static final String datePattern = "MM/dd/yyyy";

    private static final String timePattern = datePattern + " HH:MM a";

    public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat DATE_FORMAT_CN = FastDateFormat.getInstance("yyyy年 MM月 dd日");
    public static final FastDateFormat DATE_FORMAT_RSS = FastDateFormat.getInstance("E, d MMM yyyy HH:mm:ss z", Locale.CHINA);

    /**
     * Return default datePattern (MM/dd/yyyy)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return datePattern;
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static String date2Str(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static String date2Str(String pattern, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(pattern);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask   the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            return null;
        }

        return (date);
    }

    public static Date str2Date(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            return null;
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate == null) {
            System.out.print("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(datePattern, aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate)
            throws ParseException {
        Date aDate = null;

        try {

            aDate = convertStringToDate(datePattern, strDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;

        }
        return aDate;
    }

    //日期格式转换成时间戳
    public static long getTimeStamp(String pattern, String strDate) {
        long returnTimeStamp = 0;
        Date aDate;
        try {
            aDate = convertStringToDate(pattern, strDate);
        } catch (ParseException pe) {
            aDate = null;
        }
        if (aDate == null) {
            returnTimeStamp = 0;
        } else {
            returnTimeStamp = aDate.getTime();
        }
        return returnTimeStamp;
    }

    //获取当前日期的邮戳
    public static long getNowTimeStamp() {
        long returnTimeStamp;
        Date aDate = null;
        try {
            aDate = convertStringToDate("yyyy-MM-dd HH:mm:ss", getNowDateTime());
        } catch (ParseException pe) {
            aDate = null;
        }
        if (aDate == null) {
            returnTimeStamp = 0;
        } else {
            returnTimeStamp = aDate.getTime();
        }
        return returnTimeStamp;
    }

    /**
     * 得到格式化后的系统当前日期
     *
     * @param strScheme 格式模式字符串
     * @return 格式化后的系统当前时间，如果有异常产生，返回空串""
     * @see java.util.SimpleDateFormat
     */
    public static String getNowDateTime(String strScheme) {
        String strReturn;
        Date now = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strScheme);
            strReturn = sdf.format(now);
        } catch (Exception e) {
            strReturn = "";
        }
        return strReturn;
    }

    public static String getNowDateTime() {
        String strReturn;
        Date now = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strReturn = sdf.format(now);
        } catch (Exception e) {
            strReturn = "";
        }
        return strReturn;
    }

    /**
     * 转化日期格式"MM/dd/YY、MM.dd.YY、MM-dd-YY、MM/dd/YY"，并输出为正常的格式yyyy-MM-dd
     *
     * @param strDate 待转换的日期格式
     * @return 格式化后的日期，如果有异常产生，返回空串""
     * @see java.util.SimpleDateFormat
     */
    public static String convertNormalDate(String strDate) {
        String strReturn = null;
        //先把传入参数分格符转换成java认识的分格符
        String[] date_arr = strDate.split("\\.|\\/|\\-");
        try {
            if (date_arr.length == 3) {
                if (date_arr[2].length() != 4) {
                    String nowYear = getNowDateTime("yyyy");
                    date_arr[2] = nowYear.substring(0, 2) + date_arr[2];
                }
                strReturn = DateUtil.getDateTime("yyyy-MM-dd",
                        convertStringToDate(combineStringArray(date_arr, "/")));
            }

        } catch (Exception e) {
            return strReturn;
        }
        return strReturn;
    }

    /**
     * 将字符串数组使用指定的分隔符合并成一个字符串。
     *
     * @param array 字符串数组
     * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
     * @return 合并后的字符串
     * @since 0.4
     */
    public static String combineStringArray(String[] array, String delim) {
        int length = array.length - 1;
        if (delim == null) {
            delim = "";
        }
        StringBuilder result = new StringBuilder(length * 8);
        for (int i = 0; i < length; i++) {
            result.append(array[i]);
            result.append(delim);
        }
        result.append(array[length]);
        return result.toString();
    }

    public static int getWeekNum(String strWeek) {
        int returnValue = 0;
        if (strWeek.equals("Mon")) {
            returnValue = 1;
        } else if (strWeek.equals("Tue")) {
            returnValue = 2;
        } else if (strWeek.equals("Wed")) {
            returnValue = 3;
        } else if (strWeek.equals("Thu")) {
            returnValue = 4;
        } else if (strWeek.equals("Fri")) {
            returnValue = 5;
        } else if (strWeek.equals("Sat")) {
            returnValue = 6;
        } else if (strWeek.equals("Sun")) {
            returnValue = 0;
        } else if (strWeek == null) {
            returnValue = 0;
        }

        return returnValue;
    }

    /**
     * 获取日期字符串中的中文时间表示字符串
     *
     * @param strDate
     * @return
     */
    public static String getSabreTime(String strDate) {
        String strReturn = "";
        try {

            Date d = DateUtil.str2Date("yyyy-MM-dd HH:mm:ss", StringUtil.replace(
                    strDate, "T", " "));
            strReturn = DateUtil.date2Str("hh:mm aaa", d);

        } catch (Exception e) {
            return strReturn;
        }
        return strReturn;
    }

    /**
     * 获取日期字符串中的中文日期表示字符串
     *
     * @param strDate
     * @return
     */
    public static String getSabreDate(String strDate) {
        String strReturn = "";
        try {
            String p;
            if (strDate.length() > 10)
                p = "yyyy-MM-dd HH:mm:ss";
            else
                p = "yyyy-MM-dd";
            Date d = DateUtil.str2Date(p, StringUtil.replace(strDate, "T", " "));
            strReturn = DateUtil.date2Str("EEE d-MMM", d);

        } catch (Exception e) {
            return strReturn;
        }
        return strReturn;
    }

    /**
     * 获取日期字符串的中文日期时间表示
     *
     * @param strDate
     * @return
     */
    public static String getSabreDateTime(String strDate) {
        String strReturn = "";
        try {
            String p;
            if (strDate.length() > 10)
                p = "yyyy-MM-dd HH:mm:ss";
            else
                p = "yyyy-MM-dd";
            Date d = DateUtil.str2Date(p, StringUtil.replace(strDate, "T", " "));
            strReturn = DateUtil.date2Str("EEE d-MMM hh:mm aaa", d);

        } catch (Exception e) {
            return strReturn;
        }
        return strReturn;
    }

    /**
     * 得到格式化后的指定日期
     *
     * @param strScheme 格式模式字符串
     * @param date      指定的日期值
     * @return 格式化后的指定日期，如果有异常产生，返回空串""
     */
    public static String getDateTime(Date date, String strScheme) {
        String strReturn;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strScheme);
            strReturn = sdf.format(date);
        } catch (Exception e) {
            strReturn = "";
        }

        return strReturn;
    }

    /**
     * 获取日期
     *
     * @param timeType 时间类型，譬如：Calendar.DAY_OF_YEAR
     * @param timenum  时间数字，譬如：-1 昨天，0 今天，1 明天
     * @return 日期
     */
    public static Date getDateFromNow(int timeType, int timenum) {
        Calendar cld = Calendar.getInstance();
        cld.set(timeType, cld.get(timeType) + timenum);
        return cld.getTime();
    }

    /**
     * 获取日期
     *
     * @param timeType      时间类型，譬如：Calendar.DAY_OF_YEAR
     * @param timenum       时间数字，譬如：-1 昨天，0 今天，1 明天
     * @param format_string 时间格式，譬如："yyyy-MM-dd HH:mm:ss"
     * @return 字符串
     */
    public static String getDateFromNow(int timeType, int timenum, String format_string) {
        if ((format_string == null) || (format_string.equals("")))
            format_string = "yyyy-MM-dd HH:mm:ss";
        Calendar cld = Calendar.getInstance();
        Date date = null;
        DateFormat df = new SimpleDateFormat(format_string);
        cld.set(timeType, cld.get(timeType) + timenum);
        date = cld.getTime();
        return df.format(date);
    }

    /**
     * 获取当前日期的字符串
     *
     * @param format_string 时间格式，譬如："yyyy-MM-dd HH:mm:ss"
     * @return 字符串
     */
    public static String getDateNow(String format_string) {
        if ((format_string == null) || (format_string.equals("")))
            format_string = "yyyy-MM-dd HH:mm:ss";
        Calendar cld = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(format_string);
        return df.format(cld.getTime());
    }

    /**
     * 格式化成yyyy-MM-dd
     *
     * @return String    返回类型
     */
    public static String format(Timestamp tamp) {
        return DATE_FORMAT.format(tamp);
    }

    /**
     * 格式化成中文日期
     *
     * @return String    返回类型
     */
    public static String formatCn(Timestamp tamp) {
        return DATE_FORMAT_CN.format(tamp);
    }

    /**
     * 格式化成RSS需要格式
     *
     * @return String    返回类型
     */
    public static String formartRss(Timestamp tamp) {
        return DATE_FORMAT_RSS.format(tamp);
    }

    /**
     * hour小时之前
     */
    public static Date hourBefor(int hour) {
        return DateUtils.addHours(new Date(), -hour);
    }

    public static int openDay = 5;
    private String iDate = "";
    private int iYear;
    private int iMonth;
    private int iDay;

    //  iDateTime = 2002-01-01 23:23:23
    public void setDate(String iDateTime) {
        this.iDate = iDateTime.substring(0, 10);
    }

    public String getDate() {
        return this.iDate;
    }

    public int getYear() {
        iYear = Integer.parseInt(iDate.substring(0, 4));
        return iYear;
    }

    public int getMonth() {
        iMonth = Integer.parseInt(iDate.substring(5, 7));
        return iMonth;
    }

    public int getDay() {
        iDay = Integer.parseInt(iDate.substring(8, 10));
        return iDay;
    }

    public static String subDate(String date) {
        return date.substring(0, 10);
    }

    /**
     * 计算是否是季度末
     *
     * @param date
     * @return
     */
    public static boolean isSeason(String date) {
        int getMonth = Integer.parseInt(date.substring(5, 7));
        boolean sign = false;
        if (getMonth == 3)
            sign = true;
        if (getMonth == 6)
            sign = true;
        if (getMonth == 9)
            sign = true;
        if (getMonth == 12)
            sign = true;
        return sign;
    }

    /**
     * 计算从现在开始几天后的时间
     *
     * @param afterDay
     * @return
     */
    public static String getDateFromNow(int afterDay) {
        GregorianCalendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        date = calendar.getTime();

        return df.format(date);
    }

    /**
     * 带格式
     *
     * @param afterDay
     * @param format_string
     * @return
     */
    public static String getDateFromNow(int afterDay, String format_string) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;

        DateFormat df = new SimpleDateFormat(format_string);

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        date = calendar.getTime();

        return df.format(date);
    }

    /**
     * 得到当前时间，用于文件名，没有特殊字符，使用yyyyMMddHHmmss格式
     *
     * @param afterDay
     * @return by tim
     */
    public static String getNowForFileName(int afterDay) {
        GregorianCalendar calendar = new GregorianCalendar();
//    Date date = calendar.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        Date date = calendar.getTime();

        return df.format(date);
    }

    //==============================================================================
//比较日期，与现在-N天的日期对比  -1 0 1
//==============================================================================
    public int getDateCompare(String limitDate, int afterDay) {
        GregorianCalendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        date = calendar.getTime();//date是新来的天数，跟今天相比的天数

        iDate = limitDate;
        calendar.set(getYear(), getMonth() - 1, getDay());
        Date dateLimit = calendar.getTime();
        return dateLimit.compareTo(date);
    }

    //==============================================================================
//比较日期，与现在的日期对比
//==============================================================================
    public int getDateCompare(String limitDate) {
        iDate = limitDate;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(getYear(), getMonth() - 1, getDay());
        Date date = calendar.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        return date.compareTo(nowDate);
    }

    //==============================================================================
//比较日期，与现在的日期对比  得到天数
//==============================================================================
    public long getLongCompare(String limitDate) {

        iDate = limitDate;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(getYear(), getMonth() - 1, getDay());
        Date date = calendar.getTime();
        long datePP = date.getTime();
        Date nowDate = new Date();
        long dateNow = nowDate.getTime();
        return ((dateNow - datePP) / (24 * 60 * 60 * 1000));

    }

    //==============================================================================
//比较日期，与现在的日期对比  得到信息
//==============================================================================
    public String getStringCompare(String limitDate, int openDay) {
        iDate = limitDate;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(getYear(), getMonth() - 1, getDay());
        Date date = calendar.getTime();
        long datePP = date.getTime();
        Date nowDate = new Date();
        long dateNow = nowDate.getTime();
        long overDay = ((dateNow - datePP) / (24 * 60 * 60 * 1000));
        String info = "";
        return info;

    }

    //==============================================================================
//比较日期，与现在的日期对比  得到信息
//==============================================================================
    public String getPicCompare(String limitDate, int openDay) {

        iDate = limitDate;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(getYear(), getMonth() - 1, getDay());
        Date date = calendar.getTime();
        long datePP = date.getTime();
        Date nowDate = new Date();
        long dateNow = nowDate.getTime();
        long overDay = ((dateNow - datePP) / (24 * 60 * 60 * 1000));
        String info = "";
        if (overDay > 0) {
            info = "plaint1.gif";
        }
        if (overDay == 0) {
            info = "plaint2.gif";
        }
        if (overDay < 0 && overDay >= -openDay) {
            info = "plaint2.gif";
        }
        if (overDay < -openDay) {
            info = "plaint3.gif";
        }
        if (overDay < -150) {
            info = "plaint3.gif";
        }
        return info;

    }

    /**
     * method diffdate 计算两个日期间相隔的日子
     *
     * @param beforDate 格式:2005-06-20
     * @param afterDate 格式:2005-06-21
     * @return
     */
    public static int diffDate(String beforeDate, String afterDate) {
        String[] tt = beforeDate.split("-");
        Date firstDate = new Date(Integer.parseInt(tt[0]), Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));

        tt = afterDate.split("-");
        Date nextDate = new Date(Integer.parseInt(tt[0]), Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
        return (int) (nextDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取当前月份
     *
     * @return 返回字符串 格式：两位数
     */
    public static String getCurrentMonth() {
        String strmonth = null;
        Calendar cld = Calendar.getInstance();
        java.util.Date date = new Date();
        cld.setTime(date);
        int intMon = cld.get(Calendar.MONTH) + 1;
        if (intMon < 10)
            strmonth = "0" + String.valueOf(intMon);
        else
            strmonth = String.valueOf(intMon);
        date = null;
        return strmonth;
    }

    public static String getCurrMonth() {
        Calendar cld = Calendar.getInstance();
        java.util.Date date = new Date();
        cld.setTime(date);
        int intMon = cld.get(Calendar.MONTH) + 1;

        return String.valueOf(intMon);
    }

    /**
     * 获取昨天的日期的字符串
     */
    public static String getYestoday() {
        Calendar cld = Calendar.getInstance();
        java.util.Date date = new Date();
        cld.setTime(date);
        cld.add(Calendar.DATE, -1);
        int intMon = cld.get(Calendar.MONTH) + 1;
        int intDay = cld.get(Calendar.DAY_OF_MONTH);
        String mons = String.valueOf(intMon);
        String days = String.valueOf(intDay);
        if (intMon < 10)
            mons = "0" + String.valueOf(intMon);
        if (intDay < 10)
            days = "0" + String.valueOf(intDay);
        return String.valueOf(cld.get(Calendar.YEAR)) + "-" + mons + "-" + days;
    }

    /**
     * 此函数用来计算员工的工作天数，如在使用期和离职期该月份的工作日
     * 输入（离职日期，-1）可得该月工作天数  时间以2002-12-14为准
     * 输入（入司时间，1）可的该月工作天数
     */
    public static int getWorkDay(String date, int sign) {
        int month = 0;
        int week = 0;
        int workDay = 0;
        Calendar rightNow = Calendar.getInstance();

        DateUtil dateOver = new DateUtil();
        dateOver.setDate(date);

        rightNow.set(Calendar.YEAR, dateOver.getYear());
        rightNow.set(Calendar.MONTH, dateOver.getMonth() - 1);
        rightNow.set(Calendar.DATE, dateOver.getDay());

        month = rightNow.get(Calendar.MONTH);

        while (rightNow.get(Calendar.MONTH) == month) {
            week = rightNow.get(Calendar.DAY_OF_WEEK);
            if (week == 1 || week == 7) {
            } else {
                workDay++;
                System.out.println(rightNow.get(Calendar.DATE));
            }
            rightNow.add(Calendar.DATE, sign);
        }
        return workDay;
    }

    public static void main(String args[]) {
        System.out.println(isSeason("2002-03-02"));
        String cc = "100.123.342";
        System.out.println(cc.indexOf(".", 3));
        StringTokenizer st = new StringTokenizer(cc, ".");
        if (st.countTokens() != 2) {
            String event = st.nextToken();
            System.out.println("" + event);
            String strDate = getDateFromNow(0, "yyyy-MM-dd HH:mm:ss");
            System.out.println("date:" + strDate);
            System.out.println("15:" + strDate.substring(0, 16));
        }
    }
}






