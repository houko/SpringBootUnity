package info.xiaomo.core.base

import java.io.Serializable
import java.util.*
import javax.persistence.*

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
 * Date: 2016/4/1 20:37
 * Copyright(©) 2015 by xiaomo.
 */

@MappedSuperclass
abstract class BaseModel : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    var id: Long? = null
        set(id) {
            field = this.id
        }

    @Column(name = "Name")
    var name: String? = null
        set(name) {
            field = this.name
        }

    @Column(name = "CreateTime")
    var createTime: Date? = null
        set(createTime) {
            field = this.createTime
        }

    @Column(name = "UpdateTime")
    var updateTime: Date? = null
        set(updateTime) {
            field = this.updateTime
        }
}
