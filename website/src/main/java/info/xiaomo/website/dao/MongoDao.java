package info.xiaomo.website.dao;

import info.xiaomo.website.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/4/1 20:46
 * @Description: 公共dao层
 * @Copyright(©) 2015 by xiaomo.
 **/
@Repository
public interface MongoDao extends MongoRepository<UserModel, Long> {


}