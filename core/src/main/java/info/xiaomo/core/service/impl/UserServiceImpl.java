package info.xiaomo.core.service.impl;

import info.xiaomo.core.dao.UserDao;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

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
    private UserDao dao;

    @Override
    public UserModel findUserById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public UserModel findUserByUserName(String userName) {
        return dao.findUserByUserName(userName);
    }

    @Override
    public UserModel addUser(UserModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public UserModel updateUser(UserModel model) throws UserNotFoundException {
        UserModel userUpdate = dao.findOne(model.getId());
        if (userUpdate == null) {
            throw new UserNotFoundException();
        }
        if (!Objects.equals(model.getPassword(), userUpdate.getPassword())) {
            userUpdate.setPassword(model.getPassword());
        }
        if (!Objects.equals(model.getAddress(), userUpdate.getAddress())) {
            userUpdate.setAddress(model.getAddress());
        }
        if (!Objects.equals(model.getEmail(), userUpdate.getEmail())) {
            userUpdate.setEmail(model.getEmail());
        }
        if (model.getGender() != userUpdate.getGender()) {
            userUpdate.setGender(model.getGender());
        }
        if (!Objects.equals(model.getImgUrl(), userUpdate.getImgUrl())) {
            userUpdate.setImgUrl(model.getImgUrl());
        }
        if (!Objects.equals(model.getNickName(), userUpdate.getNickName())) {
            userUpdate.setNickName(model.getNickName());
        }
        if (!Objects.equals(model.getPhone(), userUpdate.getPhone())) {
            userUpdate.setPhone(model.getPhone());
        }
        if (!Objects.equals(model.getUserName(), userUpdate.getUserName())) {
            userUpdate.setUserName(model.getUserName());
        }
        userUpdate.setUpdateTime(new Date());
        dao.save(userUpdate);
        return userUpdate;
    }

    @Override
    public Page<UserModel> getUsers(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public UserModel deleteUserById(Long id) throws UserNotFoundException {
        UserModel userModel = dao.findOne(id);
        if (userModel == null) {
            throw new UserNotFoundException();
        }
        dao.delete(userModel.getId());
        return userModel;
    }
}
