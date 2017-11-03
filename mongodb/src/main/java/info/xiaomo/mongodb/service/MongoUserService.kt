package info.xiaomo.mongodb.service

import info.xiaomo.mongodb.model.MongoUser

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
 * Date: 2016/11/15 15:45
 * Copyright(©) 2015 by xiaomo.
 */


interface MongoUserService {

    /**
     * 查所有
     *
     * @return
     */
    fun findAll(): List<MongoUser>

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    fun findById(id: Long?): MongoUser

    /**
     * 根据名字查
     *
     * @param userName
     * @return
     */
    fun findByName(userName: String): MongoUser

    /**
     * 添加
     *
     * @param mongoUser
     * @return
     */
    fun add(mongoUser: MongoUser): MongoUser

    /**
     * 删除
     *
     * @param id
     */
    fun delete(id: Long?)

    /**
     * 更新
     *
     * @param mongoUser
     * @return
     */
    fun update(mongoUser: MongoUser): MongoUser

}
