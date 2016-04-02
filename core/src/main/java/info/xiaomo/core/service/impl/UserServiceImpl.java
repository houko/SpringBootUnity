package info.xiaomo.core.service.impl;

import info.xiaomo.core.dao.CommonDao;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Date: 2016/4/1 17:46
 * @Description: 用户service实现
 * @Copyright(©) 2015 by xiaomo.
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CommonDao dao;

    @Override
    public UserModel findUserById(Long id) {
        return dao.get(UserModel.class, id);
    }
}
