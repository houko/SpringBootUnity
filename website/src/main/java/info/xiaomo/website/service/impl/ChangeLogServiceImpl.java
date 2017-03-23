package info.xiaomo.website.service.impl;

import info.xiaomo.website.dao.ChangeLogDao;
import info.xiaomo.website.model.ChangeLogModel;
import info.xiaomo.website.service.ChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

 * Date: 2016/4/11 19:49
 * Copyright(©) 2015 by xiaomo.
 **/
@Service
public class ChangeLogServiceImpl implements ChangeLogService {

    private final ChangeLogDao dao;

    @Autowired
    public ChangeLogServiceImpl(ChangeLogDao dao) {
        this.dao = dao;
    }

    @Override
    public ChangeLogModel findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public ChangeLogModel findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public Page<ChangeLogModel> findAll(int start, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return dao.findAll(new PageRequest(start - 1, pageSize, sort));
    }

    @Override
    public List<ChangeLogModel> findAll() {
        return dao.findAll();
    }

    @Override
    public ChangeLogModel add(ChangeLogModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public ChangeLogModel update(ChangeLogModel model) {
        ChangeLogModel updateModel = dao.findOne(model.getId());
        if (model.getName() != null) {
            updateModel.setName(model.getName());
        }
        updateModel.setUpdateTime(new Date());
        return dao.save(updateModel);
    }

    @Override
    public ChangeLogModel delete(Long id) {
        ChangeLogModel model = dao.findOne(id);
        if (model != null) {
            dao.delete(id);
        }
        return model;
    }
}
