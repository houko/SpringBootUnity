package info.xiaomo.website.service.impl;

import info.xiaomo.website.dao.LinkDao;
import info.xiaomo.website.model.LinkModel;
import info.xiaomo.website.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 *
 * @author : xiaomo
 * github: https://github.com/houko
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/11 19:50
 * Copyright(©) 2015 by xiaomo.
 **/
@Service
public class LinkServiceImpl implements LinkService {

    private final LinkDao dao;

    @Autowired
    public LinkServiceImpl(LinkDao dao) {
        this.dao = dao;
    }

    @Override
    public LinkModel findById(Long id) {
        Optional<LinkModel> optionalModel = dao.findById(id);
        return optionalModel.orElse(null);
    }

    @Override
    public LinkModel findByName(String name) {
        return dao.findLinkByName(name);
    }

    @Override
    public Page<LinkModel> findAll(int start, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "order");
        return dao.findAll(PageRequest.of(start - 1, pageSize, sort));
    }

    @Override
    public List<LinkModel> findAll() {
        return dao.findAll();
    }

    @Override
    public LinkModel add(LinkModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public LinkModel update(LinkModel model) {
        Optional<LinkModel> optional = dao.findById(model.getId());
        if (!optional.isPresent()) {
            return null;
        }
        LinkModel linkModel = optional.get();
        if (model.getName() != null) {
            linkModel.setName(model.getName());
        }
        if (model.getUrl() != null) {
            linkModel.setUrl(model.getUrl());
        }
        model.setUpdateTime(new Date());
        return dao.save(linkModel);
    }

    @Override
    public LinkModel delete(Long id) {
        Optional<LinkModel> optional = dao.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        LinkModel linkModel = optional.get();
        dao.delete(linkModel);
        return linkModel;
    }
}
