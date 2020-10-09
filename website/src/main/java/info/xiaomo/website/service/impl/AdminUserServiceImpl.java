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
import java.util.Optional;

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

 *
 * @author : xiaomo
 * github: https://github.com/houko
 * email: xiaomo@xiaomo.info
 * <p>
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
        Optional<AdminModel> optionalModel = dao.findById(id);
        return optionalModel.orElse(null);
    }

    @Override
    public AdminModel addAdminUser(AdminModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);

    }

    @Override
    public AdminModel updateAdminUser(AdminModel model) throws UserNotFoundException {
        Optional<AdminModel> optionalModel = dao.findById(model.getId());
        if (!optionalModel.isPresent()) {
            throw new UserNotFoundException();
        }
        AdminModel adminModel = optionalModel.get();
        if (model.getPassword() != null) {
            adminModel.setPassword(model.getPassword());
        }
        if (model.getUserName() != null) {
            adminModel.setUserName(model.getUserName());
        }
        adminModel.setUpdateTime(new Date());
        return dao.save(adminModel);
    }

    @Override
    public Page<AdminModel> getAdminUsers(int start, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        return dao.findAll(PageRequest.of(start - 1, pageSize, sort));
    }

    @Override
    public AdminModel deleteAdminUserById(Long id) throws UserNotFoundException {
        Optional<AdminModel> optionalModel = dao.findById(id);
        if (!optionalModel.isPresent()) {
            throw new UserNotFoundException();
        }
        AdminModel adminModel = optionalModel.get();
        dao.delete(adminModel);
        return adminModel;
    }

    @Override
    public AdminModel forbidAdminUserById(Long id) throws UserNotFoundException {
        Optional<AdminModel> optionalModel = dao.findById(id);
        if (!optionalModel.isPresent()) {
            throw new UserNotFoundException();
        }
        AdminModel adminModel = optionalModel.get();
        adminModel.setStatus(2);
        return dao.save(adminModel);
    }

    @Override
    public List<AdminModel> getAdminUsers() {
        return dao.findAll();
    }
}
