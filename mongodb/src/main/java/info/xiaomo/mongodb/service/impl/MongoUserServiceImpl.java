package info.xiaomo.mongodb.service.impl;

import info.xiaomo.mongodb.dao.MongoUserDao;
import info.xiaomo.mongodb.model.MongoUser;
import info.xiaomo.mongodb.service.MongoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * Date: 2016/11/15 15:45
 * Copyright(©) 2015 by xiaomo.
 **/

@Service
public class MongoUserServiceImpl implements MongoUserService {
    private final MongoUserDao dao;

    @Autowired
    public MongoUserServiceImpl(MongoUserDao dao) {
        this.dao = dao;
    }

    @Override
    public List<MongoUser> findAll() {
        return dao.findAll();
    }

    @Override
    public MongoUser findById(Long id) {
        Optional<MongoUser> optionalUser = dao.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public MongoUser findByName(String userName) {
        return dao.findByUserName(userName);
    }

    @Override
    public MongoUser add(MongoUser mongoUser) {
        return dao.save(mongoUser);
    }

    @Override
    public void delete(Long id) {
        Optional<MongoUser> optional = dao.findById(id);
        if (!optional.isPresent()) {
            return;
        }
        dao.delete(optional.get());
    }

    @Override
    public MongoUser update(MongoUser mongoUser) {
        return dao.save(mongoUser);
    }
}
