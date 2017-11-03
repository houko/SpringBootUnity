package info.xiaomo.core.filter

import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
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
 * Date: 2016/4/1516:25
 * Description:
 * Copyright(©) 2015 by xiaomo.
 */
@Component
class CorsFilter : Filter {


    /**
     * 初始化
     *
     * @param filterConfig filterConfig
     * @throws ServletException ServletException
     */
    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {

    }

    /**
     * CORS 过滤器
     *
     * @param req   rq
     * @param res   res
     * @param chain chin
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val response = res as HttpServletResponse
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, If-Modified-Since")
        chain.doFilter(req, res)
    }


    /**
     * 销毁对象
     */
    override fun destroy() {}
}
