package info.xiaomo.core.untils


import org.apache.commons.lang3.StringUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * https 请求 微信为https的请求
 * @author : xiaomo
 */
class HttpUtil {

    /**
     * https 域名校验
     */
    inner class TrustAnyHostnameVerifier : HostnameVerifier {
        override fun verify(hostname: String, session: SSLSession): Boolean {
            return true
        }
    }

    companion object {

        private val DEFAULT_CHARSET = "UTF-8"

        private val GET = "GET"
        private val POST = "POST"

        private val USER_COOKIE_KEY = "uid"
        private val USER_COOKIE_SECRET = "&#%!&*"

        /**
         * 初始化http请求参数
         *
         * @throws IOException
         */
        @Throws(IOException::class)
        private fun initHttp(urlStr: String, method: String, headers: Map<String, String>?): HttpURLConnection {
            val url = URL(urlStr)
            val http = url.openConnection() as HttpURLConnection
            // 连接超时
            http.connectTimeout = 25000
            // 读取超时 --服务器响应比较慢，增大时间
            http.readTimeout = 25000
            http.requestMethod = method
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36")
            if (null != headers && !headers.isEmpty()) {
                for ((key, value) in headers) {
                    http.setRequestProperty(key, value)
                }
            }
            http.doOutput = true
            http.doInput = true
            http.connect()
            return http
        }

        /**
         * 初始化http请求参数
         *
         * @throws IOException
         * @throws NoSuchAlgorithmException
         * @throws NoSuchProviderException
         * @throws KeyManagementException
         */
        @Throws(IOException::class, NoSuchAlgorithmException::class, NoSuchProviderException::class, KeyManagementException::class)
        private fun initHttps(urlStr: String, method: String, headers: Map<String, String>?): HttpsURLConnection {
            val tm = arrayOf<TrustManager>(MyX509TrustManager())
            val sslContext = SSLContext.getInstance("SSL", "SunJSSE")
            sslContext.init(null, tm, java.security.SecureRandom())
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            val ssf = sslContext.socketFactory
            val url = URL(urlStr)
            val http = url.openConnection() as HttpsURLConnection
            // 设置域名校验
            http.hostnameVerifier = HttpUtil().TrustAnyHostnameVerifier()
            // 连接超时
            http.connectTimeout = 25000
            // 读取超时 --服务器响应比较慢，增大时间
            http.readTimeout = 25000
            http.requestMethod = method
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36")
            if (null != headers && !headers.isEmpty()) {
                for ((key, value) in headers) {
                    http.setRequestProperty(key, value)
                }
            }
            http.sslSocketFactory = ssf
            http.doOutput = true
            http.doInput = true
            http.connect()
            return http
        }

        /**
         * @return 返回类型:
         */
        @JvmOverloads operator fun get(url: String, params: Map<String, String>? = null, headers: Map<String, String>? = null): String? {
            var bufferRes: StringBuffer?
            try {
                var http: HttpURLConnection?
                if (isHttps(url)) {
                    http = initHttps(initParams(url, params), GET, headers)
                } else {
                    http = initHttp(initParams(url, params), GET, headers)
                }
                val `in` = http.inputStream
                val read = BufferedReader(InputStreamReader(`in`, DEFAULT_CHARSET))
                val valueString: String? = null
                bufferRes = StringBuffer()
                while ((valueString == read.readLine()) != null) {
                    bufferRes.append(valueString)
                }
                read.close()
                `in`.close()
                http.disconnect()// 关闭连接
                return bufferRes.toString()
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

        }

        fun post(url: String, params: String?, headers: Map<String, String>?): String? {
            var bufferRes: StringBuffer? = null
            try {
                var http: HttpURLConnection? = null
                if (isHttps(url)) {
                    http = initHttps(url, POST, headers)
                } else {
                    http = initHttp(url, POST, headers)
                }
                val out = http.outputStream
                out.write(params!!.toByteArray(charset(DEFAULT_CHARSET)))
                out.flush()
                out.close()

                val `in` = http.inputStream
                val read = BufferedReader(InputStreamReader(`in`, DEFAULT_CHARSET))
                val valueString: String? = null
                bufferRes = StringBuffer()
                while ((valueString == read.readLine())) {
                    bufferRes.append(valueString)
                }
                read.close()
                `in`.close()
                http.disconnect()// 关闭连接
                return bufferRes.toString()
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

        }

        /**
         * post map 请求
         *
         * @throws UnsupportedEncodingException
         */
        @Throws(UnsupportedEncodingException::class)
        fun post(url: String, params: Map<String, String>): String? {
            return post(url, map2Url(params), null)
        }

        /**
         * post map 请求,headers请求头
         *
         * @throws UnsupportedEncodingException
         */
        @Throws(UnsupportedEncodingException::class)
        fun post(url: String, params: Map<String, String>, headers: Map<String, String>): String? {
            return post(url, map2Url(params), headers)
        }

        /**
         * @return 返回类型:
         * @throws UnsupportedEncodingException
         */
        @Throws(UnsupportedEncodingException::class)
        fun initParams(url: String, params: Map<String, String>?): String {
            if (null == params || params.isEmpty()) {
                return url
            }
            val sb = StringBuilder(url)
            val questionMark = "?"
            if (!url.contains(questionMark)) {
                sb.append(questionMark)
            }
            sb.append(map2Url(params))
            return sb.toString()
        }

        /**
         * map构造url
         *
         * @return 返回类型:
         * @throws UnsupportedEncodingException
         */
        @Throws(UnsupportedEncodingException::class)
        fun map2Url(paramToMap: Map<String, String>?): String? {
            if (null == paramToMap || paramToMap.isEmpty()) {
                return null
            }
            val url = StringBuilder()
            var isfist = true
            for ((key, value) in paramToMap) {
                if (isfist) {
                    isfist = false
                } else {
                    url.append("&")
                }
                url.append(key).append("=")
                if (StringUtils.isNotEmpty(value)) {
                    url.append(URLEncoder.encode(value, DEFAULT_CHARSET))
                }
            }
            return url.toString()
        }

        /**
         * 检测是否https
         */
        private fun isHttps(url: String): Boolean {
            return url.startsWith("https")
        }

        /**
         * 读取cookie
         *
         * @param request request
         * @param key     key
         */
        fun getCookie(request: HttpServletRequest, key: String): String? {
            val cookies = request.cookies
            cookies?.filter { key == it.name }?.forEach { return it.value }
            return null
        }

        /**
         * 清除 某个指定的cookie
         *
         * @param response response
         * @param key      key
         */
        fun removeCookie(response: HttpServletResponse, key: String) {
            setCookie(response, key, null, 0)
        }

        /**
         * 设置cookie
         *
         * @param response        response
         * @param name            name
         * @param value           value
         * @param maxAgeInSeconds maxAgeInSeconds
         */
        private fun setCookie(response: HttpServletResponse, name: String, value: String?, maxAgeInSeconds: Int) {
            val cookie = Cookie(name, value)
            cookie.path = "/"
            cookie.maxAge = maxAgeInSeconds
            // 指定为httpOnly保证安全性
            cookie.isHttpOnly = true
            response.addCookie(cookie)
        }

        /**
         * 获取浏览器信息
         *
         * @param request request
         * @return String
         */
        fun getUserAgent(request: HttpServletRequest): String {
            return request.getHeader("User-Agent")
        }
    }


}

internal class MyX509TrustManager : X509TrustManager {

    override fun getAcceptedIssuers(): Array<X509Certificate>? {
        return null
    }

    @Throws(CertificateException::class)
    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
    }

    @Throws(CertificateException::class)
    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
    }
}