package info.xiaomo.core.service;


import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.UserModel;
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
 * @Date: 2016/4/1 17:45
 * @Description: 用户接口
 * @Copyright(©) 2015 by xiaomo.
 **/
public interface UserService {

    UserModel findUserById(Long id);

    UserModel findUserByEmail(String email);

    UserModel addUser(UserModel model);

    UserModel updateUser(UserModel model) throws UserNotFoundException;

    Page<UserModel> findAll(int start ,int pageSize);

    UserModel deleteUserById(Long id) throws UserNotFoundException;

}
