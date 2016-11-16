package info.xiaomo.website.service.impl;

import info.xiaomo.website.dao.WebSetDao;
import info.xiaomo.website.model.SystemSetModel;
import info.xiaomo.website.service.WebSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
 * @Date: 2016/5/6 14:24
 * @Copyright(©) 2015 by xiaomo.
 **/
@Service
public class WebSetServiceImpl implements WebSetService {

    private final WebSetDao dao;

    @Autowired
    public WebSetServiceImpl(WebSetDao dao) {
        this.dao = dao;
    }

    @Override
    public List<SystemSetModel> findAll() {
        return dao.findAll();
    }

    @Override
    public SystemSetModel findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public SystemSetModel update(SystemSetModel model) {
        SystemSetModel one = dao.findOne(model.getId());
        if (one == null) {
            return null;
        }
        if (model.getBeianNumber() != null) {
            one.setBeianNumber(model.getBeianNumber());
        }
        if (model.getBeianUrl() != null) {
            one.setBeianUrl(model.getBeianUrl());
        }
        if (model.getFromYear() != 0) {
            one.setFromYear(model.getFromYear());
        }
        if (model.getToYear() != 0) {
            one.setToYear(model.getToYear());
        }
        if (model.getIcon() != null) {
            one.setIcon(model.getIcon());
        }
        if (model.getSiteName() != null) {
            one.setSiteName(model.getSiteName());
        }
        one.setUpdateTime(new Date());
        dao.save(one);
        return one;

    }

    @Override
    public SystemSetModel add(SystemSetModel model) {
        dao.save(model);
        return model;
    }
}
