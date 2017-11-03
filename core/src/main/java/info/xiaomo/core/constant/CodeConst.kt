package info.xiaomo.core.constant

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
 *
 * Date: 2016/10/29 10:59
 * Description: 错误码
 * Copyright(©) 2015 by xiaomo.
 */
enum class CodeConst {
    /**
     * success
     */
    SUCCESS(200, "成功"),
    NOT_FOUNT(404, "找不到"),
    REPEAT(992, "数据重复"),
    CodeOR(993, "系统错误"),
    ADMIN_USER_REPEAT(994, "后台用户名重复"),
    NULL_DATA(995, "没有数据"),
    TIME_PASSED(996, "时间己过期"),
    USER_NOT_FOUND(997, "找不到用户"),
    USER_REPEAT(998, "用户重复"),
    AUTH_FAILED(999, "用户名或密码错误");


    var resultCode: Int = 0
    var message: String? = null

    constructor(resultCode: Int) {
        this.resultCode = resultCode
    }

    constructor(message: String) {
        this.message = message
    }

    constructor(resultCode: Int, message: String) {
        this.resultCode = resultCode
        this.message = message
    }
}
