package info.xiaomo.core.service.impl;

import info.xiaomo.core.dao.UserDao;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import info.xiaomo.core.untils.DateUtil;
import info.xiaomo.core.untils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public UserModel findUserByEmail(String email) {
        return dao.findUserByEmail(email);
    }

    @Override
    public UserModel addUser(UserModel model) {
        String content = this.appendEmailContent(model);
        MailUtil.send(model.getEmail(), content);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public UserModel updateUser(UserModel model) throws UserNotFoundException {
        UserModel userUpdate = dao.findUserByEmail(model.getEmail());
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
        userUpdate.setUpdateTime(new Date());
        dao.save(userUpdate);
        return userUpdate;
    }

    @Override
    public Page<UserModel> findAll(int start, int pageSize) {
        return dao.findAll(new PageRequest(start - 1, pageSize));
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


    private String appendEmailContent(UserModel model) {
        StringBuilder sb = new StringBuilder("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8888/web/user/validateEmail?email=");
        sb.append(model.getEmail());
        sb.append("&validateCode=");
        sb.append(model.getValidateCode());
        sb.append("\">");
        sb.append("http://localhost:8888/web/user/validateEmail?email=");
        sb.append(model.getEmail());
        sb.append("&validateCode=");
        sb.append(model.getValidateCode());
        sb.append("</a><br/>");
        sb.append("<span style='float:right;padding-right:4%'>小莫</span></br>");
        sb.append("<span style='float:right'>");
        sb.append(DateUtil.getFormatDate());
        sb.append("</span></br>");
        return sb.toString();
    }
}
