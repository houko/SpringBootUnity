package info.xiaomo.website.service


import info.xiaomo.website.model.LinkModel
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
 * Date: 2016/4/1119:49
 * Copyright(©) 2015 by xiaomo.
 */
interface LinkService {

    /**
     * 根据id查友链
     *
     * @param id
     * @return
     */
    fun findById(id: Long?): LinkModel

    /**
     * 根据名字查友链
     *
     * @param name
     * @return
     */
    fun findByName(name: String): LinkModel

    /**
     * 分页查
     *
     * @param start
     * @param pageSize
     * @return
     */
    fun findAll(start: Int, pageSize: Int): Page<LinkModel>

    /**
     * 查所有
     *
     * @return
     */
    fun findAll(): List<LinkModel>

    /**
     * 添加
     *
     * @param model
     * @return
     */
    fun add(model: LinkModel): LinkModel

    /**
     * 更新
     *
     * @param model
     * @return
     */
    fun update(model: LinkModel): LinkModel

    /**
     * 删除
     *
     * @param id
     * @return
     */
    fun delete(id: Long?): LinkModel

}
