package info.xiaomo.website.service

import info.xiaomo.website.model.ChangeLogModel
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
 * Date: 2016/4/1119:49
 * Copyright(©) 2015 by xiaomo.
 */
interface ChangeLogService {

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    fun findById(id: Long?): ChangeLogModel

    /**
     * 根据名字查
     * @param name
     * @return
     */
    fun findByName(name: String): ChangeLogModel

    /**
     * find all
     * @param start
     * @param pageSize
     * @return
     */
    fun findAll(start: Int, pageSize: Int): Page<ChangeLogModel>

    /**
     * find all
     * @return
     */
    fun findAll(): List<ChangeLogModel>

    /**
     * add
     * @param model
     * @return
     */
    fun add(model: ChangeLogModel): ChangeLogModel

    /**
     * update
     * @param model
     * @return
     */
    fun update(model: ChangeLogModel): ChangeLogModel

    /**
     * delete
     * @param id
     * @return
     */
    fun delete(id: Long?): ChangeLogModel
}
