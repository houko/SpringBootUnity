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
 * Date: 2016/4/1119:47
 * Copyright(©) 2015 by xiaomo.
 */
@Entity
@Table(name = "link")
// lomlok

@EqualsAndHashCode(callSuper = false)

@NoArgsConstructor
class LinkModel : BaseModel(), Serializable {
    @Column(name = "Url")
    var url: String? = null
        set(url) {
            field = this.url
        }
}
