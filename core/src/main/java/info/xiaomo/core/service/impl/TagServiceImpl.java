package info.xiaomo.core.service.impl;

import info.xiaomo.core.dao.TagDao;
import info.xiaomo.core.model.TagModel;
import info.xiaomo.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * @Date: 2016/4/1119:49
 * @Description: 标签
 * @Copyright(©) 2015 by xiaomo.
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao dao;

    @Override
    public TagModel findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public TagModel findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public Page<TagModel> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public TagModel add(TagModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public TagModel update(TagModel model) {
        TagModel updateModel = dao.findOne(model.getId());
        if (!model.getName().equals(updateModel.getName())) {
            updateModel.setName(model.getName());
        }
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(updateModel);
    }

    @Override
    public TagModel delete(Long id) {
        TagModel model = dao.findOne(id);
        if (model != null) {
            dao.delete(id);
        }
        return model;
    }
}
