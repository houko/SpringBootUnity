package info.xiaomo.api.dao;

import info.xiaomo.api.model.SystemSetModel;
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

 * Date: 2016/5/6 14:26
 * Copyright(©) 2015 by xiaomo.
 **/
@Repository
public interface WebSetDao extends JpaRepository<SystemSetModel, Long> {

}
