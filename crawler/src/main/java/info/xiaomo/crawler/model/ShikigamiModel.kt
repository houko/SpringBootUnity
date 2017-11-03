package info.xiaomo.crawler.model

import info.xiaomo.core.base.BaseModel
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.ToString
import javax.persistence.Entity
import javax.persistence.Table

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:09
 */

@Entity
@Table(name = "shikigame")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
class ShikigamiModel : BaseModel() {

    /**
     * 图片
     */
    var image: String? = null
        set(image) {
            field = this.image
        }

    /**
     * 声优
     */
    var seiyou: String? = null
        set(seiyou) {
            field = this.seiyou
        }


    /**
     * 性别
     */
    var sex: String? = null
        set(sex) {
            field = this.sex
        }

    /**
     * 星级
     */
    var star: String? = null
        set(star) {
            field = this.star
        }

    /**
     * 获取方式
     */
    var getWay: String? = null
        set(getWay) {
            field = this.getWay
        }


    /**
     * N/R/SR/SSR
     */
    var level: String? = null
        set(level) {
            field = this.level
        }


    /**
     * 描述
     */
    var des: String? = null
        set(des) {
            field = this.des
        }


}
