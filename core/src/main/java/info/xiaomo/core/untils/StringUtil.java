package info.xiaomo.core.untils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
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
            sb.append(CharKit.regularize(string.charAt(i)));
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
}
