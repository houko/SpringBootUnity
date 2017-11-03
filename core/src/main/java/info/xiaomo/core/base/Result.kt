package info.xiaomo.core.base

import lombok.Data

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
 * Date: 2016/10/31 15:25
 * Description: 返回结果
 * Copyright(©) 2015 by xiaomo.
 */
@Data
class Result<T> {

    private var resultCode = 200

    private var message = "SUCCESS"

    private var data: T? = null

    /**
     * 只返回错误码
     *
     * @param resultCode resultCode
     */
    constructor(resultCode: Int) {
        this.resultCode = resultCode
    }

    /**
     * 只有返回数据的(验证成功)
     *
     * @param data data
     */
    constructor(data: T) {
        this.data = data
    }

    /**
     * 只有错误码和错误信息的
     *
     * @param resultCode resultCode
     * @param message    message
     */
    constructor(resultCode: Int, message: String) {
        this.resultCode = resultCode
        this.message = message
    }


    /**
     * 全部参数
     *
     * @param resultCode resultCode
     * @param message    message
     * @param data       data
     */
    constructor(resultCode: Int, message: String, data: T) {
        this.resultCode = resultCode
        this.message = message
        this.data = data
    }
}
