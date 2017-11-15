package info.xiaomo.core.untils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 对字符串的简单处理
 * <p>
 *
 * @author : xiaomo
 * @Date 2013-6-6 下午5:08:06
 */
public class StringUtil extends StringUtils {

    /**
     * ip正则表达式
     */
    public static final String IP_REGEX = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\." + "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
    private final static String[] HEX = {"00", "01", "02", "03", "04", "05",
            "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B",
            "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26",
            "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31",
            "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C",
            "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47",
            "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52",
            "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D",
            "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68",
            "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73",
            "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E",
            "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
            "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94",
            "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
            "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA",
            "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5",
            "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0",
            "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB",
            "CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6",
            "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1",
            "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC",
            "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7",
            "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF"};
    private final static byte[] VAL = {0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01,
            0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F};
    private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
    private static final char[] AMP_ENCODE = "&amp;".toCharArray();
    private static final char[] LT_ENCODE = "&lt;".toCharArray();
    private static final char[] GT_ENCODE = "&gt;".toCharArray();

    /**
     * 是否是空字符串
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * String数组转成int数组
     *
     * @param numbers String[]
     * @return List<Integer>
     */
    public static List<Integer> strArrToIntList(String[] numbers) {
        List<Integer> intArr = new ArrayList<>();
        for (String number : numbers) {
            intArr.add(Integer.parseInt(number));
        }
        return intArr;
    }

    /**
     * String数组转成int数组
     *
     * @param numbers String[]
     * @return int[]
     */
    public static int[] strArrToIntArr(String[] numbers) {
        int[] intArr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            intArr[i] = Integer.parseInt(numbers[i]);
        }
        return intArr;
    }

    /**
     * 根据指定的分隔符将字符串转为int数组
     *
     * @param source 字符串
     * @param split  分割符
     * @return int[]
     */
    public static int[] strToIntArr(String source, String split) {
        if (isBlank(source)) {
            return new int[0];
        }
        String[] numbers = source.split(split);
        return strArrToIntArr(numbers);
    }

    /**
     * 截取文字safe 中文
     *
     * @return String    返回类型
     */
    public static String subCn(String string, int length, String more) {
        if (StringUtils.isNotEmpty(string)) {
            char[] chars = string.toCharArray();
            if (chars.length > length) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    sb.append(chars[i]);
                }
                sb.append(more);
                return sb.toString();
            }
        }
        return string;
    }


    /**
     * 字符串全角转半角
     *
     * @return String    返回类型
     */
    public static String togglecase(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            sb.append(CharUtil.regularize(string.charAt(i)));
        }
        return sb.toString();
    }


    /**
     * 计算文字长度-.-无中文问题
     *
     * @return int    返回类型
     */
    public static int getLength(String string) {
        if (StringUtils.isBlank(string)) {
            return 0;
        } else {
            char[] strChars = string.toCharArray();
            return strChars.length;
        }
    }

    /**
     * 将字符串中特定模式的字符转换成map中对应的值,
     *
     * @param s   需要转换的字符串
     * @param map 转换所需的键值对集合
     * @return 转换后的字符串
     */
    public static String replace(String s, Map<String, String> map) {
        StringBuilder sb = new StringBuilder((int) (s.length() * 1.5));
        int cursor = 0;
        String str = "${";
        char ch = '}';
        for (int start, end; (start = s.indexOf(str, cursor)) != -1 && (end = s.indexOf(ch, start)) != -1; ) {
            sb.append(s.substring(cursor, start));
            String key = s.substring(start + 2, end);
            sb.append(map.get(StringUtils.trim(key)));
            cursor = end + 1;
        }
        sb.append(s.substring(cursor, s.length()));
        return sb.toString();
    }


    /**
     * 获取ip
     *
     * @return ip 如果返回null,说明是一个不合法的ip地址格式
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Requested-For");
        String unknown = "unknown";
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (!ip.matches(IP_REGEX)) {
            return null;
        }
        return ip;
    }

    /**
     * 判断字符串是否为空，并删除首尾空格
     *
     * @param tempSql
     * @return
     */
    public static String convertNullCode(String tempSql) {
        if (tempSql == null) {
            tempSql = "";
        }
        return tempSql;
    }

    /**
     * 代码转换，GBK转换为ISO-8859-1
     *
     * @param tempSql 要转换的字符串
     */
    public static String isocode(String tempSql) {

        String returnString = convertNullCode(tempSql);

        try {
            byte[] ascii = returnString.getBytes("GBK");
            returnString = new String(ascii, "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * 代码转换，ISO-8859-1转换为GBK
     *
     * @param tempSql 要转换的字符串
     * @return
     */
    public static String gbkcode(String tempSql) {
        String returnString = convertNullCode(tempSql);
        try {
            byte[] ascii = returnString.getBytes("ISO-8859-1");
            returnString = new String(ascii, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * 代码转换 从srcCode转换为destCode
     *
     * @param srcCode  原编码
     * @param destCode 目标编码
     * @param strTmp   要转换的字符串
     * @return
     */
    public static String convertCode(String srcCode, String destCode, String strTmp) {
        String returnString = convertNullCode(strTmp);
        try {
            byte[] ascii = returnString.getBytes(srcCode);
            returnString = new String(ascii, destCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * 代码转换，GBK转换为big5
     *
     * @param tempSql 要转换的字符串
     * @return
     */
    public static String gbk2big5code(String tempSql) {
        String returnString = convertNullCode(tempSql);
        try {
            byte[] ascii = returnString.getBytes("GBK");
            returnString = new String(ascii, "big5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * 替换非法字符
     *
     * @param input
     * @return
     */
    public static String convertHtml(String input) {
        StringBuffer returnString = new StringBuffer(input.length());

        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {

            ch = input.charAt(i);

            if (ch == '<') {
                returnString = returnString.append("&lt");
            } else if (ch == '>') {
                returnString = returnString.append("&gt");
            } else if (ch == ' ') {
                returnString = returnString.append("&nbsp");
            } else if (ch == '\\') {
                returnString = returnString.append("&acute");
            } else {
                returnString = returnString.append(ch);
            }
        }
        return returnString.toString();
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
    public static String escapeHTMLTags(String in) {
        if (in == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = in.toCharArray();
        int len = input.length;
        StringBuilder out = new StringBuilder((int) (len * 1.3));
        for (; i < len; i++) {
            ch = input[i];

            if (ch > '>') {
                continue;
            } else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT_ENCODE);
            } else if (ch == '>') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(GT_ENCODE);
            }
        }
        if (last == 0) {
            return in;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    public static String filterString(String allstr) {
        StringBuilder returnString = new StringBuilder(allstr.length());
        char ch = ' ';
        for (int i = 0; i < allstr.length(); i++) {
            ch = allstr.charAt(i);
            String lsTemp = "'";
            char lcTemp = lsTemp.charAt(0);
            if (ch == lcTemp) {
                returnString.append("''");
            } else {
                returnString.append(ch);
            }
        }
        return returnString.toString();
    }

    /**
     * 数字的金额表达式
     */
    public static String convertNumToMoney(int num) {
        NumberFormat formatc = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return formatc.format(num);
    }


    /**
     * 数字的金额表达式
     *
     * @param num      金额
     * @param inLocale 币种
     * @return 处理好的币种
     */
    public static String convertNumToMoney(int num, Locale inLocale) {
        NumberFormat formatc = NumberFormat.getCurrencyInstance(inLocale);
        return formatc.format(num);
    }

    /**
     * 格式化字符串，如果没有对应的参数则按照原样输出
     * <p>
     * <ul>
     * 例如:
     * <li>"获得{0}元宝,20"输出"获得20元宝"</li>
     * <li>"{0}获得{1}元宝,XX"输出"XX获得{1}元宝"</li>
     * <li>"{0}获得{1}元宝,XX,20"输出"XX获得20元宝"</li>
     * </ul>
     *
     * @param str
     * @param params
     * @return
     */
    public static String format(String str, Object... params) {
        if (isBlank(str)) {
            return str;
        }
        if (params == null || params.length == 0) {
            return str;
        }
        String regex = "\\{(\\d+)}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String param = m.group();
            int index = Integer.parseInt(m.group(1));
            if (params.length > index) {
                Object obj = params[index];
                if (obj != null) {
                    param = obj.toString();
                }
            }
            m.appendReplacement(sb, param);
        }
        m.appendTail(sb);
        return sb.toString();
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
     */
    public static String replace(String strSrc, String strOld, String strNew) {
        if (strSrc == null || strOld == null || strNew == null) {
            return "";
        }

        int i = 0;
//避免新旧字符一样产生死循环
        if (strOld.equals(strNew)) {
            return strSrc;
        }

        if ((i = strSrc.indexOf(strOld, i)) >= 0) {
            char[] arrCsrc = strSrc.toCharArray();
            char[] arrCnew = strNew.toCharArray();

            int intOldLen = strOld.length();
            StringBuilder buf = new StringBuilder(arrCsrc.length);
            buf.append(arrCsrc, 0, i).append(arrCnew);

            i += intOldLen;
            int j = i;

            while ((i = strSrc.indexOf(strOld, i)) > 0) {
                buf.append(arrCsrc, j, i - j).append(arrCnew);
                i += intOldLen;
                j = i;
            }
            buf.append(arrCsrc, j, arrCsrc.length - j);
            return buf.toString();
        }
        return strSrc;
    }


    /**
     * 在将数据存入数据库前转换
     *
     * @param strVal 要转换的字符串
     * @return 从“ISO8859_1”到“GBK”得到的字符串
     * @since 1.0
     */
    public static String toChinese(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = strVal.trim();
                strVal = new String(strVal.getBytes("ISO8859_1"), "GBK");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * 编码转换 从UTF-8到GBK
     *
     * @param strVal
     * @return
     */
    public static String toGBK(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = strVal.trim();
                strVal = new String(strVal.getBytes("UTF-8"), "GBK");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * 将数据从数据库中取出后转换   *
     *
     * @param strVal 要转换的字符串
     * @return 从“GBK”到“ISO8859_1”得到的字符串
     * @since 1.0
     */
    public static String toISO(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("GBK"), "ISO8859_1");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    public static String gbk2UTF8(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("GBK"), "UTF-8");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    public static String iso2utf8(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("ISO-8859-1"), "UTF-8");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    public static String utf82iso(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("UTF-8"), "ISO-8859-1");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }


    /**
     * 实际处理 return toChineseNoReplace(null2Blank(str));
     * 主要应用于老牛的信息发布
     *
     * @param str 要进行处理的字符串
     * @return 转换后的字符串
     */
    public static String toChineseAndHtmlEncode(String str, int quotes) {
        return HtmlUtil.htmlEncode(toChinese(str), quotes);
    }

    /**
     * 把null值和""值转换成&nbsp;
     * 主要应用于页面表格格的显示
     *
     * @param str 要进行处理的字符串
     * @return 转换后的字符串
     */
    public static String str4Table(String str) {
        if (str == null) {
            return "&nbsp;";
        } else if ("".equals(str)) {
            return "&nbsp;";
        } else {
            return str;
        }
    }

    /**
     * String型变量转换成int型变量
     *
     * @param str 要进行转换的字符串
     * @return intVal 如果str不可以转换成int型数据，返回-1表示异常,否则返回转换后的值
     * @since 1.0
     */
    public static int str2Int(String str) {
        int intVal;

        try {
            intVal = Integer.parseInt(str);
        } catch (Exception e) {
            intVal = 0;
        }

        return intVal;
    }

    public static double str2Double(String str) {
        double dVal = 0;

        try {
            dVal = Double.parseDouble(str);
        } catch (Exception e) {
            dVal = 0;
        }

        return dVal;
    }


    public static long str2Long(String str) {
        long longVal = 0;

        try {
            longVal = Long.parseLong(str);
        } catch (Exception e) {
            longVal = 0;
        }

        return longVal;
    }

    public static float stringToFloat(String floatstr) {
        float floatee;
        floatee = Float.valueOf(floatstr);
        return floatee;
    }

    public static String floatToString(float value) {
        Float floatee = value;
        return floatee.toString();
    }

    /**
     * int型变量转换成String型变量
     *
     * @param intVal 要进行转换的整数
     * @return str 如果intVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
     */
    public static String int2Str(int intVal) {
        String str;

        try {
            str = String.valueOf(intVal);
        } catch (Exception e) {
            str = "";
        }

        return str;
    }

    /**
     * long型变量转换成String型变量
     *
     * @param longVal 要进行转换的整数
     * @return str 如果longVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
     */

    public static String long2Str(long longVal) {
        String str;

        try {
            str = String.valueOf(longVal);
        } catch (Exception e) {
            str = "";
        }

        return str;
    }

    /**
     * null 处理
     *
     * @param str 要进行转换的字符串
     * @return 如果str为null值，返回空串"",否则返回str
     */
    public static String null2Blank(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * null 处理
     *
     * @param d 要进行转换的日期对像
     * @return 如果d为null值，返回空串"",否则返回d.toString()
     */

    public static String null2Blank(Date d) {
        if (d == null) {
            return "";
        } else {
            return d.toString();
        }
    }

    /**
     * null 处理
     *
     * @param str 要进行转换的字符串
     * @return 如果str为null值，返回空串整数0,否则返回相应的整数
     */
    public static int null2Zero(String str) {
        int intTmp;
        intTmp = str2Int(str);
        if (intTmp == -1) {
            return 0;
        } else {
            return intTmp;
        }
    }

    /**
     * 把null转换为字符串"0"
     *
     * @param str
     * @return
     */
    public static String null2SZero(String str) {
        str = null2Blank(str);
        if ("".equals(str)) {
            return "0";
        } else {
            return str;
        }
    }


    /**
     * 字符串从GBK编码转换为Unicode编码
     *
     * @param text
     * @return
     */
    public static String stringToUnicode(String text) {
        StringBuffer result = new StringBuffer();
        int input;
        StringReader isr;
        try {
            isr = new StringReader(new String(text.getBytes(), "GBK"));
        } catch (UnsupportedEncodingException e) {
            return "-1";
        }
        try {
            while ((input = isr.read()) != -1) {
                result.append("&#x").append(input).append(";");

            }
        } catch (IOException e) {
            return "-2";
        }
        isr.close();
        return result.toString();

    }

    /**
     * @param inStr
     * @return
     */
    public static String gb2utf(String inStr) {
        char temChr;
        int ascInt;
        int i;
        StringBuilder stringBuffer = new StringBuilder();
        if (inStr == null) {
            inStr = "";
        }
        for (i = 0; i < inStr.length(); i++) {
            temChr = inStr.charAt(i);
            ascInt = temChr;
            if (Integer.toHexString(ascInt).length() > 2) {
                stringBuffer.append("&#x").append(Integer.toHexString(ascInt)).append(";");
            } else {
                stringBuffer.append(temChr);
            }

        }
        return stringBuffer.toString();
    }

    /**
     * This method will encode the String to unicode.
     *
     * @param gbString
     * @return
     */
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }

    /**
     * This method will decode the String to a recognized String
     * in ui.
     *
     * @param dataStr
     * @return
     */
    public static StringBuffer decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            // 16进制parse整形字符串。
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(Character.toString(letter));
            start = end;
        }
        return buffer;
    }


    /** */
    /**
     * 编码
     *
     * @param s
     * @return
     */
    public static String encode(String s) {
        StringBuilder sbuf = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int ch = s.charAt(i);
            if ('A' <= ch && ch <= 'Z') {
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') {
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') {
                sbuf.append((char) ch);
            } else if (ch == '-' || ch == '_' || ch == '.' || ch == '!'
                    || ch == '~' || ch == '*' || ch == '\'' || ch == '('
                    || ch == ')') {
                sbuf.append((char) ch);
            } else if (ch <= 0x007F) {
                sbuf.append('%');
                sbuf.append(HEX[ch]);
            } else {
                sbuf.append('%');
                sbuf.append('u');
                sbuf.append(HEX[(ch >>> 8)]);
                sbuf.append(HEX[(0x00FF & ch)]);
            }
        }
        return sbuf.toString();
    }

    /** */
    /**
     * 解码 说明：本方法保证 不论参数s是否经过escape()编码，均能得到正确的“解码”结果
     *
     * @param s
     * @return
     */
    public static String decode(String s) {
        StringBuilder sbuf = new StringBuilder();
        int i = 0;
        int len = s.length();
        while (i < len) {
            int ch = s.charAt(i);
            if ('A' <= ch && ch <= 'Z') {
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') {
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') {
                sbuf.append((char) ch);
            } else if (ch == '-' || ch == '_' || ch == '.' || ch == '!'
                    || ch == '~' || ch == '*' || ch == '\'' || ch == '('
                    || ch == ')') {
                sbuf.append((char) ch);
            } else if (ch == '%') {
                int cint = 0;
                if ('u' != s.charAt(i + 1)) {
                    cint = (cint << 4) | VAL[s.charAt(i + 1)];
                    cint = (cint << 4) | VAL[s.charAt(i + 2)];
                    i += 2;
                } else {
                    cint = (cint << 4) | VAL[s.charAt(i + 2)];
                    cint = (cint << 4) | VAL[s.charAt(i + 3)];
                    cint = (cint << 4) | VAL[s.charAt(i + 4)];
                    cint = (cint << 4) | VAL[s.charAt(i + 5)];
                    i += 5;
                }
                sbuf.append((char) cint);
            } else {
                sbuf.append((char) ch);
            }
            i++;
        }
        return sbuf.toString();
    }
}
