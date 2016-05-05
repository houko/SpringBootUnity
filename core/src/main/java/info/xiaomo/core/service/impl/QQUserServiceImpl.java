package info.xiaomo.core.service.impl;

import info.xiaomo.core.dao.QQUserDao;
import info.xiaomo.core.model.QQUserModel;
import info.xiaomo.core.service.QQUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

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
 * @Date: 2016/4/1810:37
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
@Service
public class QQUserServiceImpl implements QQUserService {

    @Autowired
    private QQUserDao dao;


    @Override
    public QQUserModel findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public QQUserModel findByOpenId(String openId) {
        return dao.findQQUserByOpenId(openId);
    }

    @Override
    public Page<QQUserModel> findAll(int start, int pageSize) {
        PageRequest request = new PageRequest(start - 1, pageSize);
        return dao.findAll(request);
    }


    @Override
    public QQUserModel update(QQUserModel model) {
        QQUserModel userModel = dao.findOne(model.getId());
        if (userModel == null) {
            return null;
        }
        if (model.getNickName() != null) {
            userModel.setNickName(model.getNickName());
        }
        if (model.getImgUrl() != null) {
            userModel.setImgUrl(model.getImgUrl());
        }
        if (model.getAddress() != null) {
            userModel.setAddress(model.getAddress());
        }
        if (model.getGender() != null) {
            userModel.setGender(model.getGender());
        }
        if (model.getYear() != 0) {
            userModel.setYear(model.getYear());
        }
        userModel.setUpdateTime(new Date());
        return null;
    }

    @Override
    public QQUserModel delete(Long id) {
        QQUserModel userModel = dao.findOne(id);
        if (userModel != null) {
            dao.delete(id);
        } else {
            return null;
        }
        return userModel;
    }


    @Override
    public QQUserModel add(QQUserModel model) {
        QQUserModel userModel = dao.findQQUserByOpenId(model.getOpenId());
        if (userModel == null) {
            model.setCreateTime(new Date());
            model.setUpdateTime(new Date());
            dao.save(model);
        } else {
            return null;
        }
        return model;
    }
}
