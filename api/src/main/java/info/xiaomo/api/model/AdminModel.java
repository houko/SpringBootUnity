package info.xiaomo.api.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import info.xiaomo.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 16/4/2 12:39
 * @Copyright(©) 2015 by xiaomo.
 */
@Entity
@Table(name = "adminUser")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
// fast jackson
@JsonInclude(JsonInclude.Include.NON_EMPTY)
// swagger ui
@ApiModel(value = "后台用户实体类")
public class AdminModel extends BaseModel implements Serializable {

    @ApiModelProperty(value = "用户名", required = true)
    @Column(name = "UserName")
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    @Column(name = "Password")
    private String password;

    @ApiModelProperty(value = "状态1正常2异常")
    @Column(name = "Status")
    private int status = 1;

    @ApiModelProperty(value = "盐值")
    @Column(name = "Salt")
    private String salt;

}
