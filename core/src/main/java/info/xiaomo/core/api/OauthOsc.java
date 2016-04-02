package info.xiaomo.core.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import info.xiaomo.core.untils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

/**
 * Oauth osc
 */
public class OauthOsc extends Oauth {

    private static final Logger LOGGER = LoggerFactory.getLogger(OauthOsc.class);

    private static final String AUTH_URL = "http://www.oschina.net/action/oauth2/authorize"; // 授权
    private static final String TOKEN_URL = "http://www.oschina.net/action/openapi/token"; // tonken
    private static final String USER_INFO_URL = "http://www.oschina.net/action/openapi/user"; // 用户信息
    private static final String TWEET_PUB = "http://www.oschina.net/action/openapi/tweet_pub"; // 动弹

    private static OauthOsc oauthOsc = new OauthOsc();

    public OauthOsc() {
        setClientId(OathConfig.getValue("openid_osc"));
        setClientSecret(OathConfig.getValue("openkey_osc"));
        setRedirectUri(OathConfig.getValue("redirect_osc"));
    }

    /**
     * 用于链式操作
     */
    public static OauthOsc me() {
        return oauthOsc;
    }

    /**
     * @return String    返回类型
     * @throws UnsupportedEncodingException 获取授权url
     */
    public String getAuthorizeUrl(String state) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        params.put("response_type", "code");
        params.put("client_id", getClientId());
        params.put("redirect_uri", getRedirectUri());
        if (StringUtils.isNotBlank(state)) {
            params.put("state", state); //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
        }
        return super.getAuthorizeUrl(AUTH_URL, params);
    }

    /**
     * @return String    返回类型
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException   获取token
     */
    public String getTokenByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", getClientId());
        params.put("client_secret", getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", getRedirectUri());
        String token = TokenUtil.getAccessToken(super.doGet(TOKEN_URL, params));
        LOGGER.debug(token);
        return token;
    }

    /**
     * 获取用户信息
     *
     * @param accessToken accessToken
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public JSONObject getUserInfo(String accessToken) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String userInfo = super.doGet(USER_INFO_URL, params);
        JSONObject dataMap = JSON.parseObject(userInfo);
        LOGGER.debug(dataMap.toJSONString());
        return dataMap;
    }

    /**
     * 发送文字动弹
     *
     * @param accessToken accessToken
     * @param msg         msg
     * @return JSONObject
     */
    public JSONObject tweetPub(String accessToken, String msg) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("msg", msg);
        try {
            JSONObject dataMap = JSON.parseObject(super.doPost(TWEET_PUB, params));
            System.out.println(dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return void    返回类型
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException   根据code一步获取用户信息
     */
    public JSONObject getUserInfoByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        String accessToken = getTokenByCode(code);
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }
        JSONObject dataMap = getUserInfo(accessToken);
        dataMap.put("access_token", accessToken);
        return dataMap;
    }
}
