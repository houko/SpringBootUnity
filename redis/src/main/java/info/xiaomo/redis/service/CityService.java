package info.xiaomo.redis.service;

import info.xiaomo.redis.model.CityInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author : xiaomo
 */
@Component
@CacheConfig(cacheNames = "CityService")
public class CityService {

    @Cacheable
    public CityInfo getCity(int id, String city) {
        return new CityInfo(id, city);
    }
}
