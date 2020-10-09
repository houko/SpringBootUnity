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
        Optional<ChangeLogModel> changeLogModel = dao.findById(id);
        return changeLogModel.orElse(null);
    }

    @Override
    public ChangeLogModel findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public Page<ChangeLogModel> findAll(int start, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        return dao.findAll(PageRequest.of(start - 1, pageSize, sort));
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
        Optional<ChangeLogModel> optionalModel = dao.findById(model.getId());
        if (!optionalModel.isPresent()) {
            return null;
        }
        ChangeLogModel changeLogModel = optionalModel.get();
        if (model.getName() != null) {
            changeLogModel.setName(model.getName());
        }
        changeLogModel.setUpdateTime(new Date());
        return dao.save(changeLogModel);
    }

    @Override
    public ChangeLogModel delete(Long id) {
        Optional<ChangeLogModel> optionalLogModel = dao.findById(id);
        if (!optionalLogModel.isPresent()) {
            return null;
        }
        ChangeLogModel changeLogModel = optionalLogModel.get();
        dao.delete(changeLogModel);
        return changeLogModel;
    }
}
