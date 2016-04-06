package info.xiaomo.core.untils;

import org.apache.commons.lang3.RandomStringUtils;
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
 *
 * @author L.cm
 * @date 2013-6-6 下午5:08:06
 */
public class StringUtil extends StringUtils {

    private static final String NUM_S = "0123456789";
    private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";

    private final static String[] hex = {"00", "01", "02", "03", "04", "05",
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

    private final static byte[] val = {0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
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
     * 生成一个10位的tonken用于http cache
     *
     * @return String    返回类型
     */
    public static String getTonken() {
        return RandomStringUtils.random(10, NUM_S);
    }

    /**
     * 生成随机数
     *
     * @return String    返回类型
     */
    public static String randomPwd(int count) {
        return RandomStringUtils.random(count, STR_S);
    }


    /**
     * 百度获ip获取到的城市处理 供大众点评
     * 暂用，以后会用类是分词的技术，好吧完全没有算法的概念
     *
     * @return String    返回类型
     */
    public static String cityMatcher(String city) {
        String regex = "(.+)[市|省|自治区]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(city);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    /**
     * 字符串全角转半角
     *
     * @return String    返回类型
     */
    public static String togglecase(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            sb.append(CharUtil.regularize(string.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * 功能描述: 生成sql占位符 ?,?,?
     *
     * @return String    返回类型
     */
    public static String sqlHolder(int size) {
        String[] paras = new String[size];
        Arrays.fill(paras, "?");
        return StringUtils.join(paras, ',');
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
        for (int start, end; (start = s.indexOf("${", cursor)) != -1 && (end = s.indexOf('}', start)) != -1; ) {
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
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Requested-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    //判断字符串是否为空，并删除首尾空格
    public static String convertNullCode(String tempSql) {
        if (tempSql == null) tempSql = "";
        return tempSql;
    }

    /**
     * 代码转换，GBK转换为ISO-8859-1
     *
     * @param tempSql 要转换的字符串
     */
    public static String ISOCode(String tempSql) {

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
    public static String GBKCode(String tempSql) {
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
    public static String GBK2BIG5Code(String tempSql) {
        String returnString = convertNullCode(tempSql);
        try {
            byte[] ascii = returnString.getBytes("GBK");
            returnString = new String(ascii, "big5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    //替换非法字符
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

    /*
     *
     */
    private String delSQlString(String sql) {
        String delSql = "in(";
        StringTokenizer Tokenizer = new StringTokenizer(sql, "|");

        // 标记本身等于分隔符的特殊情况
        delSql += Tokenizer.nextToken().toString();
        while (Tokenizer.hasMoreTokens()) {
            delSql += Tokenizer.nextToken() + ",";
        }
        delSql = delSql.substring(0, delSql.length() - 1) + ")";
        return delSql;
    }

    /*
     * format selectedIDs to sql language
     * in (...)
     * second of methods bt own idea
     */
    private String delNewSQlString(String sql) {
        return "in (" + sql.replace('|', ',') + ")";
    }

    private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
    private static final char[] AMP_ENCODE = "&amp;".toCharArray();
    private static final char[] LT_ENCODE = "&lt;".toCharArray();
    private static final char[] GT_ENCODE = "&gt;".toCharArray();

    /**
     * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
     * &lt;table&gt;, etc) and converts the '&lt'' and '&gt;' characters to
     * their HTML escape sequences.
     *
     * @param in the text to be converted.
     * @return the input string with the characters '&lt;' and '&gt;' replaced
     * with their HTML escape sequences.
     */
    public static final String escapeHTMLTags(String in) {
        if (in == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = in.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer((int) (len * 1.3));
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
        String strcurr = formatc.format(num);
        System.out.println(strcurr);
        return strcurr;
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
        if (strSrc == null || strOld == null || strNew == null)
            return "";

        int i = 0;

        if (strOld.equals(strNew)) //避免新旧字符一样产生死循环
            return strSrc;

        if ((i = strSrc.indexOf(strOld, i)) >= 0) {
            char[] arr_cSrc = strSrc.toCharArray();
            char[] arr_cNew = strNew.toCharArray();

            int intOldLen = strOld.length();
            StringBuffer buf = new StringBuffer(arr_cSrc.length);
            buf.append(arr_cSrc, 0, i).append(arr_cNew);

            i += intOldLen;
            int j = i;

            while ((i = strSrc.indexOf(strOld, i)) > 0) {
                buf.append(arr_cSrc, j, i - j).append(arr_cNew);
                i += intOldLen;
                j = i;
            }

            buf.append(arr_cSrc, j, arr_cSrc.length - j);

            return buf.toString();
        }

        return strSrc;
    }

    /**
     * 用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
     *
     * @param strSrc 要进行替换操作的字符串
     * @return 替换特殊字符后的字符串
     * @since 1.0
     */

    public static String htmlEncode(String strSrc) {
        if (strSrc == null)
            return "";

        char[] arr_cSrc = strSrc.toCharArray();
        StringBuffer buf = new StringBuffer(arr_cSrc.length);
        char ch;

        for (int i = 0; i < arr_cSrc.length; i++) {
            ch = arr_cSrc[i];

            if (ch == '<')
                buf.append("&lt;");
            else if (ch == '>')
                buf.append("&gt;");
            else if (ch == '"')
                buf.append("&quot;");
            else if (ch == '\'')
                buf.append("&#039;");
            else if (ch == '&')
                buf.append("&amp;");
            else
                buf.append(ch);
        }

        return buf.toString();
    }

    /**
     * 用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
     *
     * @param strSrc 要进行替换操作的字符串
     * @param quotes 为0时单引号和双引号都替换，为1时不替换单引号，为2时不替换双引号，为3时单引号和双引号都不替换
     * @return 替换特殊字符后的字符串
     * @since 1.0
     */
    public static String htmlEncode(String strSrc, int quotes) {

        if (strSrc == null)
            return "";
        if (quotes == 0) {
            return htmlEncode(strSrc);
        }

        char[] arr_cSrc = strSrc.toCharArray();
        StringBuffer buf = new StringBuffer(arr_cSrc.length);
        char ch;

        for (int i = 0; i < arr_cSrc.length; i++) {
            ch = arr_cSrc[i];
            if (ch == '<')
                buf.append("&lt;");
            else if (ch == '>')
                buf.append("&gt;");
            else if (ch == '"' && quotes == 1)
                buf.append("&quot;");
            else if (ch == '\'' && quotes == 2)
                buf.append("&#039;");
            else if (ch == '&')
                buf.append("&amp;");
            else
                buf.append(ch);
        }

        return buf.toString();
    }

    /**
     * 和htmlEncode正好相反
     *
     * @param strSrc 要进行转换的字符串
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String htmlDecode(String strSrc) {
        if (strSrc == null)
            return "";
        strSrc = strSrc.replaceAll("&lt;", "<");
        strSrc = strSrc.replaceAll("&gt;", ">");
        strSrc = strSrc.replaceAll("&quot;", "\"");
        strSrc = strSrc.replaceAll("&#039;", "'");
        strSrc = strSrc.replaceAll("&amp;", "&");
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

    public static String ISO2UTF8(String strVal) {
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

    public static String UTF82ISO(String strVal) {
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
     * @see fs_com.utils.CTools#toChinese
     * @see fs_com.utils.CTools#null2Blank
     */
    public static String toChineseAndHtmlEncode(String str, int quotes) {
        return htmlEncode(toChinese(str), quotes);
    }

    /**
     * 把null值和""值转换成&nbsp;
     * 主要应用于页面表格格的显示
     *
     * @param str 要进行处理的字符串
     * @return 转换后的字符串
     */
    public static String str4Table(String str) {
        if (str == null)
            return "&nbsp;";
        else if (str.equals(""))
            return "&nbsp;";
        else
            return str;
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
        Float floatee;
        floatee = Float.valueOf(floatstr);
        return floatee.floatValue();
    }

    //change the float type to the string type
    public static String floatToString(float value) {
        Float floatee = new Float(value);
        return floatee.toString();
    }

    /**
     *int型变量转换成String型变量
     *@param intVal 要进行转换的整数
     *@return str 如果intVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
     */
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
        if (str == null)
            return "";
        else
            return str;
    }

    /**
     * null 处理
     *
     * @param d 要进行转换的日期对像
     * @return 如果d为null值，返回空串"",否则返回d.toString()
     */

    public static String null2Blank(Date d) {
        if (d == null)
            return "";
        else
            return d.toString();
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
        if (intTmp == -1)
            return 0;
        else
            return intTmp;
    }

    /**
     * 把null转换为字符串"0"
     *
     * @param str
     * @return
     */
    public static String null2SZero(String str) {
        str = null2Blank(str);
        if (str.equals(""))
            return "0";
        else
            return str;
    }

    /**
     * sql语句 处理
     *
     * @param sql    要进行处理的sql语句
     * @param dbtype 数据库类型
     * @return 处理后的sql语句
     */
    public static String sql4DB(String sql, String dbtype) {
        if (!dbtype.equalsIgnoreCase("oracle")) {
            sql = toISO(sql);
        }
        return sql;
    }

    /**
     * 字符串从GBK编码转换为Unicode编码
     *
     * @param text
     * @return
     */
    public static String StringToUnicode(String text) {
        String result = "";
        int input;
        StringReader isr;
        try {
            isr = new StringReader(new String(text.getBytes(), "GBK"));
        } catch (UnsupportedEncodingException e) {
            return "-1";
        }
        try {
            while ((input = isr.read()) != -1) {
                result = result + "&#x" + Integer.toHexString(input) + ";";

            }
        } catch (IOException e) {
            return "-2";
        }
        isr.close();
        return result;

    }

    /**
     * @param inStr
     * @return
     */
    public static String gb2utf(String inStr) {
        char temChr;
        int ascInt;
        int i;
        String result = new String("");
        if (inStr == null) {
            inStr = "";
        }
        for (i = 0; i < inStr.length(); i++) {
            temChr = inStr.charAt(i);
            ascInt = temChr + 0;
            //System.out.println("1=="+ascInt);
            //System.out.println("1=="+Integer.toBinaryString(ascInt));
            if (Integer.toHexString(ascInt).length() > 2) {
                result = result + "&#x" + Integer.toHexString(ascInt) + ";";
            } else {
                result = result + temChr;
            }

        }
        return result;
    }

    /**
     * This method will encode the String to unicode.
     *
     * @param gbString
     * @return
     */

    //代码:--------------------------------------------------------------------------------
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
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
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
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
                sbuf.append(hex[ch]);
            } else {
                sbuf.append('%');
                sbuf.append('u');
                sbuf.append(hex[(ch >>> 8)]);
                sbuf.append(hex[(0x00FF & ch)]);
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
        StringBuffer sbuf = new StringBuffer();
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
                    cint = (cint << 4) | val[s.charAt(i + 1)];
                    cint = (cint << 4) | val[s.charAt(i + 2)];
                    i += 2;
                } else {
                    cint = (cint << 4) | val[s.charAt(i + 2)];
                    cint = (cint << 4) | val[s.charAt(i + 3)];
                    cint = (cint << 4) | val[s.charAt(i + 4)];
                    cint = (cint << 4) | val[s.charAt(i + 5)];
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

    public static void main(String[] args) {
        String stest = "一声笑傲江湖之曲1234 abcd[]()<+>,.~\"";
        System.out.println(stest);
        System.out.println(encode(stest));
        System.out.println(decode(encode(stest)));
    }
}
