package info.xiaomo.core.controller;

import info.xiaomo.core.constant.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public abstract class BaseController extends Code {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * result
     */
    protected HashMap<String, Object> result = new HashMap<>();

}
