package info.xiaomo.website.service.impl

import info.xiaomo.website.dao.ChangeLogDao
import info.xiaomo.website.model.ChangeLogModel
import info.xiaomo.website.service.ChangeLogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
 * Date: 2016/4/11 19:49
 * Copyright(©) 2015 by xiaomo.
 */
@Service
class ChangeLogServiceImpl @Autowired
constructor(private val dao: ChangeLogDao) : ChangeLogService {

    override fun findById(id: Long?): ChangeLogModel {
        return dao.findOne(id)
    }

    override fun findByName(name: String): ChangeLogModel {
        return dao.findByName(name)
    }

    override fun findAll(start: Int, pageSize: Int): Page<ChangeLogModel> {
        val sort = Sort(Sort.Direction.DESC, "createTime")
        return dao.findAll(PageRequest(start - 1, pageSize, sort))
    }

    override fun findAll(): List<ChangeLogModel> {
        return dao.findAll()
    }

    override fun add(model: ChangeLogModel): ChangeLogModel {
        model.createTime = Date()
        model.updateTime = Date()
        return dao.save(model)
    }

    override fun update(model: ChangeLogModel): ChangeLogModel {
        val updateModel = dao.findOne(model.id)
        if (model.name != null) {
            updateModel.name = model.name
        }
        updateModel.updateTime = Date()
        return dao.save(updateModel)
    }

    override fun delete(id: Long?): ChangeLogModel {
        val model = dao.findOne(id)
        if (model != null) {
            dao.delete(id)
        }
        return model
    }
}
