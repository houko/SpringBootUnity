package info.xiaomo.website.service.impl

import info.xiaomo.core.exception.UserNotFoundException
import info.xiaomo.website.dao.AdminUserDao
import info.xiaomo.website.model.AdminModel
import info.xiaomo.website.service.AdminUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 * Date: 16/4/2 13:34
 * Description: 后台用户serviceImpl
 * Copyright(©) 2015 by xiaomo.
 */
@Service
class AdminUserServiceImpl
@Autowired
constructor(private val dao: AdminUserDao) : AdminUserService {

    override fun findAdminUserByUserName(userName: String): AdminModel {
        return dao.findAdminUserByUserName(userName)
    }

    override fun findAdminUserById(id: Long?): AdminModel {
        return dao.findOne(id)
    }

    override fun addAdminUser(model: AdminModel): AdminModel {
        model.createTime = Date()
        model.updateTime = Date()
        return dao.save(model)

    }

    @Throws(UserNotFoundException::class)
    override fun updateAdminUser(model: AdminModel): AdminModel {
        val userUpdate = dao.findOne(model.id) ?: throw UserNotFoundException()
        if (model.password != null) {
            userUpdate.password = model.password
        }
        if (model.userName != null) {
            userUpdate.userName = model.userName
        }
        userUpdate.updateTime = Date()
        return dao.save(userUpdate)
    }

    override fun getAdminUsers(start: Int, pageSize: Int): Page<AdminModel> {
        val sort = Sort(Sort.Direction.DESC, "createTime")
        return dao.findAll(PageRequest(start - 1, pageSize, sort))
    }

    @Throws(UserNotFoundException::class)
    override fun deleteAdminUserById(id: Long?): AdminModel {
        val adminModel = dao.findOne(id) ?: throw UserNotFoundException()
        dao.delete(adminModel.id)
        return adminModel
    }

    @Throws(UserNotFoundException::class)
    override fun forbidAdminUserById(id: Long?): AdminModel {
        val model = dao.findOne(id) ?: throw UserNotFoundException()
        model.status = 2
        return dao.save(model)
    }

    override fun getAdminUsers(): List<AdminModel> {
        return dao.findAll()
    }
}
