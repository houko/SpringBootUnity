package info.xiaomo.website.service.impl

import info.xiaomo.website.dao.LinkDao
import info.xiaomo.website.model.LinkModel
import info.xiaomo.website.service.LinkService
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
 * Date: 2016/4/11 19:50
 * Copyright(©) 2015 by xiaomo.
 */
@Service
class LinkServiceImpl @Autowired
constructor(private val dao: LinkDao) : LinkService {

    override fun findById(id: Long?): LinkModel {
        return dao.findOne(id)
    }

    override fun findByName(name: String): LinkModel {
        return dao.findLinkByName(name)
    }

    override fun findAll(start: Int, pageSize: Int): Page<LinkModel> {
        val sort = Sort(Sort.Direction.DESC, "order")
        return dao.findAll(PageRequest(start - 1, pageSize, sort))
    }

    override fun findAll(): List<LinkModel> {
        return dao.findAll()
    }

    override fun add(model: LinkModel): LinkModel {
        model.createTime = Date()
        model.updateTime = Date()
        return dao.save(model)
    }

    override fun update(model: LinkModel): LinkModel {
        val updateModel = dao.findOne(model.id)
        if (model.name != null) {
            updateModel.name = model.name
        }
        if (model.url != null) {
            updateModel.url = model.url
        }
        model.updateTime = Date()
        return dao.save(updateModel)
    }

    override fun delete(id: Long?): LinkModel {
        val model = dao.findOne(id)
        if (model != null) {
            dao.delete(id)
        }
        return model
    }
}
