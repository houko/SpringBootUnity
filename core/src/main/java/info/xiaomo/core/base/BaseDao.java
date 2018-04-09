package info.xiaomo.core.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/13 11:23
 */
@Repository
public interface BaseDao<T> extends JpaRepository<T, Long> {

    /**
     * 根据名字查
     *
     * @param name
     * @return
     */
    T findByName(String name);

    /**
     * 删除
     *
     * @param name
     * @return
     */
    boolean deleteByName(String name);
}
