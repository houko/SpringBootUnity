package info.xiaomo.website.service;


import info.xiaomo.website.model.LinkModel;
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
public interface LinkService {

    /**
     * 根据id查友链
     *
     * @param id
     * @return
     */
    LinkModel findById(Long id);

    /**
     * 根据名字查友链
     *
     * @param name
     * @return
     */
    LinkModel findByName(String name);

    /**
     * 分页查
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<LinkModel> findAll(int start, int pageSize);

    /**
     * 查所有
     *
     * @return
     */
    List<LinkModel> findAll();

    /**
     * 添加
     *
     * @param model
     * @return
     */
    LinkModel add(LinkModel model);

    /**
     * 更新
     *
     * @param model
     * @return
     */
    LinkModel update(LinkModel model);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    LinkModel delete(Long id);

}
