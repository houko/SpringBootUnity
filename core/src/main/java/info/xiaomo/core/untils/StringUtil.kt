package info.xiaomo.core.untils

import org.apache.commons.lang3.StringUtils
import java.io.IOException
import java.io.StringReader
import java.io.UnsupportedEncodingException
import java.text.NumberFormat
import java.util.*
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest


/**
 * 对字符串的简单处理
 *
 *
 *
 * @author : xiaomo
 * @Date 2013-6-6 下午5:08:06
 */
class StringUtil : StringUtils() {
    companion object {

        /**
         * ip正则表达式
         */
        val IP_REGEX = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\." + "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}"
        private val HEX = arrayOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF")
        private val VAL = byteArrayOf(0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F)
        private val QUOTE_ENCODE = "&quot;".toCharArray()
        private val AMP_ENCODE = "&amp;".toCharArray()
        private val LT_ENCODE = "&lt;".toCharArray()
        private val GT_ENCODE = "&gt;".toCharArray()

        /**
         * 是否是空字符串
         *
         * @param str 字符串
         * @return 是否为空
         */
        fun isBlank(str: String?): Boolean {
            return str == null || str.trim { it <= ' ' }.isEmpty()
        }

        /**
         * String数组转成int数组
         *
         * @param numbers String[]
         * @return List<Integer>
        </Integer> */
        fun strArrToIntList(numbers: Array<String>): List<Int> {
            val intArr = ArrayList<Int>()
            for (number in numbers) {
                intArr.add(Integer.parseInt(number))
            }
            return intArr
        }

        /**
         * String数组转成int数组
         *
         * @param numbers String[]
         * @return int[]
         */
        fun strArrToIntArr(numbers: Array<String>): IntArray {
            val intArr = IntArray(numbers.size)
            for (i in numbers.indices) {
                intArr[i] = Integer.parseInt(numbers[i])
            }
            return intArr
        }

        /**
         * 根据指定的分隔符将字符串转为int数组
         *
         * @param source 字符串
         * @param split  分割符
         * @return int[]
         */
        fun strToIntArr(source: String, split: String): IntArray {
            if (isBlank(source)) {
                return IntArray(0)
            }
            val numbers = source.split(split.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return strArrToIntArr(numbers)
        }

        /**
         * 截取文字safe 中文
         *
         * @return String    返回类型
         */
        fun subCn(string: String, length: Int, more: String): String? {
            if (StringUtils.isNotEmpty(string)) {
                val chars = string.toCharArray()
                if (chars.size > length) {
                    val sb = StringBuilder()
                    for (i in 0 until length) {
                        sb.append(chars[i])
                    }
                    sb.append(more)
                    return sb.toString()
                }
            }
            return string
        }


        /**
         * 字符串全角转半角
         *
         * @return String    返回类型
         */
        fun togglecase(string: String): String {
            val sb = StringBuilder()
            for (i in 0 until string.length) {
                sb.append(CharUtil.regularize(string[i]))
            }
            return sb.toString()
        }


        /**
         * 计算文字长度-.-无中文问题
         *
         * @return int    返回类型
         */
        fun getLength(string: String): Int {
            if (StringUtils.isBlank(string)) {
                return 0
            } else {
                val strChars = string.toCharArray()
                return strChars.size
            }
        }


        /**
         * 获取ip
         *
         * @return ip 如果返回null,说明是一个不合法的ip地址格式
         */
        fun getIP(request: HttpServletRequest): String? {
            var ip = request.getHeader("X-Requested-For")
            val unknown = "unknown"
            if (StringUtils.isBlank(ip) || unknown.equals(ip, ignoreCase = true)) {
                ip = request.getHeader("X-Forwarded-For")
            }
            if (StringUtils.isBlank(ip) || unknown.equals(ip, ignoreCase = true)) {
                ip = request.getHeader("Proxy-Client-IP")
            }
            if (StringUtils.isBlank(ip) || unknown.equals(ip, ignoreCase = true)) {
                ip = request.getHeader("WL-Proxy-Client-IP")
            }
            if (StringUtils.isBlank(ip) || unknown.equals(ip, ignoreCase = true)) {
                ip = request.getHeader("HTTP_CLIENT_IP")
            }
            if (StringUtils.isBlank(ip) || unknown.equals(ip, ignoreCase = true)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR")
            }
            if (StringUtils.isBlank(ip) || unknown.equals(ip, ignoreCase = true)) {
                ip = request.remoteAddr
            }
            return if (!ip.matches(IP_REGEX.toRegex())) {
                null
            } else ip
        }

        /**
         * 判断字符串是否为空，并删除首尾空格
         *
         * @param tempSql
         * @return
         */
        fun convertNullCode(tempSql: String?): String {
            var tempSql = tempSql
            if (tempSql == null) {
                tempSql = ""
            }
            return tempSql
        }

        /**
         * 代码转换，GBK转换为ISO-8859-1
         *
         * @param tempSql 要转换的字符串
         */
        fun isocode(tempSql: String): String {

            var returnString = convertNullCode(tempSql)

            try {
                val ascii = returnString.toByteArray(charset("GBK"))
                returnString = String(ascii)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return returnString
        }

        /**
         * 代码转换，ISO-8859-1转换为GBK
         *
         * @param tempSql 要转换的字符串
         * @return
         */
        fun gbkcode(tempSql: String): String {
            var returnString = convertNullCode(tempSql)
            try {
                val ascii = returnString.toByteArray(charset("ISO-8859-1"))
                returnString = String(ascii)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return returnString
        }

        /**
         * 代码转换，GBK转换为big5
         *
         * @param tempSql 要转换的字符串
         * @return
         */
        fun gbk2big5code(tempSql: String): String {
            var returnString = convertNullCode(tempSql)
            try {
                val ascii = returnString.toByteArray(charset("GBK"))
                returnString = String(ascii)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return returnString
        }

        /**
         * 替换非法字符
         * @param input
         * @return
         */
        fun convertHtml(input: String): String {
            var returnString = StringBuffer(input.length)

            var ch = ' '
            for (i in 0 until input.length) {

                ch = input[i]

                if (ch == '<') {
                    returnString = returnString.append("&lt")
                } else if (ch == '>') {
                    returnString = returnString.append("&gt")
                } else if (ch == ' ') {
                    returnString = returnString.append("&nbsp")
                } else if (ch == '\\') {
                    returnString = returnString.append("&acute")
                } else {
                    returnString = returnString.append(ch)
                }
            }
            return returnString.toString()
        }


        /**
         * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
         * &lt;table&gt;, etc) and converts the '&lt'' and '&gt;' characters to
         * their HTML escape sequences.
         *
         * @param in the text to be converted.
         * @return the input string with the characters '&lt;' and '&gt;' replaced
         * with their HTML escape sequences.
         */
        fun escapeHTMLTags(`in`: String?): String? {
            if (`in` == null) {
                return null
            }
            var ch: Char
            var i = 0
            var last = 0
            val input = `in`.toCharArray()
            val len = input.size
            val out = StringBuilder((len * 1.3).toInt())
            while (i < len) {
                ch = input[i]

                if (ch > '>') {
                    i++
                    continue
                } else if (ch == '<') {
                    if (i > last) {
                        out.append(input, last, i - last)
                    }
                    last = i + 1
                    out.append(LT_ENCODE)
                } else if (ch == '>') {
                    if (i > last) {
                        out.append(input, last, i - last)
                    }
                    last = i + 1
                    out.append(GT_ENCODE)
                }
                i++
            }
            if (last == 0) {
                return `in`
            }
            if (i > last) {
                out.append(input, last, i - last)
            }
            return out.toString()
        }

        fun filterString(allstr: String): String {
            val returnString = StringBuilder(allstr.length)
            var ch = ' '
            for (i in 0 until allstr.length) {
                ch = allstr[i]
                val lsTemp = "'"
                val lcTemp = lsTemp[0]
                if (ch == lcTemp) {
                    returnString.append("''")
                } else {
                    returnString.append(ch)
                }
            }
            return returnString.toString()
        }

        /**
         * 数字的金额表达式
         */
        fun convertNumToMoney(num: Int): String {
            val formatc = NumberFormat.getCurrencyInstance(Locale.CHINA)
            return formatc.format(num.toLong())
        }


        /**
         * 数字的金额表达式
         *
         * @param num      金额
         * @param inLocale 币种
         * @return 处理好的币种
         */
        fun convertNumToMoney(num: Int, inLocale: Locale): String {
            val formatc = NumberFormat.getCurrencyInstance(inLocale)
            return formatc.format(num.toLong())
        }

        /**
         * 格式化字符串，如果没有对应的参数则按照原样输出
         *
         *
         *
         * 例如:
         *  * "获得{0}元宝,20"输出"获得20元宝"
         *  * "{0}获得{1}元宝,XX"输出"XX获得{1}元宝"
         *  * "{0}获得{1}元宝,XX,20"输出"XX获得20元宝"
         *
         *
         * @param str
         * @param params
         * @return
         */
        fun format(str: String, vararg params: Any): String? {
            if (isBlank(str)) {
                return str
            }
            if (params == null || params.size == 0) {
                return str
            }
            val regex = "\\{(\\d+)}"
            val p = Pattern.compile(regex)
            val m = p.matcher(str)
            val sb = StringBuffer()
            while (m.find()) {
                var param = m.group()
                val index = Integer.parseInt(m.group(1))
                if (params.size > index) {
                    val obj = params[index]
                    if (obj != null) {
                        param = obj.toString()
                    }
                }
                m.appendReplacement(sb, param)
            }
            m.appendTail(sb)
            return sb.toString()
        }


        /**
         * <pre>
         * 例：
         * String strVal="This is a dog";
         * String strResult=CTools.replace(strVal,"dog","cat");
         * 结果：
         * strResult equals "This is cat"
         *
         * @param strSrc 要进行替换操作的字符串
         * @param strOld 要查找的字符串
         * @param strNew 要替换的字符串
         * @return 替换后的字符串
         * <pre>
        </pre></pre> */
        fun replace(strSrc: String?, strOld: String?, strNew: String?): String {
            if (strSrc == null || strOld == null || strNew == null) {
                return ""
            }

            var i = 0
            //避免新旧字符一样产生死循环
            if (strOld == strNew) {
                return strSrc
            }

            if ((strSrc.indexOf(strOld, i)) >= 0) {
                val arrCsrc = strSrc.toCharArray()
                val arrCnew = strNew.toCharArray()

                val intOldLen = strOld.length
                val buf = StringBuilder(arrCsrc.size)
                buf.append(arrCsrc, 0, i).append(arrCnew)

                i += intOldLen
                var j = i

                while ((strSrc.indexOf(strOld, i)) > 0) {
                    buf.append(arrCsrc, j, i - j).append(arrCnew)
                    i += intOldLen
                    j = i
                }
                buf.append(arrCsrc, j, arrCsrc.size - j)
                return buf.toString()
            }
            return strSrc
        }


        /**
         * 在将数据存入数据库前转换
         *
         * @param strVal 要转换的字符串
         * @return 从“ISO8859_1”到“GBK”得到的字符串
         * @since 1.0
         */
        fun toChinese(strVal: String?): String {
            var strVal = strVal
            return try {
                if (strVal == null) {
                    ""
                } else {
                    strVal = strVal.trim { it <= ' ' }
                    strVal = String(strVal.toByteArray(charset("ISO8859_1")))
                    strVal
                }
            } catch (exp: Exception) {
                ""
            }

        }

        /**
         * 编码转换 从UTF-8到GBK
         *
         * @param strVal
         * @return
         */
        fun toGBK(strVal: String?): String {
            var strVal = strVal
            return try {
                if (strVal == null) {
                    ""
                } else {
                    strVal = strVal.trim { it <= ' ' }
                    strVal = String(strVal.toByteArray(charset("UTF-8")))
                    strVal
                }
            } catch (exp: Exception) {
                ""
            }

        }

        /**
         * 将数据从数据库中取出后转换   *
         *
         * @param strVal 要转换的字符串
         * @return 从“GBK”到“ISO8859_1”得到的字符串
         * @since 1.0
         */
        fun toISO(strVal: String?): String {
            var strVal = strVal
            return try {
                if (strVal == null) {
                    ""
                } else {
                    strVal = String(strVal.toByteArray(charset("GBK")))
                    strVal
                }
            } catch (exp: Exception) {
                ""
            }

        }

        fun gbk2UTF8(strVal: String?): String {
            var strVal = strVal
            return try {
                if (strVal == null) {
                    ""
                } else {
                    strVal = String(strVal.toByteArray(charset("GBK")))
                    strVal
                }
            } catch (exp: Exception) {
                ""
            }

        }

        fun iso2utf8(strVal: String?): String {
            var strVal = strVal
            return try {
                if (strVal == null) {
                    ""
                } else {
                    strVal = String(strVal.toByteArray(charset("ISO-8859-1")))
                    strVal
                }
            } catch (exp: Exception) {
                ""
            }

        }

        fun utf82iso(strVal: String?): String {
            var strVal = strVal
            try {
                if (strVal == null) {
                    return ""
                } else {
                    strVal = String(strVal.toByteArray(charset("UTF-8")))
                    return strVal
                }
            } catch (exp: Exception) {
                return ""
            }

        }


        /**
         * 实际处理 return toChineseNoReplace(null2Blank(str));
         * 主要应用于老牛的信息发布
         *
         * @param str 要进行处理的字符串
         * @return 转换后的字符串
         */
        fun toChineseAndHtmlEncode(str: String, quotes: Int): String {
            return HtmlUtil.htmlEncode(toChinese(str), quotes)
        }

        /**
         * 把null值和""值转换成&nbsp;
         * 主要应用于页面表格格的显示
         *
         * @param str 要进行处理的字符串
         * @return 转换后的字符串
         */
        fun str4Table(str: String?): String {
            return if (str == null) {
                "&nbsp;"
            } else if ("" == str) {
                "&nbsp;"
            } else {
                str
            }
        }

        /**
         * String型变量转换成int型变量
         *
         * @param str 要进行转换的字符串
         * @return intVal 如果str不可以转换成int型数据，返回-1表示异常,否则返回转换后的值
         * @since 1.0
         */
        fun str2Int(str: String): Int {
            var intVal: Int

            try {
                intVal = Integer.parseInt(str)
            } catch (e: Exception) {
                intVal = 0
            }

            return intVal
        }

        fun str2Double(str: String): Double {
            var dVal = 0.0

            try {
                dVal = java.lang.Double.parseDouble(str)
            } catch (e: Exception) {
                dVal = 0.0
            }

            return dVal
        }


        fun str2Long(str: String): Long {
            var longVal: Long = 0

            try {
                longVal = java.lang.Long.parseLong(str)
            } catch (e: Exception) {
                longVal = 0
            }

            return longVal
        }

        fun stringToFloat(floatstr: String): Float {
            val floatee: Float
            floatee = java.lang.Float.valueOf(floatstr)
            return floatee
        }

        fun floatToString(value: Float): String {
            return value.toString()
        }

        /**
         * int型变量转换成String型变量
         *
         * @param intVal 要进行转换的整数
         * @return str 如果intVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
         */
        fun int2Str(intVal: Int): String {
            var str: String

            try {
                str = intVal.toString()
            } catch (e: Exception) {
                str = ""
            }

            return str
        }

        /**
         * long型变量转换成String型变量
         *
         * @param longVal 要进行转换的整数
         * @return str 如果longVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
         */

        fun long2Str(longVal: Long): String {
            var str: String

            try {
                str = longVal.toString()
            } catch (e: Exception) {
                str = ""
            }

            return str
        }

        /**
         * null 处理
         *
         * @param str 要进行转换的字符串
         * @return 如果str为null值，返回空串"",否则返回str
         */
        fun null2Blank(str: String?): String {
            return str ?: ""
        }

        /**
         * null 处理
         *
         * @param d 要进行转换的日期对像
         * @return 如果d为null值，返回空串"",否则返回d.toString()
         */

        fun null2Blank(d: Date?): String {
            return d?.toString() ?: ""
        }

        /**
         * null 处理
         *
         * @param str 要进行转换的字符串
         * @return 如果str为null值，返回空串整数0,否则返回相应的整数
         */
        fun null2Zero(str: String): Int {
            val intTmp: Int
            intTmp = str2Int(str)
            return if (intTmp == -1) {
                0
            } else {
                intTmp
            }
        }

        /**
         * 把null转换为字符串"0"
         *
         * @param str
         * @return
         */
        fun null2SZero(str: String): String {
            var str = str
            str = null2Blank(str)
            return if ("" == str) {
                "0"
            } else {
                str
            }
        }


        /**
         * 字符串从GBK编码转换为Unicode编码
         *
         * @param text
         * @return
         */
        fun stringToUnicode(text: String): String {
            val result = StringBuffer()
            var input = 0
            val isr: StringReader
            try {
                isr = StringReader(String(text.toByteArray()))
            } catch (e: UnsupportedEncodingException) {
                return "-1"
            }

            try {
                while ((isr.read()) != -1) {
                    result.append("&#x").append(input).append(";")

                }
            } catch (e: IOException) {
                return "-2"
            }

            isr.close()
            return result.toString()

        }

        /**
         * @param inStr
         * @return
         */
        fun gb2utf(inStr: String?): String {
            var inStr = inStr
            var temChr: Char
            var ascInt: Int
            var i = 0
            val stringBuffer = StringBuilder()
            if (inStr == null) {
                inStr = ""
            }
            while (i < inStr.length) {
                temChr = inStr[i]
                ascInt = temChr.toInt()
                if (Integer.toHexString(ascInt).length > 2) {
                    stringBuffer.append("&#x").append(Integer.toHexString(ascInt)).append(";")
                } else {
                    stringBuffer.append(temChr)
                }
                i++

            }
            return stringBuffer.toString()
        }

        /**
         * This method will encode the String to unicode.
         *
         * @param gbString
         * @return
         */
        fun gbEncoding(gbString: String): String {
            val utfBytes = gbString.toCharArray()
            var unicodeBytes = ""
            for (utfByte in utfBytes) {
                var hexB = Integer.toHexString(utfByte.toInt())
                if (hexB.length <= 2) {
                    hexB = "00" + hexB
                }
                unicodeBytes = unicodeBytes + "\\u" + hexB
            }
            println("unicodeBytes is: " + unicodeBytes)
            return unicodeBytes
        }

        /**
         * This method will decode the String to a recognized String
         * in ui.
         *
         * @param dataStr
         * @return
         */
        fun decodeUnicode(dataStr: String): StringBuffer {
            var start = 0
            var end = 0
            val buffer = StringBuffer()
            while (start > -1) {
                end = dataStr.indexOf("\\u", start + 2)
                var charStr = ""
                if (end == -1) {
                    charStr = dataStr.substring(start + 2, dataStr.length)
                } else {
                    charStr = dataStr.substring(start + 2, end)
                }
                // 16进制parse整形字符串。
                val letter = Integer.parseInt(charStr, 16).toChar()
                buffer.append(Character.toString(letter))
                start = end
            }
            return buffer
        }


        /**  */
        /**
         * 编码
         *
         * @param s
         * @return
         */
        fun encode(s: String): String {
            val sbuf = StringBuilder()
            val len = s.length
            for (i in 0 until len) {
                val ch = s[i].toInt()
                if (ch.toChar() in 'A'..'Z') {
                    sbuf.append(ch.toChar())
                } else if (ch.toChar() in 'a'..'z') {
                    sbuf.append(ch.toChar())
                } else if (ch.toChar() in '0'..'9') {
                    sbuf.append(ch.toChar())
                } else if (ch.toChar() == '-' || ch.toChar() == '_' || ch.toChar() == '.' || ch.toChar() == '!'
                        || ch.toChar() == '~' || ch.toChar() == '*' || ch.toChar() == '\'' || ch.toChar() == '('
                        || ch.toChar() == ')') {
                    sbuf.append(ch.toChar())
                } else if (ch <= 0x007F) {
                    sbuf.append('%')
                    sbuf.append(HEX[ch])
                } else {
                    sbuf.append('%')
                    sbuf.append('u')
                    sbuf.append(HEX[ch.ushr(8)])
                    sbuf.append(HEX[0x00FF and ch])
                }
            }
            return sbuf.toString()
        }

        /**  */
        /**
         * 解码 说明：本方法保证 不论参数s是否经过escape()编码，均能得到正确的“解码”结果
         *
         * @param s
         * @return
         */
        fun decode(s: String): String {
            val sbuf = StringBuilder()
            var i = 0
            val len = s.length
            while (i < len) {
                val ch = s[i].toInt()
                if (ch.toChar() in 'A'..'Z') {
                    sbuf.append(ch.toChar())
                } else if (ch.toChar() in 'a'..'z') {
                    sbuf.append(ch.toChar())
                } else if (ch.toChar() in '0'..'9') {
                    sbuf.append(ch.toChar())
                } else if (ch.toChar() == '-' || ch.toChar() == '_' || ch.toChar() == '.' || ch.toChar() == '!'
                        || ch.toChar() == '~' || ch.toChar() == '*' || ch.toChar() == '\'' || ch.toChar() == '('
                        || ch.toChar() == ')') {
                    sbuf.append(ch.toChar())
                } else if (ch.toChar() == '%') {
                    var cint = 0
                    if ('u' != s[i + 1]) {
                        cint = cint shl 4 or VAL[s[i + 1].toInt()].toInt()
                        cint = cint shl 4 or VAL[s[i + 2].toInt()].toInt()
                        i += 2
                    } else {
                        cint = cint shl 4 or VAL[s[i + 2].toInt()].toInt()
                        cint = cint shl 4 or VAL[s[i + 3].toInt()].toInt()
                        cint = cint shl 4 or VAL[s[i + 4].toInt()].toInt()
                        cint = cint shl 4 or VAL[s[i + 5].toInt()].toInt()
                        i += 5
                    }
                    sbuf.append(cint.toChar())
                } else {
                    sbuf.append(ch.toChar())
                }
                i++
            }
            return sbuf.toString()
        }
    }
}
