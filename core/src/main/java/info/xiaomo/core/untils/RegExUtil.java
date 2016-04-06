package info.xiaomo.core.untils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.*;

/**
 * 这是个正则表达式应用类，用来匹配和替换字串用的
 * @author
 * @version
 */

public class RegExUtil {

  /**
   * 用户名
   */
  public static final String USER_NAME = "^[a-zA-Z\\u4E00-\\u9FA5][a-zA-Z0-9_\\u4E00-\\u9FA5]{1,11}$";

  /**
   * 密码
   */
  public static final String USER_PASSWORD = "^.{6,32}$";

  /**
   * 邮箱
   */
  public static final String EMAIL = "^\\w+([-+.]*\\w+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$";

  /**
   * 手机号
   */
  public static final String PHONE = "^1[34578]\\d{9}$";

  /**
   * 手机号或者邮箱
   */
  public static final String EMAIL_OR_PHONE = EMAIL + "|" + PHONE;

  /**
   * URL路径
   */
  public static final String URL = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})(:[\\d]+)?([\\/\\w\\.-]*)*\\/?$";

  /**
   * 身份证校验，初级校验，具体规则有一套算法
   */
  public static final String ID_CARD = "^\\d{15}$|^\\d{17}([0-9]|X)$";

  /**
   * 编译传入正则表达式和字符串去匹配,忽略大小写
   *
   * @param regex        regex
   * @param beTestString beTestString
   */
  public static boolean match(String regex, String beTestString) {
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(beTestString);
    return matcher.matches();
  }

  /**
   * 编译传入正则表达式在字符串中寻找，如果匹配到则为true
   *
   * @param regex        regex
   * @param beTestString beTestString
   */
  public static boolean find(String regex, String beTestString) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(beTestString);
    return matcher.find();
  }

  /**
   * 编译传入正则表达式在字符串中寻找，如果找到返回第一个结果<br/>
   * 找不到返回null
   *
   * @param regex         regex
   * @param beFoundString beFoundString
   */
  public static String findResult(String regex, String beFoundString) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(beFoundString);
    if (matcher.find()) {
      return matcher.group();
    }
    return null;
  }

  /**
   * 隐藏手机号中间4位
   *
   * @param phone phone
   * @return String
   */
  public static String encodePhone(String phone) {
    if (StringUtils.isBlank(phone)) {
      return "";
    }
    if (match(PHONE, phone)) {
      String begin = phone.substring(0, 3);
      String end = phone.substring(7, phone.length());
      return begin + "****" + end;
    }
    return phone;
  }

  /**
   * 要求大小写都匹配正则表达式
   * @param pattern 正则表达式模式
   * @param str 要匹配的字串
   * @return boolean值
   * @since  1.0
   */
  public static final boolean ereg(String pattern, String str)  throws PatternSyntaxException
  {
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      return m.find();
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }

  /**
   * 匹配且替换字串
   * @param pattern 正则表达式模式
   * @param newstr 要替换匹配到的新字串
   * @param str 原始字串
   * @return 匹配后的字符串
   * @since  1.0
   */

  public static final String ereg_replace(String pattern, String newstr, String str)  throws PatternSyntaxException
  {
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      return m.replaceAll(newstr);
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }

  /**
   * 主要用于模板中模块标记分析函数 把查找到的元素加到vector中
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @return vector
   * @since  1.0
   */
  public static final Vector splitTags2Vector(String pattern, String str) throws PatternSyntaxException
  {
    Vector vector = new Vector();
    try {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      while (m.find())
      {
        vector.add(ereg_replace("(\\[\\#)|(\\#\\])", "", m.group()));
      }
      return vector;
    }
    catch (PatternSyntaxException e) {
      throw e;
    }
  }
  /**
   * 模块标记分析函数
   * 功能主要是把查找到的元素加到vector中
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @since  1.0
   */
  public static final String[] splitTags(String pattern, String str)
  {
    try {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      String[] array = new String[m.groupCount()];
      int i = 0;
      while (m.find())
      {
        array[i] = ereg_replace("(\\[\\#)|(\\#\\])", "", m.group());
        i++;
      }
      return array;
    }
    catch (PatternSyntaxException e) {
      throw e;
    }
  }


  /**
   * 匹配所有符合模式要求的字串并加到矢量vector数组中
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @return vector
   * @since  1.0
   */
  public static final Vector regMatchAll2Vector(String pattern, String str) throws PatternSyntaxException
  {
    Vector vector = new Vector();
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      while (m.find())
      {
        vector.add(m.group());
      }
      return vector;
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }

  /**
   * 匹配所有符合模式要求的字串并加到字符串数组中
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @return array
   * @since  1.0
   */
  public static final String[] regMatchAll2Array(String pattern, String str) throws PatternSyntaxException
  {
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      String[] array = new String[m.groupCount()];
      int i = 0;
      while (m.find())
      {
        array[i] = m.group();
        i++;
      }
      return array;
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }
  /**
   * 转义正则表达式字符(之所以需要将\和$字符用escapeDollarBackslash方法的方式是因为用repalceAll是不行的，简单的试试"$".repalceAll("\\$","\\\\$")你会发现这个调用会导致数组越界错误)
   * @param pattern为正则表达式模式
   * @param str 原始字串
   * @return array
   * @since  1.0
   */
  public static String escapeDollarBackslash(String original) {
    StringBuffer buffer=new StringBuffer(original.length());
    for (int i=0;i<original.length();i++) {
      char c=original.charAt(i);
      if (c=='\\'||c=='$') {
        buffer.append("\\").append(c);
      } else{
        buffer.append(c);
      }
    }
    return buffer.toString();
  }

  /**
   * 提取指定字串的函数
   * 功能主要是把查找到的元素
   * @param pattern 为正则表达式模式
   * @param str 原始字串
   * @since  1.0
   */
  public static String fetchStr(String pattern, String str) {
    String returnValue = null;
    try {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      while (m.find()) {
        returnValue = m.group();
      }
      return returnValue;
    } catch (PatternSyntaxException e) {
      return returnValue;
    }
  }
}
