package info.xiaomo.api.service;


import info.xiaomo.api.model.UserModel;
import info.xiaomo.core.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
 * @Date: 2016/4/1 17:45
 * @Copyright(©) 2015 by xiaomo.
 **/
public interface UserService {

    UserModel findUserById(Long id);

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    UserModel findUserByEmail(String email);

    UserModel addUser(UserModel model);

    UserModel updateUser(UserModel model) throws UserNotFoundException;

    Page<UserModel> findAll(int start, int pageSize);

    List<UserModel> findAll();

    UserModel deleteUserById(Long id) throws UserNotFoundException;

}
