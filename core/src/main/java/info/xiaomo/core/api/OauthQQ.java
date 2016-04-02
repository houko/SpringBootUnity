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
 * OauthQQ
 */
public class OauthQQ extends Oauth {

    private static final Logger LOGGER = LoggerFactory.getLogger(OauthQQ.class);

    private static final String AUTH_URL = "https://graph.qq.com/oauth2.0/authorize";
    private static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
    private static final String TOKEN_INFO_URL = "https://graph.qq.com/oauth2.0/me";
    private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info";

    private static OauthQQ oauthQQ = new OauthQQ();

    public OauthQQ() {
        setClientId(OathConfig.getValue("openid_qq"));
        setClientSecret(OathConfig.getValue("openkey_qq"));
        setRedirectUri(OathConfig.getValue("redirect_qq"));
    }

    /**
     * 用于链式操作
     */
    public static OauthQQ me() {
        return oauthQQ;
    }

    /**
     * 获取授权url
     * DOC：http://wiki.connect.qq.com/%E4%BD%BF%E7%94%A8authorization_code%E8%8E%B7%E5%8F%96access_token
     *
     * @return String    返回类型
     * @throws UnsupportedEncodingException
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
        // access_token=FE04************************CCE2&expires_in=7776000
        String token = TokenUtil.getAccessToken(super.doGet(TOKEN_URL, params));
        LOGGER.debug(token);
        return token;
    }

    /**
     * @return 设定文件
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException              获取TokenInfo
     */
    public String getTokenInfo(String accessToken) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        // callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        String openid = TokenUtil.getOpenId(super.doGet(TOKEN_INFO_URL, params));
        LOGGER.debug(openid);
        return openid;
    }

    /**
     * 获取用户信息
     * DOC：http://wiki.connect.qq.com/get_user_info
     *
     * @param accessToken accessToken
     * @param uid         uid
     * @return 设定文件
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public String getUserInfo(String accessToken, String uid) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        params.put("oauth_consumer_key", getClientId());
        params.put("openid", uid);
        params.put("format", "json");
        return super.doGet(USER_INFO_URL, params);
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
        String openId = getTokenInfo(accessToken);
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        JSONObject dataMap = JSON.parseObject(getUserInfo(accessToken, openId));
        dataMap.put("openid", openId);
        dataMap.put("access_token", accessToken);
        return dataMap;
    }
}
