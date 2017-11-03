package info.xiaomo.website.service.impl

import info.xiaomo.website.dao.WorksDao
import info.xiaomo.website.model.WorksModel
import info.xiaomo.website.service.WorksService
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
class WorksServiceImpl @Autowired
constructor(private val dao: WorksDao) : WorksService {

    override fun findAll(): List<WorksModel> {
        return dao.findAll()
    }

    override fun findAll(start: Int, pageSize: Int): Page<WorksModel> {
        return dao.findAll(PageRequest(start - 1, pageSize))
    }

    override fun findById(id: Long?): WorksModel {
        return dao.findOne(id)
    }

    override fun findByName(name: String): WorksModel {
        return dao.findWorkByName(name)
    }

    override fun update(model: WorksModel): WorksModel? {
        val result = dao.findOne(model.id) ?: return null
        if ("" == model.completeTime && model.completeTime != null) {
            result.completeTime = model.completeTime
        }
        if ("" == model.imgUrl && model.imgUrl != null) {
            result.imgUrl = model.imgUrl
        }
        if ("" == model.name && model.name != null) {
            result.name = model.name
        }
        if ("" == model.summary && model.summary != null) {
            result.summary = model.summary
        }
        if ("" == model.url && model.url != null) {
            result.url = model.url
        }
        result.updateTime = Date()
        return dao.save(result)
    }

    override fun add(model: WorksModel): WorksModel {
        model.createTime = Date()
        model.updateTime = Date()
        return dao.save(model)
    }

    override fun del(id: Long?) {
        dao.delete(id)
    }
}
