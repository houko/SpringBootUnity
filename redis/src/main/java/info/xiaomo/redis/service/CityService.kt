package info.xiaomo.redis.service

import info.xiaomo.redis.model.CityInfo
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

/**
 * @author : xiaomo
 */
@Component
@CacheConfig(cacheNames = arrayOf("CityService"))
class CityService {

    @Cacheable
    fun getCity(id: Int, city: String): CityInfo {
        return CityInfo(id, city)
    }
}
