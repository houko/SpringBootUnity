package info.xiaomo.mongodb.dao;

import info.xiaomo.mongodb.model.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * author: xiaomo
 * github: https://github.com/syoubaku
 * email: xiaomo@xiaomo.info

 * Date: 2016/11/15 15:42
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 **/

@Repository
public interface MongoUserDao extends MongoRepository<MongoUser,Long>{

    MongoUser findByUserName(String userName);

}
