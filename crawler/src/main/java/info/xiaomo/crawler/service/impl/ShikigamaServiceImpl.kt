package info.xiaomo.crawler.service.impl

import com.alibaba.fastjson.JSON
import info.xiaomo.crawler.dao.ShikigamaDao
import info.xiaomo.crawler.model.ShikigamiModel
import info.xiaomo.crawler.service.ShikigamaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:54
 */
@Service
class ShikigamaServiceImpl @Autowired
constructor(private val dao: ShikigamaDao) : ShikigamaService {

    override fun findByName(name: String): ShikigamiModel? {
        return dao.findByName(name)
    }

    override fun save(model: ShikigamiModel) {
        val shikigamiModel = dao.findByName(model.name)
        if (shikigamiModel == null) {
            dao.save(model)
            LOGGER.debug("插入数据:{}", JSON.toJSONString(model))
        }
    }

    override fun findAll(): List<ShikigamiModel> {
        return dao.findAll()
    }

    companion object {


        private val LOGGER = org.slf4j.LoggerFactory.getLogger(ShikigamaServiceImpl::class.java)
    }
}
