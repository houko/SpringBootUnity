package info.xiaomo.website.dao.base

import info.xiaomo.core.base.BaseModel
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

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
 * Date: 2016/4/1 20:46
 * Description: 公共dao层
 * Copyright(©) 2015 by xiaomo.
 */
@Repository
class CommonDao {

    @PersistenceContext
    private val entityManager: EntityManager? = null

    operator fun <T : BaseModel> get(type: Class<T>, id: Long): T {
        return entityManager!!.find(type, id)
    }

    fun <T : BaseModel> update(entity: T): T {
        return entityManager!!.merge(entity)
    }

    fun <T : BaseModel> save(entity: T) {
        entityManager!!.persist(entity)
    }

    fun <T : BaseModel> delete(entity: T) {
        entityManager!!.remove(entity)
    }

    fun getAll(tableClass: Class<out BaseModel>): List<*> {
        val query = entityManager!!.createQuery("from " + tableClass.simpleName)
        return query.resultList
    }


}