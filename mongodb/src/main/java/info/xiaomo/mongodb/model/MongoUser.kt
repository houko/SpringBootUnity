package info.xiaomo.mongodb.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id


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
 * Date: 2016/11/15 15:39
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 */

class MongoUser {

    @Id
    private val id: Int = 0

    @ApiModelProperty(value = "登录用户")
    private val email: String? = null

    @ApiModelProperty(value = "昵称")
    private val userName: String? = null

    @ApiModelProperty(value = "密码")
    private val password: String? = null

    @ApiModelProperty(value = "盐值")
    private val salt: String? = null

    @ApiModelProperty(value = "激活码")
    private val validateCode: String? = null

    @ApiModelProperty(value = "性别：1男2女0保密")
    private val gender = 0

    @ApiModelProperty(value = "电话")
    private val phone = 0L

    @ApiModelProperty(value = "图片地址")
    private val imgUrl = ""

    @ApiModelProperty(value = "地址")
    private val address = ""

    @ApiModelProperty(value = "注册时间(时间戳)")
    private val registerTime = 0L
}
