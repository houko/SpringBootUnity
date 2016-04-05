package info.xiaomo.core.controller;

import info.xiaomo.core.model.base.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/4/1 14:06
 * @Description: controller基类
 * @Copyright(©) 2015 by xiaomo.
 **/

public abstract class BaseController<E extends BaseModel> {

    protected static final String code = "code";

    /**
     * 成功
     */
    protected static final int success = 200;

    /**
     * 出错
     */
    protected static final int error = 400;

    /**
     * 找不到
     */
    protected static final int notFound = 404;

    /**
     * 己激活
     */
    protected static final int activated = 303;

    /**
     * 己过期
     */
    protected static final int expired = 304;

    /**
     * 未激活
     */
    protected static final int notActivated = 305;

    /**
     * result
     */
    protected HashMap<String,Object> result = new HashMap<>();


    /**
     * 跨域处理
     * 后端需要加个callback
     * 前端要使用jsoup
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 跨域处理
     *
     * @param response response
     */
    protected void crossDomain(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods", "GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Methods", "POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Methods", "PUT"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Methods", "DELETE"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type"); //允许哪些请求头可以跨域
        response.setContentType("application/json");
    }

}
