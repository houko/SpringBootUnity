package info.xiaomo.core.service.impl;

import info.xiaomo.core.dao.LinkDao;
import info.xiaomo.core.model.LinkModel;
import info.xiaomo.core.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
 * @Date: 2016/4/1119:50
 * @Description: 友情链接
 * @Copyright(©) 2015 by xiaomo.
 **/
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao dao;

    @Override
    public LinkModel findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public LinkModel findByName(String name) {
        return dao.findLinkByName(name);
    }

    @Override
    public Page<LinkModel> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public LinkModel add(LinkModel model) {
        return dao.save(model);
    }

    @Override
    public LinkModel update(LinkModel model) {
        LinkModel updateModel = dao.findOne(model.getId());
        if (!model.getName().equals(updateModel.getName())) {
            updateModel.setName(model.getName());
        }
        if (!model.getUrl().equals(updateModel.getUrl())) {
            updateModel.setUrl(model.getName());
        }
        return dao.save(updateModel);
    }

    @Override
    public LinkModel delete(Long id) {
        LinkModel model = dao.findOne(id);
        if (model != null) {
            dao.delete(id);
        }
        return model;
    }
}
