package info.xiaomo.core.constant;

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
 * @Date: 2016/10/29 10:59
 * @Description: 错误码
 * @Copyright(©) 2015 by xiaomo.
 */
public enum Err {
    SUCCESS(200, "成功"),
    NOT_FOUNT(404, "找不到"),
    REPEAT(992, "数据重复"),
    ERROR(993, "系统错误"),
    ADMIN_USER_REPEAT(994, "后台用户名重复"),
    NULL_DATA(995, "没有数据"),
    TIME_PASSED(996, "时间己过期"),
    USER_NOT_FOUND(997, "找不到用户"),
    USER_REPEAT(998, "用户重复"),
    AUTH_FAILED(999, "用户名或密码错误");


    private int resultCode;
    private String message;

    Err(int resultCode) {
        this.resultCode = resultCode;
    }

    Err(String message) {
        this.message = message;
    }

    Err(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
