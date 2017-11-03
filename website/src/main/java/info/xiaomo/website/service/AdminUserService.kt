package info.xiaomo.website.service

import info.xiaomo.core.exception.UserNotFoundException
import info.xiaomo.website.model.AdminModel
import org.springframework.data.domain.Page

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
 * Date: 16/4/2 13:04
 * Copyright(©) 2015 by xiaomo.
 */
interface AdminUserService {

    /**
     * 查所有
     *
     * @return
     */
    fun getAdminUsers(): List<AdminModel>

    /**
     * 根据用户名查用户
     *
     * @param userName
     * @return
     */
    fun findAdminUserByUserName(userName: String): AdminModel

    /**
     * 根据id查用户
     *
     * @param id
     * @return
     */
    fun findAdminUserById(id: Long?): AdminModel

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    fun addAdminUser(model: AdminModel): AdminModel

    /**
     * 更新用户
     *
     * @param model
     * @return
     * @throws UserNotFoundException
     */
    @Throws(UserNotFoundException::class)
    fun updateAdminUser(model: AdminModel): AdminModel

    /**
     * 获取分页
     *
     * @param start
     * @param pageSize
     * @return
     */
    fun getAdminUsers(start: Int, pageSize: Int): Page<AdminModel>

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @Throws(UserNotFoundException::class)
    fun deleteAdminUserById(id: Long?): AdminModel

    /**
     * 禁
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @Throws(UserNotFoundException::class)
    fun forbidAdminUserById(id: Long?): AdminModel

}
