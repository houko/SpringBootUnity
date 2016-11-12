package info.xiaomo.core.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@Data
@ApiModel("返回结果")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result {

    @ApiModelProperty(value  = "返回码")
    private int code;

    @ApiModelProperty(value  = "返回消息")
    private String message;

    @ApiModelProperty(value  = "返回数据")
    private Object data;

    /**
     * 只返回错误码
     * @param code code
     */
    public Result(int code) {
        this.code = code;
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
     * @param code code
     * @param message message
     */
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }


    /**
     * 全部参数
     * @param code code
     * @param message message
     * @param data data
     */
    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
