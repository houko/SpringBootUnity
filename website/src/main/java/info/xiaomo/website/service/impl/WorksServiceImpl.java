package info.xiaomo.website.service.impl;

import info.xiaomo.website.dao.WorksDao;
import info.xiaomo.website.model.WorksModel;
import info.xiaomo.website.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
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
 * Date: 2016/11/3 14:34
 * Copyright(©) 2015 by xiaomo.
 **/

@Service
public class WorksServiceImpl implements WorksService {

    private WorksDao dao;

    @Autowired
    public WorksServiceImpl(WorksDao dao) {
        this.dao = dao;
    }

    @Override
    public List<WorksModel> findAll() {
        return dao.findAll();
    }

    @Override
    public Page<WorksModel> findAll(int start, int pageSize) {
        return dao.findAll(PageRequest.of(start - 1, pageSize));
    }

    @Override
    public WorksModel findById(Long id) {
        Optional<WorksModel> optionalModel = dao.findById(id);
        return optionalModel.orElse(null);
    }

    @Override
    public WorksModel findByName(String name) {
        return dao.findWorkByName(name);
    }

    @Override
    public WorksModel update(WorksModel model) {
        Optional<WorksModel> optional = dao.findById(model.getId());
        if (!optional.isPresent()) {
            return null;
        }
        WorksModel result = optional.get();
        if ("".equals(model.getCompleteTime()) && model.getCompleteTime() != null) {
            result.setCompleteTime(model.getCompleteTime());
        }
        if ("".equals(model.getImgUrl()) && model.getImgUrl() != null) {
            result.setImgUrl(model.getImgUrl());
        }
        if ("".equals(model.getName()) && model.getName() != null) {
            result.setName(model.getName());
        }
        if ("".equals(model.getSummary()) && model.getSummary() != null) {
            result.setSummary(model.getSummary());
        }
        if ("".equals(model.getUrl()) && model.getUrl() != null) {
            result.setUrl(model.getUrl());
        }
        result.setUpdateTime(new Date());
        return dao.save(result);
    }

    @Override
    public WorksModel add(WorksModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public void del(Long id) {
        Optional<WorksModel> optional = dao.findById(id);
        if (!optional.isPresent()) {
            return;
        }
        dao.delete(optional.get());
    }
}
