package info.xiaomo.core.untils

import org.apache.commons.lang3.StringUtils
import java.util.*
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * 这是个正则表达式应用类，用来匹配和替换字串用的
 *
 * @author xiaomo
 */

object RegExUtil {

    /**
     * 用户名
     */
    val USER_NAME = "^[a-zA-Z\\u4E00-\\u9FA5][a-zA-Z0-9_\\u4E00-\\u9FA5]{1,11}$"

    /**
     * 密码
     */
    val USER_PASSWORD = "^.{6,32}$"

    /**
     * 邮箱
     */
    val EMAIL = "^\\w+([-+.]*\\w+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$"

    /**
     * 手机号
     */
    val PHONE = "^1[34578]\\d{9}$"

    /**
     * 手机号或者邮箱
     */
    val EMAIL_OR_PHONE = EMAIL + "|" + PHONE

    /**
     * URL路径
     */
    val URL = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})(:[\\d]+)?([\\/\\w\\.-]*)*\\/?$"

    /**
     * 身份证校验，初级校验，具体规则有一套算法
     */
    val ID_CARD = "^\\d{15}$|^\\d{17}([0-9]|X)$"

    /**
     * 编译传入正则表达式和字符串去匹配,忽略大小写
     *
     * @param regex        regex
     * @param beTestString beTestString
     */
    fun match(regex: String, beTestString: String): Boolean {
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(beTestString)
        return matcher.matches()
    }

    /**
     * 编译传入正则表达式在字符串中寻找，如果匹配到则为true
     *
     * @param regex        regex
     * @param beTestString beTestString
     */
    fun find(regex: String, beTestString: String): Boolean {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(beTestString)
        return matcher.find()
    }

    /**
     * 编译传入正则表达式在字符串中寻找，如果找到返回第一个结果<br></br>
     * 找不到返回null
     *
     * @param regex         regex
     * @param beFoundString beFoundString
     */
    fun findResult(regex: String, beFoundString: String): String? {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(beFoundString)
        return if (matcher.find()) {
            matcher.group()
        } else null
    }

    /**
     * 隐藏手机号中间4位
     *
     * @param phone phone
     * @return String
     */
    fun encodePhone(phone: String): String {
        if (StringUtils.isBlank(phone)) {
            return ""
        }
        if (match(PHONE, phone)) {
            val begin = phone.substring(0, 3)
            val end = phone.substring(7, phone.length)
            return begin + "****" + end
        }
        return phone
    }

    /**
     * 要求大小写都匹配正则表达式
     *
     * @param pattern 正则表达式模式
     * @param str     要匹配的字串
     * @return boolean值
     * @since 1.0
     */
    @Throws(PatternSyntaxException::class)
    fun ereg(pattern: String, str: String): Boolean {
        val p = Pattern.compile(pattern)
        val m = p.matcher(str)
        return m.find()
    }

    /**
     * 匹配且替换字串
     *
     * @param pattern 正则表达式模式
     * @param newstr  要替换匹配到的新字串
     * @param str     原始字串
     * @return 匹配后的字符串
     * @since 1.0
     */

    @Throws(PatternSyntaxException::class)
    fun eregReplace(pattern: String, newstr: String, str: String): String {
        val p = Pattern.compile(pattern)
        val m = p.matcher(str)
        return m.replaceAll(newstr)
    }

    /**
     * 主要用于模板中模块标记分析函数 把查找到的元素加到vector中
     *
     * @param pattern 为正则表达式模式
     * @param str             原始字串
     * @return vector
     * @since 1.0
     */
    @Throws(PatternSyntaxException::class)
    fun splitTags2Vector(pattern: String, str: String): Vector<String> {
        val vector = Vector<String>()
        val p = Pattern.compile(pattern)
        val m = p.matcher(str)
        while (m.find()) {
            vector.add(eregReplace("(\\[\\#)|(\\#\\])", "", m.group()))
        }
        return vector
    }

    /**
     * 模块标记分析函数
     * 功能主要是把查找到的元素加到vector中
     *
     * @param pattern 为正则表达式模式
     * @param str             原始字串
     * @since 1.0
     */
    fun splitTags(pattern: String, str: String): Array<String?> {
        val p = Pattern.compile(pattern)
        val m = p.matcher(str)
        val array = arrayOfNulls<String>(m.groupCount())
        var i = 0
        while (m.find()) {
            array[i] = eregReplace("(\\[\\#)|(\\#\\])", "", m.group())
            i++
        }
        return array
    }


    /**
     * 匹配所有符合模式要求的字串并加到矢量vector数组中
     *
     * @param pattern 为正则表达式模式
     * @param str             原始字串
     * @return vector
     * @since 1.0
     */
    @Throws(PatternSyntaxException::class)
    fun regMatchAll2Vector(pattern: String, str: String): Vector<String> {
        val vector = Vector<String>()
        val p = Pattern.compile(pattern)
        val m = p.matcher(str)
        while (m.find()) {
            vector.add(m.group())
        }
        return vector
    }

    /**
     * 匹配所有符合模式要求的字串并加到字符串数组中
     *
     * @param pattern 为正则表达式模式
     * @param str             原始字串
     * @return array
     * @since 1.0
     */
    @Throws(PatternSyntaxException::class)
    fun regMatchAll2Array(pattern: String, str: String): Array<String?> {
        val p = Pattern.compile(pattern)
        val m = p.matcher(str)
        val array = arrayOfNulls<String>(m.groupCount())
        var i = 0
        while (m.find()) {
            array[i] = m.group()
            i++
        }
        return array
    }

    /**
     * 转义正则表达式字符(之所以需要将\和$字符用escapeDollarBackslash方法的方式是因为用repalceAll是不行的，简单的试试"$".repalceAll("\\$","\\\\$")你会发现这个调用会导致数组越界错误)
     *
     * @param original 为正则表达式模式
     * @return array
     * @since 1.0
     */
    fun escapeDollarBackslash(original: String): String {
        val buffer = StringBuilder(original.length)
        for (i in 0 until original.length) {
            val c = original[i]
            if (c == '\\' || c == '$') {
                buffer.append("\\").append(c)
            } else {
                buffer.append(c)
            }
        }
        return buffer.toString()
    }

    /**
     * 提取指定字串的函数
     * 功能主要是把查找到的元素
     *
     * @param pattern 为正则表达式模式
     * @param str     原始字串
     * @since 1.0
     */
    fun fetchStr(pattern: String, str: String): String? {
        var returnValue: String? = null
        try {
            val p = Pattern.compile(pattern)
            val m = p.matcher(str)
            while (m.find()) {
                returnValue = m.group()
            }
            return returnValue
        } catch (e: PatternSyntaxException) {
            return returnValue
        }

    }


    @JvmStatic
    fun main(args: Array<String>) {
        println(ereg(ID_CARD, "420325199210211911"))
    }
}
