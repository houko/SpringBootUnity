package info.xiaomo.app.service.impl;

import info.xiaomo.app.dao.WorksDao;
import info.xiaomo.app.model.WorksModel;
import info.xiaomo.app.service.WorksService;
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
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/11/3 14:34
 * @Copyright(©) 2015 by xiaomo.
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
        return dao.findAll(new PageRequest(start - 1, pageSize));
    }

    @Override
    public WorksModel findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public WorksModel findByName(String name) {
        return dao.findWorkByName(name);
    }

    @Override
    public WorksModel update(WorksModel model) {
        WorksModel result = dao.findOne(model.getId());
        if (result == null) {
            return null;
        }
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
        dao.delete(id);
    }
}
