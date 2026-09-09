package info.xiaomo.mongodb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;


/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!

 *
 * @author : xiaomo
 * github: https://github.com/houko
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

    @Schema(description = "登录用户")
    private String email;

    @Schema(description = "昵称")
    private String userName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "盐值")
    private String salt;

    @Schema(description = "激活码")
    private String validateCode;

    @Schema(description = "性别：1男2女0保密")
    private int gender = 0;

    @Schema(description = "电话")
    private Long phone = 0L;

    @Schema(description = "图片地址")
    private String imgUrl = "";

    @Schema(description = "地址")
    private String address = "";

    @Schema(description = "注册时间(时间戳)")
    private Long registerTime = 0L;
}
