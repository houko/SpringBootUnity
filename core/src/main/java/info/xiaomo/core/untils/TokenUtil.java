package info.xiaomo.core.untils;

import com.alibaba.fastjson.JSONObject;
import info.xiaomo.core.constant.Symbol;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Token 帮助类
 *
 * @author L.cm
 *         email: 596392912@qq.com
 *         site:  http://www.dreamlu.net
 * @date Jun 24, 2013 9:58:25 PM
 */
public class TokenUtil {

    private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 参考自 qq sdk
     *
     * @param string string
     * @return String    返回类型
     */
    public static String getAccessToken(String string) {
        String accessToken = "";
        try {
            JSONObject json = JSONObject.parseObject(string);
            if (null != json) {
                accessToken = json.getString("access_token");
            }
        } catch (Exception e) {
            Matcher m = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$").matcher(string);
            if (m.find()) {
                accessToken = m.group(1);
            } else {
                Matcher m2 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$").matcher(string);
                if (m2.find()) {
                    accessToken = m2.group(1);
                } else {
                    String temp = string.split(Symbol.DENGHAO)[1];
                    accessToken = temp.split(Symbol.AND)[0];
                }
            }
        }
        return accessToken;
    }

    /**
     * 匹配openid
     *
     * @return String    返回类型
     */
    public static String getOpenId(String string) {
        String openid = null;
        Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(string);
        if (m.find())
            openid = m.group(1);
        return openid;
    }

    /**
     * sina uid于qq分离
     *
     * @return String    返回类型
     */
    public static String getUid(String string) {
        JSONObject json = JSONObject.parseObject(string);
        return json.getString("uid");
    }

    /**
     * 生成一个随机数
     */
    public static String randomState() {
        return RandomStringUtils.random(24, STR_S);
    }
}
