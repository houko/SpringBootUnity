package info.xiaomo.core.controller;

import info.xiaomo.core.model.base.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 己激活
     */
    protected static final int activated = 201;
    /**
     * 己过期
     */
    protected static final int expired = 202;
    /**
     * 未激活
     */
    protected static final int notActivated = 203;
    /**
     * 重复
     */
    protected static final int repeat = 204;

    /**
     * 出错
     */
    protected static final int error = 205;

    /**
     * 找不到
     */
    protected static final int notFound = 404;
    /**
     * 跨域处理
     * 后端需要加个callback
     * 前端要使用jsoup
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * result
     */
    protected HashMap<String, Object> result = new HashMap<>();

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

    /**
     * 查
     *
     * @param id id
     * @return return
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public HashMap<String, Object> findById(@RequestParam("id") Long id) {
        return result;
    }

    /**
     * 分页查询
     *
     * @param start    start
     * @param pageSize pageSize
     * @return
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public HashMap<String, Object> RequestParam(@RequestParam("start") int start, @RequestParam("pageSize") int pageSize) {
        return result;
    }

    /**
     * 修改
     *
     * @param params params
     * @return result
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public HashMap<String, Object> update(@RequestParam String params) {
        return result;
    }

    /**
     * 增加
     *
     * @param params params
     * @return result
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public HashMap<String, Object> add(@RequestParam String params) {
        return result;
    }

    /**
     * 查
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "deleteById/", method = RequestMethod.GET)
    public HashMap<String, Object> deleteById(@RequestParam("id") Long id) {
        return result;
    }

}
