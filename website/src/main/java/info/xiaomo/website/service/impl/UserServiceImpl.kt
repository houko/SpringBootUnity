package info.xiaomo.website.service.impl

import info.xiaomo.core.exception.UserNotFoundException
import info.xiaomo.website.dao.UserDao
import info.xiaomo.website.model.UserModel
import info.xiaomo.website.service.UserService
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
 * Date: 2016/4/1 17:46
 * Description: 用户service实现
 * Copyright(©) 2015 by xiaomo.
 */
@Service
class UserServiceImpl @Autowired
constructor(private val dao: UserDao) : UserService {

    override fun findUserById(id: Long?): UserModel {
        return dao.findOne(id)
    }

    override fun findUserByEmail(email: String): UserModel {
        return dao.findUserByEmail(email)
    }

    override fun addUser(model: UserModel): UserModel {
        model.createTime = Date()
        model.updateTime = Date()
        return dao.save(model)
    }

    @Throws(UserNotFoundException::class)
    override fun updateUser(model: UserModel): UserModel {
        val userUpdate = dao.findUserByEmail(model.email!!)
        if (userUpdate.address != null) {
            userUpdate.address = model.address
        }
        if (userUpdate.email != null) {
            userUpdate.email = model.email
        }
        if (userUpdate.gender != 0) {
            userUpdate.gender = model.gender
        }
        if (userUpdate.imgUrl != null) {
            userUpdate.imgUrl = model.imgUrl
        }
        if (userUpdate.nickName != null) {
            userUpdate.nickName = model.nickName
        }
        if (userUpdate.phone != 0L) {
            userUpdate.phone = model.phone
        }
        userUpdate.updateTime = Date()
        dao.save(userUpdate)
        return userUpdate
    }

    override fun findAll(start: Int, pageSize: Int): Page<UserModel> {
        val sort = Sort(Sort.Direction.DESC, "createTime")
        return dao.findAll(PageRequest(start - 1, pageSize, sort))
    }

    override fun findAll(): List<UserModel> {
        return dao.findAll()
    }

    @Throws(UserNotFoundException::class)
    override fun deleteUserById(id: Long?): UserModel {
        val userModel = dao.findOne(id) ?: throw UserNotFoundException()
        dao.delete(userModel.id)
        return userModel
    }

}
