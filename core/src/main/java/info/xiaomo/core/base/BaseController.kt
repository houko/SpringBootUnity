package info.xiaomo.core.base

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/11 16:41
 */
@RestController
abstract class BaseController<T> {

    protected val LOGGER = LoggerFactory.getLogger(javaClass)

    /**
     * 查找所有(不带分页)
     *
     * @return result
     */
    abstract fun findAll(): Result<List<T>>

    /**
     * 带分页
     *
     * @param start    起始页
     * @param pageSize 页码数
     * @return result
     */
    abstract fun findAll(@PathVariable start: Int, @PathVariable pageSize: Int): Result<Page<T>>

    /**
     * 根据id查看模型
     *
     * @param id id
     * @return result
     */
    abstract fun findById(@PathVariable id: Long?): Result<T>

    /**
     * 根据名字查找模型
     *
     * @param name name
     * @return result
     */
    abstract fun findByName(@PathVariable name: String): Result<T>

    /**
     * 根据名字删除模型
     *
     * @param name name
     * @return result
     */
    abstract fun delByName(@PathVariable name: String): Result<Boolean>


    /**
     * 根据id删除模型
     *
     * @param id id
     * @return result
     */
    abstract fun delById(@PathVariable id: Long?): Result<Boolean>

    /**
     * 添加模型
     *
     * @param model model
     * @return result
     */
    abstract fun add(@RequestBody model: T): Result<Boolean>


    /**
     * 更新
     *
     * @param model model
     * @return result
     */
    abstract fun update(@RequestBody model: T): Result<Boolean>


    /**
     * 批量删除
     *
     * @param ids ids
     * @return result
     */
    abstract fun delByIds(@PathVariable ids: List<Long>): Result<Boolean>

}