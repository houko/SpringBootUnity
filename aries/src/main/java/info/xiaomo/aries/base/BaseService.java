package info.xiaomo.aries.base;

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

    void delById(Long id);

    void delByName(String name);

    void add(T model);

    void update(T model);
}
