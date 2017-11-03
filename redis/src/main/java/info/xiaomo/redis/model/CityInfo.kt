package info.xiaomo.redis.model

import java.io.Serializable

/**
 * @author : xiaomo
 */
class CityInfo : Serializable {

    var id: Int = 0
    var city: String? = null

    constructor(id: Int, city: String) {
        this.id = id
        this.city = city
    }

    companion object {
        private const val serialVersionUID = 2845294956907027149L
    }
}
