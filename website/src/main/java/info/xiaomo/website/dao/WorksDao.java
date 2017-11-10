package info.xiaomo.website.dao;

import info.xiaomo.website.model.WorksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 16/4/2 13:08
 * Copyright(©) 2015 by xiaomo.
 */
@Repository
public interface WorksDao extends JpaRepository<WorksModel, Long> {

    /**
     * 根据名字查
     *
     * @param name
     * @return
     */
    WorksModel findWorkByName(String name);

}
