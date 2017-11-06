package info.xiaomo.website.service


import info.xiaomo.core.exception.UserNotFoundException
import info.xiaomo.website.model.UserModel
import org.springframework.data.domain.Page

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
 *
 * Date: 2016/4/1 17:45
 * Copyright(©) 2015 by xiaomo.
 */
interface UserService {
    /**
     * 根据id查用户
     *
     * @param id id
     * @return UserModel
     */
    fun findUserById(id: Long?): UserModel

    /**
     * 根据邮件查用户
     *
     * @param email email
     * @return UserModel
     */
    fun findUserByEmail(email: String): UserModel

    /**
     * 添加用户
     *
     * @param model model
     * @return UserModel
     */
    fun addUser(model: UserModel): UserModel

    /**
     * 更新用户
     *
     * @param model model
     * @return UserModel
     * @throws UserNotFoundException UserNotFoundException
     */
    @Throws(UserNotFoundException::class)
    fun updateUser(model: UserModel): UserModel?

    /**
     * 查找所有 带分页
     *
     * @param start    start
     * @param pageSize pageSize
     * @return Page
     */
    fun findAll(start: Int, pageSize: Int): Page<UserModel>

    /**
     * 查找所有 不带分页
     *
     * @return List
     */
    fun findAll(): List<UserModel>

    /**
     * 删除用户
     *
     * @param id id
     * @return UserModel
     * @throws UserNotFoundException
     */
    @Throws(UserNotFoundException::class)
    fun deleteUserById(id: Long?): UserModel

}
