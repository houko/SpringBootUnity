package info.xiaomo.aries.service.impl;

import info.xiaomo.aries.dao.UserDao;
import info.xiaomo.aries.model.UserModel;
import info.xiaomo.aries.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @version : 2017/1/11 16:39
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserModel findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public UserModel findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<UserModel> findAll() {
        return null;
    }

    @Override
    public Page<UserModel> findAll(int start, int pageSize) {
        return null;
    }

    @Override
    public void delById(Long id) {
        userDao.delete(id);
    }

    @Override
    public void delByName(String name) {
        userDao.deleteByName(name);
    }

    @Override
    public void add(UserModel model) {

    }

    @Override
    public void update(UserModel model) {
    }
}
