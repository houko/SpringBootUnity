package info.xiaomo.reader.model;


import info.xiaomo.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * author: xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info

 * Date: 2016/4/1 17:36
 * Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "user")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户实体类")
public class UserModel extends BaseModel implements Serializable {

    @ApiModelProperty(value = "登录用户")
    @Column(name = "Email")
    private String email;

    @ApiModelProperty(value = "昵称")
    @Column(name = "Name")
    private String name;

    @ApiModelProperty(value = "密码")
    @Column(name = "Password")
    private String password;

    @ApiModelProperty(value = "盐值")
    @Column(name = "Salt")
    private String salt;

}
