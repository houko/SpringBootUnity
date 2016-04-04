package info.xiaomo.core.service.impl;

import info.xiaomo.core.dao.AdminUserDao;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.AdminModel;
import info.xiaomo.core.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 16/4/2 13:34
 * @Description: 后台用户serviceImpl
 * @Copyright(©) 2015 by xiaomo.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao dao;

    @Override
    public AdminModel findAdminUserByUserName(String userName) {
        return dao.findAdminUserByUserName(userName);
    }

    @Override
    public AdminModel findAdminUserById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public AdminModel addAdminUser(AdminModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public AdminModel updateAdminUser(AdminModel model) throws UserNotFoundException {
        AdminModel userUpdate = dao.findOne(model.getId());
        if (userUpdate == null) {
            throw new UserNotFoundException();
        }
        if (!Objects.equals(model.getPassword(), userUpdate.getPassword())) {
            userUpdate.setPassword(model.getPassword());
        }
        if (userUpdate.getAuthLevel() != model.getAuthLevel()) {
            userUpdate.setAuthLevel(model.getAuthLevel());
        }
        if (!Objects.equals(model.getUserName(), userUpdate.getUserName())) {
            userUpdate.setUserName(model.getUserName());
        }
        userUpdate.setUpdateTime(new Date());
        return userUpdate;
    }

    @Override
    public Page<AdminModel> getAdminUsers(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public AdminModel deleteAdminUserById(Long id) throws UserNotFoundException {
        AdminModel adminModel = dao.findOne(id);
        if (adminModel == null) {
            throw new UserNotFoundException();
        }
        dao.delete(adminModel.getId());
        return adminModel;
    }
}
