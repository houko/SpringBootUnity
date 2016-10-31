package info.xiaomo.core.controller;

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
 * @Date: 2016/10/31 15:25
 * @Description: 返回结果
 * @Copyright(©) 2015 by xiaomo.
 */
public class Result {

    private int errorCode;
    private String errorMsg;
    private Object data;

    /**
     * 只返回错误码
     * @param errorCode errorCode
     */
    public Result(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 只有返回数据的(验证成功)
     * @param data data
     */
    public Result(Object data) {
        this.data = data;
    }

    /**
     * 只有错误码和错误信息的
     * @param errorCode errorCode
     * @param errorMsg errorMsg
     */
    public Result(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    /**
     * 全部参数
     * @param errorCode errorCode
     * @param errorMsg errorMsg
     * @param data data
     */
    public Result(int errorCode, String errorMsg, Object data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
