package info.xiaomo.website.dao.base;

import info.xiaomo.core.base.BaseModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!

 *
 * @author : xiaomo
 * github: https://github.com/houko
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/1 20:46
 * Description: 公共dao层
 * Copyright(©) 2015 by xiaomo.
 **/
@Repository
@Transactional(rollbackFor = {})
public class CommonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public <T extends BaseModel> T get(Class<T> type, long id) {
        return entityManager.find(type, id);
    }

    public <T extends BaseModel> T update(T entity) {
        return entityManager.merge(entity);
    }

    public <T extends BaseModel> void save(T entity) {
        entityManager.persist(entity);
    }

    public <T extends BaseModel> void delete(T entity) {
        // 传入的实体可能是游离态, 不在持久化上下文中时先 merge 再 remove, 避免 IllegalArgumentException
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public List getAll(Class<? extends BaseModel> tableClass) {
        Query query = entityManager.createQuery("from " + tableClass.getSimpleName());
        return query.getResultList();
    }


}