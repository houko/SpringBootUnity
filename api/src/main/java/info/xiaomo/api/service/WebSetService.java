package info.xiaomo.api.service;


import info.xiaomo.api.model.SystemSetModel;

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

 * Date: 2016/5/6 14:23
 * Copyright(©) 2015 by xiaomo.
 **/
public interface WebSetService {

    List<SystemSetModel> findAll();

    SystemSetModel findById(Long id);

    SystemSetModel update(SystemSetModel model);

    SystemSetModel add(SystemSetModel model);
}
