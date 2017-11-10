package info.xiaomo.website.service;


import info.xiaomo.website.model.TechnologyModel;
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

public interface TechnologyService {

    /**
     * find all
     *
     * @return
     */
    List<TechnologyModel> findAll();

    /**
     * find all
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<TechnologyModel> findAll(int start, int pageSize);

    /**
     * find
     *
     * @param id
     * @return
     */
    TechnologyModel findById(Long id);

    /**
     * find
     *
     * @param name
     * @return
     */
    TechnologyModel findByName(String name);

    /**
     * update
     *
     * @param model
     * @return
     */
    TechnologyModel update(TechnologyModel model);

    /**
     * add
     *
     * @param model
     * @return
     */
    TechnologyModel add(TechnologyModel model);

    /**
     * del
     *
     * @param id
     */
    void del(Long id);

}
