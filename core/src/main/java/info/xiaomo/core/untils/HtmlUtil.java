package info.xiaomo.core.untils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 * <p>
 *
 * @author : xiaomo
 * github: https://github.com/syoubaku
 * email: xiaomo@xiamoo.info
 * QQ_NO: 83387856
 * Date: 17/5/11 17:57
 * Description:
 * Copyright(©) 2017 by xiaomo.
 */
public class HtmlUtil {


    /**
     * 是否包含有HTML标签
     *
     * @param str 字符串
     * @return 是否包含html标签
     */
    public static boolean containsHTMLTag(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }

        String pattern = "<\\s*(\\S+)(\\s[^>]*)?>[\\s\\S]*<\\s*/\\1\\s*>";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.find();
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
        if (strSrc == null) {
            return "";
        }
        char[] arrCsrc = strSrc.toCharArray();
        StringBuilder buf = new StringBuilder(arrCsrc.length);
        char ch;
        for (char anArrCSrc : arrCsrc) {
            ch = anArrCSrc;

            if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else if (ch == '"') {
                buf.append("&quot;");
            } else if (ch == '\'') {
                buf.append("&#039;");
            } else if (ch == '&') {
                buf.append("&amp;");
            } else {
                buf.append(ch);
            }
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

        if (strSrc == null) {
            return "";
        }
        if (quotes == 0) {
            return htmlEncode(strSrc);
        }

        char[] arrCsrc = strSrc.toCharArray();
        StringBuilder buf = new StringBuilder(arrCsrc.length);
        char ch;

        for (int i = 0; i < arrCsrc.length; i++) {
            ch = arrCsrc[i];
            if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else if (ch == '"' && quotes == 1) {
                buf.append("&quot;");
            } else if (ch == '\'' && quotes == 2) {
                buf.append("&#039;");
            } else if (ch == '&') {
                buf.append("&amp;");
            } else {
                buf.append(ch);
            }
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
        if (strSrc == null) {
            return "";
        }
        strSrc = strSrc.replaceAll("&lt;", "<");
        strSrc = strSrc.replaceAll("&gt;", ">");
        strSrc = strSrc.replaceAll("&quot;", "\"");
        strSrc = strSrc.replaceAll("&#039;", "'");
        strSrc = strSrc.replaceAll("&amp;", "&");
        return strSrc;
    }

    /**
     * 去除html tag
     *
     * @param htmlStr html
     * @return string
     */
    public static String delHTMLTag(String htmlStr) {
        //定义script的正则表达式
        String regexScript = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式
        String regexStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式
        String regexHtml = "<[^>]+>";

        Pattern pScript = Pattern.compile(regexScript, Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(htmlStr);
        //过滤script标签
        htmlStr = mScript.replaceAll("");

        Pattern pStyle = Pattern.compile(regexStyle, Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(htmlStr);
        //过滤style标签
        htmlStr = mStyle.replaceAll("");

        Pattern pHtml = Pattern.compile(regexHtml, Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(htmlStr);
        //过滤html标签
        htmlStr = mHtml.replaceAll("");
        //返回文本字符串
        return htmlStr.trim();
    }

}
