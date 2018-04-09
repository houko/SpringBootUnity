package info.xiaomo.javase.dao;

import info.xiaomo.javase.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 *
 * @author : xiaomo
 * github  : https://github.com/xiaomoinfo
 * email   : xiaomo@xiaomo.info
 * QQ      : 83387856
 * Date    : 2017/11/20 19:00
 * desc    :
 * Copyright(©) 2017 by xiaomo.
 */
@Repository
public interface QuestionDao extends JpaRepository<QuestionModel, Long> {
}
