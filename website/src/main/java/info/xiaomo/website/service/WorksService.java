package info.xiaomo.website.service;


import info.xiaomo.website.model.WorksModel;
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
 * Date: 2016/11/3 14:33
 * Copyright(©) 2015 by xiaomo.
 **/

public interface WorksService {

    /**
     * find all
     *
     * @return
     */
    List<WorksModel> findAll();

    /**
     * find all page
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<WorksModel> findAll(int start, int pageSize);

    /**
     * find
     *
     * @param id
     * @return
     */
    WorksModel findById(Long id);

    /**
     * find
     *
     * @param name
     * @return
     */
    WorksModel findByName(String name);

    /**
     * update
     *
     * @param model
     * @return
     */
    WorksModel update(WorksModel model);


    /**
     * add
     *
     * @param model
     * @return
     */
    WorksModel add(WorksModel model);


    /**
     * del
     *
     * @param id
     */
    void del(Long id);
}
