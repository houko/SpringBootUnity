package info.xiaomo.website.service


import info.xiaomo.website.model.WorksModel
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
 * Date: 2016/11/3 14:33
 * Copyright(©) 2015 by xiaomo.
 */

interface WorksService {

    /**
     * find all
     *
     * @return
     */
    fun findAll(): List<WorksModel>

    /**
     * find all page
     *
     * @param start
     * @param pageSize
     * @return
     */
    fun findAll(start: Int, pageSize: Int): Page<WorksModel>

    /**
     * find
     *
     * @param id
     * @return
     */
    fun findById(id: Long?): WorksModel

    /**
     * find
     *
     * @param name
     * @return
     */
    fun findByName(name: String): WorksModel

    /**
     * update
     *
     * @param model
     * @return
     */
    fun update(model: WorksModel): WorksModel?


    /**
     * add
     *
     * @param model
     * @return
     */
    fun add(model: WorksModel): WorksModel


    /**
     * del
     *
     * @param id
     */
    fun del(id: Long?)
}
