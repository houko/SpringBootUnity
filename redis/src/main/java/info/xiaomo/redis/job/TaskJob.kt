package info.xiaomo.redis.job

import com.alibaba.fastjson.JSON
import info.xiaomo.redis.service.CityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author : xiaomo
 */
@Component
class TaskJob @Autowired
constructor(private val cityService: CityService) {

    /**
     * Job
     */
    @Scheduled(fixedDelay = 5000)
    fun retrieveCountry() {
        val index = Random().nextInt(LIST.size)
        val city = find(index)
        val info = cityService.getCity(index, city)
        LOGGER.debug("{}", JSON.toJSONString(info))
    }

    private fun find(index: Int): String {
        return LIST[index]
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(TaskJob::class.java)
        private val LIST = Arrays.asList("北京市", "上海市", "天津市", "重庆市", "河北省", "山西省", "内蒙古自治区", "辽宁省",
                "吉林省", "黑龙江", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西自治区", "海南省", "四川省",
                "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省", "青海省", "宁夏自治区", "新疆自治区", "香港特别行政区", "澳门特别行政区", "台湾省")
    }
}
