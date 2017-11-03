package info.xiaomo.website.model


import info.xiaomo.core.base.BaseModel
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
 * Date: 2016/11/3 14:15
 * Copyright(©) 2015 by xiaomo.
 */

// hibernate
@Entity
@Table(name = "works")
class WorksModel : BaseModel() {

    @Column(name = "Url")
    var url: String? = null
        set(url) {
            field = this.url
        }

    @Column(name = "Summary")
    var summary: String? = null
        set(summary) {
            field = this.summary
        }

    @Column(name = "CompleteTime")
    var completeTime: String? = null
        set(completeTime) {
            field = this.completeTime
        }

    @Column(name = "ImgUrl")
    var imgUrl: String? = null
        set(imgUrl) {
            field = this.imgUrl
        }
}
