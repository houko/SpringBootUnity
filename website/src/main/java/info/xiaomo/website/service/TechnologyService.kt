package info.xiaomo.website.service


import info.xiaomo.website.model.TechnologyModel
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

interface TechnologyService {

    /**
     * find all
     *
     * @return
     */
    fun findAll(): List<TechnologyModel>

    /**
     * find all
     *
     * @param start
     * @param pageSize
     * @return
     */
    fun findAll(start: Int, pageSize: Int): Page<TechnologyModel>

    /**
     * find
     *
     * @param id
     * @return
     */
    fun findById(id: Long?): TechnologyModel

    /**
     * find
     *
     * @param name
     * @return
     */
    fun findByName(name: String): TechnologyModel

    /**
     * update
     *
     * @param model
     * @return
     */
    fun update(model: TechnologyModel): TechnologyModel

    /**
     * add
     *
     * @param model
     * @return
     */
    fun add(model: TechnologyModel): TechnologyModel

    /**
     * del
     *
     * @param id
     */
    fun del(id: Long?)

}
