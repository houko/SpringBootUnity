package info.xiaomo.website.model


import info.xiaomo.core.base.BaseModel
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

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
 *
 * Date: 16/4/2 12:39
 * Copyright(©) 2015 by xiaomo.
 */
@Entity
@Table(name = "adminUser")
class AdminModel : BaseModel(), Serializable {

    @Column(name = "UserName")
    var userName: String? = null
        set(userName) {
            field = this.userName
        }

    @Column(name = "Password")
    var password: String? = null
        set(password) {
            field = this.password
        }

    @Column(name = "Status")
    var status = 1
        set(status) {
            field = this.status
        }

    @Column(name = "Salt")
    var salt: String? = null
        set(salt) {
            field = this.salt
        }

}
