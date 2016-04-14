package info.xiaomo.core.constant;

import info.xiaomo.core.model.base.BaseModel;

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
 * @Date: 2016/4/611:02
 * @Description: 返回码
 * @Copyright(©) 2015 by xiaomo.
 **/
public abstract class Code extends Module {

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
     * 图片格式不对
     */
    protected static final int notImg = 206;

    /**
     * 权限不够
     */
    protected static final int authError = 207;

    /**
     * 找不到
     */
    protected static final int notFound = 404;

}
