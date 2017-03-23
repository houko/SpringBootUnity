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
 * author: xiaomo
 * github: https://github.com/syoubaku
 * email: xiaomo@xiaomo.info

 * Date: 2016/4/1119:49
 * Copyright(©) 2015 by xiaomo.
 **/
public interface LinkService {

    LinkModel findById(Long id);

    LinkModel findByName(String name);

    Page<LinkModel> findAll(int start , int pageSize);

    List<LinkModel> findAll();

    LinkModel add(LinkModel model);

    LinkModel update(LinkModel model);

    LinkModel delete(Long id);

}
