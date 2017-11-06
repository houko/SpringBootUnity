package info.xiaomo.core.untils


import info.xiaomo.core.constant.SymbolConst
import org.apache.commons.lang3.time.DateUtils
import org.apache.commons.lang3.time.FastDateFormat
import org.slf4j.LoggerFactory
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间和日期的工具类
 * @author : xiaomo
 */
object TimeUtil {

    /**
     * 一分钟的毫秒时长
     */
    val ONE_MINUTE_IN_MILLISECONDS = 60L * 1000
    /**
     * 一小时的毫秒时长
     */
    val ONE_HOUR_IN_MILLISECONDS = 60L * ONE_MINUTE_IN_MILLISECONDS
    /**
     * 一天的毫秒时长
     */
    val ONE_DAY_IN_MILLISECONDS = 24L * ONE_HOUR_IN_MILLISECONDS
    /**
     * 一天的秒时长
     */
    val ONE_DAY_IN_SECENDS = 24L * 60 * 60
    /**
     * 2015-02-23 12:12:12格式
     */
    val DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss"
    val DEFAULT_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    val LOGGER = LoggerFactory.getLogger(TimeUtil::class.java)
    /**
     * 返回 日期格式
     *
     * @return string
     */
    val datePattern = "yyyy-MM-dd"
    val DATE_PATTERN_WITH_XIEXIAN = "yyyy/MM/dd"
    val YEAR = "yyyy"
    val MONTH = "MM"
    val DAY = "dd"
    val DATE = MONTH + SymbolConst.HENGXIAN + DAY

    /**
     * 返回时间格式
     *
     * @return string
     */
    val timePattern = datePattern + " HH:mm:ss"
    val DATE_PATTERN = " HH:mm:ss"

    val DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd")
    val DATE_FORMAT_STRING = "yyyyMMddHHmmss"
    val DATE_FORMAT_CN = FastDateFormat.getInstance("yyyy年 MM月 dd日")
    val DATE_FORMAT_RSS = FastDateFormat.getInstance("E, d MMM yyyy HH:mm:ss z", Locale.CHINA)
    var openDay = 5

    /**
     * 获取当前日期的时间戳
     */
    val nowTimeStamp: Long
        get() {
            val returnTimeStamp: Long
            var aDate: Date? = convertStringToDate("yyyy-MM-dd HH:mm:ss", fullNowDateTime)
            returnTimeStamp = if (aDate == null) {
                0
            } else {
                aDate.time
            }
            return returnTimeStamp
        }

    /**
     * 获取当前的完整的日期和时间
     */
    val fullNowDateTime: String
        get() {
            var strReturn: String
            val now = Date()
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                strReturn = sdf.format(now)
            } catch (e: Exception) {
                strReturn = ""
            }

            return strReturn
        }

    /**
     * 获取当前月份
     *
     * @return 返回字符串 格式：两位数
     */
    val currentMonth: String
        get() {
            val strMonth: String
            val cld = Calendar.getInstance()
            val date = Date()
            cld.time = date
            val intMon = cld.get(Calendar.MONTH) + 1
            val ten = 10
            if (intMon < ten) {
                strMonth = "0" + intMon.toString()
            } else {
                strMonth = intMon.toString()
            }
            return strMonth
        }

    /**
     * 获取当前月份
     *
     * @return 返回字符串 格式：不带0
     */
    val currMonth: String
        get() {
            val cld = Calendar.getInstance()
            val date = Date()
            cld.time = date
            val intMon = cld.get(Calendar.MONTH) + 1
            return intMon.toString()
        }

    /**
     * 获取昨天的日期的字符串
     */
    val yesterday: String
        get() {
            val cld = Calendar.getInstance()
            val date = Date()
            cld.time = date
            cld.add(Calendar.DATE, -1)
            val intMon = cld.get(Calendar.MONTH) + 1
            val intDay = cld.get(Calendar.DAY_OF_MONTH)
            var mons = intMon.toString()
            var days = intDay.toString()
            val ten = 10
            if (intMon < ten) {
                mons = "0" + intMon.toString()
            }
            if (intDay < ten) {
                days = "0" + intDay.toString()
            }
            return cld.get(Calendar.YEAR).toString() + "-" + mons + "-" + days
        }


    /**
     * 返回当前时间（单位/秒）
     */
    val nowOfSeconds: Int
        get() = (System.currentTimeMillis() / 1000).toInt()

    /**
     * 返回当前时间（单位/毫秒）
     *
     * @return long 毫秒
     */
    val nowOfMills: Long
        get() = System.currentTimeMillis()


    /**
     * 描述：返回今天的日期（几号）
     *
     * @return int 几号
     */
    val todayDate: Int
        get() {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            return calendar.get(Calendar.DAY_OF_MONTH)
        }

    /**
     * 描述：返回星期几
     *
     * @return int 星期｛DAY of week｝
     */
    val todayOfWeek: Int
        get() {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            return calendar.get(Calendar.DAY_OF_WEEK) - 1
        }

    /**
     * 描述：返回 今天是这个月的第几周
     *
     * @return int 第几周
     */
    val weekOfMouth: Int
        get() {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)
        }

    val formatDateToday: String
        get() {
            val format = SimpleDateFormat("yyyyMMdd")
            return format.format(Date())
        }

    val formatDate: String
        get() {
            val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            return format.format(Date())
        }

    val year: Int
        get() = Calendar.getInstance().get(Calendar.YEAR)

    /**
     * 获取格式化后的时间或日期
     *
     * @param date date
     * @return string
     */
    fun date2Str(date: Date?): String {
        val df: SimpleDateFormat
        var returnValue = ""
        if (date != null) {
            df = SimpleDateFormat(datePattern)
            returnValue = df.format(date)
        }
        return returnValue
    }

    /**
     * 获取格式化后的时间或日期
     *
     * @param pattern pattern
     * @param aDate   aDate
     */
    fun date2Str(pattern: String, aDate: Date?): String {
        val df: SimpleDateFormat
        var returnValue = ""
        if (aDate != null) {
            df = SimpleDateFormat(pattern)
            returnValue = df.format(aDate)
        }
        return returnValue
    }

    /**
     * 将字符串转成时间
     *
     * @param datePattern 格式
     * @param strDate     字符串的时间
     */
    fun convertStringToDate(datePattern: String, strDate: String): Date? {
        var dp = datePattern
        var dataStr = strDate
        val df: SimpleDateFormat
        val date: Date
        //传入的时间是以 / 分割
        val length = 2
        if (dataStr.split(SymbolConst.HENGXIAN.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size < length) {
            dataStr = dataStr.replace(SymbolConst.ZHENGXIEXIAN, SymbolConst.HENGXIAN)
        }
        if (dataStr.split(SymbolConst.SPACE.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size > 1) {
            dp = timePattern
        }
        df = SimpleDateFormat(dp)
        try {
            date = df.parse(dataStr)
        } catch (pe: ParseException) {
            return null
        }

        return date
    }

    /**
     * 字符串转时间
     *
     * @param strDate strDate
     */
    fun convertStringToDate(strDate: String): Date? {
        var str = strDate
        val aDate: Date?
        //传入的时间是以 / 分割
        val length = 2
        if (str.split(SymbolConst.HENGXIAN.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size < length) {
            str = str.replace(SymbolConst.ZHENGXIEXIAN, SymbolConst.HENGXIAN)
        }
        aDate = convertStringToDate(datePattern, str)
        return aDate
    }

    /**
     * 获取当前时间或时间
     *
     * @param theTime theTime
     */
    fun getTimeOrTimeNow(theTime: Date): String {
        return getDateTime(timePattern, theTime)
    }

    /**
     * 获取当前时间或时间
     *
     * @param theTime theTime
     */
    fun getTimeOrTimeNow(pattern: String, theTime: Date): String {
        return getDateTime(pattern, theTime)
    }

    /**
     * 日期格式转换成时间戳
     *
     * @param pattern pattern
     * @param strDate strDate
     */
    fun getTimeStamp(pattern: String, strDate: String): Long {
        val returnTimeStamp: Long
        val aDate: Date?
        aDate = convertStringToDate(pattern, strDate)
        if (aDate == null) {
            returnTimeStamp = 0
        } else {
            returnTimeStamp = aDate.time
        }
        return returnTimeStamp
    }

    /**
     * 得到格式化后的系统当前日期
     *
     * @param pattern 格式模式字符串
     * @return 格式化后的系统当前时间，如果有异常产生，返回空串""
     */
    fun getNowDateTimeWithPattern(pattern: String): String {
        var strReturn: String
        val now = Date()
        try {
            val sdf = SimpleDateFormat(pattern)
            strReturn = sdf.format(now)
        } catch (e: Exception) {
            strReturn = ""
        }

        return strReturn
    }

    /**
     * 将字符串数组使用指定的分隔符合并成一个字符串。
     *
     * @param array 字符串数组
     * @param split 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
     * @return 合并后的字符串
     * @since 0.4
     */
    fun combineStringArray(array: Array<String>, split: String?): String {
        var strArr = split
        val length = array.size - 1
        if (strArr == null) {
            strArr = ""
        }
        val result = StringBuilder(length * 8)
        for (i in 0 until length) {
            result.append(array[i])
            result.append(strArr)
        }
        result.append(array[length])
        return result.toString()
    }

    /**
     * 没搞清楚要传什么参数进去
     *
     * @param strWeek strWeek
     */
    fun getWeekNum(strWeek: String): Int {
        var returnValue: Int = when (strWeek) {
            "Mon" -> 1
            "Tue" -> 2
            "Wed" -> 3
            "Thu" -> 4
            "Fri" -> 5
            "Sat" -> 6
            "Sun" -> 0
            else -> 0
        }
        return returnValue
    }

    /**
     * 获取日期字符串中的中文时间表示字符串
     *
     * @param strDate strDate
     */
    fun getSabreTime(strDate: String): String {
        var strReturn = ""
        try {

            val d = TimeUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", StringUtil.replace(
                    strDate, "T", " "))
            strReturn = TimeUtil.date2Str("hh:mm aaa", d)

        } catch (e: Exception) {
            return strReturn
        }

        return strReturn
    }

    /**
     * 获取日期字符串中的中文日期表示字符串
     *
     * @param strDate strDate
     */
    fun getSabreDate(strDate: String): String {
        var strReturn = ""
        try {
            val p: String
            val length = 10
            if (strDate.length > length) {
                p = "yyyy-MM-dd HH:mm:ss"
            } else {
                p = "yyyy-MM-dd"
            }
            val d = TimeUtil.convertStringToDate(p, StringUtil.replace(strDate, "T", " "))
            strReturn = TimeUtil.date2Str("EEE d-MMM", d)

        } catch (e: Exception) {
            return strReturn
        }

        return strReturn
    }

    /**
     * 获取日期字符串的中文日期时间表示
     *
     * @param strDate strDate
     */
    fun getSabreDateTime(strDate: String): String {
        var strReturn = ""
        try {
            val p: String
            val length = 10
            if (strDate.length > length) {
                p = "yyyy-MM-dd HH:mm:ss"
            } else {
                p = "yyyy-MM-dd"
            }
            val d = TimeUtil.convertStringToDate(p, StringUtil.replace(strDate, "T", " "))
            strReturn = TimeUtil.date2Str("EEE d-MMM hh:mm aaa", d)

        } catch (e: Exception) {
            return strReturn
        }

        return strReturn
    }

    /**
     * 获取指定的日期
     *
     * @param timeType 时间类型，譬如：Calendar.DAY_OF_YEAR
     * @param timenum  时间数字，譬如：-1 昨天，0 今天，1 明天
     * @return 日期
     */
    fun getDateFromNow(timeType: Int, timenum: Int): Date {
        val cld = Calendar.getInstance()
        cld.set(timeType, cld.get(timeType) + timenum)
        return cld.time
    }

    /**
     * 获取日期
     *
     * @param timeType 时间类型，譬如：Calendar.DAY_OF_YEAR
     * @param timeNum  时间数字，譬如：-1 昨天，0 今天，1 明天
     * @param pattern  时间格式，譬如："yyyy-MM-dd HH:mm:ss"
     * @return 字符串
     */
    fun getDateFromNow(timeType: Int, timeNum: Int, pattern: String?): String {
        var pt = pattern

        if (pt == null || "" == pt) {
            pt = "yyyy-MM-dd HH:mm:ss"
        }
        val cld = Calendar.getInstance()
        val date: Date
        val df = SimpleDateFormat(pt)
        cld.set(timeType, cld.get(timeType) + timeNum)
        date = cld.time
        return df.format(date)
    }

    /**
     * 获取当前日期的字符串
     *
     * @param pattern 时间格式，譬如："yyyy-MM-dd HH:mm:ss"
     * @return 字符串
     */
    fun getDateNow(pattern: String?): String {
        var pt = pattern
        if (pt == null || "" == pt) {
            pt = "yyyy-MM-dd HH:mm:ss"
        }
        val cld = Calendar.getInstance()
        val df = SimpleDateFormat(pt)
        return df.format(cld.time)
    }

    /**
     * 格式化成yyyy-MM-dd
     *
     * @return String    返回类型
     */
    fun format(tamp: Timestamp): String {
        return DATE_FORMAT.format(tamp)
    }

    /**
     * 格式化成中文日期
     *
     * @return String    返回类型
     */
    fun formatCn(tamp: Timestamp): String {
        return DATE_FORMAT_CN.format(tamp)
    }

    /**
     * 格式化成RSS需要格式
     *
     * @return String    返回类型
     */
    fun formartRss(tamp: Timestamp): String {
        return DATE_FORMAT_RSS.format(tamp)
    }

    /**
     * hour小时之前
     */
    fun hourBefor(hour: Int): Date {
        return DateUtils.addHours(Date(), -hour)
    }

    fun subDate(date: String): String {
        return date.substring(0, 10)
    }

    /**
     * 计算是否是季度末
     *
     * @param date date
     */
    fun isSeason(date: String): Boolean {
        val getMonth = Integer.parseInt(date.substring(5, 7))
        var sign = false
        val monthThree = 3
        if (getMonth == monthThree) {
            sign = true
        }
        val monthSix = 6
        if (getMonth == monthSix) {
            sign = true
        }
        val monthNine = 9
        if (getMonth == monthNine) {
            sign = true
        }
        val maxMonth = 12
        if (getMonth == maxMonth) {
            sign = true
        }
        return sign
    }

    /**
     * 计算从现在开始几天后的时间
     *
     * @param afterDay afterDay
     */
    fun getDateFromNow(afterDay: Int): String {
        val calendar = GregorianCalendar()
        val date: Date

        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay)
        date = calendar.time

        return df.format(date)
    }

    /**
     * 带格式
     *
     * @param afterDay afterDay
     * @param pattern  pattern
     */
    fun getDateFromNow(afterDay: Int, pattern: String): String {
        val calendar = Calendar.getInstance()
        val date: Date
        val df = SimpleDateFormat(pattern)
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay)
        date = calendar.time
        return df.format(date)
    }

    /**
     * 得到当前时间，用于文件名，没有特殊字符，使用yyyyMMddHHmmss格式
     *
     * @param afterDay afterDay
     */
    fun getNowForFileName(afterDay: Int): String {
        val calendar = GregorianCalendar()
        val df = SimpleDateFormat("yyyyMMddHHmmss")

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay)
        val date = calendar.time

        return df.format(date)
    }

    /**
     * 获取时间
     *
     * @param pattern pattern
     * @param aDate   aDate
     */
    private fun getDateTime(pattern: String, aDate: Date?): String {
        val df: SimpleDateFormat
        var returnValue = ""
        if (aDate == null) {
            print("aDate is null!")
        } else {
            df = SimpleDateFormat(pattern)
            returnValue = df.format(aDate)
        }

        return returnValue
    }

    /**
     * 判断两个时间是否是同一天
     *
     * @param sourceTime sourceTime
     * @param targetTime targetTime
     * @return
     */
    fun isSameDay(sourceTime: Long, targetTime: Long): Boolean {
        return getLogicIntervalDays(sourceTime, targetTime) == 0
    }

    /**
     * 判断指定的时间是否是今天
     *
     * @param time time
     */
    fun isToday(time: Long): Boolean {
        return isSameDay(System.currentTimeMillis(), time)
    }

    /**
     * 获取两个时间的逻辑间隔天数,以源时间为基准,目标时间小于源时间则返回大于或等于天数，反之返回小于等于天数
     *
     *
     * 举例：sourceTime=今天凌晨0点0分1秒,targetTime=昨天晚上11点59分59秒,则返回1
     *
     * @param sourceTime sourceTime
     * @param targetTime targetTime
     * @return
     */
    fun getLogicIntervalDays(sourceTime: Long, targetTime: Long): Int {
        val source0ClockTime = getZeroClockTime(sourceTime)
        val target0ClockTime = getZeroClockTime(targetTime)

        return getRealIntervalDays(source0ClockTime, target0ClockTime)
    }

    /**
     * 获取两个时间的实际间隔天数
     *
     * @param sourceTime sourceTime
     * @param targetTime targetTime
     */
    fun getRealIntervalDays(sourceTime: Long, targetTime: Long): Int {
        return getIntervalTime(sourceTime, targetTime, ONE_DAY_IN_MILLISECONDS).toInt()
    }

    /**
     * 根据指定的时间单位获取相差的单位时间，如时间单位为一天的毫秒数则该函数跟{@link#getRealIntervalDays} 则是相同的效果
     *
     * @param sourceTime sourceTime
     * @param targetTime targetTime
     * @param timeUnit   时间单位(毫秒)
     * @return
     */
    fun getIntervalTime(sourceTime: Long, targetTime: Long, timeUnit: Long): Long {
        return (sourceTime - targetTime) / timeUnit
    }

    /**
     * 获取在指定时间戳和指定小时，分钟，秒，毫秒数的时间
     *
     * @param time        时间戳
     * @param hour        小时(24小时制)
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     */
    fun getTimeInMillis(time: Long, hour: Int, minute: Int, second: Int, milliSecond: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        calendar.set(Calendar.MILLISECOND, milliSecond)
        return calendar.timeInMillis
    }

    /**
     * 获取指定日期的时间戳
     *
     * @param year        year
     * @param month       从1开始
     * @param day         day
     * @param hour        月
     * @param minute      分
     * @param second      秒
     * @param milliSecond 毫秒
     */
    fun getTimeInMillis(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int, milliSecond: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        calendar.set(Calendar.MILLISECOND, milliSecond)
        return calendar.timeInMillis
    }

    /**
     * 获取今日指定的时间
     *
     * @param hour        小时(24小时制)
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     */
    fun getTodayTime(hour: Int, minute: Int, second: Int, milliSecond: Int): Long {
        return getTimeInMillis(System.currentTimeMillis(), hour, minute, second, milliSecond)
    }

    /**
     * 获取指定时间的零点时间
     */
    fun getZeroClockTime(time: Long): Long {
        return getTimeInMillis(time, 0, 0, 0, 0)
    }


    /**
     * 返回指定时间和格式的时间字符串
     */
    fun getTimeString(time: Long, format: String): String {
        val dateFormat = SimpleDateFormat(format)
        return dateFormat.format(Date(time))
    }

    /**
     * 从字符串中获取时间
     */
    fun getTimeFromString(timeStr: String, format: String): Long {
        val dateFormat = SimpleDateFormat(format)
        try {
            return dateFormat.parse(timeStr).time
        } catch (e: ParseException) {
            LOGGER.error("", e)
        }

        return java.lang.Long.MIN_VALUE
    }

    /**
     * 获取格式化的剩余时间
     *
     *
     * 例如:1天20小时5分0秒,20小时0分0秒,1秒
     */
    fun getLeftTimeString(leftTime: Long): String {
        var time = leftTime
        val sb = StringBuilder()
        // 剩余秒数
        val leftSecond = (time / 1000).toInt()
        // 秒数
        val second = leftSecond % 60
        if (second > 0) {
            sb.insert(0, second.toString() + "秒")
        }
        // 剩余分钟数
        val leftMinute = leftSecond / 60
        // 分钟数
        var minute = leftMinute % 60
        if (minute > 0) {
            sb.insert(0, minute.toString() + "分")
        }
        // 剩余小时
        val leftHour = leftMinute / 60
        var hour = leftHour % 24
        if (hour > 0) {
            sb.insert(0, hour.toString() + "小时")
        }
        // 剩余天数
        val leftDay = leftHour / 24
        if (leftDay > 0) {
            sb.insert(0, leftDay.toString() + "天")
        }
        // 获取剩余天数
        val day = (time / ONE_DAY_IN_MILLISECONDS).toInt()
        // 1天及以上的显示剩余天
        if (day > 0) {
            sb.append(day).append("天")
            time -= day * ONE_DAY_IN_MILLISECONDS
        }
        hour = (time / ONE_HOUR_IN_MILLISECONDS).toInt()
        // 1小时及以上或者前面显示了天数则后面需要小时
        if (hour > 0 || sb.length > 0) {
            sb.append(hour).append("小时")
            time -= hour * ONE_HOUR_IN_MILLISECONDS
        }
        minute = (time / ONE_MINUTE_IN_MILLISECONDS).toInt()
        if (minute > 0 || sb.length > 0) {
            sb.append(minute).append("分")
            time -= minute * ONE_MINUTE_IN_MILLISECONDS
        }
        sb.append(time / 1000).append("秒")
        return sb.toString()
    }

    /**
     * 得到几天前的时间
     */
    fun getDateBefore(d: Date, day: Int): Date {
        val now = Calendar.getInstance()
        now.time = d
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day)
        return now.time
    }

    /**
     * 得到几天后的时间
     */
    fun getDateAfter(d: Date, day: Int): Date {
        val now = Calendar.getInstance()
        now.time = d
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day)
        return now.time
    }

    fun getFormatDate(date: Long?, pattern: String): String {
        val format = SimpleDateFormat(pattern)
        return format.format(date)
    }

}






