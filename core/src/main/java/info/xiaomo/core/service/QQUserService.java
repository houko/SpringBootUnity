package info.xiaomo.core.service;

import info.xiaomo.core.model.QQUserModel;
import org.springframework.data.domain.Page;

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
 * @Date: 2016/4/1810:36
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
public interface QQUserService {

    QQUserModel findById(Long id);

    QQUserModel findByOpenId(String openId);

    Page<QQUserModel> findAll(int start, int pageSize);

    QQUserModel update(QQUserModel model);

    QQUserModel delete(Long id);

    QQUserModel add(QQUserModel model);

}
