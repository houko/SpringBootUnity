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
 * author: xiaomo
 * github: https://github.com/syoubaku
 * email: xiaomo@xiaomo.info

 * Date: 2016/4/1119:49
 * Copyright(©) 2015 by xiaomo.
 **/
public interface ChangeLogService {

    ChangeLogModel findById(Long id);

    ChangeLogModel findByName(String name);

    Page<ChangeLogModel> findAll(int start , int pageSize);

    List<ChangeLogModel> findAll();

    ChangeLogModel add(ChangeLogModel model);

    ChangeLogModel update(ChangeLogModel model);

    ChangeLogModel delete(Long id);
}
