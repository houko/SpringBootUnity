package info.xiaomo.website.model;


import info.xiaomo.core.base.BaseModel;
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
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 16/4/2 12:39
 * Copyright(©) 2015 by xiaomo.
 */
@Entity
@Table(name = "adminUser")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AdminModel extends BaseModel implements Serializable {

    @Column(name = "UserName")
    private String userName;

    @Column(name = "Password")
    private String password;

    @Column(name = "Status")
    private int status = 1;

    @Column(name = "Salt")
    private String salt;

}
