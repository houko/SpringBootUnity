package info.xiaomo.app.service;


import info.xiaomo.app.model.TechnologyModel;
import org.springframework.data.domain.Page;

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
 * @Date: 2016/11/3 14:33
 * @Copyright(©) 2015 by xiaomo.
 **/

public interface TechnologyService {

    List<TechnologyModel> findAll();

    Page<TechnologyModel> findAll(int start, int pageSize);

    TechnologyModel findById(Long id);

    TechnologyModel findByName(String name);

    TechnologyModel update(TechnologyModel model);

    TechnologyModel add(TechnologyModel model);

    void del(Long id);
    
}
