package info.xiaomo.website.service;


import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.website.model.UserModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/1 17:45
 * Copyright(©) 2015 by xiaomo.
 **/
public interface UserService {
    /**
     * 根据id查用户
     *
     * @param id id
     * @return UserModel
     */
    Optional<UserModel> findUserById(Long id);

    /**
     * 根据邮件查用户
     *
     * @param email email
     * @return UserModel
     */
    UserModel findUserByEmail(String email);

    /**
     * 添加用户
     *
     * @param model model
     * @return UserModel
     */
    UserModel addUser(UserModel model);

    /**
     * 更新用户
     *
     * @param model model
     * @return UserModel
     * @throws UserNotFoundException UserNotFoundException
     */
    UserModel updateUser(UserModel model) throws UserNotFoundException;

    /**
     * 查找所有 带分页
     *
     * @param start    start
     * @param pageSize pageSize
     * @return Page
     */
    Page<UserModel> findAll(int start, int pageSize);

    /**
     * 查找所有 不带分页
     *
     * @return List
     */
    List<UserModel> findAll();

    /**
     * 删除用户
     *
     * @param id id
     * @return UserModel
     * @throws UserNotFoundException
     */
    UserModel deleteUserById(Long id) throws UserNotFoundException;

}
