package info.xiaomo.website.interceptor

import info.xiaomo.website.model.UserModel
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 * Date: 2016/11/21 10:42
 * Copyright(©) 2015 by xiaomo.
 */

class LoginInterceptor : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any): Boolean {
        val user = httpServletRequest.session.getAttribute("currentUser") as UserModel
        if (user == null) {
            //用户没有登录
            httpServletResponse.sendRedirect(httpServletRequest.contextPath + "/user/toLogin")
            return false
        }
        //用户已经登录
        return true

    }

    @Throws(Exception::class)
    override fun postHandle(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any, modelAndView: ModelAndView) {

    }

    @Throws(Exception::class)
    override fun afterCompletion(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any, e: Exception) {

    }
}
