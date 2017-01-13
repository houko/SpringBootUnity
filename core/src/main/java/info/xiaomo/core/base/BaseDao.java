package info.xiaomo.core.base;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @version : 2017/1/13 11:23
 */

public interface BaseDao<T> extends JpaRepository<T, Long> {

    T findById(String name);

    T findByName(String name);
}
