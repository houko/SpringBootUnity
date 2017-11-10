package info.xiaomo.mongodb.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;


/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/11/15 15:39
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 **/

@Data
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MongoUser {

    @Id
    private int id;

    @ApiModelProperty(value = "登录用户")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "激活码")
    private String validateCode;

    @ApiModelProperty(value = "性别：1男2女0保密")
    private int gender = 0;

    @ApiModelProperty(value = "电话")
    private Long phone = 0L;

    @ApiModelProperty(value = "图片地址")
    private String imgUrl = "";

    @ApiModelProperty(value = "地址")
    private String address = "";

    @ApiModelProperty(value = "注册时间(时间戳)")
    private Long registerTime = 0L;
}
