package info.xiaomo.api.service.impl;

import info.xiaomo.api.dao.TechnologyDao;
import info.xiaomo.api.model.TechnologyModel;
import info.xiaomo.api.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * author: xiaomo
 * github: https://github.com/syoubaku
 * email: xiaomo@xiaomo.info

 * Date: 2016/11/3 14:34
 * Copyright(©) 2015 by xiaomo.
 **/

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private TechnologyDao dao;

    @Autowired
    public TechnologyServiceImpl(TechnologyDao dao) {
        this.dao = dao;
    }

    @Override
    public List<TechnologyModel> findAll() {
        return dao.findAll();
    }

    @Override
    public Page<TechnologyModel> findAll(int start, int pageSize) {
        return dao.findAll(new PageRequest(start - 1, pageSize));
    }

    @Override
    public TechnologyModel findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public TechnologyModel findByName(String name) {
        return dao.findTechnologyByName(name);
    }

    @Override
    public TechnologyModel update(TechnologyModel model) {
        TechnologyModel result = dao.findOne(model.getId());
        if ("".equals(model.getUrl()) && model.getUrl() != null) {
            result.setUrl(model.getUrl());
        }
        if ("".equals(model.getName()) && model.getName() != null) {
            result.setName(model.getName());
        }

        if ("".equals(model.getSummary()) && model.getSummary() != null) {
            result.setSummary(model.getSummary());
        }
        if ("".equals(model.getImgUrl()) && model.getImgUrl() != null) {
            result.setImgUrl(model.getImgUrl());
        }
        result.setUpdateTime(new Date());
        return dao.save(result);
    }

    @Override
    public TechnologyModel add(TechnologyModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public void del(Long id) {
        dao.delete(id);
    }
}
