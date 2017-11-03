package info.xiaomo.website.model


import info.xiaomo.core.base.BaseModel
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

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
 * Date: 2016/4/1 17:36
 * Copyright(©) 2015 by xiaomo.
 */
@Entity
@Table(name = "user")

@EqualsAndHashCode(callSuper = false)

@NoArgsConstructor
class UserModel : BaseModel(), Serializable {

    @Column(name = "Email")
    var email: String? = null
        set(email) {
            field = this.email
        }

    @Column(name = "NickName")
    var nickName: String? = null
        set(nickName) {
            field = this.nickName
        }

    @Column(name = "Password")
    var password: String? = null
        set(password) {
            field = this.password
        }

    @Column(name = "Salt")
    var salt: String? = null
        set(salt) {
            field = this.salt
        }

    @Column(name = "Gender")
    var gender = 0
        set(gender) {
            field = this.gender
        }

    @Column(name = "Phone")
    var phone: Long? = 0L
        set(phone) {
            field = this.phone
        }

    @Column(name = "validateCode")
    var validateCode = ""
        set(validateCode) {
            field = this.validateCode
        }

    @Column(name = "ImgUrl")
    var imgUrl = ""
        set(imgUrl) {
            field = this.imgUrl
        }

    @Column(name = "Address")
    var address = ""
        set(address) {
            field = this.address
        }

    @Column(name = "RegisterTime")
    var registerTime: Long? = 0L
        set(registerTime) {
            field = this.registerTime
        }


}
