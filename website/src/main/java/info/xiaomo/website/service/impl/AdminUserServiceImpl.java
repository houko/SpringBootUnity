package info.xiaomo.website.service.impl;

import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.website.dao.AdminUserDao;
import info.xiaomo.website.model.AdminModel;
import info.xiaomo.website.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
 * author: xiaomo
 * github: https://github.com/syoubaku
 * email: xiaomo@xiaomo.info

 * Date: 16/4/2 13:34
 * Description: 后台用户serviceImpl
 * Copyright(©) 2015 by xiaomo.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserDao dao;

    @Autowired
    public AdminUserServiceImpl(AdminUserDao dao) {
        this.dao = dao;
    }

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
        if (model.getPassword() != null) {
            userUpdate.setPassword(model.getPassword());
        }
        if (model.getUserName() != null) {
            userUpdate.setUserName(model.getUserName());
        }
        userUpdate.setUpdateTime(new Date());
        return dao.save(userUpdate);
    }

    @Override
    public Page<AdminModel> getAdminUsers(int start, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return dao.findAll(new PageRequest(start - 1, pageSize, sort));
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

    @Override
    public AdminModel forbidAdminUserById(Long id) throws UserNotFoundException {
        AdminModel model = dao.findOne(id);
        if (model == null) {
            throw new UserNotFoundException();
        }
        model.setStatus(2);
        return dao.save(model);
    }

    @Override
    public List<AdminModel> getAdminUsers() {
        return dao.findAll();
    }
}
