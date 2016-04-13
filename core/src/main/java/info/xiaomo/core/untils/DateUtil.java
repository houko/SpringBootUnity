package info.xiaomo.core.untils;

/**
 * <p>Title: 时间和日期的工具类</p>
 */

import info.xiaomo.core.constant.Symbol;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 一分钟的毫秒时长
     */
    public static final long ONE_MINUTE_IN_MILLISECONDS = 60L * 1000;
    /**
     * 一小时的毫秒时长
     */
    public static final long ONE_HOUR_IN_MILLISECONDS = 60L * ONE_MINUTE_IN_MILLISECONDS;
    /**
     * 一天的毫秒时长
     */
    public static final long ONE_DAY_IN_MILLISECONDS = 24L * ONE_HOUR_IN_MILLISECONDS;
    /**
     * 一天的秒时长
     */
    public static final long ONE_DAY_IN_SECENDS = 24L * 60 * 60;
    /**
     * 2015-02-23 12:12:12格式
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    private static final String datePatternWithHengXian = "yyyy-MM-dd";
    private static final String datePatternWithXieXian = "yyyy/MM/dd";
    private static final String year = "yyyy";
    private static final String month = "MM";
    private static final String day = "dd";
    private static final String date = month + Symbol.HENGXIAN + day;

    private static final String timePattern = datePatternWithHengXian + " HH:mm:ss";

    public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final String DATE_FORMAT_STRING = "yyyyMMddHHmmss";
    public static final FastDateFormat DATE_FORMAT_CN = FastDateFormat.getInstance("yyyy年 MM月 dd日");
    public static final FastDateFormat DATE_FORMAT_RSS = FastDateFormat.getInstance("E, d MMM yyyy HH:mm:ss z", Locale.CHINA);

    /**
     * 返回 日期格式
     *
     * @return string
     */
    public static String getDatePattern() {
        return datePatternWithHengXian;
    }

    /**
     * 返回时间格式
     *
     * @return string
     */
    public static String getTimePattern() {
        return timePattern;
    }


    /**
     * 获取格式化后的时间或日期
     *
     * @param aDate
     * @return string
     */
    public static String date2Str(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat(datePatternWithHengXian);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 获取格式化后的时间或日期
     *
     * @param pattern
     * @param aDate
     * @return
     */
    public static String date2Str(String pattern, Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat(pattern);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 将字符串转成时间
     *
     * @param datePattern 格式
     * @param strDate     字符串的时间
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String datePattern, String strDate) {
        SimpleDateFormat df;
        Date date;
        if (strDate.split(Symbol.HENGXIAN).length < 2) {//传入的时间是以 / 分割
            strDate = strDate.replace(Symbol.ZHENGXIEXIAN, Symbol.HENGXIAN);
        }
        if (strDate.split(Symbol.SPACE).length > 1) {
            datePattern = timePattern;
        }
        df = new SimpleDateFormat(datePattern);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            return null;
        }
        return (date);
    }

    /**
     * 字符串转时间
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) {
        Date aDate;
        if (strDate.split(Symbol.HENGXIAN).length < 2) {//传入的时间是以 / 分割
            strDate = strDate.replace(Symbol.ZHENGXIEXIAN, Symbol.HENGXIAN);
        }
        aDate = convertStringToDate(datePatternWithHengXian, strDate);
        return aDate;
    }


    /**
     * 获取当前时间或时间
     *
     * @param theTime
     * @return
     */
    public static String getTimeOrTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * 获取当前时间或时间
     *
     * @param theTime
     * @return
     */
    public static String getTimeOrTimeNow(String pattern, Date theTime) {
        return getDateTime(pattern, theTime);
    }

    /**
     * 日期格式转换成时间戳
     *
     * @param pattern
     * @param strDate
     * @return
     */
    public static long getTimeStamp(String pattern, String strDate) {
        long returnTimeStamp;
        Date aDate;
        aDate = convertStringToDate(pattern, strDate);
        if (aDate == null) {
            returnTimeStamp = 0;
        } else {
            returnTimeStamp = aDate.getTime();
        }
        return returnTimeStamp;
    }

    /**
     * 获取当前日期的时间戳
     *
     * @return
     */
    public static long getNowTimeStamp() {
        long returnTimeStamp;
        Date aDate = null;
        aDate = convertStringToDate("yyyy-MM-dd HH:mm:ss", getFullNowDateTime());
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
    public static String getNowDateTimeWithPattern(String pattern) {
        String strReturn;
        Date now = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            strReturn = sdf.format(now);
        } catch (Exception e) {
            strReturn = "";
        }
        return strReturn;
    }

    /**
     * 获取当前的完整的日期和时间
     *
     * @return
     */
    public static String getFullNowDateTime() {
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
     * 将字符串数组使用指定的分隔符合并成一个字符串。
     *
     * @param array 字符串数组
     * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
     * @return 合并后的字符串
     * @since 0.4
     */
    public static String combineStringArray(String[] array, String split) {
        int length = array.length - 1;
        if (split == null) {
            split = "";
        }
        StringBuilder result = new StringBuilder(length * 8);
        for (int i = 0; i < length; i++) {
            result.append(array[i]);
            result.append(split);
        }
        result.append(array[length]);
        return result.toString();
    }

    /**
     * 没搞清楚要传什么参数进去
     *
     * @param strWeek
     * @return
     */
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

            Date d = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", StringUtil.replace(
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
            Date d = DateUtil.convertStringToDate(p, StringUtil.replace(strDate, "T", " "));
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
            Date d = DateUtil.convertStringToDate(p, StringUtil.replace(strDate, "T", " "));
            strReturn = DateUtil.date2Str("EEE d-MMM hh:mm aaa", d);

        } catch (Exception e) {
            return strReturn;
        }
        return strReturn;
    }

    /**
     * 获取指定的日期
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
     * @param timeType 时间类型，譬如：Calendar.DAY_OF_YEAR
     * @param timenum  时间数字，譬如：-1 昨天，0 今天，1 明天
     * @param pattern  时间格式，譬如："yyyy-MM-dd HH:mm:ss"
     * @return 字符串
     */
    public static String getDateFromNow(int timeType, int timenum, String pattern) {
        if ((pattern == null) || (pattern.equals("")))
            pattern = "yyyy-MM-dd HH:mm:ss";
        Calendar cld = Calendar.getInstance();
        Date date = null;
        DateFormat df = new SimpleDateFormat(pattern);
        cld.set(timeType, cld.get(timeType) + timenum);
        date = cld.getTime();
        return df.format(date);
    }

    /**
     * 获取当前日期的字符串
     *
     * @param pattern 时间格式，譬如："yyyy-MM-dd HH:mm:ss"
     * @return 字符串
     */
    public static String getDateNow(String pattern) {
        if ((pattern == null) || (pattern.equals("")))
            pattern = "yyyy-MM-dd HH:mm:ss";
        Calendar cld = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(pattern);
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
     * @param pattern
     * @return
     */
    public static String getDateFromNow(int afterDay, String pattern) {
        Calendar calendar = Calendar.getInstance();
        Date date;
        DateFormat df = new SimpleDateFormat(pattern);
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

    /**
     * 计算两个日期间相隔的日子
     *
     * @param beforeDate 格式:2005-06-20
     * @param afterDate  格式:2005-06-21
     * @return
     */
    public static int diffDate(String beforeDate, String afterDate) {
        String[] tt = beforeDate.split(Symbol.HENGXIAN);
        Date firstDate = new Date(Integer.parseInt(tt[0]), Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));

        tt = afterDate.split(Symbol.HENGXIAN);
        Date nextDate = new Date(Integer.parseInt(tt[0]), Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
        return (int) (nextDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取当前月份
     *
     * @return 返回字符串 格式：两位数
     */
    public static String getCurrentMonth() {
        String strmonth;
        Calendar cld = Calendar.getInstance();
        java.util.Date date = new Date();
        cld.setTime(date);
        int intMon = cld.get(Calendar.MONTH) + 1;
        if (intMon < 10)
            strmonth = "0" + String.valueOf(intMon);
        else
            strmonth = String.valueOf(intMon);
        return strmonth;
    }

    /**
     * 获取当前月份
     *
     * @return 返回字符串 格式：不带0
     */
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
    public static String getYesterday() {
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
     * 获取时间
     *
     * @param pattern
     * @param aDate
     * @return
     */
    private static String getDateTime(String pattern, Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";
        if (aDate == null) {
            System.out.print("aDate is null!");
        } else {
            df = new SimpleDateFormat(pattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 判断两个时间是否是同一天
     *
     * @param sourceTime
     * @param targetTime
     * @return
     */
    public static boolean isSameDay(long sourceTime, long targetTime) {
        return getLogicIntervalDays(sourceTime, targetTime) == 0;
    }

    /**
     * 判断指定的时间是否是今天
     *
     * @param time
     * @return
     */
    public static boolean isToday(long time) {
        return isSameDay(System.currentTimeMillis(), time);
    }

    /**
     * 获取两个时间的逻辑间隔天数,以源时间为基准,目标时间小于源时间则返回大于或等于天数，反之返回小于等于天数
     * <p/>
     * 举例：sourceTime=今天凌晨0点0分1秒,targetTime=昨天晚上11点59分59秒,则返回1
     *
     * @param sourceTime
     * @param targetTime
     * @return
     */
    public static int getLogicIntervalDays(long sourceTime, long targetTime) {
        long source0ClockTime = getZeroClockTime(sourceTime);
        long target0ClockTime = getZeroClockTime(targetTime);

        return getRealIntervalDays(source0ClockTime, target0ClockTime);
    }

    /**
     * 获取两个时间的实际间隔天数
     *
     * @param sourceTime
     * @param targetTime
     * @return
     */
    public static int getRealIntervalDays(long sourceTime, long targetTime) {
        return (int) getIntervalTime(sourceTime, targetTime, ONE_DAY_IN_MILLISECONDS);
    }

    /**
     * 根据指定的时间单位获取相差的单位时间，如时间单位为一天的毫秒数则该函数跟{@link#getRealIntervalDays} 则是相同的效果
     *
     * @param sourceTime
     * @param targetTime
     * @param timeUnit   时间单位(毫秒)
     * @return
     */
    public static long getIntervalTime(long sourceTime, long targetTime, long timeUnit) {
        return (sourceTime - targetTime) / timeUnit;
    }

    /**
     * 获取在指定时间戳和指定小时，分钟，秒，毫秒数的时间
     *
     * @param time        时间戳
     * @param hour        小时(24小时制)
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     * @return
     */
    public static long getTimeInMillis(long time, int hour, int minute, int second, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取指定日期的时间戳
     *
     * @param year
     * @param month       从1开始
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param milliSecond
     * @return
     */
    public static long getTimeInMillis(int year, int month, int day, int hour, int minute, int second, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取今日指定的时间
     *
     * @param hour        小时(24小时制)
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     * @return
     */
    public static long getTodayTime(int hour, int minute, int second, int milliSecond) {
        return getTimeInMillis(System.currentTimeMillis(), hour, minute, second, milliSecond);
    }

    /**
     * 获取指定时间的零点时间
     *
     * @param time
     * @return
     */
    public static long getZeroClockTime(long time) {
        return getTimeInMillis(time, 0, 0, 0, 0);
    }


    /**
     * 返回指定时间和格式的时间字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String getTimeString(long time, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(time));
    }

    /**
     * 从字符串中获取时间
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static long getTimeFromString(String timeStr, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(timeStr).getTime();
        } catch (ParseException e) {
            LOGGER.error("", e);
        }
        return Long.MIN_VALUE;
    }

    /**
     * 获取格式化的剩余时间
     * <p/>
     * 例如:1天20小时5分0秒,20小时0分0秒,1秒
     *
     * @param leftTime
     * @return
     */
    public static String getLeftTimeString(long leftTime) {
        StringBuilder sb = new StringBuilder();
         int leftSecond = (int) (leftTime / 1000);// 剩余秒数
         int second = leftSecond % 60;// 秒数
         if (second > 0) {
         sb.insert(0, second + "秒");
         }
         int leftMinute = leftSecond / 60;// 剩余分钟数
         int minute = leftMinute % 60;// 分钟数
         if (minute > 0) {
         sb.insert(0, minute + "分");
         }
         int leftHour = leftMinute / 60;// 剩余小时
         int hour = leftHour % 24;
         if (hour > 0) {
         sb.insert(0, hour + "小时");
         }
         int leftDay = leftHour / 24;// 剩余天数
         if (leftDay > 0) {
         sb.insert(0, leftDay + "天");
         }
        int day = (int) (leftTime / ONE_DAY_IN_MILLISECONDS);// 获取剩余天数
        if (day > 0) {// 1天及以上的显示剩余天
            sb.append(day).append("天");
            leftTime -= (day * ONE_DAY_IN_MILLISECONDS);
        }
        hour = (int) (leftTime / ONE_HOUR_IN_MILLISECONDS);
        if (hour > 0 || sb.length() > 0) {// 1小时及以上或者前面显示了天数则后面需要小时
            sb.append(hour).append("小时");
            leftTime -= (hour * ONE_HOUR_IN_MILLISECONDS);
        }
        minute = (int) (leftTime / ONE_MINUTE_IN_MILLISECONDS);
        if (minute > 0 || sb.length() > 0) {
            sb.append(minute).append("分");
            leftTime -= (minute * ONE_MINUTE_IN_MILLISECONDS);
        }
        sb.append(leftTime / 1000).append("秒");
        return sb.toString();
    }


    /**
     * 返回当前时间（单位/秒）
     *
     * @return
     */
    public static int getNowOfSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 返回当前时间（单位/毫秒）
     *
     * @return long 毫秒
     */
    public static long getNowOfMills() {
        return System.currentTimeMillis();
    }

    /**
     * 描述：返回今天的日期（几号）
     *
     * @return int 几号
     */
    public static int getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 描述：返回星期几
     *
     * @return int 星期｛day of week｝
     */
    public static int getTodayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 描述：返回 今天是这个月的第几周
     *
     * @return int 第几周
     */
    public static int getWeekOfMouth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    public static String getFormatDateToday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    public static String getFormatDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }
    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static void main(String args[]) {
        System.out.println(getYesterday());
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






