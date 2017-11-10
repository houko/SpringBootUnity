package info.xiaomo.website.service;

import info.xiaomo.website.model.ChangeLogModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/1119:49
 * Copyright(©) 2015 by xiaomo.
 **/
public interface ChangeLogService {

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    ChangeLogModel findById(Long id);

    /**
     * 根据名字查
     *
     * @param name
     * @return
     */
    ChangeLogModel findByName(String name);

    /**
     * find all
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<ChangeLogModel> findAll(int start, int pageSize);

    /**
     * find all
     *
     * @return
     */
    List<ChangeLogModel> findAll();

    /**
     * add
     *
     * @param model
     * @return
     */
    ChangeLogModel add(ChangeLogModel model);

    /**
     * update
     *
     * @param model
     * @return
     */
    ChangeLogModel update(ChangeLogModel model);

    /**
     * delete
     *
     * @param id
     * @return
     */
    ChangeLogModel delete(Long id);
}
