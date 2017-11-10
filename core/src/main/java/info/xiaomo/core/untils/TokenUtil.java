package info.xiaomo.core.untils;

import com.alibaba.fastjson.JSONObject;
import info.xiaomo.core.constant.SymbolConst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Token 帮助类
 *
 * @author : xiaomo
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
            String regex = "^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$";
            Matcher m = Pattern.compile(regex).matcher(string);
            if (m.find()) {
                accessToken = m.group(1);
            } else {
                String regex1 = "^access_token=(\\w+)&expires_in=(\\w+)$";
                Matcher m2 = Pattern.compile(regex1).matcher(string);
                if (m2.find()) {
                    accessToken = m2.group(1);
                } else {
                    String temp = string.split(SymbolConst.DENGHAO)[1];
                    accessToken = temp.split(SymbolConst.AND)[0];
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
        String regex = "\"openid\"\\s*:\\s*\"(\\w+)\"";
        Matcher m = Pattern.compile(regex).matcher(string);
        if (m.find()) {
            openid = m.group(1);
        }
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
}
