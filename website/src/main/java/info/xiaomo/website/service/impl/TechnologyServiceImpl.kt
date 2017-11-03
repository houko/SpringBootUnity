package info.xiaomo.website.service.impl

import info.xiaomo.website.dao.TechnologyDao
import info.xiaomo.website.model.TechnologyModel
import info.xiaomo.website.service.TechnologyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

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
 * Date: 2016/11/3 14:34
 * Copyright(©) 2015 by xiaomo.
 */

@Service
class TechnologyServiceImpl @Autowired
constructor(private val dao: TechnologyDao) : TechnologyService {

    override fun findAll(): List<TechnologyModel> {
        return dao.findAll()
    }

    override fun findAll(start: Int, pageSize: Int): Page<TechnologyModel> {
        return dao.findAll(PageRequest(start - 1, pageSize))
    }

    override fun findById(id: Long?): TechnologyModel {
        return dao.findOne(id)
    }

    override fun findByName(name: String): TechnologyModel {
        return dao.findTechnologyByName(name)
    }

    override fun update(model: TechnologyModel): TechnologyModel {
        val result = dao.findOne(model.id)
        if ("" == model.url && model.url != null) {
            result.url = model.url
        }
        if ("" == model.name && model.name != null) {
            result.name = model.name
        }

        if ("" == model.summary && model.summary != null) {
            result.summary = model.summary
        }
        if ("" == model.imgUrl && model.imgUrl != null) {
            result.imgUrl = model.imgUrl
        }
        result.updateTime = Date()
        return dao.save(result)
    }

    override fun add(model: TechnologyModel): TechnologyModel {
        model.createTime = Date()
        model.updateTime = Date()
        return dao.save(model)
    }

    override fun del(id: Long?) {
        dao.delete(id)
    }
}
