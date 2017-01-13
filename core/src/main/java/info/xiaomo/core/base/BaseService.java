package info.xiaomo.core.base;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @version : 2017/1/11 16:42
 */
public interface BaseService<T> {

    T findById(Long id);

    T findByName(String name);

    List<T> findAll();

    Page<T> findAll(int start, int pageSize);

    Boolean delById(Long id);

    Boolean delByName(String name);

    Boolean add(T model);

    Boolean update(T model);

    Boolean delByIds(List<Long> ids);
}
