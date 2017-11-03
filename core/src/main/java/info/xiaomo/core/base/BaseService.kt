package info.xiaomo.core.base

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/11 16:42
 */
@Service
interface BaseService<T> {

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    fun findById(id: Long?): T

    /**
     * 根据名字查
     *
     * @param name
     * @return
     */
    fun findByName(name: String): T

    /**
     * 查找所有
     *
     * @return
     */
    fun findAll(): List<T>

    /**
     * 分页查询
     *
     * @param start
     * @param pageSize
     * @return
     */
    fun findAll(start: Int, pageSize: Int): Page<T>

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    fun deleteById(id: Long?): Boolean

    /**
     * 根据名字删除
     *
     * @param name
     * @return
     */
    fun deleteByName(name: String): Boolean

    /**
     * 增加
     *
     * @param model
     * @return
     */
    fun add(model: T): Boolean

    /**
     * 更新
     *
     * @param model
     * @return
     */
    fun update(model: T): Boolean

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    fun deleteByIds(ids: List<Long>): Boolean
}
