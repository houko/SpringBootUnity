package info.xiaomo.core.untils;


import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;


/**
 * https 请求 微信为https的请求
 *
 * @author L.cm
 * @date 2013-10-1 下午2:40:19
 */
public class HttpKit {

    private static final String DEFAULT_CHARSET = "UTF-8"; // 默认字符集

    private static final String _GET = "GET"; // GET
    private static final String _POST = "POST";// POST

    /**
     * 初始化http请求参数
     *
     * @param url
     * @param method
     * @param headers
     * @return
     * @throws IOException
     */
    private static HttpURLConnection initHttp(String url, String method, Map<String, String> headers) throws IOException {
        URL _url = new URL(url);
        HttpURLConnection http = (HttpURLConnection) _url.openConnection();
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
     * 初始化http请求参数
     *
     * @param url
     * @param method
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws KeyManagementException
     */
    private static HttpsURLConnection initHttps(String url, String method, Map<String, String> headers) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象  
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL _url = new URL(url);
        HttpsURLConnection http = (HttpsURLConnection) _url.openConnection();
        // 设置域名校验
        http.setHostnameVerifier(new HttpKit().new TrustAnyHostnameVerifier());
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
        http.setSSLSocketFactory(ssf);
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        return http;
    }

    /**
     * @return 返回类型:
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        StringBuffer bufferRes = null;
        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(initParams(url, params), _GET, headers);
            } else {
                http = initHttp(initParams(url, params), _GET, headers);
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
            if (http != null) {
                http.disconnect();// 关闭连接
            }
            return bufferRes.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return 返回类型:
     * @description 功能描述: get 请求
     */
    public static String get(String url) {
        return get(url, null);
    }

    /**
     * @return 返回类型:
     * @throws UnsupportedEncodingException
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    /**
     * @return 返回类型:
     * @description 功能描述: POST 请求
     */
    public static String post(String url, String params, Map<String, String> headers) {
        StringBuffer bufferRes = null;
        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(url, _POST, headers);
            } else {
                http = initHttp(url, _POST, headers);
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
            if (http != null) {
                http.disconnect();// 关闭连接
            }
            return bufferRes.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post map 请求
     *
     * @param url
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String post(String url, Map<String, String> params) throws UnsupportedEncodingException {
        return post(url, map2Url(params), null);
    }

    /**
     * post map 请求,headers请求头
     *
     * @param url
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String post(String url, Map<String, String> params, Map<String, String> headers) throws UnsupportedEncodingException {
        return post(url, map2Url(params), headers);
    }

    /**
     * @return 返回类型:
     * @throws UnsupportedEncodingException
     * @description 功能描述: 构造请求参数
     */
    public static String initParams(String url, Map<String, String> params) throws UnsupportedEncodingException {
        if (null == params || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") == -1) {
            sb.append("?");
        }
        sb.append(map2Url(params));
        return sb.toString();
    }

    /**
     * map构造url
     *
     * @return 返回类型:
     * @throws UnsupportedEncodingException
     * @description 功能描述:
     */
    public static String map2Url(Map<String, String> paramToMap) throws UnsupportedEncodingException {
        if (null == paramToMap || paramToMap.isEmpty()) {
            return null;
        }
        StringBuffer url = new StringBuffer();
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
     *
     * @param url
     */
    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }

    /**
     * https 域名校验
     *
     * @return
     */
    public class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;// 直接返回true
        }
    }
}

// 证书管理
class MyX509TrustManager implements X509TrustManager {

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }
}