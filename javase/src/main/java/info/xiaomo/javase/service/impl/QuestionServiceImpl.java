package info.xiaomo.javase.service.impl;

import info.xiaomo.javase.dao.QuestionDao;
import info.xiaomo.javase.model.QuestionModel;
import info.xiaomo.javase.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 *
 * @author : xiaomo
 * github  : https://github.com/xiaomoinfo
 * email   : xiaomo@xiaomo.info
 * QQ      : 83387856
 * Date    : 2017/11/20 19:01
 * desc    :
 * Copyright(©) 2017 by xiaomo.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }


    @Override
    public QuestionModel findById(Long id) {
        Optional<QuestionModel> optionalModel = questionDao.findById(id);
        return optionalModel.orElse(null);
    }

    @Override
    public boolean add(QuestionModel questionModel) {
        questionDao.save(questionModel);
        return true;
    }
}
