package info.xiaomo.website.dao;

import info.xiaomo.website.model.LinkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
 * Date: 2016/4/1119:52
 * Copyright(©) 2015 by xiaomo.
 **/
@Repository
public interface LinkDao extends JpaRepository<LinkModel, Long> {

    /**
     * 根据名字查友链
     *
     * @param name
     * @return
     */
    LinkModel findLinkByName(String name);

}
