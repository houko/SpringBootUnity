package info.xiaomo.core.utils;


import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;


/**
 * https 请求 微信为https的请求
 *
 * @author : xiaomo
 */
public class HttpUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String GET = "GET";
    private static final String POST = "POST";

    private final static String USER_COOKIE_KEY = "uid";
    private final static String USER_COOKIE_SECRET = "&#%!&*";

    /**
     * 初始化http请求参数
     *
     * @throws IOException
     */
    private static HttpURLConnection initHttp(String urlStr, String method, Map<String, String> headers) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(25000);
        http.setRequestMethod(method);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
        if (null != headers && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        return http;
    }

    /**
     * 初始化 https 请求参数。证书与主机名校验一律交给 JDK 默认实现。
     *
     * @throws IOException IOException
     */
    private static HttpsURLConnection initHttps(String urlStr, String method, Map<String, String> headers) throws IOException {
        URL url = new URL(urlStr);
        HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
        // 不覆盖 SSLSocketFactory 与 HostnameVerifier, 使用 JDK 默认实现:
        // 默认实现会校验证书链和主机名。此处原先装的是"接受任何证书"的 TrustManager 和
        // "永远返回 true"的 HostnameVerifier, 等于完全关闭了 TLS 校验, 任何中间人都能解密和篡改流量。
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(25000);
        http.setRequestMethod(method);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
        if (null != headers && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        return http;
    }

    /**
     * @return 返回类型:
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        StringBuffer bufferRes = null;
        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(initParams(url, params), GET, headers);
            } else {
                http = initHttp(initParams(url, params), GET, headers);
            }
            InputStream in = http.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
            String valueString = null;
            bufferRes = new StringBuffer();
            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }
            read.close();
            in.close();
            http.disconnect();// 关闭连接
            return bufferRes.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String get(String url) {
        return get(url, null);
    }

    public static String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    public static String post(String url, String params, Map<String, String> headers) {
        StringBuffer bufferRes = null;
        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(url, POST, headers);
            } else {
                http = initHttp(url, POST, headers);
            }
            OutputStream out = http.getOutputStream();
            out.write(params.getBytes(DEFAULT_CHARSET));
            out.flush();
            out.close();

            InputStream in = http.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
            String valueString = null;
            bufferRes = new StringBuffer();
            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }
            read.close();
            in.close();
            http.disconnect();// 关闭连接
            return bufferRes.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post map 请求
     *
     * @throws UnsupportedEncodingException
     */
    public static String post(String url, Map<String, String> params) throws UnsupportedEncodingException {
        return post(url, map2Url(params), null);
    }

    /**
     * post map 请求,headers请求头
     *
     * @throws UnsupportedEncodingException
     */
    public static String post(String url, Map<String, String> params, Map<String, String> headers) throws UnsupportedEncodingException {
        return post(url, map2Url(params), headers);
    }

    /**
     * @return 返回类型:
     * @throws UnsupportedEncodingException
     */
    public static String initParams(String url, Map<String, String> params) throws UnsupportedEncodingException {
        if (null == params || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        String wenhao = "?";
        if (!url.contains(wenhao)) {
            sb.append(wenhao);
        }
        sb.append(map2Url(params));
        return sb.toString();
    }

    /**
     * map构造url
     *
     * @return 返回类型:
     * @throws UnsupportedEncodingException
     */
    public static String map2Url(Map<String, String> paramToMap) throws UnsupportedEncodingException {
        if (null == paramToMap || paramToMap.isEmpty()) {
            return null;
        }
        StringBuilder url = new StringBuilder();
        boolean isfist = true;
        for (Entry<String, String> entry : paramToMap.entrySet()) {
            if (isfist) {
                isfist = false;
            } else {
                url.append("&");
            }
            url.append(entry.getKey()).append("=");
            String value = entry.getValue();
            if (StringUtils.isNotEmpty(value)) {
                url.append(URLEncoder.encode(value, DEFAULT_CHARSET));
            }
        }
        return url.toString();
    }

    /**
     * 检测是否https
     */
    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }

    /**
     * 读取cookie
     *
     * @param request request
     * @param key     key
     */
    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 清除 某个指定的cookie
     *
     * @param response response
     * @param key      key
     */
    public static void removeCookie(HttpServletResponse response, String key) {
        setCookie(response, key, null, 0);
    }

    /**
     * 设置cookie
     *
     * @param response        response
     * @param name            name
     * @param value           value
     * @param maxAgeInSeconds maxAgeInSeconds
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeInSeconds);
        // httpOnly: 禁止 JavaScript 读取, 降低 XSS 窃取 cookie 的风险
        cookie.setHttpOnly(true);
        // secure: 只允许通过 https 发送, 避免明文链路上被嗅探。本地用 http 调试时该 cookie 不会被浏览器回传
        cookie.setSecure(true);
        // 限制跨站发送, 缓解 CSRF
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);
    }

    /**
     * 获取浏览器信息
     *
     * @param request request
     * @return String
     */
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

}