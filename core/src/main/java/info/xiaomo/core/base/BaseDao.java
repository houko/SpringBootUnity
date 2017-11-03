package info.xiaomo.core.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/13 11:23
 */
@Repository
public interface BaseDao<T> extends JpaRepository<T, Long> {

    T findById(Long id);

    T findByName(String name);

    boolean deleteByName(String name);
}
