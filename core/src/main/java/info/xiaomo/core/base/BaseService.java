package info.xiaomo.core.base;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/11 16:42
 */
@Service
public interface BaseService<T> {

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 根据名字查
     *
     * @param name
     * @return
     */
    T findByName(String name);

    /**
     * 查找所有
     *
     * @return
     */
    List<T> findAll();

    /**
     * 分页查询
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<T> findAll(int start, int pageSize);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    /**
     * 根据名字删除
     *
     * @param name
     * @return
     */
    boolean deleteByName(String name);

    /**
     * 增加
     *
     * @param model
     * @return
     */
    boolean add(T model);

    /**
     * 更新
     *
     * @param model
     * @return
     */
    boolean update(T model);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(List<Long> ids);
}
