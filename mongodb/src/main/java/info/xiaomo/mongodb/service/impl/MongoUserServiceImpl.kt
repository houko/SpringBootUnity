package info.xiaomo.mongodb.service.impl

import info.xiaomo.mongodb.dao.MongoUserDao
import info.xiaomo.mongodb.model.MongoUser
import info.xiaomo.mongodb.service.MongoUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
 * Date: 2016/11/15 15:45
 * Copyright(©) 2015 by xiaomo.
 */

@Service
class MongoUserServiceImpl @Autowired
constructor(private val dao: MongoUserDao) : MongoUserService {

    override fun findAll(): List<MongoUser> {
        return dao.findAll()
    }

    override fun findById(id: Long?): MongoUser {
        return dao.findOne(id)
    }

    override fun findByName(userName: String): MongoUser {
        return dao.findByUserName(userName)
    }

    override fun add(mongoUser: MongoUser): MongoUser {
        return dao.save(mongoUser)
    }

    override fun delete(id: Long?) {
        dao.delete(id)
    }

    override fun update(mongoUser: MongoUser): MongoUser {
        return dao.save(mongoUser)
    }
}
