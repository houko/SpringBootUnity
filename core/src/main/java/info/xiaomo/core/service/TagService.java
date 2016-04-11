package info.xiaomo.core.service;

import info.xiaomo.core.model.TagModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
 * @Description:
 * @Copyright(©) 2015 by xiaomo.
 **/
public interface TagService {

    TagModel findById(Long id);

    TagModel findByName(String name);

    Page<TagModel> findAll(Pageable pageable);

    TagModel add(TagModel model);

    TagModel update(TagModel model);

    TagModel delete(Long id);
}
