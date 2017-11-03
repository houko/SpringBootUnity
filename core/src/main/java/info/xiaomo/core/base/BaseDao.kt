package info.xiaomo.core.base

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/13 11:23
 */
@Repository
interface BaseDao<T> : JpaRepository<T, Long> {

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    fun findById(id: Long?): T

    /**
     * 根据名字查
     * @param name
     * @return
     */
    fun findByName(name: String): T

    /**
     * 删除
     * @param name
     * @return
     */
    fun deleteByName(name: String): Boolean
}
