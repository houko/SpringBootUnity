package info.xiaomo.website.model;


import info.xiaomo.core.base.BaseModel;
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
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/1 17:36
 * Copyright(©) 2015 by xiaomo.
 **/
@Entity
@Table(name = "user")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends BaseModel implements Serializable {

    @Column(name = "Email")
    private String email;

    @Column(name = "NickName")
    private String nickName;

    @Column(name = "Password")
    private String password;

    @Column(name = "Salt")
    private String salt;

    @Column(name = "Gender")
    private int gender = 0;

    @Column(name = "Phone")
    private Long phone = 0L;

    @Column(name = "ValidateCode")
    private String validateCode = "";

    @Column(name = "ImgUrl")
    private String imgUrl = "";

    @Column(name = "Address")
    private String address = "";

    @Column(name = "RegisterTime")
    private Long registerTime = 0L;


}
