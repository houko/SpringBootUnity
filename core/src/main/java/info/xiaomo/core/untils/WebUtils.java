package info.xiaomo.core.untils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web相关工具类
 *
 * @author L.cm
 *         email: 596392912@qq.com
 *         site:http://www.dreamlu.net
 *         date 2015年7月5日下午7:48:48
 */
public final class WebUtils {

    private final static String USER_COOKIE_KEY = "uid";
    private final static String USER_COOKIE_SECRET = "&#%!&*";

    private WebUtils() {
    }

//    /**
//     * 返回当前用户信息
//     *
//     * @param c c
//     * @return UserModel
//     */
//    public static User currentUser(Controller c) {
//        HttpServletRequest request = c.getRequest();
//        HttpServletResponse response = c.getResponse();
//        return currentUser(request, response);
//    }

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
        // 指定为httpOnly保证安全性
        cookie.setHttpOnly(true);
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
